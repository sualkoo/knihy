package sk.stuba.fei.uim.oop.assignment3.products;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class TestController {

    @Autowired
    private IProductsService service;

    @GetMapping()
    public List<ProductsResponse> getAllProducts(){
        var result = new ArrayList<ProductsResponse>();
        for (Products p : this.service.getAll()){
            result.add(new ProductsResponse(p));
        }
        return result;
    }

    @PostMapping
    public ProductsResponse addProducts(@RequestBody ProductsRequest request){
        return new ProductsResponse(this.service.create(request));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ProductsResponse> getAllProductsById(@PathVariable("id") Long id){
        if (this.service.getAllById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        var result = new ProductsResponse(this.service.getAllById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById (@PathVariable("id") Long id){
        if (this.service.getAllById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity <ProductsResponse> updateById (@PathVariable("id") Long id){
        if (this.service.getAllById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        var result = new ProductsResponse(this.service.updateById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}/amount")
    public ResponseEntity<JSONObject> getAmount(@PathVariable("id") Long id){
        if (this.service.getAllById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        JSONObject result = new JSONObject();
        result.put("amount", this.service.getAllById(id).getAmount());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{id}/amount")
    public ResponseEntity<JSONObject> addAmount(@PathVariable("id") Long id, @RequestBody ProductsRequest request){
        if (this.service.getAllById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        service.addAmount(id, request.getAmount());
        JSONObject result = new JSONObject();
        result.put("amount", this.service.getAllById(id).getAmount());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
