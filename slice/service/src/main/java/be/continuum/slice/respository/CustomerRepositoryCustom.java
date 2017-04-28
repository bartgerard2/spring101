package be.continuum.slice.respository;

import be.continuum.slice.model.Customer;

import java.util.List;

/**
 * CustomCustomerRepositoryImpl
 *
 * @author bartgerard
 * @version v0.0.1
 */
public interface CustomerRepositoryCustom {
    List<Customer> findByEmail(final String email);
}
