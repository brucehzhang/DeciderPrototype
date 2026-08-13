package com.tuning.deciderprototype.agents;

import com.tuning.deciderprototype.models.AgentModel;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class GeminiAgentClient implements AgentClient {

    private static final Set<AgentModel> SUPPORTED = Set.of(
            AgentModel.GEMINI_3_5_FLASH,
            AgentModel.GEMINI_3_6_FLASH
    );

    @Override
    public boolean supports(AgentModel agentModel) {
        return SUPPORTED.contains(agentModel);
    }
}