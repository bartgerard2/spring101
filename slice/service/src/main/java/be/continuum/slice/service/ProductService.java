package be.continuum.slice.service;

import be.continuum.slice.model.Product;
import be.continuum.slice.value.ProductName;

import java.util.Collection;
import java.util.List;

/**
 * ProductService
 *
 * @author bartgerard
 * @version v0.0.1
 */
public interface ProductService {

    Product findOne(ProductName name);

    List<Product> findAll();

    List<Product> findAll(Collection<ProductName> names);

    Product save(Product product);

}
