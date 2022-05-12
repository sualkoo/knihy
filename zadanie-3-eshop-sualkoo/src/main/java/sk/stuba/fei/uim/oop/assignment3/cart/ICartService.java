package sk.stuba.fei.uim.oop.assignment3.cart;

import sk.stuba.fei.uim.oop.assignment3.products.ProductsRepository;

import java.util.List;

public interface ICartService {

    Cart getById(Long id);
    Cart createCart();
    void delete(Long id);
    Cart addToCart(Long id, CartRequest request, ProductsRepository repository);
}
