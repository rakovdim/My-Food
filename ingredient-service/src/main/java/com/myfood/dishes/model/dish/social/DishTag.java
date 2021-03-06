package com.myfood.dishes.model.dish.social;

import com.myfood.commons.model.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by rakov on 06.08.2019.
 */
@Entity
@Table(name="dish_tag")
public class DishTag extends AbstractEntity {
    @Column(nullable = false, unique = true, updatable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
