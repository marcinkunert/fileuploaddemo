package pl.javastart.fileuploaddemo;

import lombok.Data;

@Data
public class Recipe {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
}
