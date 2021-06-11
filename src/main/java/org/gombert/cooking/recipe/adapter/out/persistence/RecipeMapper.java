package org.gombert.cooking.recipe.adapter.out.persistence;

import org.gombert.cooking.recipe.domain.Recipe;
import org.gombert.cooking.recipe.domain.RecipeFactory;
import org.gombert.cooking.recipe.domain.RecipeId;
import org.gombert.cooking.recipe.domain.RecipeIngredient;
import org.gombert.cooking.recipe.domain.port.out.GetRecipePort;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class RecipeMapper
{
    Recipe mapFromEntity(final GetRecipePort.ReconstitueRecipeCommand reconstitueRecipeCommand)
    {
        return RecipeFactory.reconstitueRecipe(reconstitueRecipeCommand);
    }

    Recipe mapFromEntity(final RecipeJPAEntity recipeJPAEntity)
    {
        final var ingredients = recipeJPAEntity.recipeIngredients.stream()
                .map(ingredient -> new GetRecipePort.ReconstitueRecipeIngredientCommand(ingredient.name, ingredient.amount, ingredient.unit))
                .collect(Collectors.toList());
        var reconstitueRecipeCommand = new GetRecipePort.ReconstitueRecipeCommand(
                new RecipeId(recipeJPAEntity.getRecipeId()), new TenantId(recipeJPAEntity.tenantId), recipeJPAEntity.name,
                recipeJPAEntity.description, recipeJPAEntity.comment, ingredients,
                recipeJPAEntity.methods);
        return RecipeFactory.reconstitueRecipe(reconstitueRecipeCommand);
    }

    RecipeJPAEntity mapFromDomain(final Recipe recipe)
    {
        return new RecipeJPAEntity(
                        recipe.recipeId().getId(),
                        recipe.tenantId().getId(),
                        recipe.name(),
                        recipe.description(),
                        recipe.comment(),
                        mapIngredientsFromDomain(recipe.ingredients()),
                        recipe.methods()
        );
    }

    List<RecipeIngredientJPAEntity> mapIngredientsFromDomain(final List<RecipeIngredient> recipeIngredients)
    {
        return recipeIngredients.stream().map(recipeIngredient ->
                new RecipeIngredientJPAEntity(null, recipeIngredient.ingredient(), recipeIngredient.amount(), recipeIngredient.unit())).collect(Collectors.toList());
    }
}
