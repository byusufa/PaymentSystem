package uz.pdp.paymentsystemforcafe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.paymentsystemforcafe.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}