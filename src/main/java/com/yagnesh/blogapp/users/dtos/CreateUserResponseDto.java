package com.yagnesh.blogapp.users.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Data
@Setter(AccessLevel.NONE)
public class CreateUserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String bio;
    private String image;

}
