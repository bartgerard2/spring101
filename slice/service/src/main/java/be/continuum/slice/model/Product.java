package be.continuum.slice.model;

import be.continuum.slice.value.Category;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import static lombok.AccessLevel.PROTECTED;

/**
 * Product
 *
 * @author bartgerard
 * @version v0.0.1
 */
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@EqualsAndHashCode(of = "name")
@ToString

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
public abstract class Product {

    @Id
    @NonNull
    private String name;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "category", length = 100))
    @NonNull
    private Category category;

    public abstract Type getType();

    public enum Type {
        CONSUMABLE,
        NON_CONSUMABLE
    }

}
