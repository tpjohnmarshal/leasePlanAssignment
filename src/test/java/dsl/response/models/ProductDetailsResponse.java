package dsl.response.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductDetailsResponse {
    String provider;
    String title;
    String url;
    String brand;
    Float price;
    String unit;
    Boolean isPromo;
    String promoDetails;
    String image;
}
