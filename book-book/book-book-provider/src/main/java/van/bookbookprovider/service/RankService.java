package van.bookbookprovider.service;



import base.ServerResponse;
import van.bookbookprovider.entity.Book;

import java.util.List;

/**
 * @author Van
 * @date 2020/3/17 - 12:10
 */
public interface RankService {
    ServerResponse save(String fileName, Integer rank, String openId);

    Integer rank(String openId,String fileName);

    double rankAvg(String fileName);

    int rankNum(String fileName);

    List<Book> getHighRankBook(int rank);
    List<Book> getHighRankBookByCategory(int category,int rank,int size);
}
