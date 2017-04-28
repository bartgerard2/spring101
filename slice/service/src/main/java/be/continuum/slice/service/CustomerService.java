package be.continuum.slice.service;

import be.continuum.slice.model.Customer;

import java.util.function.Consumer;

/**
 * CustomerService
 *
 * @author bartgerard
 * @version v0.0.1
 */
public interface CustomerService {

    Customer findOne(String username);

    Customer save(
            String username,
            Consumer<Customer> event
    );

}
