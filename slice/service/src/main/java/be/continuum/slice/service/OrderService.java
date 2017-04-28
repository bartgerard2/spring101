package be.continuum.slice.service;

import be.continuum.slice.model.Order;

import java.util.List;

/**
 * OrderService
 *
 * @author bartgerard
 * @version v0.0.1
 */
public interface OrderService {

    Order findOne(Long id);

    List<Order> findAll();

    Order save(Order order);

}
