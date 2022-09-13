package ua.hnure.eshop.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Model that define a basic Product in EShop0
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Table(name = "products")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Column(name = "available_count", nullable = false)
    private Integer availableCount = 0;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_products",
            joinColumns = @JoinColumn(name = "product_fk"),
            inverseJoinColumns = @JoinColumn(name = "category_fk"))
    private Set<Category> categories = new HashSet<>();

    @PrePersist
    public void prePersist() {
        this.createdDate = Instant.now();
    }
}
