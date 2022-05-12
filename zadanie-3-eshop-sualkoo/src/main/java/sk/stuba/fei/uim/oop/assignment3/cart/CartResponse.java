package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;

import java.util.List;

@Getter
public class CartResponse {
    private final Long id;
    private final boolean payed;
    private final List<Item> shoppingList;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.payed = cart.getPayed();
        this.shoppingList = cart.getShoppingList();
    }
}
