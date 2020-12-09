package org.ilong.yuekeyun.service.impl;

import org.ilong.yuekeyun.bean.VideoHistory;
import org.ilong.yuekeyun.bean.common.page.TailPage;
import org.ilong.yuekeyun.mapper.VideoHistoryMapper;
import org.ilong.yuekeyun.service.VideoHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-12-07 10:30
 */
@Service
public class VideoHistoryServiceImpl implements VideoHistoryService {

    @Autowired
    VideoHistoryMapper videoHistoryMapper;

    @Override
    public VideoHistory getVideoHistoryByVideoUrl(String videoUrl) {
        return videoHistoryMapper.getVideoHistoryByVideoUrl(videoUrl);
    }

    @Override
    public Integer getTotalItemsCount(VideoHistory queryEntity) {
        return videoHistoryMapper.getTotalItemsCount(queryEntity);
    }

    @Override
    public List<VideoHistory> queryAll() {
        return videoHistoryMapper.queryAll();
    }

    @Override
    public List<VideoHistory> queryPage(VideoHistory videoHistory, TailPage<VideoHistory> page) {
        return videoHistoryMapper.queryPage(videoHistory,page);
    }

    @Override
    public VideoHistory getById(Long id) {
        return videoHistoryMapper.getById(id);
    }

    @Override
    public void create(VideoHistory entity) {
        videoHistoryMapper.create(entity);
    }

    @Override
    public void update(VideoHistory entity) {
        videoHistoryMapper.update(entity);
    }

    @Override
    public void delete(VideoHistory entity) {
        videoHistoryMapper.delete(entity);
    }

    @Override
    public void deleteLogic(VideoHistory entity) {
        videoHistoryMapper.deleteLogic(entity);
    }
}
