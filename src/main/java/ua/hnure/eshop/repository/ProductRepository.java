package ua.hnure.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.hnure.eshop.model.Product;

/**
 * Class repository for database
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product deleteByProductId(final Long id);

}
