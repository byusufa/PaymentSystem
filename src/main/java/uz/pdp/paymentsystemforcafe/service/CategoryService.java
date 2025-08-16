package uz.pdp.paymentsystemforcafe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.paymentsystemforcafe.dto.CategoryDto;
import uz.pdp.paymentsystemforcafe.entity.Category;
import uz.pdp.paymentsystemforcafe.repo.CategoryRepository;
import uz.pdp.paymentsystemforcafe.repo.ProductRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Category not found"));
    }

    public Category addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return categoryRepository.save(category);
    }

    public Category deleteCategory(Integer id) {
        boolean hasProduct = productRepository.existsByCategoryId(id);
        if (hasProduct) {
            throw new RuntimeException("Bu kategoriyani o‘chira olmaysiz. Unda mahsulotlar mavjud.");
        }

        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Bunday IDga ega kategoriya topilmadi."));
        categoryRepository.delete(category);
        return category;
    }

    public Category updateCategory(Integer id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Bunday idli category yo'q: " + id));
        category.setName(categoryDto.getName());
        return categoryRepository.save(category);
    }

}
