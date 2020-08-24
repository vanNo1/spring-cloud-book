package van.bookbookprovider.service;


import base.ServerResponse;

/**
 * @author Van
 * @date 2020/4/5 - 14:41
 */
public interface BookListLikeService {
    ServerResponse likeOrDislike(String openId, String bookList);
}
