package com.tuning.deciderprototype.agents;

import com.tuning.deciderprototype.models.AgentModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OpenAiAgentClient implements AgentClient {

    private static final Map<AgentModel, String> SUPPORTED = Map.of(
            AgentModel.GPT_5_4, "gpt-5.4",
            AgentModel.GPT_5_4_NANO, "gpt-5.4-nano",
            AgentModel.GPT_5_4_MINI, "gpt-5.4-mini",
            AgentModel.GPT_5_4_PRO, "gpt-5.4-pro",
            AgentModel.GPT_5_5, "gpt-5.5",
            AgentModel.GPT_5_5_PRO, "gpt-5.5-pro",
            AgentModel.GPT_5_6_SOL, "gpt-5.6-sol",
            AgentModel.GPT_5_6_TERRA, "gpt-5.6-terra",
            AgentModel.GPT_5_6_LUNA, "gpt-5.6-luna"
    );

    @Override
    public boolean supports(AgentModel agentModel) {
        return SUPPORTED.containsKey(agentModel);
    }

    @Override
    public void beginDecisionMaking(Prompt startingPrompt, AgentModel model) {
        System.out.println(startingPrompt);
    }
}