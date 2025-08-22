package uz.pdp.paymentsystemforcafe.dto;

import lombok.*;


import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponseDto {
    private UUID id;
    private Integer count;
    private UUID orderId;
    private Integer productId;

}
