package van.bookbookprovider.service.serviceImpl;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import van.bookbookprovider.dao.CategoryMapper;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest extends TestCase {
    private CategoryMapper categoryMapper;
@Before
    public void setUp() throws Exception {
        this.categoryMapper= Mockito.mock(CategoryMapper.class);
    }
    @Test
    public void test(){

    }
}