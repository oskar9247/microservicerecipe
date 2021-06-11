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
class CreateRecipeDTO
{
    private CreateRecipeDTO(){}

    private UUID clientGeneratedId;
    private String name;
    private String description;
    private String comment;
    private List<CreateRecipeIngredientDTO> recipeIngredients;
    private List<String> methodSteps;
}
