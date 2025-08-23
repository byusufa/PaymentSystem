package uz.pdp.paymentsystemforcafe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.paymentsystemforcafe.dto.RoleResponseDto;
import uz.pdp.paymentsystemforcafe.dto.UserRequestDto;
import uz.pdp.paymentsystemforcafe.dto.UserResponseDto;
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

    public UserResponseDto getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found"));

        return UserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .attachmentId(user.getAttachment().getId())
                .roleResDtos(
                        user.getRoles().stream()
                                .map(role -> RoleResponseDto.builder()
                                        .id(role.getId())
                                        .roleName(role.getRoleName())
                                        .build()
                                )
                                .toList()
                )

                .build();
    }


    public UserResponseDto addUsers(UserRequestDto userRequestDto) {
        Attachment attachment = attachmentRepository.findById(userRequestDto.getAttachmentId()).orElseThrow(
                () -> new IllegalArgumentException("Attachment not found"));
        List<Role> roles = roleRepository.findAllById(userRequestDto.getRoleIds());
        User user = new User();
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setAttachment(attachment);
        user.setRoles(roles);
        User saveUser = userRepository.save(user);

        return UserResponseDto.builder()
                .id(saveUser.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(saveUser.getUsername())
                .password(saveUser.getPassword())
                .attachmentId(saveUser.getAttachment().getId())
                .roleResDtos(
                        saveUser.getRoles().stream()
                                .map(role -> RoleResponseDto.builder()
                                        .id(role.getId())
                                        .roleName(role.getRoleName())
                                        .build()
                                )
                                .toList()
                )
                .build();
    }

    public List<RoleResponseDto> getUserRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(
                role ->
                        RoleResponseDto.builder()
                                .id(role.getId())
                                .roleName(role.getRoleName())
                                .build()

        ).toList();
    }

    public UserResponseDto deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found"));
        Attachment attachment = attachmentRepository.findById(user.getAttachment().getId()).orElseThrow(
                () -> new IllegalArgumentException("Attachment not found"));
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId());
        attachmentContentRepository.delete(attachmentContent);
        userRepository.delete(user);
        attachmentRepository.delete(attachment);
        return UserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .attachmentId(attachment.getId())
                .roleResDtos(user.getRoles().stream().map(
                                role -> RoleResponseDto.builder()
                                        .id(role.getId())
                                        .roleName(role.getRoleName())
                                        .build()
                        ).toList()
                )
                .build();
    }

    public UserResponseDto updateUser(Integer id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        List<Role> roles1 = roleRepository.findAllById(userRequestDto.getRoleIds());
        user.setRoles(roles1);
        Attachment attachment = attachmentRepository.findById(userRequestDto.getAttachmentId()).orElseThrow(
                () -> new IllegalArgumentException("Attachment not found"));
        user.setAttachment(attachment);
        User updateUser = userRepository.save(user);
        return UserResponseDto.builder()
                .id(user.getId())
                .firstName(updateUser.getFirstName())
                .lastName(updateUser.getLastName())
                .username(updateUser.getUsername())
                .password(updateUser.getPassword())
                .attachmentId(updateUser.getAttachment().getId())
                .roleResDtos(updateUser.getRoles().stream().map(
                                role -> RoleResponseDto.builder()
                                        .id(role.getId())
                                        .roleName(role.getRoleName())
                                        .build()
                        ).toList()
                )
                .build();

    }
}
