package uz.pdp.paymentsystemforcafe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.paymentsystemforcafe.dto.UserDto;
import uz.pdp.paymentsystemforcafe.entity.Attachment;
import uz.pdp.paymentsystemforcafe.entity.AttachmentContent;
import uz.pdp.paymentsystemforcafe.entity.Role;
import uz.pdp.paymentsystemforcafe.entity.User;
import uz.pdp.paymentsystemforcafe.projection.UserPro;
import uz.pdp.paymentsystemforcafe.repo.AttachmentContentRepository;
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
    private final AttachmentContentRepository attachmentContentRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserPro> getAllUsers() {
        return userRepository.getAllUsersAndRoles();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
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
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAttachment(attachment);
        user.setRoles(new ArrayList<>(List.of(roles)));
        userRepository.save(user);
        return user;
    }

    public List<Role> getUserRoles() {
        return roleRepository.findAll();
    }

    public User deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found"));
        if (user.getAttachment() != null) {
            Attachment attachment = attachmentRepository.findById(user.getAttachment().getId()).orElseThrow(
                    () -> new IllegalArgumentException("Attachment not found"));
            AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId());
            attachmentContentRepository.delete(attachmentContent);
            attachmentRepository.delete(attachment);
        }
        userRepository.delete(user);
        return user;
    }

    public User updateUser(Integer id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role roles = roleRepository.findById(userDto.getRoleId()).orElseThrow(() -> new IllegalArgumentException("Role not found"));
        user.setRoles(new ArrayList<>(List.of(roles)));
        Attachment attachment = attachmentRepository.findById(userDto.getAttachmentId()).orElseThrow(
                () -> new IllegalArgumentException("Attachment not found"));
        user.setAttachment(attachment);
        userRepository.save(user);
        return user;
    }
}
