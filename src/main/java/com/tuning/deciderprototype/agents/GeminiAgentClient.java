package com.tuning.deciderprototype.agents;

import com.tuning.deciderprototype.models.AgentModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GeminiAgentClient implements AgentClient {

    private static final Map<AgentModel, String> SUPPORTED = Map.of(
            AgentModel.GEMINI_3_5_FLASH, "gemini-3.5-flash",
            AgentModel.GEMINI_3_6_FLASH, "gemini-3.6-flash"
    );

    private final ChatClient _chatClient;

    public GeminiAgentClient(ChatClient.Builder chatClientBuilder, ToolCallbackProvider tools) {
        _chatClient = chatClientBuilder
                .defaultTools(tools)
                .build();
    }

    @Override
    public boolean supports(AgentModel agentModel) {
        return SUPPORTED.containsKey(agentModel);
    }

    @Override
    public void beginDecisionMaking(Prompt startingPrompt, AgentModel model) {
        System.out.println("Begin decision making for Gemini model " + model + " with starting prompt: " + startingPrompt);
        String modelName = SUPPORTED.get(model); // e.g. "claude-opus-4-7"

        ChatResponse response = _chatClient.prompt(startingPrompt)
                .options(GoogleGenAiChatOptions.builder()
                        .model(modelName))
                .call()
                .chatResponse();

        System.out.println("Gemini model " + model + " decision making response: " + response.toString());
    }
}