package uz.pdp.paymentsystemforcafe.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Float price;
    private Boolean isActive;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Attachment attachment;
}
