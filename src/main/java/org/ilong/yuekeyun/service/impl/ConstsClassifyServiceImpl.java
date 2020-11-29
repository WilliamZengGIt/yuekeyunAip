package org.ilong.yuekeyun.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.ilong.yuekeyun.bean.ConstsClassify;
import org.ilong.yuekeyun.bean.common.page.TailPage;
import org.ilong.yuekeyun.mapper.ConstsClassifyMapper;
import org.ilong.yuekeyun.service.ConstsClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-13 9:38
 */
@Service
public class ConstsClassifyServiceImpl implements ConstsClassifyService {
    @Autowired
    ConstsClassifyMapper constsClassifyMapper;

    @Override
    public ConstsClassify getById(Long id) {
        return constsClassifyMapper.getById(id);
    }

    @Override
    public List<ConstsClassify> queryAll() {
        return constsClassifyMapper.queryAll();
    }

    @Override
    public ConstsClassify getByCode(String code) {
        if(StringUtils.isEmpty(code)){
            return null;
        }
        ConstsClassify queryEntity = new ConstsClassify();
        queryEntity.setCode(code);
        List<ConstsClassify> list = constsClassifyMapper.queryByCondition(queryEntity);
        if(CollectionUtils.isNotEmpty(list))
            return list.get(0);
        return null;
    }

    @Override
    public List<ConstsClassify> queryByCondition(ConstsClassify queryEntity) {
        return constsClassifyMapper.queryByCondition(queryEntity);
    }

    @Override
    public TailPage<ConstsClassify> queryPage(ConstsClassify queryEntity, TailPage<ConstsClassify> page) {
        Integer itemsTotalCount = constsClassifyMapper.getTotalItemsCount(queryEntity);
        List<ConstsClassify> items = constsClassifyMapper.queryPage(queryEntity,page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }

    @Override
    public void create(ConstsClassify entity) {
        constsClassifyMapper.create(entity);
    }

    @Override
    public void createSelectivity(ConstsClassify entity) {
        constsClassifyMapper.createSelectivity(entity);
    }

    @Override
    public void updateSelectivity(ConstsClassify entity) {
        constsClassifyMapper.updateSelectivity(entity);
    }

    @Override
    public void delete(ConstsClassify entity) {
        constsClassifyMapper.delete(entity);
    }

    @Override
    public void deleteLogic(ConstsClassify entity) {
        constsClassifyMapper.deleteLogic(entity);
    }
}
