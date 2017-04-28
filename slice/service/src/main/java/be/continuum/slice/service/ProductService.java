package be.continuum.slice.service;

import be.continuum.slice.model.Product;

import java.util.Collection;
import java.util.List;

/**
 * ProductService
 *
 * @author bartgerard
 * @version v0.0.1
 */
public interface ProductService {

    Product findOne(String name);

    List<Product> findAll();

    List<Product> findAll(Collection<String> names);

    Product save(Product product);

}
