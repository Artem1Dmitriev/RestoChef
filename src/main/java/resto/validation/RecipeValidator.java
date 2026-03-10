package resto.validation;

import resto.exception.*;
import resto.model.Recipe;
import resto.model.RecipeLine;

public class RecipeValidator {

    public void validateRecipe(Recipe recipe) throws RecipeValidationException {
        // TODO: занятие 4 - проверить что recipe не null, dishName не пустой
        // TODO: проверить что lines не пустой (рецепт должен содержать ингредиенты)
        // TODO: проверить что все ингредиенты из справочника (не null)
    }

    public void validateNormDeviation(double planned, double actual, double allowedPercent) 
            throws NormViolationException {
        // TODO: занятие 4 - рассчитать отклонение: |actual - planned| / planned * 100
        // TODO: бросить NormViolationException если отклонение > allowedPercent
    }
    
    // TODO: занятие 4 - создать RecipeValidationException если не существует
}
