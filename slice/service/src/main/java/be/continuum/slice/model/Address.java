package be.continuum.slice.model;

import be.continuum.slice.value.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import static javax.persistence.EnumType.STRING;
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

    @Column(length = 100)
    private String street;

    @Column(length = 50)
    private String number;

    @Column(length = 50)
    private String city;

    @Column(length = 20)
    private String zipCode;

    @Enumerated(STRING)
    @Column(length = 3)
    private Country country;

}
