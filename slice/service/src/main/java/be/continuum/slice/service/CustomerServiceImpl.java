package be.continuum.slice.service;

import be.continuum.slice.model.Customer;
import be.continuum.slice.respository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * CustomerServiceImpl
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    @Override
    public Customer findOne(final String username) {
        return customerRepository.findOne(username);
    }

    @Override
    public Customer save(
            final String username,
            final Consumer<Customer> event
    ) {
        final Customer customer = findOrCreate(username);

        event.accept(customer);

        return customerRepository.save(customer);
    }

    private Customer findOrCreate(final String username) {
        final Customer existingCustomer = customerRepository.findOne(username);

        if (Objects.nonNull(existingCustomer)) {
            return existingCustomer;
        }

        return Customer.builder()
                       .username(username)
                       .build();
    }

}
