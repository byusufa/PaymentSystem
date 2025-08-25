package uz.pdp.paymentsystemforcafe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.paymentsystemforcafe.dto.ProductRequestDto;
import uz.pdp.paymentsystemforcafe.service.ProductService;

@RequestMapping("/api/product")
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    @GetMapping("/isActive")
    public ResponseEntity<?> getAllProductsIsActive() {
        return ResponseEntity.status(200).body(productService.getAllProductsIsActive());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.status(201).body(productService.addProduct(productRequestDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(productService.deleteProduct(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.status(200).body(productService.updateProduct(id, productRequestDto));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getProductsByCategoryId(@PathVariable Integer categoryId) {
        if (categoryId == null) {
            return ResponseEntity.status(200).body(productService.getAllProducts());
        } else {
            return ResponseEntity.status(200).body(productService.getProductByCategoryId(categoryId));
        }
    }
}
