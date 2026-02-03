package com.dev.client.app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.dev.client.app.dto.UserProfile;

@Service
public class ResourceServerService {

    private final WebClient webClient;

    public ResourceServerService(WebClient webClient) {
        this.webClient = webClient;
    }

    public UserProfile fetchActualUserData() {
        return webClient.get()
                .uri("http://127.0.0.1:8081/api/secure/user-profile")
                .retrieve()
                .bodyToMono(UserProfile.class) // JSON ko Java Object mein badal raha hai
                .block(); 
    }
    
    public String getSecureData() {
        return webClient.get()
                .uri("http://127.0.0.1:8081/api/secure/hello")
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Synchronous call ke liye
    }
}