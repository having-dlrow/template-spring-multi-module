package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

//@EnableResourceServer
@SpringBootApplication
public class ApiApplication {

//    @Bean
//    public ResourceServerConfigurerAdapter resourceServerConfigurerAdapter() {
//        return new ResourceServerConfigurerAdapter() {
//            @Override
//            public void configure(HttpSecurity http) throws Exception {
//                http.headers().frameOptions().disable();
//                http.authorizeRequests()
//                        .antMatchers("/members", "/members/**").access("#oauth2.hasScope('read')")
//                        .antMatchers("/", "/**").access("permitAll")
//                        .antMatchers("/h2-console/**").permitAll() // 추가
//                        .anyRequest().authenticated()
//                        .and()
//                    .formLogin()
//                        .and()
//                    .httpBasic();
//            }
//        };
//    }

//    /**
//     * API를 조회시 출력될 테스트 데이터
//     * @param memberRepository
//     * @return
//     */
//    @Bean
//    public CommandLineRunner commandLineRunner(MemberRepository memberRepository) {
//        return args -> {
//            memberRepository.save(new Member("이철수", "chulsoo", "test111"));
//            memberRepository.save(new Member("김정인", "jungin11", "test222"));
//            memberRepository.save(new Member("류정우", "jwryu991", "test333"));
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}

