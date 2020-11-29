package org.ilong.yuekeyun.mapper;

import org.ilong.yuekeyun.bean.ConstsClassify;
import org.ilong.yuekeyun.bean.common.page.TailPage;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-13 9:39
 */
public interface ConstsClassifyMapper {
    /**
     *根据id获取
     **/
     ConstsClassify getById(Long id);

    /**
     * 根据code获取
     *//*
     ConstsCollege getByCode(String code);
*/
    /**
     *获取所有
     **/
     List<ConstsClassify> queryAll();

    /**
     * 根据条件动态获取
     * @param queryEntity
     * @return
     */
     List<ConstsClassify> queryByCondition(ConstsClassify queryEntity);

    /**
     *获取总数量
     **/
     Integer getTotalItemsCount(ConstsClassify queryEntity);

    /**
     *分页获取
     **/
     List<ConstsClassify> queryPage(ConstsClassify queryEntity , TailPage<ConstsClassify> page);

    /**
     *创建新记录
     **/
     void create(ConstsClassify entity);

    /**
     * 创建新记录
     */
     void createSelectivity(ConstsClassify entity);

    /**
     *根据id更新
     **/
     void update(ConstsClassify entity);

    /**
     *根据id选择性更新自动
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
