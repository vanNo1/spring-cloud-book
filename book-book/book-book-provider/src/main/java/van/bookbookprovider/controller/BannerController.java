package van.bookbookprovider.controller;


import base.ServerResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import van.bookbookprovider.service.serviceImpl.BannerServiceImpl;

import javax.annotation.Resource;

/**
 * @author Van
 * @date 2020/4/3 - 23:06
 */
@RestController
@RequestMapping("/banner")
public class BannerController {
    @Resource
    private BannerServiceImpl bannerService;
    @RequestMapping("/list")
    public ServerResponse list(){
        return bannerService.generateBanner();
    }
}
