package be.continuum.slice.command;

import be.continuum.slice.value.PhoneNumber;
import lombok.Data;

/**
 * RemovePhoneNumberCommand
 *
 * @author bgerard
 * @version 1.0
 */
@Data
public class RemovePhoneNumberCommand {
    private PhoneNumber phoneNumber;
}
