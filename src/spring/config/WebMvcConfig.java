package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import spring.converter.DepartmentIdToObjConverter;
import spring.converter.PosTypeIdToObjConverter;
import spring.converter.WorkerIdToObjConverter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"spring"})
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    DepartmentIdToObjConverter departmentIdToObjConverter;
    @Autowired
    PosTypeIdToObjConverter posTypeIdToObjConverter;
    @Autowired
    WorkerIdToObjConverter workerIdToObjConverter;

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

    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        return source;
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }
}
