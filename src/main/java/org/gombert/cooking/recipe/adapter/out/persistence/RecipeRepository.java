package org.gombert.cooking.recipe.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;

public interface RecipeRepository extends JpaRepository<RecipeJPAEntity, UUID>
{
    RecipeJPAEntity findByRecipeIdAndTenantId(final UUID recipeId, final UUID tenantId);
    Collection<RecipeJPAEntity> findAllByTenantId(final UUID tenantId);
}
