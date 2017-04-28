package be.continuum.slice.command;

import lombok.Data;

/**
 * ChangeCustomerData
 *
 * @author bgerard
 * @version 1.0
 */
@Data
public class ChangeCustomerDataCommand {
    private String email;
    private String firstName;
    private String lastName;
}
