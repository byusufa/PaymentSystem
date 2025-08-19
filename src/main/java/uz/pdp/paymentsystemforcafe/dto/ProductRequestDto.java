package uz.pdp.paymentsystemforcafe.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    private String name;
    private Float price;
    private Boolean isActive;
    private Integer categoryId;
    private Integer attachmentId;
}
