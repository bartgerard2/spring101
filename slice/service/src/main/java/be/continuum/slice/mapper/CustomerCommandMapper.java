package be.continuum.slice.mapper;

import be.continuum.slice.command.AddPhoneNumberCommand;
import be.continuum.slice.command.ChangeCustomerDataCommand;
import be.continuum.slice.command.RemoveAddressCommand;
import be.continuum.slice.command.RemovePhoneNumberCommand;
import be.continuum.slice.event.AddressRemovedEvent;
import be.continuum.slice.event.CustomerDataChangedEvent;
import be.continuum.slice.event.PhoneNumberAddedEvent;
import be.continuum.slice.event.PhoneNumberRemovedEvent;
import org.springframework.stereotype.Component;

/**
 * CustomerCommandMapper
 *
 * @author bgerard
 * @version 1.0
 */
@Component
public class CustomerCommandMapper {

    public PhoneNumberAddedEvent map(final AddPhoneNumberCommand command) {
        return PhoneNumberAddedEvent.builder()
                .phoneNumber(command.getPhoneNumber())
                .build();
    }

    public PhoneNumberRemovedEvent map(final RemovePhoneNumberCommand command) {
        return PhoneNumberRemovedEvent.builder()
                .phoneNumber(command.getPhoneNumber())
                .build();
    }

    public AddressRemovedEvent map(final RemoveAddressCommand command) {
        return AddressRemovedEvent.builder()
                .alias(command.getAlias())
                .build();
    }

    public CustomerDataChangedEvent map(final ChangeCustomerDataCommand command) {
        return CustomerDataChangedEvent.builder()
                .email(command.getEmail())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .build();
    }

}
