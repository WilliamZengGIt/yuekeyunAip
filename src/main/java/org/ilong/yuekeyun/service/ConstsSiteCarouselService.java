package org.ilong.yuekeyun.service;

import org.ilong.yuekeyun.bean.ConstsSiteCarousel;
import org.ilong.yuekeyun.bean.common.page.PageBean;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-10-11 21:10
 */
public interface ConstsSiteCarouselService {
    /**
     * 获取轮播图   条件：按权重高低 和是否可用 1：可用 0：禁用
     * @param count 获取的个数
     * @return
     */
    List<ConstsSiteCarousel> queryCarousels(Integer count);

    /**
     *根据id获取
     **/
    ConstsSiteCarousel getById(Long id);


    /**
     *获取所有
     **/
    List<ConstsSiteCarousel> queryAll(ConstsSiteCarousel queryEntity);

    /**
     *获取总数量
     **/
    Integer getTotalItemsCount(ConstsSiteCarousel queryEntity);

    /**
     *分页获取
     **/
    List<ConstsSiteCarousel> queryPage(ConstsSiteCarousel queryEntity , PageBean<ConstsSiteCarousel> page);

    /**
     *创建新记录
     **/
    void create(ConstsSiteCarousel entity);

    /**
     * 创建新记录
     */
    void createSelectivity(ConstsSiteCarousel entity);

    /**
     *根据id更新
     **/
    void update(ConstsSiteCarousel entity);

    /**
     *根据id选择性更新自动
     **/
    void updateSelectivity(ConstsSiteCarousel entity);

    /**
     *物理删除
     **/
    void delete(ConstsSiteCarousel entity);

    /**
     *逻辑删除
     **/
    void deleteLogic(ConstsSiteCarousel entity);
}
