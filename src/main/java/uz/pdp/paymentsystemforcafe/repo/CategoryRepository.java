package uz.pdp.paymentsystemforcafe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.paymentsystemforcafe.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}