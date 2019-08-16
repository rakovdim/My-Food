package com.myfood.dishes.model.dish;

import com.myfood.commons.model.entities.AuditedEntity;
import com.myfood.dishes.model.dish.cooking.CookingInfo;
import com.myfood.dishes.model.dish.cooking.Principle;
import com.myfood.dishes.model.dish.cooking.Receipt;
import com.myfood.dishes.model.dish.cooking.ScalingStrategy;
import com.myfood.dishes.model.dish.social.Comment;
import com.myfood.dishes.model.dish.social.DishTag;
import com.myfood.dishes.model.dish.social.SocialInfo;
import com.myfood.dishes.model.dish.system.Status;
import com.myfood.dishes.model.dish.system.SystemInfo;
import com.myfood.dishes.model.dish.system.Visibility;
import com.myfood.dishes.model.ingredient.IngredientQuantity;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by rakov on 06.08.2019.
 */
@Entity
@Table(name = "dishes")
@EqualsAndHashCode(callSuper = true)
public class Dish extends AuditedEntity {

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;

    @Embedded
    private SocialInfo socialInfo;
    @Embedded
    private CookingInfo cookingInfo;
    @Embedded
    private SystemInfo systemInfo;


    public Dish() {
    }


    public Dish(UUID id) {
        super(id);
    }


    public Dish(UUID id, String name, Visibility visibility) {
        super(id);
        this.name = name;
        this.socialInfo = new SocialInfo(id);
        this.systemInfo = new SystemInfo(id, visibility);
        this.cookingInfo = new CookingInfo(id);
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public SystemInfo getSystemInfo() {
        return systemInfo;
    }


    public SocialInfo getSocialInfo() {
        return socialInfo;
    }

    public List<Comment> getComments() {
        return socialInfo.getComments();
    }

    public CookingInfo getCookingInfo() {
        return cookingInfo;
    }


    public void addCategory(Category category) {
        systemInfo.addCategory(category);
    }

    public Status getStatus() {
        return systemInfo.getStatus();
    }

    public void setStatus(Status status) {
        systemInfo.setStatus(status);
    }

    public List<Category> getCategories() {
        return systemInfo.getCategories();
    }


    public void addPrinciple(Principle principle) {
        cookingInfo.addPrinciple(principle);
    }


    public List<Principle> getPrinciples() {
        return cookingInfo.getPrinciples();
    }


    public Set<DishTag> getTags() {
        return socialInfo.getDishTags();
    }


    public String getImageId() {
        return socialInfo.getImageId();
    }


    public String getVideoId() {
        return socialInfo.getVideoId();
    }


    public UUID getAuthorId() {
        return socialInfo.getAuthorId();
    }


    public void addIngredient(IngredientQuantity ingredientQuantity) {
        cookingInfo.addIngredient(ingredientQuantity);
    }


    public Receipt getReceipt() {
        return cookingInfo.getReceipt();
    }


    public ScalingStrategy getScalingStrategy() {
        return systemInfo.getScalingStrategy();
    }


    public Visibility getVisibility() {
        return systemInfo.getVisibility();
    }


    public List<IngredientQuantity> getIngredients() {
        return cookingInfo.getIngredients();
    }


    public <T> void addItemIfNotPresent(T item, Supplier<Collection<T>> supplier, Consumer<T> consumer) {
        if (supplier.get().stream().filter(t -> t.equals(item)).findAny().isPresent())
            return;

        consumer.accept(item);
    }
}


