package kr.kimdavid.api.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
        private String index;
        private String name;
        private String description;
        private String brand;
        private String category;
        private String price;
        private String currency;
        private String stock;
        private String ean;
        private String color;
        private String size;
        private String availability;
        private String internalId;
}
