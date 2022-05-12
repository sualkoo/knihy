package sk.stuba.fei.uim.oop.assignment3.products;

import java.util.List;

public interface IProductsService {

    List<Products> getAll();
    Products create(ProductsRequest request);
    Products getAllById(Long id);
    void deleteById(Long id);
    Products updateById(Long id);
    Long getAmount(Long id);
    void addAmount(Long id, Long amount);
}
