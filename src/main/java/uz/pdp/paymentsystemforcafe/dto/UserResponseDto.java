package uz.pdp.paymentsystemforcafe.dto;

import lombok.*;
import uz.pdp.paymentsystemforcafe.entity.Attachment;
import uz.pdp.paymentsystemforcafe.entity.Role;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Integer attachmentId;
    private List<RoleResponseDto> roleResDtos;

}
