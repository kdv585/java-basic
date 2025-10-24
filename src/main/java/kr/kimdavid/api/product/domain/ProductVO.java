package kr.kimdavid.api.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {
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
