package van.bookbookprovider.service.serviceImpl;

import base.Const;
import base.ServerResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import utils.JsonUtil;
import utils.LocalCacheUtil;
import utils.RedisPoolUtil;
import van.bookbookprovider.dao.BookMapper;
import van.bookbookprovider.dao.CategoryMapper;
import van.bookbookprovider.entity.Book;
import van.bookbookprovider.entity.Category;
import van.bookbookprovider.service.CategoryService;
import van.bookbookprovider.vo.CategoryVO;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Van
 * @date 2020/3/16 - 11:25
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private BookMapper bookMapper;
    @Resource
    private CategoryMapper categoryMapper;

    @PostConstruct
    public void warmUpCategoryTree() {
        LocalCacheUtil cacheUtil = new LocalCacheUtil();
        Map map = new HashMap();
        List<Category> categoryList = categoryMapper.selectByMap(map);
        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (Category categoryItem : categoryList) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCover(categoryItem.getCover());
            categoryVO.setCategory(categoryItem.getCategory());
            categoryVO.setCategoryText(categoryItem.getCategoryText());
            categoryVO.setCover2(categoryItem.getCover2());
            categoryVO.setNum(findNumberOfCategroy(categoryItem.getCategory()));
            categoryVOList.add(categoryVO);
        }
        String categoryCache = JsonUtil.object2String(categoryVOList);
        RedisPoolUtil.set(Const.CACHE_CATEGORY, categoryCache);
        cacheUtil.put(Const.CACHE_CATEGORY, categoryCache);
    }

    //find how much number of books a category contains
    public int findNumberOfCategroy(int categoryId) {
        Map map = new HashMap();
        map.put("category", categoryId);
        List<Book> bookList = bookMapper.selectByMap(map);
        return bookList.size();
    }

    //select all category
    public ServerResponse<List<CategoryVO>> list() {
        //先从本地缓存找，找不到从redis中找，redis还找不到才返回null
        LocalCacheUtil cacheUtil = new LocalCacheUtil();
        String categoryCache = (String) cacheUtil.get(Const.CACHE_CATEGORY);
        if (categoryCache != null) {
            return ServerResponse.success("查询成功", JsonUtil.string2Object(categoryCache, new TypeReference<List<CategoryVO>>() {
            }));
        }
        //name:(key:categoryCache,value:(String)categoryVOList)
        //redis is not exist
        Map map = new HashMap();
        List<Category> categoryList = categoryMapper.selectByMap(map);
        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (Category categoryItem : categoryList) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCover(categoryItem.getCover());
            categoryVO.setCategory(categoryItem.getCategory());
            categoryVO.setCategoryText(categoryItem.getCategoryText());
            categoryVO.setCover2(categoryItem.getCover2());
            categoryVO.setNum(findNumberOfCategroy(categoryItem.getCategory()));
            categoryVOList.add(categoryVO);
        }
        categoryCache = JsonUtil.object2String(categoryVOList);
        RedisPoolUtil.set(Const.CACHE_CATEGORY, categoryCache);
        cacheUtil.put(Const.CACHE_CATEGORY, categoryCache);
        return ServerResponse.success("查询成功", categoryVOList);
    }


}
