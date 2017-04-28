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
@DiscriminatorValue("CONSUMABLE")
public class ConsumableProduct extends Product {

    private String field1;

    @Builder
    public ConsumableProduct(
            ProductName name,
            Category category,
            String field1
    ) {
        super(name, category);
        this.field1 = field1;
    }

    @Override
    public Type getType() {
        return Type.CONSUMABLE;
    }

}
