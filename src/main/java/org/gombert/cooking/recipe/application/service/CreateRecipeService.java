package org.gombert.cooking.recipe.application.service;

import lombok.AllArgsConstructor;
import org.gombert.cooking.recipe.domain.Recipe;
import org.gombert.cooking.recipe.domain.RecipeFactory;
import org.gombert.cooking.recipe.domain.exception.RecipeCreationException;
import org.gombert.cooking.recipe.domain.port.in.CreateRecipeUseCase;
import org.gombert.cooking.recipe.domain.port.out.CreateRecipePort;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotActiveException;
import org.gombert.cooking.tenant.domain.model.port.in.IsTenantActiveUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
class CreateRecipeService implements CreateRecipeUseCase
{
    private final CreateRecipePort createRecipePort;
    private final IsTenantActiveUseCase isTenantActiveUseCase;

    @Override
    @Transactional
    public void createRecipe(final TenantId tenantId, final CreateRecipeUseCase.CreateRecipeCommand createRecipeCommand)
    {

        if(isTenantActiveUseCase.isTenantActive(tenantId))
        {
            final Recipe createdRecipe = RecipeFactory.createRecipe(tenantId, createRecipeCommand);

            createRecipePort.create(createdRecipe);
        }
        else
        {
            throw new TenantNotActiveException("Tenant not active");
        }

    }
}
