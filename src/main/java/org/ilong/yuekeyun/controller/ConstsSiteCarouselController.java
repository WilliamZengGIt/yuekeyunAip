package org.ilong.yuekeyun.controller;

import org.ilong.yuekeyun.bean.ConstsSiteCarousel;
import org.ilong.yuekeyun.service.ConstsSiteCarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 轮播图控制器
 *
 * @author long
 * @date 2020-10-11 20:52
 */
@RestController
@RequestMapping("/ConstsSiteCarousel")
public class ConstsSiteCarouselController {

    @Autowired
    ConstsSiteCarouselService constsSiteCarouselService;

    /**
     * 首页轮播图 5个  按权重高低排序 和是否可用 1：可用 0：禁用
     * @return
     */
    @GetMapping("/queryCarousels")
    public List<ConstsSiteCarousel> queryCarousels(){
        return constsSiteCarouselService.queryCarousels(5);
    }
}
