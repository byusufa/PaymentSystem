package uz.pdp.paymentsystemforcafe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.paymentsystemforcafe.dto.ProductDto;
import uz.pdp.paymentsystemforcafe.entity.Product;
import uz.pdp.paymentsystemforcafe.repo.AttachmentRepository;
import uz.pdp.paymentsystemforcafe.repo.CategoryRepository;
import uz.pdp.paymentsystemforcafe.repo.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsIsActive() {
        return productRepository.getAllProductsIsActive();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Product not found"));
    }

    public Product addProduct(ProductDto productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .category(categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new NoSuchElementException("category not found")))
                .isActive(productDto.getIsActive())
                .attachment(attachmentRepository.findById(productDto.getAttachmentId()).orElseThrow(() -> new NoSuchElementException("attachment not found")))
                .build();

        return productRepository.save(product);
    }

    public Product deleteProduct(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("There is no category with id: " + id));
        productRepository.delete(product);
        return product;
    }

    public Product updateProduct(Integer id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("There is no product with id: " + id));
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() ->
                new NoSuchElementException("category not found")));
        product.setIsActive(productDto.getIsActive());
        product.setAttachment(attachmentRepository.findById(productDto.getAttachmentId()).orElseThrow(() ->
                new NoSuchElementException("attachment not found")));
        return productRepository.save(product);
    }

    public List<Product> getProductByCategoryId(Integer categoryId) {
        return productRepository.getProductsByCategoryId(categoryId);
    }

}
