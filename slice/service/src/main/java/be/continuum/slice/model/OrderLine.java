package be.continuum.slice.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PROTECTED;

/**
 * OrderQuantity
 *
 * @author bartgerard
 * @version v0.0.1
 */
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "productName")
@ToString

@Entity
public class OrderLine {

    @Id
    @Column(name = "product_name")
    private String productName;

    @Getter(NONE)
    @MapsId
    @ManyToOne
    @JoinColumn(name = "product_name", foreignKey = @ForeignKey(name = "fk_order_quantity_product"))
    private Product product;

    private int amount;

    public static OrderLine of(
            final Product product,
            int amount
    ) {
        return new OrderLine(product.getName(), product, amount);
    }

}
