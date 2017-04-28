package be.continuum.slice.repository;

import be.continuum.slice.model.ConsumableProduct;
import be.continuum.slice.model.Product;
import be.continuum.slice.respository.ProductRepository;
import be.continuum.slice.value.Category;
import be.continuum.slice.value.ProductName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ProductRepositoryTest
 *
 * @author bartgerard
 * @version v0.0.1
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void saveConsumableProduct() {
        final ConsumableProduct p1 = ConsumableProduct.builder()
                .name(ProductName.of("twix"))
                .category(Category.of("CANDY"))
                .build();

        productRepository.save(p1);

        final Product p2 = productRepository.findOne(ProductName.of("twix"));

        assertThat(p2).isEqualTo(p1);
    }

}
