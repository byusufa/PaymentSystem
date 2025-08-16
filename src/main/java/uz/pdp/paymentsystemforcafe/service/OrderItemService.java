package uz.pdp.paymentsystemforcafe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.paymentsystemforcafe.projection.OrderItemShowPro;
import uz.pdp.paymentsystemforcafe.repo.OrderItemRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public List<OrderItemShowPro> getAllOrderItemsById(UUID id) {
        return orderItemRepository.getOrderItemsByOrderId(id);
    }
}
