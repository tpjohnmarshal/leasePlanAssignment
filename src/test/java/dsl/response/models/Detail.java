package dsl.response.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Detail {
    Boolean error;
    String message;
    String requested_item;
    String served_by;
}
