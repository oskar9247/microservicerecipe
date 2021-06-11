package org.gombert.cooking.recipe.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "recipe_ingredient")
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientJPAEntity
{
    @Id
    @GeneratedValue
    Long id;

    @Column
    String name;

    @Column
    double amount;

    @Column
    String unit;
}
