package com.tuning.deciderprototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeciderPrototypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeciderPrototypeApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner predefinedQuestions(ChatClient.Builder chatClientBuilder, ToolCallbackProvider tools,
//                                                 ConfigurableApplicationContext context) {
//
//        return args -> {
//
//            var chatClient = chatClientBuilder
//                    .defaultTools(tools)
//                    .build();
//
//            System.out.println("\n>>> QUESTION: " + "What tools are available?");
//            System.out.println("\n>>> ASSISTANT: " + chatClient.prompt("What tools are available?").call().content());
//
//            context.close();
//        };
//    }
}
