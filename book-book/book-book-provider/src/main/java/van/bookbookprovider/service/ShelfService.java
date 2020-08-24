package van.bookbookprovider.service;


import base.ServerResponse;
import van.bookbookprovider.entity.Shelf;
import van.bookbookprovider.vo.ShelfVO;

import java.util.List;

/**
 * @author Van
 * @date 2020/3/16 - 13:05
 */
public interface ShelfService {
    ServerResponse<List<ShelfVO>> get(String fileName, String openId);

    ServerResponse save(String fileName, String openId);

    ServerResponse remove(String fileName, String openId);

    Integer findPeopleNum(String fileName);

    List<Shelf> getAllShelf(String fileName);

    int getShelfNumber(String openId);
}
