package uz.pdp.paymentsystemforcafe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.paymentsystemforcafe.dto.UserDto;
import uz.pdp.paymentsystemforcafe.entity.Attachment;
import uz.pdp.paymentsystemforcafe.entity.Role;
import uz.pdp.paymentsystemforcafe.entity.User;
import uz.pdp.paymentsystemforcafe.projection.UserPro;
import uz.pdp.paymentsystemforcafe.repo.AttachmentRepository;
import uz.pdp.paymentsystemforcafe.repo.RoleRepository;
import uz.pdp.paymentsystemforcafe.repo.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SuperAdminService {

    private final UserRepository userRepository;
    private final AttachmentRepository attachmentRepository;
    private final RoleRepository roleRepository;

    public List<UserPro> getAllUsers() {
        return userRepository.getAllUsersAndRoles();
    }


    public User addUsers(UserDto userDto) {
        Attachment attachment = attachmentRepository.findById(userDto.getAttachmentId()).orElseThrow(
                () -> new IllegalArgumentException("Attachment not found"));
        Role roles = roleRepository.findById(userDto.getRoleId()).orElseThrow(
                () -> new IllegalArgumentException("Role not found"));
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setAttachment(attachment);
        user.setRoles(new ArrayList<>(List.of(roles)));
        userRepository.save(user);
        return user;
    }

    public List<Role> getUserRoles() {
        return roleRepository.findAll();
    }


}
