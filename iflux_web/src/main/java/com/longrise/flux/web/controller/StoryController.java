package com.longrise.flux.web.controller;

import com.longrise.flux.global.domain.EntityBean;
import com.longrise.flux.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 使用响应式编程 Reactive Stream
 */
@RestController
@RequestMapping("/book")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @GetMapping(value = "/list", produces = MediaType.TEXT_EVENT_STREAM_VALUE) // 多次返回
    public Flux<EntityBean> getBookList() {
        EntityBean[] beans = storyService.getBookList();
        return Flux.fromArray(beans);

    }

    @GetMapping("/catalog/{bid}")
    public Mono<EntityBean> getBookCatalog(@PathVariable("bid") Long bid) {
        EntityBean bean = storyService.getBookCatalog(bid);
        return Mono.just(bean);
    }

    @GetMapping("/content/{sid}")
    public Mono<EntityBean> getTitleContent(@PathVariable("sid") Long sid) {
        EntityBean bean = storyService.getBookContent(sid);
        return Mono.just(bean);
    }
}
