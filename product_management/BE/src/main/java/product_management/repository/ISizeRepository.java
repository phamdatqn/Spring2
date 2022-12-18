package product_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import product_management.model.Size;

import java.util.List;

public interface ISizeRepository extends JpaRepository<Size, Integer> {
    List<Size> findAll();
}
