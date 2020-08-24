package van.bookbookprovider.service;


import base.ServerResponse;


/**
 * @author Van
 * @date 2020/4/4 - 11:24
 */
public interface ReviewService {
    ServerResponse insertOrUpdateReview(String openId, String fileName, String summary, String title);

    ServerResponse listReview(String fileName, int pageSize, int current);

    ServerResponse deleteReview(String openId, String fileName);
}
