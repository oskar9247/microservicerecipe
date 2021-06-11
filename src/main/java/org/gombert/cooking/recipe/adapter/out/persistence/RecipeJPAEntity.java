package org.gombert.cooking.recipe.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "recipe")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeJPAEntity implements Serializable
{
    @Id
    UUID recipeId;

    @Column
    UUID tenantId;

    @Column
    String name;

    @Column
    String description;

    @Column
    String comment;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    List<RecipeIngredientJPAEntity> recipeIngredients;

    @ElementCollection
    List<String> methods;

}
