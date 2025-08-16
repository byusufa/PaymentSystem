package uz.pdp.paymentsystemforcafe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.paymentsystemforcafe.projection.OrderItemShowPro;
import uz.pdp.paymentsystemforcafe.entity.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

    @Query(value = """
            select p.name as productName, p.price as productPrice, oi.count as productCount, 
            to_char(o.local_date_time,'yyyy-MM-dd HH:MI') as createdAt from order_item oi join 
            product p on oi.product_id = p.id join orders o on oi.order_id = o.id where oi.order_id = :id
            """, nativeQuery = true)
    List<OrderItemShowPro> getOrderItemsByOrderId(@Param("id") UUID id);

}