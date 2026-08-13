package com.tuning.deciderprototype.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuration class for all the chat client beans needed for the agents.
@Configuration
public class ChatClientConfiguration {

    @Bean
    public ChatClient anthropicChatClient(@Qualifier("anthropicChatModel") ChatModel chatModel, ToolCallbackProvider tools) {
        return ChatClient.builder(chatModel)
                .defaultTools(tools)
                .build();
    }

    @Bean
    public ChatClient geminiChatClient(@Qualifier("googleGenAiChatModel") ChatModel chatModel, ToolCallbackProvider tools) {
        return ChatClient.builder(chatModel)
                .defaultTools(tools)
                .build();
    }

    @Bean
    public ChatClient openAiChatClient(@Qualifier("openAiChatModel") ChatModel chatModel, ToolCallbackProvider tools) {
        return ChatClient.builder(chatModel)
                .defaultTools(tools)
                .build();
    }
}
