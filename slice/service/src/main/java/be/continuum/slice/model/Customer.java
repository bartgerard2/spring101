package be.continuum.slice.model;

import be.continuum.slice.event.PhoneNumberAddedEvent;
import be.continuum.slice.event.CustomerDataChangedEvent;
import be.continuum.slice.event.PhoneNumberRemovedEvent;
import be.continuum.slice.value.Address;
import be.continuum.slice.value.FoodAllergen;
import be.continuum.slice.value.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

/**
 * Customer
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Entity
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@Getter
@Builder
@EqualsAndHashCode(of = "username")
@ToString
public class Customer {

    @Id
    private String username;

    private String email;

    private String firstName;

    private String lastName;

    @Embedded
    @ElementCollection
    @CollectionTable(
            name = "customer_phone",
            joinColumns = @JoinColumn(name = "customer_id"),
            foreignKey = @ForeignKey(name = "fk_customer_phone")
    )
    private final Set<PhoneNumber> phoneNumbers = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(
            name = "customer_allergen",
            joinColumns = @JoinColumn(name = "customer_id"),
            foreignKey = @ForeignKey(name = "fk_customer_allergen")
    )
    @Column(name = "allergen")
    private final Set<FoodAllergen> allergens = new HashSet<>();

    @Embedded
    @ElementCollection
    @CollectionTable(
            name = "customer_address",
            joinColumns = @JoinColumn(name = "customer_id"),
            foreignKey = @ForeignKey(name = "fk_customer_address")
    )
    @MapKeyColumn(name = "alias") // alternative more specific annotations : @MapKeyEnumerated, ...
    private final Map<String, Address> addresses = new HashMap<>();

    public void handle(final CustomerDataChangedEvent customerDataChangedEvent) {
        this.email = customerDataChangedEvent.getEmail();
        this.firstName = customerDataChangedEvent.getFirstName();
        this.lastName = customerDataChangedEvent.getLastName();
    }

    public void handle(final PhoneNumberAddedEvent phoneNumberAddedEvent) {
        this.phoneNumbers.add(phoneNumberAddedEvent.getPhoneNumber());
    }

    public void handle(final PhoneNumberRemovedEvent phoneNumberRemovedEvent) {
        this.phoneNumbers.remove(phoneNumberRemovedEvent.getPhoneNumber());
    }

    public void addAllergen(final FoodAllergen allergen) {
        this.allergens.add(allergen);
    }

    public void removeAllergen(final FoodAllergen allergen) {
        this.allergens.remove(allergen);
    }

    public void addAddress(
            final String alias,
            final Address address
    ) {
        this.addresses.put(alias, address);
    }

    public void removeAddress(final String alias) {
        this.addresses.remove(alias);
    }

}
