package van.bookbookprovider.service.serviceImpl;

import base.CodeMsg;
import base.Const;
import base.ServerResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import van.bookbookprovider.convert.AssembleVOUtil;
import utils.RandomUtil;
import van.bookbookprovider.dao.BookMapper;
import van.bookbookprovider.entity.Book;
import van.bookbookprovider.entity.History;
import van.bookbookprovider.service.BookService;
import van.bookbookprovider.vo.*;
import van.bookuserprovider.service.serviceImpl.UserServiceImpl;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Van
 * @date 2020/3/16 - 12:02
 */
@Slf4j
@Service
public class BookServiceImpl implements BookService {
    @Resource
    private CategoryServiceImpl categoryService;
    @Resource
    private ShelfServiceImpl shelfService;
    @Resource
    private AssembleVOUtil assembleVOUtil;
    @Resource
    private RankServiceImpl rankService;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private HotSearchServiceImpl hotSearchService;
    @Resource
    private HistoryServiceImpl historyService;
    @Resource
    private UserServiceImpl userService;
    @Resource
    private HotBookServiceImpl hotBookService;

    public List<Book> selectBooks(List<String> fileNameList) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("file_name", fileNameList);
        List<Book> bookList = bookMapper.selectList(queryWrapper);
        return bookList;
    }

    public ServerResponse selectBookDetailV2(String fileName) {
        Map map = new HashMap();
        map.put("file_name", fileName);
        List<Book> bookList = bookMapper.selectByMap(map);
        if (bookList.size() == 0) {
            throw new GlobalException(CodeMsg.BOOK_NOT_EXIST);
        }
        BookVO2 bookVO2 = assembleVOUtil.assembleBookVO2(bookList.get(0));
        return ServerResponse.success("获取成功", bookVO2);
    }

    public Book selectBookByFileName(String fileName) {
        Map map = new HashMap();
        map.put("file_name", fileName);
        List<Book> bookList = bookMapper.selectByMap(map);
        if (bookList.size() > 0) {
            return bookList.get(0);
        } else {
            return null;
        }
    }

    public ServerResponse getDetail(String openId, String fileName) {
        Book book = selectBookByFileName(fileName);
        if (openId != null) {
            History history = new History();
            history.setOpenId(openId);
            history.setFileName(fileName);
            historyService.insert(history);
        }
        BookVO bookVO = assembleVOUtil.assembleBookVO(book, openId);
        if (bookVO == null) {
            return ServerResponse.error("获取失败,查无此书");
        } else {
            return ServerResponse.success("获取成功", bookVO);
        }
    }

    //get random books
    public ServerResponse<List<BookSimplyVO>> recomment() {
        Set<Integer> books = RandomUtil.getRandomSet(Const.MAXBOOKS, 1, 3);
        List<BookSimplyVO> bookList = new ArrayList<>();
        for (Integer bookId : books) {
            Book book = bookMapper.selectById(bookId);
            bookList.add(AssembleVOUtil.assembleBookSimplyVO(book));
        }
        return ServerResponse.success("查询成功", bookList);

    }

    public ServerResponse<List<BookSimplyVO>> recommendV2(String openId) {
        //if openId is null, then recommend three random books
        if (openId == null) {
            return recomment();
        }
        List<Book> bookList = hotBookService.hotBookThree(openId);
        //if openId is not null,but he don't have history
        if (bookList == null) {
            return recomment();
        }
        //the user have history, default history is three ,but it can be less
        List<BookSimplyVO> hotBookList = new ArrayList<>();
        int i = 4;
        for (Book book : bookList) {
            List<Book> bookList1 = rankService.getHighRankBookByCategory(book.getCategory(), 5, --i);
            //if this category don't have Book which rank is 5 then continue
            if (bookList1.size() == 0) {
                continue;
            }
            //if have high rank book then add to list
            for (Book book1 : bookList1) {
                hotBookList.add(AssembleVOUtil.assembleBookSimplyVO(book1));
            }
        }
        return ServerResponse.success("获取成功", hotBookList);
    }

    //select three books which rank is 5 score
    public ServerResponse<List<BookSimplyVO>> hotBook() {
        //get books which score is 5
        List<Book> bookList = rankService.getHighRankBook(5);
        //................................
        List<BookSimplyVO> bookSimplyVOList = new ArrayList<>();
        //bookList have only three books
        for (Book book : bookList) {
            BookSimplyVO bookSimplyVO = AssembleVOUtil.assembleBookSimplyVO(book);
            bookSimplyVOList.add(bookSimplyVO);
        }
        return ServerResponse.success("查询成功", bookSimplyVOList);
    }

    //select page
    public ServerResponse search(String keyword, int page, int pageSize) {
        SearchVO searchVO = new SearchVO();
        searchVO.setAuthor(selectAuthorByKeyword(keyword, 1, 1));
        searchVO.setBook(selectBookByKeyword(keyword, 1, 10));
        searchVO.setCategory(selectCategoryByKeyword(keyword, 1, 1));
        searchVO.setPublisher(selectPublisherByKeyword(keyword, 1, 1));
        return ServerResponse.success("查询成功", searchVO);
    }

    //select by publisher category categoryId author
    public ServerResponse searchList(String publisher, String category, Integer categoryId, String author, int page, int pageSize) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper();
        if (publisher != null) {
            queryWrapper.like("publisher", publisher);
        }
        if (category != null) {
            queryWrapper.like("category_text", category);
        }
        if (categoryId != null) {
            queryWrapper.eq("category", categoryId);
        }
        if (author != null) {
            queryWrapper.like("author", author);
        }
        Page<Book> page1 = new Page<>(page, pageSize);
        IPage iPage = bookMapper.selectPage(page1, queryWrapper);
        List<Book> bookList = iPage.getRecords();
        List<BookSimplyVO> bookSimplyVOList = new ArrayList<>();
        for (Book book : bookList) {
            BookSimplyVO bookSimplyVO = AssembleVOUtil.assembleBookSimplyVO(book);
            bookSimplyVOList.add(bookSimplyVO);
        }
        return ServerResponse.success("查询成功", bookSimplyVOList);
    }

    public ServerResponse getHomeData(String openId) {
        HomeVO homeVO = new HomeVO();
        //shelf
        List<ShelfVO> shelfVOList = new ArrayList<>();
        if (openId == null) {
            shelfVOList = null;
        } else {
            try {
                shelfVOList = shelfService.get(null, openId).getData();
            } catch (Exception e) {
                shelfVOList = null;
            }
        }
        homeVO.setShelf(shelfVOList);
        //category
        List<CategoryVO> categoryVOList = categoryService.list().getData();
        homeVO.setCategory(categoryVOList);
        //recommend
        List<BookSimplyVO> bookList = recomment().getData();
        homeVO.setRecommend(bookList);
        //hotbook
        List<BookSimplyVO> hotBookList = hotBook().getData();
        homeVO.setHotBook(hotBookList);
        //freeread
        List<BookSimplyVO> freeRead = recomment().getData();
        homeVO.setFreeRead(freeRead);
        //shelfCount
        int shelfCount;
        if (openId == null) {
            shelfCount = 0;
        } else {
            shelfCount = shelfService.getShelfNumber(openId);
        }
        homeVO.setShelfCount(shelfCount);
        //HotSearchVO
        //todo hotsearch
        HotSearchVO hotSearchVO = hotSearchService.getHotSearchVO();
        homeVO.setHotSearch(hotSearchVO);


        return ServerResponse.success("查询成功", homeVO);
    }

    public List<AuthorVO> selectAuthorByKeyword(String keyword, int current, int size) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("author", keyword);
        Page page = new Page(current, size);
        IPage iPage = bookMapper.selectPage(page, queryWrapper);
        List<Book> bookList = iPage.getRecords();
        List<AuthorVO> authorVOS = new ArrayList<>();
        for (Book book : bookList) {
            AuthorVO authorVO = new AuthorVO();
            authorVO.setAuther(book.getAuthor());
            authorVOS.add(authorVO);
        }
        return authorVOS;
    }

    public List<CategoryVO2> selectCategoryByKeyword(String keyword, int current, int size) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("category_text", keyword);
        Page page = new Page(current, size);
        IPage iPage = bookMapper.selectPage(page, queryWrapper);
        List<Book> bookList = iPage.getRecords();
        List<CategoryVO2> categoryVO2List = new ArrayList<>();
        for (Book book : bookList) {
            CategoryVO2 categoryVO2 = new CategoryVO2();
            categoryVO2.setCategory(book.getCategory());
            categoryVO2.setCategoryText(book.getCategoryText());
            categoryVO2List.add(categoryVO2);
        }
        return categoryVO2List;
    }

    public List<PublisherVO> selectPublisherByKeyword(String keyword, int current, int size) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("publisher", keyword);
        Page page = new Page(current, size);
        IPage iPage = bookMapper.selectPage(page, queryWrapper);
        List<Book> bookList = iPage.getRecords();
        List<PublisherVO> publisherVOList = new ArrayList<>();
        for (Book book : bookList) {
            PublisherVO publisherVO = new PublisherVO();
            publisherVO.setPublisher(book.getPublisher());
            publisherVOList.add(publisherVO);
        }
        return publisherVOList;
    }

    public List<BookSimplyVO> selectBookByKeyword(String keyword, int current, int size) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("file_name", keyword);
        Page<Book> bookPage = new Page<Book>(current, size, false);
        IPage iPage = bookMapper.selectPage(bookPage, queryWrapper);
        List<Book> bookList = iPage.getRecords();
        //if book is not exist
        if (bookList.size() == 0) {
            throw new GlobalException(CodeMsg.BOOK_NOT_EXIST);
        }
        List<BookSimplyVO> bookSimplyVOList = new ArrayList<>();
        for (Book book : bookList) {
            BookSimplyVO bookSimplyVO = AssembleVOUtil.assembleBookSimplyVO(book);
            bookSimplyVOList.add(bookSimplyVO);
        }
        return bookSimplyVOList;
    }
}