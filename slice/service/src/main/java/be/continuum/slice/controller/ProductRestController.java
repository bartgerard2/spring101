package be.continuum.slice.controller;

import be.continuum.slice.model.ConsumableProduct;
import be.continuum.slice.model.NonConsumableProduct;
import be.continuum.slice.model.Product;
import be.continuum.slice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ProductRestController
 *
 * @author bartgerard
 * @version v0.0.1
 */
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("{name}")
    public Product byName(@PathVariable final String name) {
        return productService.findOne(name);
    }

    @GetMapping
    public List<Product> all() {
        return productService.findAll();
    }

    @PostMapping("/consumables")
    public Product createConsumableProduct(@RequestBody final ConsumableProduct product) {
        return productService.save(product);
    }

    @PostMapping("/non-consumables")
    public Product createNonConsumableProduct(@RequestBody final NonConsumableProduct product) {
        return productService.save(product);
    }

}
