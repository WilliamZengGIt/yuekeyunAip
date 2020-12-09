package org.ilong.yuekeyun.mapper;

import org.apache.ibatis.annotations.Param;
import org.ilong.yuekeyun.bean.ImagesHistory;
import org.ilong.yuekeyun.bean.VideoHistory;
import org.ilong.yuekeyun.bean.common.page.TailPage;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-12-06 19:51
 */
public interface VideoHistoryMapper {

    /**
     * 根据视频地址获取
     * @param videoUrl
     * @return
     */
    VideoHistory getVideoHistoryByVideoUrl(@Param("videoUrl") String videoUrl);

    /**
     *获取总数量
     **/
    Integer getTotalItemsCount(VideoHistory queryEntity);

    /**
     * 获取全部
     * @return
     */
    List<VideoHistory> queryAll();

    /**
     * 分页获取
     * @param videoHistory
     * @param page
     * @return
     */
    List<VideoHistory> queryPage(VideoHistory videoHistory, TailPage<VideoHistory> page);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    VideoHistory getById(Long id);

    /**
     * 创建新记录
     * @param entity
     */
    void create(VideoHistory entity);

    /**
     * 根据id更新
     * @param entity
     */
    void update(VideoHistory entity);

    /**
     * 物理删除
     * @param entity
     */
    void  delete(VideoHistory entity);

    /**
     * 逻辑删除
     * @param entity
     */
    void  deleteLogic(VideoHistory entity);

}
