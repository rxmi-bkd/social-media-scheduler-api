package org.bkd.social_media_scheduler_api.oauth2.frameworks;


public record Platform(String clientId, String clientSecret, String scope, String redirectUri, String responseType,
                       String grantType, String authorizationUri, String tokenUri) {

}

