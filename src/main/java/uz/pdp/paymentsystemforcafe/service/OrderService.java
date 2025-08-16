package uz.pdp.paymentsystemforcafe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.paymentsystemforcafe.dto.OrderItemDto;
import uz.pdp.paymentsystemforcafe.projection.OrderPro;
import uz.pdp.paymentsystemforcafe.entity.Order;
import uz.pdp.paymentsystemforcafe.entity.OrderItem;
import uz.pdp.paymentsystemforcafe.entity.Product;
import uz.pdp.paymentsystemforcafe.repo.OrderRepository;
import uz.pdp.paymentsystemforcafe.repo.ProductRepository;

import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public Order addOrder(List<OrderItemDto> orderItemDto) {
        if (orderItemDto == null || orderItemDto.isEmpty()) {
            throw new RuntimeException("Savatcha bo‘sh. Order yaratib bo‘lmaydi.");
        }

        YearMonth currentMonth = YearMonth.now();
        int year = currentMonth.getYear();
        int month = currentMonth.getMonthValue();
        Integer maxOrderNumber = orderRepository.findMaxOrderNumberByMonth(year, month).orElse(0);
        Order order = new Order();
        order.setOrderNumber(maxOrderNumber + 1);
        order.setOrderYear(year);
        order.setOrderMonth(month);

        for (OrderItemDto item : orderItemDto) {
            OrderItem orderItem = new OrderItem();
            Product product = productRepository.findById(item.getId()).orElseThrow(()
                    -> new RuntimeException("Product not found"));
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItem.setCount(item.getCount());
            order.getOrderItems().add(orderItem);

        }

        return orderRepository.save(order);

    }

    public List<OrderPro> getAllOrders() {
        return orderRepository.getAllOrders();
    }
}
