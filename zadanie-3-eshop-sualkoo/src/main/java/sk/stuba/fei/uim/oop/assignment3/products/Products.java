package sk.stuba.fei.uim.oop.assignment3.products;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.cart.Cart;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Long amount;
    private String unit;
    private Long price;

}
