package be.continuum.slice.controller;

import be.continuum.slice.event.PhoneNumberAddedEvent;
import be.continuum.slice.event.CustomerDataChangedEvent;
import be.continuum.slice.event.PhoneNumberRemovedEvent;
import be.continuum.slice.model.Customer;
import be.continuum.slice.service.CustomerService;
import be.continuum.slice.value.Address;
import be.continuum.slice.value.FoodAllergen;
import be.continuum.slice.value.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * CustomerRestController
 *
 * @author bartgerard
 * @version v0.0.1
 */
@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping("{username}")
    public Customer byEmail(@PathVariable final String username) {
        return customerService.findOne(username);
    }

    @PutMapping("{username}")
    public Customer save(
            @PathVariable final String username,
            @RequestBody final CustomerDataChangedEvent customerDataChangedEvent
    ) {
        return customerService.save(username, customer -> customer.handle(customerDataChangedEvent));
    }

    @GetMapping("/{username}/phone-numbers")
    public Set<PhoneNumber> phonesByEmail(@PathVariable final String username) {
        return customerService.findOne(username)
                              .getPhoneNumbers();
    }

    @PostMapping("/{username}/phone-numbers")
    public Set<PhoneNumber> addPhoneNumber(
            @PathVariable final String username,
            @RequestBody final PhoneNumberAddedEvent phoneNumberAddedEvent
    ) {
        return customerService.save(username, customer -> customer.handle(phoneNumberAddedEvent))
                              .getPhoneNumbers();
    }

    @DeleteMapping("/{username}/phone-numbers")
    public Set<PhoneNumber> removePhoneNumber(
            @PathVariable final String username,
            @RequestBody final PhoneNumberRemovedEvent phoneNumberRemovedEvent
    ) {
        return customerService.save(username, customer -> customer.handle(phoneNumberRemovedEvent))
                              .getPhoneNumbers();
    }

    @GetMapping("/{username}/allergens")
    public Set<FoodAllergen> allergensByEmail(@PathVariable final String username) {
        return customerService.findOne(username)
                              .getAllergens();
    }

    @PutMapping("/{username}/allergens/{allergen}")
    public Set<FoodAllergen> addAllergen(
            @PathVariable final String username,
            @PathVariable final FoodAllergen allergen
    ) {
        return customerService.save(username, customer -> customer.addAllergen(allergen))
                              .getAllergens();
    }

    @DeleteMapping("/{username}/allergens/{allergen}")
    public Set<FoodAllergen> removeAllergen(
            @PathVariable final String username,
            @PathVariable final FoodAllergen allergen
    ) {
        return customerService.save(username, customer -> customer.removeAllergen(allergen))
                              .getAllergens();
    }

    @GetMapping("/{username}/addresses")
    public Map<String, Address> addressesByEmail(@PathVariable final String username) {
        return customerService.findOne(username)
                              .getAddresses();
    }

    @PutMapping("/{username}/addresses/{alias}")
    public Map<String, Address> addAddress(
            @PathVariable final String username,
            @PathVariable final String alias,
            @RequestBody final Address address
    ) {
        return customerService.save(username, customer -> customer.addAddress(alias, address))
                              .getAddresses();
    }

    @DeleteMapping("/{username}/addresses/{alias}")
    public Map<String, Address> removeAddress(
            @PathVariable final String username,
            @PathVariable final String alias
    ) {
        return customerService.save(username, customer -> customer.removeAddress(alias))
                              .getAddresses();
    }

}
