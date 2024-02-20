package com.yagnesh.blogapp.users;

import com.yagnesh.blogapp.users.dtos.CreateUserRequestDto;
import com.yagnesh.blogapp.users.dtos.CreateUserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final ModelMapper modelMapper;

    public UsersController(UsersService usersService, ModelMapper modelMapper) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }


    @PostMapping()
    public ResponseEntity<CreateUserResponseDto> signUpUser(@RequestBody CreateUserRequestDto req){
        Users savedUser = usersService.createUser(req);

        URI savedUserUri = URI.create("/users/"+savedUser.getId());
//        return new ResponseEntity<>(savedUser, HttpStatus.OK);

        return  ResponseEntity.ok(modelMapper.map(savedUserUri,CreateUserResponseDto.class));

    }

    
    public void loginUser(){

    }


}
