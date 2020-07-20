package com.longrise.flux.web.handle;

import com.longrise.flux.global.domain.EntityBean;
import com.longrise.flux.service.StoryService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static reactor.core.publisher.Flux.fromArray;
import static reactor.core.publisher.Mono.just;

/**
 * 使用 RouteFunction 模式
 */
@Component
public class StoryHandle {
    private final StoryService storyService;

    private StoryHandle(StoryService storyService) {
        this.storyService = storyService;
    }
    // 获取所有书籍列表
    public Mono<ServerResponse> getBookLists(ServerRequest serverRequest) {
        return ok().contentType(APPLICATION_JSON).body(fromArray(this.storyService.getBookList()), EntityBean.class);
    }

    // 获取对应书籍目录
    public Mono<ServerResponse> getBookCatalogs(ServerRequest serverRequest) {
        Long bid = Long.parseLong(serverRequest.pathVariable("bid"));
        return ok().contentType(APPLICATION_JSON).body(just(this.storyService.getBookCatalog(bid)), EntityBean.class).switchIfEmpty(notFound().build());
    }

    // 获取书籍对应的章节内容
    public Mono<ServerResponse> getBookContents(ServerRequest serverRequest){
        Long sid = Long.parseLong(serverRequest.pathVariable("sid"));
        return ok().contentType(APPLICATION_JSON).body(just(this.storyService.getBookContent(sid)), EntityBean.class).switchIfEmpty(notFound().build());
    }
}
