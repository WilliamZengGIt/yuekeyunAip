package org.ilong.yuekeyun.controller.app;

import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ilong.yuekeyun.Enum.ClassifyEnum;
import org.ilong.yuekeyun.Enum.CourseEnum;
import org.ilong.yuekeyun.bean.ConstsSiteCarousel;
import org.ilong.yuekeyun.bean.Course;
import org.ilong.yuekeyun.bean.RespBean;
import org.ilong.yuekeyun.bean.dto.CourseDto;
import org.ilong.yuekeyun.service.ConstsSiteCarouselService;
import org.ilong.yuekeyun.service.CourseService;
import org.ilong.yuekeyun.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页控制器
 *
 * @author long
 * @date 2020-10-12 15:17
 */
@RestController
@Api(tags = "首页相关接口")
@RequestMapping("/index")
public class IndexController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    ConstsSiteCarouselService constsSiteCarouselService;

    @Autowired
    CourseService courseService;
    @Resource
    RedisUtil redisUtil;
    /**
     * 首页轮播图 5个  按权重高低排序 和是否可用 1：可用 0：禁用
     * @return
     */
    @GetMapping("/queryCarousels")
    @ApiOperation("获取5个首页轮播图，按权重降序排列 和 是否可用 1：可用；0：禁用")
    public RespBean queryCarousels(){
        if (redisUtil.hasKey("constsSiteCarousels")) {
            return RespBean.ok("",  redisUtil.lGet("constsSiteCarousels",0,-1));
        }
        List<ConstsSiteCarousel> constsSiteCarousels = constsSiteCarouselService.queryCarousels(5);
        redisUtil.lSet("constsSiteCarousels",constsSiteCarousels,3600*24);
        return RespBean.ok("",constsSiteCarousels);
      /*  return constsSiteCarouselService.queryCarousels(5);*/

    }

    /**
     * 零基础课程
     * @return
     */
    @GetMapping("/lingjichu")
    @ApiOperation("获取4个零基础课程，按权重降序排列， 和 是否可用 1：可用；0：禁用")
    public RespBean getlingjichu(){
        CourseDto courseDto = new CourseDto();
        courseDto.setFree(CourseEnum.FREE.value());//免费
        courseDto.descSortField("weight");//按照weight降序排列
        courseDto.setLevel(CourseEnum.Level_Lingjichu.value());//零基础等级
        courseDto.setOnsale(CourseEnum.ONSALE.value());//上架
        courseDto.setCount(4);//课程数
        if (redisUtil.hasKey("lingjichu")){
            return RespBean.ok("",  redisUtil.lGet("lingjichu",0,-1));
        }
        List<Course> lingjichu = courseService.queryCourses(courseDto);
        redisUtil.lSet("lingjichu",lingjichu,3600*24);
        return RespBean.ok("",lingjichu);
    }

    /**
     * 职场提升课程
     * @return
     */
    @GetMapping("/zhichangtisheng")
    @ApiOperation("获取4个职场提升课程，按权重降序排列， 和 是否可用 1：可用；0：禁用")
    public RespBean getzhichangtisheng(){
        CourseDto courseDto = new CourseDto();
        courseDto.setFree(CourseEnum.FREE_NOT.value());//收费
        courseDto.descSortField("weight");//按照weight降序排列
        courseDto.setLevel(CourseEnum.Level_TiSheng.value());//提示等级
        courseDto.setOnsale(CourseEnum.ONSALE.value());//上架
        courseDto.setCount(4);//课程数
        if (redisUtil.hasKey("zhichangtisheng")){
            return RespBean.ok("",
                    redisUtil.lGet("zhichangtisheng",0,-1));
        }
        List<Course> zhichangtisheng = courseService.queryCourses(courseDto);
        redisUtil.lSet("zhichangtisheng",zhichangtisheng,3600*24);
        return RespBean.ok("",zhichangtisheng);
    }

    /**
     * 实战推荐课程
     * @return
     */
    @GetMapping("/shizhangtuijian")
    @ApiOperation("获取4个实战推荐课程，按权重降序排列， 和 是否可用 1：可用；0：禁用")
    public RespBean getshizhangtuijian(){
        CourseDto courseDto=new CourseDto();
        courseDto.setFree(CourseEnum.FREE_NOT.value());//收费
        courseDto.setLevel(CourseEnum.Level_Zhongji.value());//中级课程
        courseDto.setOnsale(CourseEnum.ONSALE.value());//上架
        courseDto.descSortField("weight");//按照weight降序排列
        courseDto.setCount(4);//课程数
        if (redisUtil.hasKey("shizhangtuijian")){
            return RespBean.ok("",redisUtil.lGet("shizhangtuijian",0,-1));
        }
         List<Course> shizhangtuijian = courseService.queryCourses(courseDto);
         redisUtil.lSet("shizhangtuijian",shizhangtuijian,3600*24);
         return RespBean.ok("",shizhangtuijian);
    }

    /**
     * 好课推荐课程
     * @return
     */
    @GetMapping("/haoketuijian")
    @ApiOperation("获取4个好课推荐课程，按权重降序排列， 和 是否可用 1：可用；0：禁用")
    public RespBean gethaoketuijian(){
        CourseDto courseDto=new CourseDto();
        courseDto.setFree(CourseEnum.FREE_NOT.value());
        courseDto.setOnsale(CourseEnum.ONSALE.value());
        courseDto.setCount(4);
        if (redisUtil.hasKey("haoketuijian")){
            return RespBean.ok("",redisUtil.lGet("haoketuijian",0,-1));
        }
         List<Course> haoketuijian = courseService.queryCourses(courseDto);
         redisUtil.lSet("haoketuijian",haoketuijian,3600*24);
         return RespBean.ok("",haoketuijian) ;
    }

    /**
     * java推荐课程
     * @return
     */
    @GetMapping("/javatuijian")
    @ApiOperation("获取8个java推荐课程，按权重降序排列， 和 是否可用 1：可用；0：禁用")
    public RespBean getjavatuijian(){
        CourseDto courseDto=new CourseDto();
        courseDto.setCount(8);
        courseDto.setFree(null);//不分收费和免费
        courseDto.setOnsale(CourseEnum.ONSALE.value());
        courseDto.setSubClassify(ClassifyEnum.Classify_sub_java.value());//java分类
        courseDto.descSortField("studyCount");//按学习人数降序排列
        if (redisUtil.hasKey("javatuijian")){
            return RespBean.ok("",redisUtil.lGet("javatuijian",0,-1));
        }
        List<Course> javatuijian = courseService.queryCourses(courseDto);
        redisUtil.lSet("javatuijian",javatuijian,3600*24);
        return RespBean.ok("",javatuijian);
    }

}
