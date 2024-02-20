package com.yagnesh.blogapp.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.OptionalLong;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long>{

    Optional<Users> findByUsername(String username);
}
