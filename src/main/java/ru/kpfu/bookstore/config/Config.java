package ru.kpfu.bookstore.config;  
  
import freemarker.template.utility.XmlEscape;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;


import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * Code for studying purposes.
 * Programming course. Kazan Federal University, ITIS.
 * http://study.istamendil.info/
 * 
 * @author Alexander Ferenets <istamendil.info>
 */
@Configuration
@ComponentScan("ru.kpfu.bookstore")
@EnableWebMvc
@EnableSpringDataWebSupport
public class Config extends WebMvcConfigurerAdapter {


    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix("");

        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html; charset=UTF-8");
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
        res.setBasenames("classpath:i18n/messages", "classpath:i18n/validation");
        res.setCacheSeconds(0);
        res.setDefaultEncoding("UTF-8");
        res.setUseCodeAsDefaultMessage(false);
        return res;
    }
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        return filter;
    }
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieName("lang");
        localeResolver.setCookieMaxAge(Integer.MAX_VALUE);
        localeResolver.setCookieDomain("/");
        localeResolver.setDefaultLocale(new Locale("ru", "RU"));
        return localeResolver;
    }

        @Bean(name ="freemarkerConfig")
        public FreeMarkerConfigurer freemarkerConfig() {
            FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
            configurer.setTemplateLoaderPath("/WEB-INF/view/");
            configurer.setDefaultEncoding("UTF-8");
            Map<String, Object> map = new HashMap<>();
            map.put("xml_escape", new XmlEscape());
            configurer.setFreemarkerVariables(map);

            return configurer;
        }

        @Override
        public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
            configurer.ignoreUnknownPathExtensions(false).defaultContentType(MediaType.TEXT_HTML);
        }
        @Override
        public void configureViewResolvers(ViewResolverRegistry registry) {
            registry.freeMarker();
        }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
        ;
//
    }




}
