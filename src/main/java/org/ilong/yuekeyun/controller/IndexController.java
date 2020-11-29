package org.ilong.yuekeyun.controller;

import org.ilong.yuekeyun.Enum.ClassifyEnum;
import org.ilong.yuekeyun.Enum.CourseEnum;
import org.ilong.yuekeyun.bean.ConstsSiteCarousel;
import org.ilong.yuekeyun.bean.Course;
import org.ilong.yuekeyun.bean.dto.CourseDto;
import org.ilong.yuekeyun.service.ConstsSiteCarouselService;
import org.ilong.yuekeyun.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页控制器
 *
 * @author long
 * @date 2020-10-12 15:17
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    ConstsSiteCarouselService constsSiteCarouselService;

    @Autowired
    CourseService courseService;
    /**
     * 首页轮播图 5个  按权重高低排序 和是否可用 1：可用 0：禁用
     * @return
     */
    @GetMapping("/queryCarousels")
    public List<ConstsSiteCarousel> queryCarousels(){
        return constsSiteCarouselService.queryCarousels(5);
    }

    /**
     * 零基础课程
     * @return
     */
    @GetMapping("/lingjichu")
    public List<Course> getlingjichu(){
        CourseDto courseDto = new CourseDto();
        courseDto.setFree(CourseEnum.FREE.value());//免费
        courseDto.descSortField("weight");//按照weight降序排列
        courseDto.setLevel(CourseEnum.Level_Lingjichu.value());//零基础等级
        courseDto.setOnsale(CourseEnum.ONSALE.value());//上架
        courseDto.setCount(4);//课程数
        return courseService.queryCourses(courseDto);
    }

    /**
     * 职场提升课程
     * @return
     */
    @GetMapping("/zhichangtisheng")
    public List<Course> getzhichangtisheng(){
        CourseDto courseDto = new CourseDto();
        courseDto.setFree(CourseEnum.FREE_NOT.value());//收费
        courseDto.descSortField("weight");//按照weight降序排列
        courseDto.setLevel(CourseEnum.Level_TiSheng.value());//提示等级
        courseDto.setOnsale(CourseEnum.ONSALE.value());//上架
        courseDto.setCount(4);//课程数
        return courseService.queryCourses(courseDto);
    }

    /**
     * 实战推荐课程
     * @return
     */
    @GetMapping("/shizhangtuijian")
    public List<Course> getshizhangtuijian(){
        CourseDto courseDto=new CourseDto();
        courseDto.setFree(CourseEnum.FREE_NOT.value());//收费
        courseDto.setLevel(CourseEnum.Level_Zhongji.value());//中级课程
        courseDto.setOnsale(CourseEnum.ONSALE.value());//上架
        courseDto.descSortField("weight");//按照weight降序排列
        courseDto.setCount(4);//课程数
        return courseService.queryCourses(courseDto);
    }

    /**
     * 好课推荐课程
     * @return
     */
    @GetMapping("/haoketuijian")
    public List<Course> gethaoketuijian(){
        CourseDto courseDto=new CourseDto();
        courseDto.setFree(CourseEnum.FREE_NOT.value());
        courseDto.setOnsale(CourseEnum.ONSALE.value());
        courseDto.setCount(4);
        return courseService.queryCourses(courseDto);
    }

    /**
     * java推荐课程
     * @return
     */
    @GetMapping("/javatuijian")
    public List<Course> getjavatuijian(){
        CourseDto courseDto=new CourseDto();
        courseDto.setCount(8);
        courseDto.setFree(null);//不分收费和免费
        courseDto.setOnsale(CourseEnum.ONSALE.value());
        courseDto.setSubClassify(ClassifyEnum.Classify_sub_java.value());//java分类
        courseDto.descSortField("studyCount");//按学习人数降序排列
        return courseService.queryCourses(courseDto);

    }

}
