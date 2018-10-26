# sampleJavaZeroMQChat

## Start server
  mvn exec:java -Djava.rmi.server.useCodebaseOnly=false \ 
  -Djava.security.policy=policy \
  -Dexec.mainClass=server.PresenceService
  
## Start Client
  mvn exec:java -Djava.rmi.server.useCodebaseOnly=false \
  -Djava.security.policy=policy \ 
  -Dexec.mainClass=client.Client -Dexec.args="localhost username port_number"
