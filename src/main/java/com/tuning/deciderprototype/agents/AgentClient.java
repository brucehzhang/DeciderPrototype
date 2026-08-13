package com.tuning.deciderprototype.agents;

import com.tuning.deciderprototype.models.AgentModel;
import org.springframework.ai.chat.prompt.Prompt;

public interface AgentClient {

    boolean supports(AgentModel agentModel);

    void beginDecisionMaking(Prompt startingPrompt, AgentModel model);
}