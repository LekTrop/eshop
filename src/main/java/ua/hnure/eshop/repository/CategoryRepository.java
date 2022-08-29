package ua.hnure.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.hnure.eshop.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
