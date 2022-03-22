package com.social.facebook;

import com.example.SocialProvider;
import com.social.BasePrincipalExtractor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FacebookPrincipalExtractor extends BasePrincipalExtractor {

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        String id = map.get("id").toString();
        // 유저 정보 저장
        this.saveSocialUser(id, SocialProvider.FACEBOOK);
        // 유저 정보 SocialProvider 에서 추출.
        return this.createPrincipal(map);
    }
}
