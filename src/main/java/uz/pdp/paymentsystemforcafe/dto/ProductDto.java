package uz.pdp.paymentsystemforcafe.dto;

import lombok.*;
import uz.pdp.paymentsystemforcafe.entity.Category;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private Float price;
    private Boolean isActive;
    private Integer categoryId;
    private Integer attachmentId;
}
