package uz.pdp.paymentsystemforcafe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.paymentsystemforcafe.service.OrderItemService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orderItem")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderItemById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(orderItemService.getAllOrderItemsById(id));

    }
}
