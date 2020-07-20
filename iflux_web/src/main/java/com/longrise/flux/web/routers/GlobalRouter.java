package com.longrise.flux.web.routers;

import com.longrise.flux.web.handle.StoryHandle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GlobalRouter {

    // 书籍的路由
    @Bean
    public RouterFunction<ServerResponse> storyRouter(StoryHandle storyHandle) {
        return nest(
                GET("book"), // 相当于 controller 类上的 RequestMapping("/book") 注解
                route(GET("lists"), storyHandle::getBookLists) // 相当于 controller 类里面的 RequestMapping 注解
                        .andRoute(GET("catalogs/{bid}"), storyHandle::getBookCatalogs)
                        .andRoute(GET("contents/{sid}"), storyHandle::getBookContents));
    }
}
