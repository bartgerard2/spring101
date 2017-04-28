package be.continuum.slice.service;

import be.continuum.slice.model.Order;
import be.continuum.slice.respository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * OrderServiceImpl
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order findOne(final Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(final Order order) {
        return orderRepository.save(order);
    }

}
