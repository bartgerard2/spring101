package be.continuum.slice.respository;

import be.continuum.slice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ProductRepository
 *
 * @author bartgerard
 * @version v0.0.1
 */
public interface ProductRepository extends JpaRepository<Product, String> {
}
