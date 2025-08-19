package uz.pdp.paymentsystemforcafe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.paymentsystemforcafe.dto.ProductRequestDto;
import uz.pdp.paymentsystemforcafe.dto.ProductResponseDto;
import uz.pdp.paymentsystemforcafe.entity.Attachment;
import uz.pdp.paymentsystemforcafe.entity.Category;
import uz.pdp.paymentsystemforcafe.entity.Product;
import uz.pdp.paymentsystemforcafe.repo.AttachmentRepository;
import uz.pdp.paymentsystemforcafe.repo.CategoryRepository;
import uz.pdp.paymentsystemforcafe.repo.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;

    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for (Product product : products) {
            ProductResponseDto productResponseDto = ProductResponseDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .isActive(product.getIsActive())
                    .categoryId(product.getCategory().getId())
                    .categoryName(product.getCategory().getName())
                    .attachmentId(product.getAttachment().getId())
                    .build();
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }

    public List<ProductResponseDto> getAllProductsIsActive() {
        return productRepository.getAllProductsIsActive();
    }

    public ProductResponseDto getProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Product not found"));
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .isActive(product.getIsActive())
                .categoryId(product.getCategory().getId())
                .attachmentId(product.getAttachment().getId())
                .build();
    }

    public ProductResponseDto addProduct(ProductRequestDto productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .category(categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new NoSuchElementException("category not found")))
                .isActive(productDto.getIsActive())
                .attachment(attachmentRepository.findById(productDto.getAttachmentId()).orElseThrow(() -> new NoSuchElementException("attachment not found")))
                .build();

        Product saveProduct = productRepository.save(product);
        return ProductResponseDto.builder()
                .id(saveProduct.getId())
                .name(saveProduct.getName())
                .price(saveProduct.getPrice())
                .isActive(saveProduct.getIsActive())
                .categoryId(saveProduct.getCategory().getId())
                .attachmentId(saveProduct.getAttachment().getId())
                .build();
    }

    public ProductResponseDto deleteProduct(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("There is no category with id: " + id));
        productRepository.delete(product);
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .isActive(product.getIsActive())
                .categoryId(product.getCategory().getId())
                .attachmentId(product.getAttachment().getId())
                .build();
    }

    public ProductResponseDto updateProduct(Integer id, ProductRequestDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("There is no product with id: " + id));
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() ->
                new NoSuchElementException("category not found"));
        product.setCategory(category);
        product.setIsActive(productDto.getIsActive());

        Attachment attachment = attachmentRepository.findById(productDto.getAttachmentId()).orElseThrow(() ->
                new NoSuchElementException("attachment not found"));
        product.setAttachment(attachment);

        Product saveProduct = productRepository.save(product);
        return ProductResponseDto.builder()
                .id(saveProduct.getId())
                .name(saveProduct.getName())
                .price(saveProduct.getPrice())
                .isActive(saveProduct.getIsActive())
                .categoryId(saveProduct.getCategory().getId())
                .attachmentId(saveProduct.getAttachment().getId())
                .build();
    }

    public List<ProductResponseDto> getProductByCategoryId(Integer categoryId) {
        return productRepository.getProductsByCategoryId(categoryId);
    }

}
