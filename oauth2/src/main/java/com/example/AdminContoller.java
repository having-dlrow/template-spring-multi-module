package com.example;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminContoller {

    @GetMapping(value = "/")
    public String home() {
        return "index";
    }

    @GetMapping(value = "/private")
    public String privatePage(@AuthenticationPrincipal Authentication authentication, Model model) {
        boolean isOauth2User = authentication instanceof OAuth2Authentication;
        model.addAttribute("oauth2User", isOauth2User);
        if (!isOauth2User) {
            model.addAttribute("user", authentication);
        } else {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;

            model.addAttribute("sUser", oAuth2Authentication.getUserAuthentication().getDetails());
        }
        return "private";
    }

    @GetMapping(value = "/public")
    public String publicPage() {
        return "public";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/admin")
    public String privatePage() {
        return "admin";
    }

    @GetMapping(value = "/denied")
    public String deniedPage() {
        return "404";
    }

    @GetMapping(value = "/api/login")
    public String oauthLogin() {
        return "oauth_login";
    }

    @GetMapping(value = "/private/context")
    public String privateContextPage(
            @AuthenticationPrincipal Authentication authentication
    ) {

        SecurityContextHolder.getContext().getAuthentication(); // Authentication 을 반환

        System.out.println(authentication.getPrincipal());

        return "private";
    }
}

