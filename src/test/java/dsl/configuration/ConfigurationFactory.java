package dsl.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

@Log
public class ConfigurationFactory {

    @Getter
    private final AutomationURLs automationURLs;

    @Getter
    transient Properties props = new Properties();
    transient String environmentName;

    public ConfigurationFactory() {
        loadProperties();
        automationURLs = new AutomationURLs(
                System.getProperty("targetURL")
        );
    }

    private void loadProperties() {
        String environmentName = System.getProperty("env");
        assertNotNull("Please provide system property called: env", environmentName);
        this.environmentName = environmentName;

        Environment environment = Environment.environments.get(environmentName);
        assertNotNull(String.format("No environment called: %s", environmentName), environment);

        String propertyFile = environment.getPropertiesFilePath();
        loadPropertyFile(propertyFile);
    }

    private void loadPropertyFile(String path) {
        FileInputStream file = openFile(path);
        loadProperties(file);
        closeFile(file);
    }

    private FileInputStream openFile(String path) {
        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            log.warning(e.getMessage());
            throw new IllegalStateException(String.format("Could not open the configuration file %s", path));
        }
    }

    private void loadProperties(FileInputStream file) {
        try {
            props.load(file);
        } catch (IOException e) {
            log.warning(e.getMessage());
            closeFile(file);
            throw new IllegalStateException(String.format("Could not read the configuration file %s", file));
        }
    }

    private void closeFile(FileInputStream file) {
        try {
            file.close();
        } catch (IOException e) {
            log.warning(e.getMessage());
            throw new IllegalStateException(String.format("Could not close the configuration file %s", file));
        }
    }

    protected String geProtocolImplementationClass(String className) {
        String protocolClassName = props.getProperty(className);
        assertNotNull(
                String.format("No configuration find for protocol %s on environment %s", className, environmentName),
                protocolClassName
        );

        log.info(String.format("Class for %s: %s", className, protocolClassName));
        return protocolClassName;
    }

    private static class Environment {
        public static Map<String, Environment> environments = new HashMap<String, Environment>() {
            {
                placeEnvironments();
            }

            private void placeEnvironments() {
                put("staging", new Environment("src/main/resources/staging.properties"));
                put("local", new Environment("src/main/resources/local.properties"));
            }
        };

        @Getter
        private final String propertiesFilePath;

        public Environment(String propertiesFilePath) {
            this.propertiesFilePath = propertiesFilePath;
        }
    }

    @AllArgsConstructor
    public class AutomationURLs {
        @Getter
        public final String gitLabURL;
    }
}
