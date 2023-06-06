package pl.javastart.fileuploaddemo;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddRecipeDto {

    private String name;
    private String description;
    private MultipartFile file;
}
