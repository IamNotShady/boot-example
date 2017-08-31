package com.wtyt.util.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by zhouxiaoxiao on 17/3/1.
 * 过滤器和拦截器的执行顺序都与代码顺序正相关
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new FirstIntercepter()).addPathPatterns("/**");
        registry.addInterceptor(new SessionIntercepter()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    /**
     * 配置过滤器
     *
     * @return
     */
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean1() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        FirstFilter firstFilter = new FirstFilter();
//        registrationBean.setFilter(firstFilter);
//        List<String> urlPatterns = new ArrayList<>();
//        urlPatterns.add("/*");
//        registrationBean.setUrlPatterns(urlPatterns);
//        return registrationBean;
//    }

    /**
     * 编码过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }

    /**
     * 配置fastjson将bean转为json
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
    }
}
