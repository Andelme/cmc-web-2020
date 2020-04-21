package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import spring.converter.DepartmentIdToObjConverter;
import spring.converter.PosTypeIdToObjConverter;
import spring.converter.WorkerIdToObjConverter;

import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"spring"})
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private DepartmentIdToObjConverter departmentIdToObjConverter;
    @Autowired
    private PosTypeIdToObjConverter posTypeIdToObjConverter;
    @Autowired
    private WorkerIdToObjConverter workerIdToObjConverter;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(departmentIdToObjConverter);
        registry.addConverter(posTypeIdToObjConverter);
        registry.addConverter(workerIdToObjConverter);
    }

    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(new Locale("ru_RU"));
        return sessionLocaleResolver;
    }

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}