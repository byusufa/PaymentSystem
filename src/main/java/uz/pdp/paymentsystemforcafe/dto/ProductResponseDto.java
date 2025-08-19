package uz.pdp.paymentsystemforcafe.dto;

import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.paymentsystemforcafe.entity.Attachment;
import uz.pdp.paymentsystemforcafe.entity.Category;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {
    private Integer id;
    private String name;
    private Float price;
    private Boolean isActive;
    private Integer categoryId;
    private String categoryName;
    private Integer attachmentId;
}
