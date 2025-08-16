package uz.pdp.paymentsystemforcafe.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.paymentsystemforcafe.entity.*;
import uz.pdp.paymentsystemforcafe.enums.RoleName;
import uz.pdp.paymentsystemforcafe.repo.CategoryRepository;
import uz.pdp.paymentsystemforcafe.repo.ProductRepository;
import uz.pdp.paymentsystemforcafe.repo.RoleRepository;
import uz.pdp.paymentsystemforcafe.repo.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlauto;

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(RoleRepository roleRepository, UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
        if (ddlauto.equals("create")) {

            Role role1 = Role.builder()
                    .roleName(RoleName.ROLE_SUPER_ADMIN)
                    .build();
            Role role2 = Role.builder()
                    .roleName(RoleName.ROLE_ADMIN)
                    .build();
            Role role3 = Role.builder()
                    .roleName(RoleName.ROLE_CASHIER)
                    .build();

            List<Role> roles = roleRepository.saveAll(new ArrayList<>(List.of(role1, role2, role3)));

            User user1 = User.builder()
                    .roles(new ArrayList<>(List.of(role1)))
                    .firstName("Eshmat")
                    .lastName("Toshmatov")
                    .username("a")
                    .password(passwordEncoder.encode("1"))
                    .build();
            User user2 = User.builder()
                    .roles(new ArrayList<>(List.of(role2)))
                    .firstName("Hikmat")
                    .lastName("Nusratov")
                    .username("b")
                    .password(passwordEncoder.encode("2"))
                    .build();
            User user3 = User.builder()
                    .roles(new ArrayList<>(List.of(role3)))
                    .firstName("Ali")
                    .lastName("Valiyev")
                    .username("c")
                    .password(passwordEncoder.encode("3"))
                    .build();
            userRepository.saveAll(new ArrayList<>(List.of(user1, user2, user3)));

            Category category1 = Category.builder().name("Osh").build();
            Category category2 = Category.builder().name("Sho'rva").build();
            Category category3 = Category.builder().name("Somsa").build();
            categoryRepository.saveAll(new ArrayList<>(List.of(category1, category2, category3)));

            Product product1 = Product.builder()
                    .name("To'y oshi")
                    .price(30000F)
                    .isActive(true)
                    .category(category1)
                    .attachment(null)
                    .build();
            Product product2 = Product.builder()
                    .name("No'xat Sho'rva")
                    .price(28000F)
                    .isActive(true)
                    .category(category2)
                    .attachment(null)
                    .build();
            Product product3 = Product.builder()
                    .name("Go'shtli somsa")
                    .price(5500F)
                    .isActive(true)
                    .category(category3)
                    .attachment(null)
                    .build();

            productRepository.saveAll(new ArrayList<>(List.of(product1, product2, product3)));

        }
    }
}
