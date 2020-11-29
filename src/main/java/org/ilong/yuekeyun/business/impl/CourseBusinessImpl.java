package org.ilong.yuekeyun.business.impl;

import org.ilong.yuekeyun.Enum.CourseEnum;
import org.ilong.yuekeyun.bean.CourseSection;
import org.ilong.yuekeyun.bean.vo.CourseSectionVO;
import org.ilong.yuekeyun.business.CourseBusiness;
import org.ilong.yuekeyun.service.CourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.*;
import org.springframework.beans.BeanUtils;
/**
 * 课程业务层
 *
 * @author long
 * @date 2020-11-23 16:20
 */
@Service
public class CourseBusinessImpl implements CourseBusiness {
    @Autowired
    private  CourseSectionService courseSectionService;
    /**
     * 获取课程章节
     */
    @Override
    public List<CourseSectionVO> queryCourseSection(Long courseId) {
        List<CourseSectionVO> resultList = new ArrayList<CourseSectionVO>();
        CourseSection queryEntity = new CourseSection();
        queryEntity.setCourseId(courseId);
        queryEntity.setOnsale(CourseEnum.ONSALE.value());//上架
        Map<Long,CourseSectionVO> tmpMap = new LinkedHashMap<Long,CourseSectionVO>();
        Iterator<CourseSection> it = courseSectionService.queryAll(queryEntity).iterator();
        while(it.hasNext()){
            CourseSection item = it.next();
            if(Long.valueOf(0).equals(item.getParentId())){//章
                CourseSectionVO vo = new CourseSectionVO();
                BeanUtils.copyProperties(item, vo);
                tmpMap.put(vo.getId(), vo);
            }else{
                tmpMap.get(item.getParentId()).getSections().add(item);//小节添加到大章中
            }
        }
        for(CourseSectionVO vo : tmpMap.values()){
            resultList.add(vo);
        }
        return resultList;

    }
}
