package uz.pdp.paymentsystemforcafe.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.paymentsystemforcafe.dto.LoginDto;
import uz.pdp.paymentsystemforcafe.entity.User;
import uz.pdp.paymentsystemforcafe.jwtservice.JwtService;
import uz.pdp.paymentsystemforcafe.jwtservice.TokenBlacklistService;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class LoginAndLogoutController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenBlacklistService blacklistService;

    public LoginAndLogoutController(AuthenticationManager authenticationManager, JwtService jwtService, TokenBlacklistService blacklistService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.blacklistService = blacklistService;
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authentication
                = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        User user = (User) authenticate.getPrincipal();
        String token = jwtService.generateToken(user);
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("roles", user.getRoles());
        return ResponseEntity.ok(response);

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // faqat token qismi
            blacklistService.add(token); // blacklistga qo‘shish
        }

        return ResponseEntity.ok("Logout successfully ✅");
    }


}
