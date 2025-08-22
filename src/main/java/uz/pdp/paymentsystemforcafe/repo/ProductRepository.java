package uz.pdp.paymentsystemforcafe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.paymentsystemforcafe.dto.ProductResponseDto;
import uz.pdp.paymentsystemforcafe.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByCategoryId(Integer categoryId);

    @Query(value = "select * from product p where p.category_id = :categoryId and p.is_active = true ", nativeQuery = true)
    List<Product> getProductsByCategoryId(@Param("categoryId") Integer categoryId);

    @Query(value = "select * from product p where p.is_active=true", nativeQuery = true)
    List<Product> getAllProductsIsActive();

}