package uz.pdp.paymentsystemforcafe.dto;

import lombok.*;
import uz.pdp.paymentsystemforcafe.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private UUID id;
    private Integer orderNumber;
    private Integer orderYear;
    private Integer orderMonth;
    private List<OrderItemResponseDto> orderItems = new ArrayList<>();
    private LocalDateTime localDateTime;
}
