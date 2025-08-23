package uz.pdp.paymentsystemforcafe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.paymentsystemforcafe.entity.User;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal User user) {

        return ResponseEntity.ok(Map.of(
                        "id", user.getId(),
                        "firstName", user.getFirstName(),
                        "attachmentId", user.getAttachment().getId()

                )
        );
    }

}
