package uz.pdp.paymentsystemforcafe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.paymentsystemforcafe.dto.CategoryRequestDto;
import uz.pdp.paymentsystemforcafe.service.CategoryService;


@RequestMapping("/api/category")
@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategory() {
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(categoryService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.status(201).body(categoryService.addCategory(categoryRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(categoryService.deleteCategory(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.status(200).body(categoryService.updateCategory(id, categoryRequestDto));
    }


}
