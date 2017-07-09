package com.pse;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = {
        "com.pse.*.service"
    },
    repositoryImplementationPostfix = "CustomImpl"
)
@RestController
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class Application {

    private static final int TASK_EXECUTOR_CORE_POOL_SIZE = 5;
    private static final int TASK_EXECUTOR_MAX_POOL_SIZE = 5;

    @RequestMapping(value = "/", method = GET)
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("PSE Fundamentals Core API", HttpStatus.OK);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Shared task executor for all services that extend the base service impl
     */
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(TASK_EXECUTOR_CORE_POOL_SIZE);
        taskExecutor.setMaxPoolSize(TASK_EXECUTOR_MAX_POOL_SIZE);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return taskExecutor;
    }

}
