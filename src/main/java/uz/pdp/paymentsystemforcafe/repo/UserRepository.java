package uz.pdp.paymentsystemforcafe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.paymentsystemforcafe.entity.Product;
import uz.pdp.paymentsystemforcafe.entity.User;
import uz.pdp.paymentsystemforcafe.projection.UserPro;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    @Query(value = """
            select u.id,u.attachment_id as attachmentId,u.first_name || ' ' || u.last_name as fullName, r.role_name
            from users u join users_roles ur on u.id = ur.user_id join roles r on ur.roles_id = r.id
            """, nativeQuery = true)
    List<UserPro> getAllUsersAndRoles();

}