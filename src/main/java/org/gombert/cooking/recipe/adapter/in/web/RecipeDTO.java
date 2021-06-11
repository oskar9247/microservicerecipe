package org.gombert.cooking.recipe.adapter.in.web;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
class RecipeDTO
{
    private UUID recipeId;
    private UUID tenantId;
    private String name;
    private String description;
    private String comment;
    private List<RecipeIngredientDTO> recipeIngredients;
    private List<String> methodSteps;
}