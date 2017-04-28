package be.continuum.slice.event;

import be.continuum.slice.value.PhoneNumber;
import lombok.Builder;
import lombok.Data;

/**
 * AddPhoneNumber
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Data
@Builder
public class PhoneNumberRemovedEvent {
    private final PhoneNumber phoneNumber;
}
