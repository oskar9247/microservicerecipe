package org.gombert.cooking.recipe.adapter.in.web;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
class RecipeIngredientDTO
{
    private String ingredient;
    private Double amount;
    private String unit;
}
