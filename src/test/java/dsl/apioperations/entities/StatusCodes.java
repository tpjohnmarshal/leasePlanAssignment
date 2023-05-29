package dsl.apioperations.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum StatusCodes {
    NOT_FOUND(404),
    SUCCESS(200);

    @Getter
    private final Integer value;
}
