package com.imdbv1.imdbbackend.userops;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String message;
        System.out.println(user.toString());
        message = userService.addUser(user);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody UserCredentials userCredentials, HttpServletResponse response) {
        System.out.println("Attempting login with credentials: " + userCredentials);

        boolean isAuthenticated = userService.authenticate(userCredentials);

        if (isAuthenticated) {
            logger.info("email from credantials {}",userCredentials.getEmail());

            User authenticatedUser = userService.findByEmail(userCredentials.getEmail());
            

            
            
            Cookie cookie = new Cookie("session_id", generateSessionId());
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60); // one day
            response.addCookie(cookie);
            
            return ResponseEntity.ok(authenticatedUser);  // Return user information
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    private String generateSessionId() {
        return UUID.randomUUID().toString();
    }

    @GetMapping("/user")
    public OidcUser user(@AuthenticationPrincipal OidcUser principal) {
        return principal;
    }

    @GetMapping("/user/me")
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        Map<String, Object> userInfo = new HashMap<>();
        if (principal != null) {
            userInfo.put("email", principal.getAttribute("email"));
            userInfo.put("name", principal.getAttribute("name"));
            // Log to see what details are available
            System.out.println("User details: " + userInfo);
        } else {
            System.out.println("No principal details found.");
        }
        return userInfo;
}

@PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidate the session and remove the session cookie
        Cookie cookie = new Cookie("session_id", null); // Set cookie value to null
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // 0 means delete the cookie now, alternatively -1 means no expiry
        response.addCookie(cookie);

        request.getSession().invalidate(); // Invalidate session if any
        logger.info("User logged out successfully.");

        return ResponseEntity.ok("Logged out successfully");
    }

}
