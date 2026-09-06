package com.tuning.deciderprototype.config;

import io.modelcontextprotocol.client.transport.HttpClientStreamableHttpTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.mcp.customizer.McpClientCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpRequest;

@Configuration
public class McpClientConfiguration {

    private static final Logger log = LoggerFactory.getLogger(McpClientConfiguration.class);

    /**
     * Supplies the API Gateway API key for the MCP transport without storing the
     * secret in application configuration.
     */
    @Bean
    public McpClientCustomizer<HttpClientStreamableHttpTransport.Builder> tuningPrototypeApiGatewayApiKey(
            @Value("${STRATEGY_TUNING_API_GATEWAY_KEY}") String apiKey) {
        log.info("Applying API key customizer, key present: {}", !apiKey.isBlank());
        return (connectionName, transportBuilder) -> transportBuilder
                .requestBuilder(HttpRequest.newBuilder().header("X-API-Key", apiKey));
    }
}
