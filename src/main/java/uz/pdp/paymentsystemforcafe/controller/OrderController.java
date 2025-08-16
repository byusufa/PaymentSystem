package uz.pdp.paymentsystemforcafe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.paymentsystemforcafe.dto.OrderItemDto;
import uz.pdp.paymentsystemforcafe.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody List<OrderItemDto> orderItemDto) {
        return ResponseEntity.status(201).body(orderService.addOrder(orderItemDto));
    }

    @GetMapping
    public ResponseEntity<?> getOrders() {
        return ResponseEntity.status(200).body(orderService.getAllOrders());

    }
}
