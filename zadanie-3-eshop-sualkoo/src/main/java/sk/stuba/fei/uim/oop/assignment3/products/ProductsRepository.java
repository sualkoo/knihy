package sk.stuba.fei.uim.oop.assignment3.products;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends CrudRepository<Products, Long> {

    List<Products> findAll();
    Products findAllById(Long id);
}
