package be.continuum.slice.respository;

import be.continuum.slice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CustomerRepository
 *
 * @author bartgerard
 * @version v0.0.1
 */
public interface CustomerRepository extends JpaRepository<Customer, String>, CustomerRepositoryCustom {
}
