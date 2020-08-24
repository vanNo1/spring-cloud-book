package van.bookbookprovider.service;



import base.ServerResponse;
import van.bookbookprovider.entity.Banner;
import van.bookbookprovider.entity.Book;
import van.bookbookprovider.vo.BannerVO2;

import java.util.List;

/**
 * @author Van
 * @date 2020/4/3 - 22:37
 */
public interface BannerService {
    BannerVO2 assembleBannserVO2(Book book, Banner banner);
    ServerResponse<List<BannerVO2>> generateBanner();
}
