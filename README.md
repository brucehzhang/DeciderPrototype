# DeciderPrototype

This repository is a prototype for a decider MCP client that uses the TuningPrototype skills for backtest sampling.

The MCP client connects to the API Gateway Streamable HTTP endpoint at
`https://l5qsvdpaa7.execute-api.ap-southeast-2.amazonaws.com/EC2-Prototype/mcp`.
Set its API Gateway key before starting the application:

```bash
export STRATEGY_TUNING_API_GATEWAY_KEY='api-gateway-key'
```

The key is sent as the `x-api-key` request header and is not stored in the repository.
