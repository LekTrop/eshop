package ua.hnure.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.hnure.eshop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
