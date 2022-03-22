package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    public UserEntity findByUsernameAndSns(String id, SocialProvider sns);
}
