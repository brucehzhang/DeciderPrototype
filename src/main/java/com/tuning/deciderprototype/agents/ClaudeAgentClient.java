package com.tuning.deciderprototype.agents;

import com.tuning.deciderprototype.models.AgentModel;
import org.springframework.ai.anthropic.AnthropicChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ClaudeAgentClient implements AgentClient {

    private static final Map<AgentModel, String> SUPPORTED = Map.of(
            AgentModel.CLAUDE_HAIKU_4_5, "claude-haiku-4.5",
            AgentModel.CLAUDE_SONNET_4_6, "claude-sonnet-4.6",
            AgentModel.CLAUDE_SONNET_5, "claude-sonnet-5",
            AgentModel.CLAUDE_OPUS_4_6, "claude-opus-4.6",
            AgentModel.CLAUDE_OPUS_4_7, "claude-opus-4.7",
            AgentModel.CLAUDE_OPUS_4_8, "claude-opus-4.8",
            AgentModel.CLAUDE_OPUS_5, "claude-opus-5",
            AgentModel.CLAUDE_FABLE_5, "claude-fable-5"
    );

    private final ChatClient _chatClient;

    public ClaudeAgentClient(@Qualifier("anthropicChatClient") ChatClient chatClient) {
        _chatClient = chatClient;
    }

    @Override
    public boolean supports(AgentModel agentModel) {
        return SUPPORTED.containsKey(agentModel);
    }

    @Override
    public void beginDecisionMaking(Prompt startingPrompt, AgentModel model) {
        System.out.println("Begin decision making for Claude model " + model + " with starting prompt: " + startingPrompt);
        String modelName = SUPPORTED.get(model);

        ChatResponse response = _chatClient.prompt(startingPrompt)
                .options(AnthropicChatOptions.builder()
                        .model(modelName))
                .call()
                .chatResponse();

        System.out.println("Claude model " + model + " decision making response: " + response.toString());
    }
}
