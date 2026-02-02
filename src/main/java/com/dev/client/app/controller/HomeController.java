package com.dev.client.app.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public Map<String, Object> home(@AuthenticationPrincipal OidcUser principal) {
		if (principal == null) {
			return Collections.singletonMap("error", "User is not authenticated");
		}
		return principal.getAttributes();
	}

	@GetMapping("/user")
	public Map<String, Object> getUser(@AuthenticationPrincipal OidcUser principal) {
		if (principal == null) {
			return Collections.singletonMap("error", "Not Authenticated");
		}
		return principal.getClaims();
	}

}
