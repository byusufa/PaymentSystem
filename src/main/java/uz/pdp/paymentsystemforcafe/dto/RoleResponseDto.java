package uz.pdp.paymentsystemforcafe.dto;

import lombok.*;
import uz.pdp.paymentsystemforcafe.enums.RoleName;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseDto {

    private Integer id;
    private RoleName roleName;

}
