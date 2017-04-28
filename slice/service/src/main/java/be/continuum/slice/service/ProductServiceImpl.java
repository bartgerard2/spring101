package be.continuum.slice.service;

import be.continuum.slice.model.Product;
import be.continuum.slice.respository.ProductRepository;
import be.continuum.slice.value.ProductName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * ProductServiceImpl
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product findOne(ProductName name) {
        return productRepository.findOne(name);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAll(final Collection<ProductName> names) {
        return productRepository.findAll(names);
    }

    @Override
    public Product save(final Product product) {
        return productRepository.save(product);
    }


}
