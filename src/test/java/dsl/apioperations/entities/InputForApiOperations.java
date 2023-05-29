package dsl.apioperations.entities;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class InputForApiOperations {
    String productName;
}
