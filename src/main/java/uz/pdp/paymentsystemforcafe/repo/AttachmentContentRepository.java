package uz.pdp.paymentsystemforcafe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.paymentsystemforcafe.entity.AttachmentContent;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {
    AttachmentContent findByAttachmentId(Integer attachmentId);
}