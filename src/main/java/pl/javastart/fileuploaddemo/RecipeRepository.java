package pl.javastart.fileuploaddemo;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RecipeRepository {

    private Map<Long, Recipe> recipeDatabase = new HashMap<>();
    private Long latestId = 0L;

    public RecipeRepository() {
        Recipe recipe = new Recipe();
        recipe.setId(incrementAndGetId());
        recipe.setName("Rosół");
        recipe.setDescription("Woda, warzywa, udka i już.");
        recipeDatabase.put(recipe.getId(), recipe);
    }

    Recipe findById(Long id) {
        return recipeDatabase.get(id);
    }

    public Recipe insert(AddRecipeDto addRecipeDto) throws IOException {
        Recipe recipe = new Recipe();
        recipe.setId(incrementAndGetId());
        recipe.setName(addRecipeDto.getName());
        recipe.setDescription(addRecipeDto.getDescription());
        recipeDatabase.put(recipe.getId(), recipe);

        String originalFilename = addRecipeDto.getFile().getOriginalFilename();
        recipe.setImageUrl("/img/" + originalFilename);
        File dir = new File("C:\\repo\\temp\\fileuploaddemo\\data\\img");
        addRecipeDto.getFile().transferTo(new File(dir, originalFilename));

        return recipe;
    }

    private Long incrementAndGetId() {
        latestId++;
        return latestId;
    }
}
