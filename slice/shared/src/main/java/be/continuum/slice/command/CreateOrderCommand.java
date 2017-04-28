package be.continuum.slice.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

/**
 * CreateOrder
 *
 * @author bartgerard
 * @version v0.0.1
 */
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@Getter
@Builder
@ToString
public class CreateOrderCommand {
    private String username;
    private List<OrderEntry> entries;

    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Getter
    @Builder
    @ToString
    public static class OrderEntry {
        private String productName;
        private int amount;
    }

}
