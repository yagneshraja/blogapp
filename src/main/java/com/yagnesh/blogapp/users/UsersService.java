package com.yagnesh.blogapp.users;


import com.yagnesh.blogapp.users.dtos.CreateUserRequestDto;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private UsersRepository usersRepository;

    private UsersService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public Users createUser(CreateUserRequestDto req){
        var newUser = Users.builder()
                .username(req.getUsername())
//                .password(req.getPassword()) // TODO: handle password
                .email(req.getEmail())
                .build();


        return usersRepository.save(newUser);
    }

    public Users getUser(String username){
        var newUser = usersRepository.findByUsername(username);

        if(newUser.isEmpty()){
            return new Users();
        }
        return newUser.get();
    }

    public Users loginUser(String username,String password){
        var user = usersRepository.findByUsername(username);
        if(user == null){
            throw new UserNotFoundException(username);
        }
        // TODO: password matching
        return user.get();
    }

    public Users getUserById(long id){
        var user =  usersRepository.findById(id);
        if(user == null){
            throw new UserNotFoundException(id);
        }
        return user.get();

    }

    public static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(String username){
            super("User "+username+" not found");
        }
        public UserNotFoundException(Long  id){
            super("User "+id+" Not Found");
        }
    }

}
