package org.gombert.cooking.recipe.application.service;

import org.gombert.cooking.recipe.adapter.out.persistence.RecipePersistenceAdapter;
import org.gombert.cooking.recipe.domain.RecipeId;
import org.gombert.cooking.recipe.domain.exception.RecipeCreationException;
import org.gombert.cooking.recipe.domain.exception.RecipeNotFoundException;
import org.gombert.cooking.recipe.domain.exception.RecipeRepositoryNotFound;
import org.gombert.cooking.tenant.domain.model.port.in.IsTenantActiveUseCase;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;

class CreateRecipeServiceTest
{
    private IsTenantActiveUseCase isTenantActiveUseCase = Mockito.mock(IsTenantActiveUseCase.class);

    @Test
    public void givenCorrectCommand_whenCreatingRecipe_RecipeIsCreatedAndStoredInRepository() throws RecipeCreationException, RecipeNotFoundException, RecipeRepositoryNotFound {
        // given
//        final var recipeRepository = new RecipePersistenceAdapter();
//        final var tenantRepository = new TenantRepositoryWithCollection();
//        final var createRecipeUseCaseAdapter = new CreateRecipeService(recipeRepository, isTenantActiveUseCase);
//
//        final var recipeId = new RecipeId(UUID.randomUUID());
//        final var tenantId = new TenantId(UUID.randomUUID());
//        final var methods = Stream.of("Step1", "Step2").collect(Collectors.toList());
//        final var ingredients = Stream.of(new CreateRecipeUseCase.CreateRecipeIngredientCommand("Milk", 1.0, "Liter")).collect(Collectors.toList());
//        var createRecipeCommand = new CreateRecipeUseCase.CreateRecipeCommand(recipeId, "name", "description", "comment",ingredients, methods);
//        Mockito.when(isTenantActiveUseCase.isTenantActive(any(TenantId.class))).thenReturn(true);
//
//        // when
//        createRecipeUseCaseAdapter.createRecipe(tenantId, createRecipeCommand);
//
//        //then
//        final var recipeFromRepository = recipeRepository.findByIdAndTenantId(tenantId, recipeId);
//        Assertions.assertEquals(recipeId, recipeFromRepository.recipeId());
    }
}