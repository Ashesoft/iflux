package com.longrise.flux.service;


import com.longrise.flux.global.domain.EntityBean;

public interface StoryService {


    /**
     * 获取故事列表
     *
     * @return 列表
     */
    EntityBean[] getBookList();

    /**
     * 获取故事简介及其章回目录
     *
     * @return 列表
     */
    EntityBean getBookCatalog(Long bid);

    /**
     * 获取对应章回内容
     *
     * @return 列表
     */
    EntityBean getBookContent(Long sid);
}
