package uz.pdp.paymentsystemforcafe.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequestDto {
    private Integer id;
    private Integer count;
}
