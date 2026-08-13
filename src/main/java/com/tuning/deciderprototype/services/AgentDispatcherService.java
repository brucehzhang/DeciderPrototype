package com.tuning.deciderprototype.services;

import com.tuning.deciderprototype.agents.AgentClient;
import com.tuning.deciderprototype.exceptions.UnsupportedAgentModelException;
import com.tuning.deciderprototype.models.SamplingAgentEvent;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@Service
public class AgentDispatcherService {

    // Injects the file located at src/main/resources/prompts/BaselinePrompt.md
    @Value("classpath:prompts/BaselinePrompt.md")
    private Resource baselinePromptResource;

    private final List<AgentClient> _agentClients;

    public AgentDispatcherService(List<AgentClient> agentClients) {
        _agentClients = agentClients; // Spring injects all AgentClient beans automatically via @Component
    }

    private String getBaselinePromptString() throws IOException {
        // Safe for reading inside executable JARs/WARs
        return Files.readString(baselinePromptResource.getFile().toPath(), StandardCharsets.UTF_8);
    }

    public void dispatch(SamplingAgentEvent event) throws IOException {
        AgentClient client = _agentClients.stream()
                .filter(c -> c.supports(event.agentModel()))
                .findFirst()
                .orElseThrow(() -> new UnsupportedAgentModelException("Decider currently does not support agent model: " + event.agentModel()));

        Map<String, Object> promptVars = Map.of(
                "strategyPrompt", event.strategyPrompt(),
                "activeWalletBalances", event.experimentFinances().activeWalletBalances(),
                "sampleWindow", event.samplingWindow(),
                "samplingTime", event.samplingTime(),
                "experimentId", event.experimentId(),
                "sampleId", event.sampleId()
        );

        Prompt startingPrompt = PromptTemplate.builder()
                .template(getBaselinePromptString())
                .build()
                .create(promptVars);
        client.beginDecisionMaking(startingPrompt, event.agentModel());
    }
}
