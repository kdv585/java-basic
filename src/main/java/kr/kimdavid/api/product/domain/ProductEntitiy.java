package kr.kimdavid.api.product.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntitiy {
        @Id
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