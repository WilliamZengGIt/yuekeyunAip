package org.ilong.yuekeyun.service;

import org.ilong.yuekeyun.bean.ConstsClassify;
import org.ilong.yuekeyun.bean.common.page.TailPage;

import java.util.List;

/**
 * 课程分类Service层
 *
 * @author long
 * @date 2020-11-13 9:33
 */
public interface ConstsClassifyService {

    /**
     *根据id获取
     **/
     ConstsClassify getById(Long id);
    /**
     *获取所有
     **/
     List<ConstsClassify> queryAll();
    /**
     * 根据code获取
     */
     ConstsClassify getByCode(String code);
    /**
     *根据条件动态获取
     **/
     List<ConstsClassify> queryByCondition(ConstsClassify queryEntity);
    /**
     *分页获取
     **/
     TailPage<ConstsClassify> queryPage(ConstsClassify queryEntity ,
                                        TailPage<ConstsClassify> page);
    /**
     *创建
     **/
     void create(ConstsClassify entity);

    /**
     * 创建
     */
     void createSelectivity(ConstsClassify entity);
    /**
     *根据id 进行可选性更新
     **/
     void updateSelectivity(ConstsClassify entity);
    /**
     *物理删除
     **/
     void delete(ConstsClassify entity);

    /**
     *逻辑删除
     **/
     void deleteLogic(ConstsClassify entity);
}
