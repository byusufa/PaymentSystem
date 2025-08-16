package uz.pdp.paymentsystemforcafe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.paymentsystemforcafe.dto.UserDto;
import uz.pdp.paymentsystemforcafe.service.SuperAdminService;

@RestController
@RequestMapping("/api/super_admin")
@RequiredArgsConstructor
public class SuperAdminController {

    private final SuperAdminService superAdminService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(200).body(superAdminService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        return ResponseEntity.status(201).body(superAdminService.addUsers(userDto));
    }

    @GetMapping("/role")
    public ResponseEntity<?> getUserRoles() {
        return ResponseEntity.status(200).body(superAdminService.getUserRoles());
    }

}
