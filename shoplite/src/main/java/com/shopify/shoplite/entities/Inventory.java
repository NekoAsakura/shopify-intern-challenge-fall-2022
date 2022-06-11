package com.shopify.shoplite.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "The inventory name may not be empty!")
    @Size(max = 256, message = "The inventory name should be less than 256 chars!")
    private String name;

    @NotEmpty(message = "The inventory may not be empty!")
    @Min(value = 0, message = "The inventory may not be negative!")
    private int quantity;

    @Size(max = 500, message = "The inventory description should be less than 500 chars!")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Inventory inventory = (Inventory) o;
        return id != null && Objects.equals(id, inventory.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
