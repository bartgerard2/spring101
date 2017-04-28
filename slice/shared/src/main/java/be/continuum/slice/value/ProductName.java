package be.continuum.slice.value;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

/**
 * ProductName
 *
 * @author bgerard
 * @version 1.0
 */
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
@EqualsAndHashCode
@ToString
public class ProductName implements Serializable {
    private String name;
}
