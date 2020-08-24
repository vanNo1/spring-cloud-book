package van.bookbookprovider.service;


import base.ServerResponse;

/**
 * @author Van
 * @date 2020/4/4 - 18:23
 */
public interface IntroductionService {
    ServerResponse getIntroduction(String fileName);
}
