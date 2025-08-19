package uz.pdp.paymentsystemforcafe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.paymentsystemforcafe.dto.CategoryRequestDto;
import uz.pdp.paymentsystemforcafe.dto.CategoryResponseDto;
import uz.pdp.paymentsystemforcafe.entity.Category;
import uz.pdp.paymentsystemforcafe.repo.CategoryRepository;
import uz.pdp.paymentsystemforcafe.repo.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDto> dtos = new ArrayList<>();
        for (Category category : categories) {
            CategoryResponseDto dto = new CategoryResponseDto();
            dto.setId(category.getId());
            dto.setName(category.getName());
            dtos.add(dto);
        }
        return dtos;

    }

    public CategoryResponseDto getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Category not found"));
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        return categoryResponseDto;

    }

    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        Category saveCategory = categoryRepository.save(category);
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(saveCategory.getId());
        categoryResponseDto.setName(saveCategory.getName());
        return categoryResponseDto;
    }

    public CategoryResponseDto deleteCategory(Integer id) {
        if (productRepository.existsByCategoryId(id)) {
            throw new IllegalStateException("Bu kategoriyani o‘chira olmaysiz. Unda mahsulotlar mavjud.");
        }
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bunday IDga ega kategoriya topilmadi."));
        categoryRepository.delete(category);
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(category.getId());
        responseDto.setName(category.getName());
        return responseDto;
    }


    public CategoryResponseDto updateCategory(Integer id, CategoryRequestDto categoryRequestDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Bunday idli category yo'q: " + id));
        category.setName(categoryRequestDto.getName());
        Category saveCategory = categoryRepository.save(category);
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(saveCategory.getId());
        categoryResponseDto.setName(saveCategory.getName());
        return categoryResponseDto;
    }

}
