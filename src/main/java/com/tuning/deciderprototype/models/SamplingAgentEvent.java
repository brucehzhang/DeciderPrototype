package com.tuning.deciderprototype.models;

// TODO:: This comes from the tuning server model, needs to be imported instead
public record SamplingAgentEvent(
        Long experimentId,
        String strategyPrompt,
        AgentModel agentModel,
        Long sampleId,
        Long samplingTime,
        ExperimentFinances experimentFinances) {
}
