package org.ilong.yuekeyun.service;

import org.apache.ibatis.annotations.Param;
import org.ilong.yuekeyun.bean.ImagesHistory;
import org.ilong.yuekeyun.bean.common.page.TailPage;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-12-04 13:54
 */
public interface ImagesHistoryService {

    /**
     * 根据头像地址获取
     * @param imgUrl
     * @return
     */
    ImagesHistory getImagesHistoryByHeader(String imgUrl);
    /**
     *获取总数量
     **/
    Integer getTotalItemsCount(ImagesHistory queryEntity);

    /**
     * 获取全部
     * @return
     */
    List<ImagesHistory> queryAll();

    /**
     * 分别获取
     * @param imagesHistory
     * @param page
     * @return
     */
    List<ImagesHistory> queryPage(ImagesHistory imagesHistory, TailPage<ImagesHistory> page);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    ImagesHistory getById(Long id);

    /**
     * 创建新记录
     * @param entity
     */
    void create(ImagesHistory entity);

    /**
     * 根据id更新
     * @param entity
     */
    void update(ImagesHistory entity);

    /**
     * 物理删除
     * @param entity
     */
    void  delete(ImagesHistory entity);

    /**
     * 逻辑删除
     * @param entity
     */
    void  deleteLogic(ImagesHistory entity);
}
