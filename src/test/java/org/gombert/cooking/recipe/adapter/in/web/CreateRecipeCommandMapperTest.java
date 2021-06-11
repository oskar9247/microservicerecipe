package org.gombert.cooking.recipe.adapter.in.web;


import org.gombert.cooking.recipe.domain.RecipeId;
import org.gombert.cooking.recipe.domain.exception.RecipeCreationException;
import org.gombert.cooking.recipe.domain.port.in.CreateRecipeUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

class CreateRecipeCommandMapperTest
{
    @Test
    public void givenCorrectDto_whenMapDtoToCommand_thenCreateCommand() throws RecipeCreationException {
        // given
        final var recipeId = new RecipeId(UUID.randomUUID());
        final var ingredients = new ArrayList<CreateRecipeIngredientDTO>();
        ingredients.add(new CreateRecipeIngredientDTO("Milk", 1.0, "Liter"));
        ingredients.add(new CreateRecipeIngredientDTO("Water", 100.0, "Milliliter"));
        final var method = new ArrayList<String>();
        method.add("Step1");
        method.add("Step2");
        final var createRecipeDTO = new CreateRecipeDTO(recipeId.getId(), "Recipe1", "recipeDescription", "Comment", ingredients, method);
        // when
        final var createRecipeCommand = CreateRecipeCommandMapper.mapDtoToCommand(createRecipeDTO);
        // then
        Assertions.assertEquals(recipeId, createRecipeCommand.getClientGeneratedId());
        Assertions.assertEquals("Recipe1", createRecipeCommand.getName());
        Assertions.assertEquals("recipeDescription", createRecipeCommand.getDescription());
        Assertions.assertEquals("Comment", createRecipeCommand.getComment());

        final var createRecipeIngredientCommand1 = new CreateRecipeUseCase.CreateRecipeIngredientCommand("Milk", 1.0, "Liter");
        Assertions.assertTrue(createRecipeCommand.getRecipeIngredients().contains(createRecipeIngredientCommand1));
        final var createRecipeIngredientCommand2 = new CreateRecipeUseCase.CreateRecipeIngredientCommand("Water", 100.0, "Milliliter");
        Assertions.assertTrue(createRecipeCommand.getRecipeIngredients().contains(createRecipeIngredientCommand2));
        Assertions.assertEquals(method, createRecipeCommand.getMethodSteps());
    }
}