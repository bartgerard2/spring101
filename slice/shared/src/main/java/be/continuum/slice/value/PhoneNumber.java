package be.continuum.slice.value;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static lombok.AccessLevel.PRIVATE;

/**
 * PhoneNumber
 *
 * @author bartgerard
 * @version v0.0.1
 */
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
@EqualsAndHashCode
@ToString
public class PhoneNumber {
    private String areaCode;
    private String number;
}
