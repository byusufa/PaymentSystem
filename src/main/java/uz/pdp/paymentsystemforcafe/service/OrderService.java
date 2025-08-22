package uz.pdp.paymentsystemforcafe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.paymentsystemforcafe.dto.OrderItemRequestDto;
import uz.pdp.paymentsystemforcafe.dto.OrderItemResponseDto;
import uz.pdp.paymentsystemforcafe.dto.OrderResponseDto;
import uz.pdp.paymentsystemforcafe.projection.OrderPro;
import uz.pdp.paymentsystemforcafe.entity.Order;
import uz.pdp.paymentsystemforcafe.entity.OrderItem;
import uz.pdp.paymentsystemforcafe.entity.Product;
import uz.pdp.paymentsystemforcafe.repo.OrderRepository;
import uz.pdp.paymentsystemforcafe.repo.ProductRepository;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public OrderResponseDto addOrder(List<OrderItemRequestDto> orderItemRequestDto) {

        if (orderItemRequestDto == null || orderItemRequestDto.isEmpty()) {
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

        for (OrderItemRequestDto item : orderItemRequestDto) {
            OrderItem orderItem = new OrderItem();
            Product product = productRepository.findById(item.getId()).orElseThrow(()
                    -> new RuntimeException("Product not found"));
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItem.setCount(item.getCount());
            order.getOrderItems().add(orderItem);

        }

        Order saveOrder = orderRepository.save(order);
        return OrderResponseDto.builder()
                .id(saveOrder.getId())
                .orderNumber(saveOrder.getOrderNumber())
                .orderYear(saveOrder.getOrderYear())
                .orderMonth(saveOrder.getOrderMonth())
                .localDateTime(saveOrder.getLocalDateTime())
                .orderItems(
                        saveOrder.getOrderItems().stream()
                                .map(orderItem -> OrderItemResponseDto.builder()
                                        .id(orderItem.getId())
                                        .count(orderItem.getCount())
                                        .orderId(saveOrder.getId()) // yoki orderItem.getOrder().getId()
                                        .productId(orderItem.getProduct().getId())
                                        .build()
                                )
                                .toList()
                )
                .build();
    }

    public List<OrderPro> getAllOrders() {
        return orderRepository.getAllOrders();
    }
}
