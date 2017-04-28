package be.continuum.slice.repository;

import be.continuum.slice.model.ConsumableProduct;
import be.continuum.slice.model.Customer;
import be.continuum.slice.model.Order;
import be.continuum.slice.model.OrderLine;
import be.continuum.slice.model.Product;
import be.continuum.slice.respository.CustomerRepository;
import be.continuum.slice.respository.OrderRepository;
import be.continuum.slice.respository.ProductRepository;
import be.continuum.slice.value.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * OrderRepositoryTest
 *
 * @author bartgerard
 * @version v0.0.1
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Before
    public void setUp() {
        final Customer c1 = Customer.builder()
                                    .email("test")
                                    .username("test")
                                    .firstName("test")
                                    .lastName("test")
                                    .build();

        customerRepository.save(c1);

        final ConsumableProduct p1 = ConsumableProduct.builder()
                                                      .name("milky-way")
                                                      .category(Category.of("CANDY"))
                                                      .build();

        productRepository.save(p1);
    }

    @Test
    public void saveOrder() {
        final Customer customer = customerRepository.findOne("test");
        final Product product = productRepository.findOne("milky-way");

        final Order o1 = Order.builder()
                              .customer(customer)
                              .build();

        o1.getOrderLines()
          .add(OrderLine.of(product, 1));

        orderRepository.save(o1);

        assertThat(orderRepository.findAll()).hasSize(1);
    }

}
