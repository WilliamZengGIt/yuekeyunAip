package org.ilong.yuekeyun.service.impl;

import org.ilong.yuekeyun.bean.ImagesHistory;
import org.ilong.yuekeyun.bean.common.page.TailPage;
import org.ilong.yuekeyun.mapper.ImagesHistoryMapper;
import org.ilong.yuekeyun.service.ImagesHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-12-04 13:58
 */
@Service
public class ImagesHistoryServiceImpl implements ImagesHistoryService {

    @Autowired
    ImagesHistoryMapper imagesHistoryMapper;

    @Override
    public ImagesHistory getImagesHistoryByHeader(String imgUrl) {
        return imagesHistoryMapper.getImagesHistoryByHeader(imgUrl);
    }

    @Override
    public Integer getTotalItemsCount(ImagesHistory queryEntity) {
        return imagesHistoryMapper.getTotalItemsCount(queryEntity);
    }

    @Override
    public List<ImagesHistory> queryAll() {
        return imagesHistoryMapper.queryAll();
    }

    @Override
    public List<ImagesHistory> queryPage(ImagesHistory imagesHistory, TailPage<ImagesHistory> page) {
        return imagesHistoryMapper.queryPage(imagesHistory,page);
    }

    @Override
    public ImagesHistory getById(Long id) {
        return imagesHistoryMapper.getById(id);
    }

    @Override
    public void create(ImagesHistory entity) {
        imagesHistoryMapper.create(entity);
    }

    @Override
    public void update(ImagesHistory entity) {
        imagesHistoryMapper.update(entity);
    }

    @Override
    public void delete(ImagesHistory entity) {
        imagesHistoryMapper.delete(entity);
    }

    @Override
    public void deleteLogic(ImagesHistory entity) {
        imagesHistoryMapper.deleteLogic(entity);
    }
}
