package uz.pdp.paymentsystemforcafe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.paymentsystemforcafe.projection.OrderPro;
import uz.pdp.paymentsystemforcafe.entity.Order;
import uz.pdp.paymentsystemforcafe.projection.ReportPro;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query(value = "select max(order_number) from orders where extract(year from local_date_time) = :year and extract(month from local_date_time) = :month", nativeQuery = true)
    Optional<Integer> findMaxOrderNumberByMonth(@Param("year") int year, @Param("month") int month);

    @Query(value = """
            select o.id,o.order_number,sum(oi.count) as count,sum(oi.count*p.price) as totalPrice, to_char(o.local_date_time,'yyyy-MM-dd HH:MI') as createdAt
            from order_item oi join orders o on o.id = oi.order_id join product p on p.id = oi.product_id
            group by o.id ,o.order_number, o.local_date_time order by o.order_number asc 
            """, nativeQuery = true)
    List<OrderPro> getAllOrders();

    @Query(value = """
            select p.name as productName,p.price as productPrice, sum(oi.count) as productCount, sum(oi.count * p.price) as totalPrice,
                   to_char(local_date_time,'yyyy-MM-dd HH24:MI') as createdAt from orders o join order_item oi on oi.order_id = o.id
                     join product p on oi.product_id = p.id where o.local_date_time between :startDate AND :endDate
            group by p.name, p.price, to_char(local_date_time,'yyyy-MM-dd HH24:MI')
            """, nativeQuery = true)
    List<ReportPro> getAllReports(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


}
