package com.social;

import com.example.SocialProvider;
import com.example.UserEntity;
import com.example.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.UUID;

public abstract class BasePrincipalExtractor implements PrincipalExtractor {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String[] PRINCIPAL_KEYS = new String[] { "user", "username",
            "userid", "user_id", "login", "id", "name" };

    @Transactional
    public void saveSocialUser(String id, SocialProvider provider) {
        // 신규 가입
        UserEntity userEntity = userRepository.findByUsernameAndSns(id, provider);
        if(userEntity == null ) {
            userEntity = new UserEntity();
            userEntity.setUsername(id);
            userEntity.setSns(provider);
            // random password
            userEntity.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
        }
        userRepository.save(userEntity);
    }

    
    public Object createPrincipal(Map<String, Object> map) {

        // 저장 데이터 추출
        for (String key : PRINCIPAL_KEYS) {
            if (map.containsKey(key)) {
                return map.get(key);
            }
        }
        return null;
    }
}
