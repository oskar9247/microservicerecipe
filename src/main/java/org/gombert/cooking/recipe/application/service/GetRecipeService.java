package org.gombert.cooking.recipe.application.service;

import lombok.AllArgsConstructor;
import org.gombert.cooking.recipe.domain.Recipe;
import org.gombert.cooking.recipe.domain.RecipeFactory;
import org.gombert.cooking.recipe.domain.RecipeId;
import org.gombert.cooking.recipe.domain.exception.RecipeNotFoundException;
import org.gombert.cooking.recipe.domain.port.in.GetRecipeUseCase;
import org.gombert.cooking.recipe.domain.port.out.GetRecipePort;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotActiveException;
import org.gombert.cooking.tenant.domain.model.port.in.IsTenantActiveUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
class GetRecipeService implements GetRecipeUseCase
{
    private final GetRecipePort getRecipePort;
    private final IsTenantActiveUseCase isTenantActiveUseCase;

    @Override
    @Transactional(readOnly = true)
    public Recipe getRecipe(final TenantId tenantId, final RecipeId recipeId) throws RecipeNotFoundException
    {
        if(isTenantActiveUseCase.isTenantActive(tenantId))
        {
            return getRecipePort.findByIdAndTenantId(tenantId, recipeId);
        }
        else
        {
            throw new TenantNotActiveException("Tenant not active");
        }
    }
}
