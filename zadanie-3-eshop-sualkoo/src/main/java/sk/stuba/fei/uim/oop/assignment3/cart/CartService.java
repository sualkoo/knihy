package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sk.stuba.fei.uim.oop.assignment3.products.Products;
import sk.stuba.fei.uim.oop.assignment3.products.ProductsRepository;

@Service
public class CartService implements ICartService{

        private final CartRepository repository;

    @Autowired
    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cart createCart() {
        Cart cart = new Cart();
        return repository.save(cart);
    }

    @Override
    public Cart getById(Long id) {
        return this.repository.findAllById(id);
    }

    @Override
    public void delete(Long id) {
        this.repository.delete(this.repository.findAllById(id));
    }

    @Override
    public Cart addToCart(Long id, CartRequest request, ProductsRepository repository) {

        Products product = repository.findById(request.getProductId()).get();
        product.setAmount(product.getAmount()- request.getAmount());
        repository.save(product);

        Cart cart = this.repository.findAllById(id);
        Item item = new Item();
        item.setAmount(request.getAmount());
        item.setProductId(request.getProductId());
        cart.getShoppingList().add(item);

        return this.repository.findAllById(id);
    }



}
