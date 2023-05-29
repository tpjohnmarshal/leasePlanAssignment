package dsl.configuration;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("PMD.PreserveStackTrace")
public class ProtocolFactory {
    private static ProtocolFactory instance = null;
    private final transient ConfigurationFactory configuration = new ConfigurationFactory();
    private final transient Map<String, Object> instances = new HashMap<>();

    private static ProtocolFactory getSingleton() {
        if (instance == null) {
            instance = new ProtocolFactory();
        }
        return instance;
    }

    public static Object getInstance(@NotNull Class interfaceToBuild, String classKey) {
        ProtocolFactory protocolFactory = getSingleton();
        String classCanonical = interfaceToBuild.getCanonicalName();

        if (!protocolFactory.instances.containsKey(classCanonical)) {
            Class<?> clazz = protocolFactory.findClass(classKey);
            Constructor<?> constructor = protocolFactory.findConstructor(clazz);
            protocolFactory.instances.put(classCanonical, protocolFactory.instantiate(constructor));
        }

        return protocolFactory.instances.get(classCanonical);
    }

    @NotNull
    private Class<?> findClass(String classKey) {
        String classCanonical = configuration.geProtocolImplementationClass(classKey);
        try {
            return Class.forName(classCanonical);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(String.format("Could not find the class %s", classCanonical), e);
        }
    }

    @NotNull
    private Constructor<?> findConstructor(Class<?> clazz) {
        try {
            return clazz.getConstructor(ConfigurationFactory.AutomationURLs.class);
        } catch (NoSuchMethodException e) {
            String message = String.format(
                    "Could a constructor for ConfigurationFactory.AutomationURLs on class: %s",
                    clazz.getCanonicalName()
            );
            throw new IllegalArgumentException(message, e);
        }
    }

    @NotNull
    private Object instantiate(Constructor<?> constructor) {
        try {
            return constructor.newInstance(configuration.getAutomationURLs());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            String message = String.format(
                    "Could not instantiate an object of the class %s. Error of type %s",
                    constructor.getName(),
                    e.getCause()
            );

            throw new IllegalArgumentException(message, e);
        }
    }
}