package org.ilong.yuekeyun.controller.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "轮播图相关接口")
public class ConstsSiteCarouselController {

    @Autowired
    ConstsSiteCarouselService constsSiteCarouselService;

    /**
     * 首页轮播图 5个  按权重高低排序 和是否可用 1：可用 0：禁用
     * @return
     */
    @GetMapping("/queryCarousels")
    @ApiOperation("获取 5个轮播图  按权重高低排序 和是否可用 1：可用 0：禁用")
    public List<ConstsSiteCarousel> queryCarousels(){
        return constsSiteCarouselService.queryCarousels(5);
    }
}
