package be.continuum.slice.event;

import be.continuum.slice.value.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static lombok.AccessLevel.PRIVATE;

/**
 * AddPhoneNumber
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Data
@Builder
public class PhoneNumberAddedEvent {
    private final PhoneNumber phoneNumber;
}
