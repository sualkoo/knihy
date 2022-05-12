package sk.stuba.fei.uim.oop.assignment3.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService implements IProductsService {


    private ProductsRepository repository;

    @Autowired
    public ProductsService(ProductsRepository repository){
        this.repository = repository;
        Products pr1 = new Products();
        pr1.setName("Pen");
        pr1.setAmount(Long.valueOf(10));
        this.repository.save(pr1);
    }

    public List<Products> getAll(){
        return this.repository.findAll();
    }

    @Override
    public Products create(ProductsRequest request) {
        Products newProduct = new Products();
        newProduct.setName(request.getName());
        newProduct.setDescription(request.getDescription());
        newProduct.setAmount(request.getAmount());
        newProduct.setUnit(request.getUnit());
        newProduct.setPrice(request.getPrice());
        return this.repository.save(newProduct);
    }

    @Override
    public Products getAllById(Long id) {
        return this.repository.findAllById(id);
    }

    @Override
    public void deleteById (Long id){
        this.repository.delete(this.repository.findAllById(id));
    }

    @Override
    public Products updateById(Long id) {
        Products newProduct = this.repository.findAllById(id);
        ProductsRequest request = new ProductsRequest();

        if (request.getName()!=null){
            newProduct.setName(request.getName());
        }
        if (request.getDescription()!=null) {
            newProduct.setDescription(request.getDescription());
        }
        return this.repository.save(newProduct);
    }

    @Override
    public Long getAmount(Long id){
        Products newProduct = this.repository.findAllById(id);
        return newProduct.getAmount();
    }

    @Override
    public void addAmount(Long id, Long amount){
        Products newProduct = this.repository.findAllById(id);
        newProduct.setAmount(newProduct.getAmount() + amount);
        this.repository.save(newProduct);
    }
}
