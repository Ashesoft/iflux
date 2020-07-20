# 目的
本项目是通过简化的看小说, 来简单了解`RouterFunction`的使用;
# Reactive
+ 传统的`springmvc`处理请求是同步阻塞的(并发量不高)
+ 使用`Reactive Stream`的方式处理请求(支持高并发量)
    + 将以往查询的到结果通过`Reactive Stream`包装的方式处理请求
    + 使用`RouterFunction`形式处理请求(推荐)
    
# RouterFunction
每个业务都是类似`HandlerFunction`接口的处理类
```java
@FunctionalInterface
public interface HandlerFunction<T extends ServerResponse> {
    Mono<T> handle(ServerRequest var1);
}
```
例如: 本例子的书籍的处理类
```java
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
        return ok().contentType(APPLICATION_JSON).body(fromArray(storyService.getBookList()), EntityBean.class);
    }

    // 获取对应书籍目录
    public Mono<ServerResponse> getBookCatalogs(ServerRequest serverRequest) {
        Long bid = Long.parseLong(serverRequest.pathVariable("bid"));
        return ok().contentType(APPLICATION_JSON).body(just(storyService.getBookCatalog(bid)), EntityBean.class).switchIfEmpty(notFound().build());
    }
}
```
相当于传统`springmvc`的`controller`类, 只是没有映射`url`.
在传统的`springmvc`中使用注解或 xml 配置的方式进行 url 的映射, 而在`RouterFunction`中就需要自己编写映射关系(即路由), 如:
```java
@Configuration
public class GlobalRouter { // 所有请求的总送入口(路由)

    // 书籍的路由
    @Bean
    public RouterFunction<ServerResponse> storyRouter(StoryHandle storyHandle) {
        return nest(
                GET("/book"), // 相当于 controller 类上的 RequestMapping("/book") 注解
                route(GET("/lists"), storyHandle::getBookLists) // 相当于 controller 类里面的 RequestMapping 注解
                        .andRoute(GET("/catalogs/{bid}"), storyHandle::getBookCatalogs));
    }
    // ...
}
```
从上面可以看到, 每个业务所对应的映射关系更集中, 阅读更直观, 最重要的是异步非阻塞的. 





