package com.tuning.deciderprototype.agents;

import com.tuning.deciderprototype.models.AgentModel;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ClaudeAgentClient implements AgentClient {

    private static final Set<AgentModel> SUPPORTED = Set.of(
            AgentModel.CLAUDE_HAIKU_4_5,
            AgentModel.CLAUDE_SONNET_4_6,
            AgentModel.CLAUDE_SONNET_5,
            AgentModel.CLAUDE_OPUS_4_6,
            AgentModel.CLAUDE_OPUS_4_7,
            AgentModel.CLAUDE_OPUS_4_8,
            AgentModel.CLAUDE_OPUS_5,
            AgentModel.CLAUDE_FABLE_5
    );

    @Override
    public boolean supports(AgentModel agentModel) {
        return SUPPORTED.contains(agentModel);
    }
}
