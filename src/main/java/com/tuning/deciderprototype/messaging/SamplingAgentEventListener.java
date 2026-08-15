package com.tuning.deciderprototype.messaging;

import com.tuning.deciderprototype.models.SamplingAgentEvent;
import com.tuning.deciderprototype.services.AgentDispatcherService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Service;

// Kinesis event listener checking for any sampling that started, then resolves and dispatcher
@Service
public class SamplingAgentEventListener {

    private final AgentDispatcherService _agentDispatcherService;

    public SamplingAgentEventListener(AgentDispatcherService agentDispatcherService) {
        _agentDispatcherService = agentDispatcherService;
    }

//    @Bean
//    public Consumer<SamplingAgentEvent> samplingAgentEventConsumer(AgentDispatcherService agentDispatcherService) {
//        return event -> {
//            System.out.println("Received: " + event);
//            try {
//                agentDispatcherService.dispatch(event);
//            } catch (Exception e) {
//                // TODO:: Handle exceptions more gracefully
//                e.printStackTrace();
//                System.out.println("Exception occurred while trying to dispatch event: " + e.getMessage());
//            }
//        };
//    }

    @SqsListener("sampling_agent_event_queue")
    public void listen(SamplingAgentEvent event) {
        // TODO:: Improve error handling and logging
        System.out.println("Received message " + event);
        try {
            _agentDispatcherService.dispatch(event);
        } catch (Exception e) {
            // TODO:: Handle exceptions more gracefully
            e.printStackTrace();
            System.out.println("Exception occurred while trying to dispatch event: " + e.getMessage());
        }
    }
}
