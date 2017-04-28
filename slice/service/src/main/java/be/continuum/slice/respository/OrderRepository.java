package be.continuum.slice.respository;

import be.continuum.slice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OrderRepository
 *
 * @author bartgerard
 * @version v0.0.1
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
