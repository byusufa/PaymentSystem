package uz.pdp.paymentsystemforcafe.controller;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.paymentsystemforcafe.entity.Attachment;
import uz.pdp.paymentsystemforcafe.entity.AttachmentContent;
import uz.pdp.paymentsystemforcafe.repo.AttachmentContentRepository;
import uz.pdp.paymentsystemforcafe.repo.AttachmentRepository;

import java.io.IOException;

@RequestMapping("/api/file")
@RestController
@RequiredArgsConstructor
@MultipartConfig
public class AttachmentController {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    @GetMapping("/{attachmentId}")
    public void getFile(@PathVariable Integer attachmentId, HttpServletResponse response) throws IOException {
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(attachmentId);
        response.getOutputStream().write(attachmentContent.getContent());
    }

    @PostMapping
    public Integer uploadFile(@RequestParam MultipartFile file) throws IOException {
        Attachment attachment = Attachment.builder()
                .file(file.getOriginalFilename())
                .build();
        attachmentRepository.save(attachment);
        AttachmentContent attachmentContent = AttachmentContent.builder().
                content(file.getBytes())
                .attachment(attachment)
                .build();
        attachmentContentRepository.save(attachmentContent);
        return attachment.getId();
    }


}
