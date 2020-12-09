package org.ilong.yuekeyun.controller.app;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ilong.yuekeyun.bean.ConstsClassify;
import org.ilong.yuekeyun.bean.RespBean;
import org.ilong.yuekeyun.service.ConstsClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-13 10:55
 */
@RestController
@RequestMapping("/classify")
@Api(tags = "课程分类相关接口")
public class ClassifyController {
    @Autowired
    private ConstsClassifyService constsClassifyService;

    //根据id获取分类
    @GetMapping ( "/getById")
    @ApiOperation("根据id获取分类")
    public RespBean getById(Long id){
        ConstsClassify constsClassify = constsClassifyService.getById(id);
        RespBean ok = RespBean.ok("", constsClassify);
        return ok;
    }

    /**
     * 获取全部课程分类
     * @return
     */
    @GetMapping("/getALLlassify")
    @ApiOperation("获取全部课程分类")
    public RespBean getcALLlassify(){
         List<ConstsClassify> constsClassifies = constsClassifyService.queryAll();
         return  RespBean.ok("",constsClassifies);
    }
}
