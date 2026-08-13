package com.tuning.deciderprototype.agents;

import com.tuning.deciderprototype.models.AgentModel;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OpenAiAgentClient implements AgentClient {

    private static final Set<AgentModel> SUPPORTED = Set.of(
            AgentModel.GPT_5_4,
            AgentModel.GPT_5_4_NANO,
            AgentModel.GPT_5_4_MINI,
            AgentModel.GPT_5_4_PRO,
            AgentModel.GPT_5_5,
            AgentModel.GPT_5_5_PRO,
            AgentModel.GPT_5_6_SOL,
            AgentModel.GPT_5_6_TERRA,
            AgentModel.GPT_5_6_LUNA
    );

    @Override
    public boolean supports(AgentModel agentModel) {
        return SUPPORTED.contains(agentModel);
    }
}