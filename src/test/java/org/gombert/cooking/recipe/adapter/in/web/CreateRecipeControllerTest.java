package org.gombert.cooking.recipe.adapter.in.web;

import org.gombert.cooking.recipe.domain.port.in.CreateRecipeUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CreateRecipeControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateRecipeUseCase createRecipeUseCase;

    @Test
    public void givenCorrectContent_whenCallingCreateRecipe_thenReturnOk200() throws Exception {
        mockMvc.perform(post("/v1/cookingrecipe/d45768c7-4efe-49fa-b0b9-d8382f90f593/recipes/")
                .content("""
                                {
                                    "clientGeneratedId":"6f651a72-698c-4892-bd7b-8e553e6acfdc",
                                    "name":"RecipeName",
                                    "description":"RecipeDescription",
                                    "comment":"RecipeComment",
                                    "methodSteps":[ "Step1", "Step2", "Step3" ],
                                    "recipeIngredients": [{"ingredient":"Milk", "amount":1.0, "unit":"Liter"}]
                                }
                        """)
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNullJsonObjects_whenCallingCreateRecipe_thenReturnOk200() throws Exception {
        mockMvc.perform(post("/v1/cookingrecipe/d45768c7-4efe-49fa-b0b9-d8382f90f593/recipes/")
                .content("""
                                {
                                    "clientGeneratedId":null,
                                    "name":null,
                                    "description":null,
                                    "comment":null,
                                    "methodSteps":null,
                                    "recipeIngredients":null
                                }
                        """)
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenEmptyJsonObjects_whenCallingCreateRecipe_thenReturnOk200() throws Exception {
        mockMvc.perform(post("/v1/cookingrecipe/d45768c7-4efe-49fa-b0b9-d8382f90f593/recipes/")
                .content("""
                                {
                                    "clientGeneratedId":"",
                                    "tenantId":"",
                                    "name":"",
                                    "description":"",
                                    "comment":"",
                                    "methodSteps":[],
                                    "recipeIngredients":[]
                                }
                        """)
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenEmptyContent_whenCallingCreateRecipe_thenReturnBadRequest404() throws Exception {
        mockMvc.perform(post("/v1/cookingrecipe/d45768c7-4efe-49fa-b0b9-d8382f90f593/recipes/")
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }
}