package van.bookbookprovider.dao;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import van.bookbookprovider.entity.Category;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryMapperTest extends TestCase {
    @Resource
    private CategoryMapper categoryMapper;
    @Test
    public void test(){
       Category category= categoryMapper.selectById(1);
        assertNotNull(category);
    }
}