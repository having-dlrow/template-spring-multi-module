package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private UserDetailsServiceImpl userDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 유저 인증정보를 설정 ( jdbc 에 인증정보를 연결 )
//        super.configure(auth);
//        auth.inMemoryAuthentication()
//                .withUser("user") // user 계정을 생성했다. 이부분에 로그인아이디가 된다.
//                .password(passwordEncoder().encode("1234")) // passwordEncoder 로 등록 한 인코더로 1234 를 암호화한다.
//                .roles("USER"); // 유저에게 USER 라는 역할을 제공한다.
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // static(html/js) 접근 처리
//        super.configure(web);
        web.ignoring().antMatchers("/css", "/js", "/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // api -> csrf 불가능 처리
        http.csrf().disable();
        // 보안 처리
        http
            .requestMatchers()
                .mvcMatchers("/login/**", "/logout/**", "/private/**", "/admin/**", "/")
            .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/private/**").hasAnyRole("USER")
                .anyRequest().permitAll()
            .and()
                .formLogin() // 로그인 form 사용.
                .usernameParameter("username") // username parameter
                .passwordParameter("password")  // password parameter
                .failureUrl("/login?error=true")
            .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
            .and()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler);
//                .accessDeniedPage("/denied");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}