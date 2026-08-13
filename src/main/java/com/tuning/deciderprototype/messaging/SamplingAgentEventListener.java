package com.tuning.deciderprototype.messaging;

import com.tuning.deciderprototype.models.SamplingAgentEvent;
import com.tuning.deciderprototype.services.AgentDispatcherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

// Kinesis event listener checking for any sampling that started, then resolves and dispatcher
@Configuration
public class SamplingAgentEventListener {

    @Bean
    public Consumer<SamplingAgentEvent> samplingAgentEventConsumer(AgentDispatcherService agentDispatcherService) {
        return event -> {
            System.out.println("Received: " + event);
            try {
                agentDispatcherService.dispatch(event);
            } catch (Exception e) {
                // TODO:: Handle exceptions more gracefully
                e.printStackTrace();
                System.out.println("Exception occurred while trying to dispatch event: " + e.getMessage());
            }
        };
    }
}
