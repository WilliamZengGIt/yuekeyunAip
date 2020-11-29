package org.ilong.yuekeyun.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.ilong.yuekeyun.bean.Course;
import org.ilong.yuekeyun.bean.common.page.PageBean;
import org.ilong.yuekeyun.bean.common.page.TailPage;
import org.ilong.yuekeyun.bean.dto.CourseDto;
import org.ilong.yuekeyun.mapper.CourseMapper;
import org.ilong.yuekeyun.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  课程服务层实现
 *
 * @author long
 * @date 2020-10-14 15:38
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    /**
     * 按条件获取指定数量的课程 按权重 weight排序
     * @param queryEntity
     * @param num
     * @return
     */
    @Override
    public List<Course> queryCourses(CourseDto queryEntity) {
        return courseMapper.queryCourses(queryEntity);
    }

    /**
     *根据id获取
     **/
    @Override
    public Course getById(Long id) {
        return courseMapper.getById(id);
    }
    /**
     *分页获取
     **/
    @Override
    public TailPage<Course> queryPage(Course queryEntity, TailPage<Course> page) {
        Integer itemsTotalCount = courseMapper.getTotalItemsCount(queryEntity);
        List<Course> items = courseMapper.queryPage(queryEntity,page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;


    }

    /**
     *创建
     **/
    @Override
    public void createSelectivity(Course entity) {

    }
    /**
     *根据id 进行可选性更新
     **/
    @Override
    public void updateSelectivity(Course entity) {

    }
    /**
     *物理删除
     **/
    @Override
    public void delete(Course entity) {

    }
    /**
     *逻辑删除
     **/
    @Override
    public void deleteLogic(Course entity) {

    }
}
