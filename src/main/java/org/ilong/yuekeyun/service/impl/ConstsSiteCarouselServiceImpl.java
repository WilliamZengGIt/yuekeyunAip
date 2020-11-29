package org.ilong.yuekeyun.service.impl;

import org.ilong.yuekeyun.bean.ConstsSiteCarousel;
import org.ilong.yuekeyun.bean.common.page.PageBean;
import org.ilong.yuekeyun.mapper.ConstsSiteCarouselMapper;
import org.ilong.yuekeyun.service.ConstsSiteCarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 * @author long
 * @date 2020-10-11 21:12
 */
@Service
public class ConstsSiteCarouselServiceImpl implements ConstsSiteCarouselService {

    @Autowired
    ConstsSiteCarouselMapper constsSiteCarouselMapper;

    @Override
    public List<ConstsSiteCarousel> queryCarousels(Integer count) {

        return constsSiteCarouselMapper.queryCarousels(count);
    }

    @Override
    public ConstsSiteCarousel getById(Long id) {
        return null;
    }

    @Override
    public List<ConstsSiteCarousel> queryAll(ConstsSiteCarousel queryEntity) {
        return null;
    }

    @Override
    public Integer getTotalItemsCount(ConstsSiteCarousel queryEntity) {
        return null;
    }

    @Override
    public List<ConstsSiteCarousel> queryPage(ConstsSiteCarousel queryEntity, PageBean<ConstsSiteCarousel> page) {
        return null;
    }

    @Override
    public void create(ConstsSiteCarousel entity) {

    }

    @Override
    public void createSelectivity(ConstsSiteCarousel entity) {

    }

    @Override
    public void update(ConstsSiteCarousel entity) {

    }

    @Override
    public void updateSelectivity(ConstsSiteCarousel entity) {

    }

    @Override
    public void delete(ConstsSiteCarousel entity) {

    }

    @Override
    public void deleteLogic(ConstsSiteCarousel entity) {

    }
}
