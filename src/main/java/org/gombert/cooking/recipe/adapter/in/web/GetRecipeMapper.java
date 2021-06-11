package org.gombert.cooking.recipe.adapter.in.web;

import org.gombert.cooking.recipe.domain.Recipe;
import org.gombert.cooking.recipe.domain.RecipeIngredient;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class GetRecipeMapper
{
    static RecipeDTO mapModelToDTO(final Recipe recipe)
    {
        final var ingredientsDTO = notNullStreamOfCollection(recipe.ingredients())
                .map(ingredients -> new RecipeIngredientDTO(ingredients.ingredient(), ingredients.amount(), ingredients.unit()))
                .collect(Collectors.toList());

        return new RecipeDTO(recipe.recipeId().getId(), recipe.tenantId().getId(), recipe.name(), recipe.description(), recipe.comment(), ingredientsDTO, recipe.methods());
    }

    static private Stream<RecipeIngredient> notNullStreamOfCollection(Collection<RecipeIngredient> collection) {
        return Optional.ofNullable(collection)
                .map(Collection::stream)
                .orElseGet(Stream::empty);
    }
}
