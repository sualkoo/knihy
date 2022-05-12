package sk.stuba.fei.uim.oop.assignment3.products;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductsRequest {
    private String name;
    private String description;
    private Long amount;
    private String unit;
    private Long price;
}
