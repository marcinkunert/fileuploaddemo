package pl.javastart.fileuploaddemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/recipe/{id}")
    public String showRecipe(@PathVariable Long id, Model model) {
        Recipe recipe = recipeRepository.findById(id);
        model.addAttribute("recipe", recipe);
        return "recipe";
    }

    @GetMapping("/add")
    public String addRecipeForm(Model model) {
        AddRecipeDto addRecipeDto = new AddRecipeDto();
        addRecipeDto.setName("Biszkopt");
        addRecipeDto.setDescription("Jajka ubić, mąka i gotowe");
        model.addAttribute("addRecipeDto", addRecipeDto);
        return "addRecipe";
    }

    @PostMapping("/add")
    public String addRecipe(AddRecipeDto addRecipeDto) throws IOException {
        Recipe recipe = recipeRepository.insert(addRecipeDto);
        return "redirect:/recipe/" + recipe.getId();
    }

}
