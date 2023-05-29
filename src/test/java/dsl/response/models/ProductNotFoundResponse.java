package dsl.response.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductNotFoundResponse {
    Detail detail;
}
