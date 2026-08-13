package com.tuning.deciderprototype.services;

import com.tuning.deciderprototype.agents.AgentClient;
import com.tuning.deciderprototype.exceptions.UnsupportedAgentModelException;
import com.tuning.deciderprototype.models.SamplingAgentEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentDispatcherService {
    private final List<AgentClient> agentClients;

    public AgentDispatcherService(List<AgentClient> agentClients) {
        this.agentClients = agentClients; // Spring injects all AgentClient beans automatically via @Component
    }

    public void dispatch(SamplingAgentEvent event) {
        AgentClient client = agentClients.stream()
                .filter(c -> c.supports(event.agentModel()))
                .findFirst()
                .orElseThrow(() -> new UnsupportedAgentModelException("Decider currently does not support agent model: " + event.agentModel()));

        // TODO:: Design baseline prompt here
        //  Step 1: Read strategy and sampling event data
        //  Step 2: Use tool chain to read market news data and create marketInsight for Sample
        //  Step 3: Make decisions on market insights and current experiment finances for purchases of new/existing stocks, sales of existing stocks, or hold reasons
        //  Step 4: Complete the sample
    }
}
