package van.bookbookprovider.service;



import base.ServerResponse;
import van.bookbookprovider.entity.Category;
import van.bookbookprovider.vo.CategoryVO;

import java.util.List;

/**
 * @author Van
 * @date 2020/3/16 - 11:24
 */
public interface CategoryService {
    int findNumberOfCategroy(int categoryId);

    ServerResponse<List<CategoryVO>> list();

    void update(Category category);
    void delete(Integer categoryId);
}
