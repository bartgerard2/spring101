package be.continuum.slice.model;

import be.continuum.slice.value.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import static lombok.AccessLevel.PRIVATE;

/**
 * Address
 *
 * @author bartgerard
 * @version v0.0.1
 */
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@Getter
@Builder
@EqualsAndHashCode
@ToString
@Embeddable
public class Address {
    private String street;
    private String number;
    private String city;
    private String zipCode;
    @Enumerated(EnumType.STRING)
    private Country country;
}
