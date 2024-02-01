package com.board.back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    //    SortHandlerMethodArgumentResolver sortArgumentResolver = new SortHandlerMethodArgumentResolver();
    //    sortArgumentResolver.setSortParameter("sortBy");
    //    sortArgumentResolver.setPropertyDelimiter("-");
    //
    //    PageableHandlerMethodArgumentResolver pageableArgumentResolver = new PageableHandlerMethodArgumentResolver(sortArgumentResolver);
    //    pageableArgumentResolver.setOneIndexedParameters(true);
    //    pageableArgumentResolver.setMaxPageSize(500);
    //    pageableArgumentResolver.setFallbackPageable(PageRequest.of(0, 10));
    //    argumentResolvers.add(pageableArgumentResolver);
    //}
}