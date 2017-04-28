package be.continuum.slice.event;

import lombok.Builder;
import lombok.Data;

/**
 * SaveCustomer
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Data
@Builder
public class CustomerDataChangedEvent {
    private final String email;
    private final String firstName;
    private final String lastName;
}
