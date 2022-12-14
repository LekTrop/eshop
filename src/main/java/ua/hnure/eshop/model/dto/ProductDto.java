package ua.hnure.eshop.model.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.hnure.eshop.model.Category;
import ua.hnure.eshop.model.Product;

/**
 * Represent DTO entity for {@link Product}
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long productId;
    private String name;
    private String description;
    private Instant createdDate;
    private BigDecimal price;
    private Integer availableCount;
}
