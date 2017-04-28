package be.continuum.slice.command;

import be.continuum.slice.value.PhoneNumber;
import lombok.Data;

/**
 * AddPhoneNumberCommand
 *
 * @author bgerard
 * @version 1.0
 */
@Data
public class AddPhoneNumberCommand {
    private final PhoneNumber phoneNumber;
}
