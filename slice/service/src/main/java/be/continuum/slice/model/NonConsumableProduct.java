package be.continuum.slice.model;

import be.continuum.slice.value.Category;
import be.continuum.slice.value.ProductName;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static lombok.AccessLevel.PRIVATE;

/**
 * ConsumableProduct
 *
 * @author bartgerard
 * @version v0.0.1
 */
@NoArgsConstructor(access = PRIVATE)

@Entity
@DiscriminatorValue("NON_CONSUMABLE")
public class NonConsumableProduct extends Product {

    private String field2;

    @Builder
    public NonConsumableProduct(
            ProductName name,
            Category category,
            String field2
    ) {
        super(name, category);
        this.field2 = field2;
    }

    @Override
    public Type getType() {
        return Type.NON_CONSUMABLE;
    }

}
