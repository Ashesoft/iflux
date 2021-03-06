package com.longrise.flux.service.impl;

import com.longrise.flux.global.domain.EntityBean;
import com.longrise.flux.mapping.StoryMapping;
import com.longrise.flux.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryMapping storyMapping;

    @Override
    public EntityBean[] getBookList() {
        return storyMapping.getBookList();
    }

    @Override
    public EntityBean getBookCatalog(Long bid) {
        EntityBean bean = new EntityBean(1);
        bean.put("desc", storyMapping.getBookDescribe(bid));
        bean.put("catalogs", storyMapping.getBookCatalog(bid));
        return bean;
    }

    @Override
    public EntityBean getBookContent(Long sid) {
        return storyMapping.getBookContent(sid);
    }
}
