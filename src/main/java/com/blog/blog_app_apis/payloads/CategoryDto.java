package com.blog.blog_app_apis.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Integer categoryId;

    @NotEmpty
    @Size(min = 4)
    private String categoryTitle;

    @NotEmpty
    @Size(max = 25)
    private String categoryDescription;
}
