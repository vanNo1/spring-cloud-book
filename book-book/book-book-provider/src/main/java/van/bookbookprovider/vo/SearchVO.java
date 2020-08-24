package van.bookbookprovider.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Van
 * @date 2020/6/3 - 15:22
 */
@Data
public class SearchVO {
    private List<BookSimplyVO>book;
    private List<AuthorVO>author;
    private List<CategoryVO2>category;
    private List<PublisherVO>publisher;
}
