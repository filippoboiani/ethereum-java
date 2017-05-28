package hello;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    // To start the geth client
    // geth --fast --cache=512 --rpcapi personal,db,eth,net,web3 --rpc --testnet

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return (String... args) -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            /*
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
            */

            // But also...
            List<String> names = Arrays.asList(ctx.getBeanDefinitionNames());
            Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
            names.forEach(name -> System.out.println(name));
        };
    }

}
