package com.yagnesh.blogapp.articles.dtos;

import jakarta.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Data
@Setter(AccessLevel.NONE)
public class CreateArticleRequestDto {
    @NonNull
    private String body;
    @NonNull
    private String title;
    @Nullable
    private String subtitle;
}
