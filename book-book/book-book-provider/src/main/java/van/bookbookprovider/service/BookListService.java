package van.bookbookprovider.service;


import base.ServerResponse;
import van.bookbookprovider.entity.BookList;

/**
 * @author Van
 * @date 2020/4/5 - 14:26
 */
public interface BookListService {
    ServerResponse deleteBookListByName(String openId, String bookList);
    BookList selectBookListByName(String bookList);
    void addLike(String  bookList);
    void cancelLike(String  bookList);
    ServerResponse createBookList(BookList bookList);

}
