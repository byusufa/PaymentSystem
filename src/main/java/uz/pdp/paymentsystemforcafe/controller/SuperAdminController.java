package uz.pdp.paymentsystemforcafe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.paymentsystemforcafe.dto.UserRequestDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(superAdminService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserRequestDto userRequestDto) {
        System.out.println(userRequestDto);
        return ResponseEntity.status(201).body(superAdminService.addUsers(userRequestDto));
    }

    @GetMapping("/role")
    public ResponseEntity<?> getUserRoles() {
        return ResponseEntity.status(200).body(superAdminService.getUserRoles());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        System.out.println(id);
        return ResponseEntity.status(200).body(superAdminService.deleteUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserRequestDto userRequestDto) {
        System.out.println(userRequestDto);
        return ResponseEntity.status(200).body(superAdminService.updateUser(id, userRequestDto));
    }


}
