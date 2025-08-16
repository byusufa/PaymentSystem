package uz.pdp.paymentsystemforcafe.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "orders", uniqueConstraints = {@UniqueConstraint(columnNames = {"order_number", "order_year", "order_month"})})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "order_number", nullable = false)
    private Integer orderNumber;
    @Column(name = "order_year", nullable = false)
    private Integer orderYear;
    @Column(name = "order_month", nullable = false)
    private Integer orderMonth;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> orderItems = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime localDateTime;
}
