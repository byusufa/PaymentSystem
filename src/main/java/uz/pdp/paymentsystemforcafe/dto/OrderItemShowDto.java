package uz.pdp.paymentsystemforcafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemShowDto {
    private String productName;
    private Float productPrice;
    private Integer productCount;
    private String createAt;
}

