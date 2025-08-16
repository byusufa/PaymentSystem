package uz.pdp.paymentsystemforcafe.service;

import org.springframework.stereotype.Service;
import uz.pdp.paymentsystemforcafe.projection.ReportPro;
import uz.pdp.paymentsystemforcafe.repo.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    private final OrderRepository orderRepository;

    public ReportService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<ReportPro> getAllReports(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.getAllReports(startDate, endDate);
    }

}
