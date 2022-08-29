package ua.hnure.eshop.model.mapper;

import org.mapstruct.Mapper;
import ua.hnure.eshop.model.Category;
import ua.hnure.eshop.model.dto.CategoryDto;

@Mapper(config = MapperConfiguration.class, uses = ProductMapper.class)
public interface CategoryMapper {

    Category toDomain(final CategoryDto categoryDto);

    CategoryDto toDto(final Category category);
}
