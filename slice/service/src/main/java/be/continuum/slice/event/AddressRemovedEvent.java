package be.continuum.slice.event;

import lombok.Builder;
import lombok.Data;

/**
 * AddAddress
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Data
@Builder
public class AddressRemovedEvent {
    private final String alias;
}
