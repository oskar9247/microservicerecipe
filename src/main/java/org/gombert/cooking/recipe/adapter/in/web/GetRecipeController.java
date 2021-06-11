package org.gombert.cooking.recipe.adapter.in.web;

import lombok.AllArgsConstructor;
import org.gombert.cooking.recipe.domain.RecipeId;
import org.gombert.cooking.recipe.domain.exception.RecipeNotFoundException;
import org.gombert.cooking.recipe.domain.port.in.GetRecipeUseCase;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotActiveException;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
@AllArgsConstructor
class GetRecipeController
{
    private final GetRecipeUseCase getRecipeUseCase;

    @GetMapping(path = "/v1/cookingrecipe/{tenantId}/recipes/{recipeId}/")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable("tenantId") UUID tenantId, @PathVariable("recipeId") UUID recipeId) throws RecipeNotFoundException
    {
        final var recipeDTO = GetRecipeMapper.mapModelToDTO(getRecipeUseCase.getRecipe(new TenantId(tenantId), new RecipeId(recipeId)));
        return ResponseEntity.status(HttpStatus.OK).body(recipeDTO);
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
