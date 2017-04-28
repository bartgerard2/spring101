package be.continuum.slice.controller;

import be.continuum.slice.command.CreateOrder;
import be.continuum.slice.model.Order;
import be.continuum.slice.model.OrderQuantity;
import be.continuum.slice.model.Product;
import be.continuum.slice.service.CustomerService;
import be.continuum.slice.service.OrderService;
import be.continuum.slice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * OrderRestController
 *
 * @author bartgerard
 * @version v0.0.1
 */
@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderService orderService;

    @GetMapping("{id}")
    public Order byId(@PathVariable final Long id) {
        return orderService.findOne(id);
    }

    @GetMapping
    public List<Order> all() {
        return orderService.findAll();
    }

    @PostMapping
    public Order order(@RequestBody final CreateOrder createOrder) {
        final Order order = Order.builder()
                                 .customer(customerService.findOne(createOrder.getUsername()))
                                 .build();


        final Map<String, Product> productMap = createOrder.getEntries()
                                                           .stream()
                                                           .map(CreateOrder.OrderEntry::getProductName)
                                                           .collect(Collectors.collectingAndThen(
                                                                   Collectors.toList(),
                                                                   productService::findAll)
                                                           )
                                                           .stream()
                                                           .collect(Collectors.toMap(
                                                                   Product::getName,
                                                                   Function.identity()
                                                           ));

        createOrder.getEntries()
                   .forEach(entry -> order.getProducts()
                                          .add(OrderQuantity.of(productMap.get(entry.getProductName()), entry.getAmount())));


        return orderService.save(order);
    }

}
