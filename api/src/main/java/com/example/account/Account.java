package com.example.account;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;

    public void encodePassword() {
        this.password = "noop" + this.password;
    }

//    public void encodePassword(PasswordEncoder passwordEncoder) {
//        this.password = passwordEncoder.encode(this.password);
//    }
}

