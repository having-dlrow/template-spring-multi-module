package com.example;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserEntity> findUsers = em.createQuery("select v from UserEntity v where v.username=:username", UserEntity.class)
                .setParameter("username", username)
                .getResultList();

        System.out.println(findUsers);

        if (findUsers.size() == 0) {
            throw new UsernameNotFoundException("검색하신 유저를 찾을 수 없습니다.");
        }

        // 1명만 검색 하므로
        UserEntity userEntity = findUsers.get(0);

        // 권한 목록 만들기
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (username.equals("admin")) {
            GrantedAuthority adminRole = new SimpleGrantedAuthority("ROLE_ADMIN");
            authorities.add(adminRole);
        }
            GrantedAuthority role = new SimpleGrantedAuthority("ROLE_USER");
            authorities.add(role);

        // 1명에 권한 넣어서 반환.
        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }
}
