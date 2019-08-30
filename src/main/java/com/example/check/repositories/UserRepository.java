package com.example.check.repositories;


import com.example.check.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findByUsername(String username);
    Boolean existsByUsername(String username);

}
