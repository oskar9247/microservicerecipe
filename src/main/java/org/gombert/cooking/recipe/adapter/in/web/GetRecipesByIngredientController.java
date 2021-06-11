package org.gombert.cooking.recipe.adapter.in.web;

import lombok.AllArgsConstructor;
import org.gombert.cooking.recipe.domain.exception.RecipeNotFoundException;
import org.gombert.cooking.recipe.domain.port.in.GetRecipesByIngredientUseCase;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotActiveException;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class GetRecipesByIngredientController
{
    private final GetRecipesByIngredientUseCase getRecipesByIngredientUseCase;

    @GetMapping(path = "/v1/cookingrecipe/{tenantId}/recipes/incredient/{ingredient}/")
    public ResponseEntity<Collection<UUID>> getRecipe(@PathVariable("tenantId") UUID tenantId, @PathVariable("ingredient") String ingredient) throws RecipeNotFoundException
    {
        final var recipeUUIDs = getRecipesByIngredientUseCase.getRecipesByIngredient(new TenantId(tenantId), ingredient)
                .stream().map(recipeId -> recipeId.getId()).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(recipeUUIDs);
    }

    @ExceptionHandler({RecipeNotFoundException.class})
    public ResponseEntity<String> handleRecipeNotFoundException()
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("recipe was not found");
    }

    @ExceptionHandler({TenantNotFoundException.class})
    public ResponseEntity<String> handleTenantNotFoundException()
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tenant was not found");
    }

    @ExceptionHandler({TenantNotActiveException.class})
    public ResponseEntity<String> handleTenantNotActiveException(TenantNotActiveException ex)
    {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("tenant not active" + ex);
    }
}
