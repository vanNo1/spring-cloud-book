package van.bookbookprovider.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Van
 * @date 2020/4/5 - 15:43
 */
@Data
public class BookListVO {
    //user
    private String openId;
    private String bookList;
    //bookList
    private String title;
    private int likes;
    //book
    private List<BookSimplyVO> books;
}
