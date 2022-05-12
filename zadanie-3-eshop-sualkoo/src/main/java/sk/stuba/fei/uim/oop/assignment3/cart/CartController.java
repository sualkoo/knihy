package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.products.ProductsRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService service;

    @Autowired
    private ProductsRepository repository;

    @PostMapping()
    public ResponseEntity<CartResponse> create(){
        return new ResponseEntity<>(new CartResponse(this.service.createCart()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCart(@PathVariable("id") Long id){
        if (this.service.getById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new CartResponse(this.service.getById(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id){
        if (this.service.getById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<CartResponse> addToCart(@PathVariable("id") long id ,@RequestBody CartRequest request){

        if (this.service.getById(id) == null || !(repository.existsById(request.getProductId()))){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (this.service.getById(id).getPayed() || repository.findById(request.getProductId()).get().getAmount() < request.getAmount()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CartResponse(this.service.addToCart(id,request,repository)),HttpStatus.OK);
    }

}
