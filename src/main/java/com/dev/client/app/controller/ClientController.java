package com.dev.client.app.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.client.app.dto.UserProfile;
import com.dev.client.app.service.ResourceServerService;

@RestController
public class ClientController {

    private final ResourceServerService resourceService;

    public ClientController(ResourceServerService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping("/fetch-resource")
    public String fetchData() {
        return resourceService.getSecureData();
    }
    
    @GetMapping("/show-profile")
    public String showData(Model model) {
    	
        UserProfile profile = resourceService.fetchActualUserData();
        
        return "User Name: " + profile.username() + ",\n Role: " + profile.role()
        +",\n status: " + profile.status() + ",\n Last Login: " + profile.lastLogin();
    }
}