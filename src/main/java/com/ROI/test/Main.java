package com.ROI.test;

import com.ROI.test.model.User;
import com.ROI.test.model.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Arrays;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class Main extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Main.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return (evt) -> Arrays.stream("jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong,root".split(",")).
                forEach(a -> userRepository.save(new User(a, "pw", "email@gmail.com")));
    }
}
