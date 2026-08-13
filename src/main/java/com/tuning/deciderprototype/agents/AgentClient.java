package com.tuning.deciderprototype.agents;

import com.tuning.deciderprototype.models.AgentModel;

public interface AgentClient {

    boolean supports(AgentModel agentModel);

}