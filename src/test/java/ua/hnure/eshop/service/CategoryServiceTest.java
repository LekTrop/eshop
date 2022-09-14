package ua.hnure.eshop.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static ua.hnure.eshop.service.TestData.CATEGORY;
import static ua.hnure.eshop.service.TestData.CATEGORY_ID;
import static ua.hnure.eshop.service.TestData.CATEGORY_OPTIONAL;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.hnure.eshop.model.Category;
import ua.hnure.eshop.repository.CategoryRepository;

/**
 * TODO: Change class description
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService unit;

    @Mock
    private CategoryRepository mockCategoryRepository;
    @Mock
    private ProductService mockProductService;

    @Test
    public void findById() {
        //GIVEN
        when(mockCategoryRepository.findById(CATEGORY_ID)).thenReturn(CATEGORY_OPTIONAL);

        //WHEN
        final Category category = unit.findById(CATEGORY_ID);

        //THEN
        Assertions.assertEquals(category, CATEGORY);

        verify(mockCategoryRepository).findById(CATEGORY_ID);
        verifyNoMoreInteractions(mockCategoryRepository);
    }
}
