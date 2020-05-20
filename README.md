jambones-java-sdk
=================

SDK for connecting to the Jambones Platform. 

### Spring Boot Quick Start
#### application.properties

    jambones.serverUrl="http://acme.com:3000"
    jambones.adminKey="1234"

#### Example Service

    @Service
    public class MyService {
    
        private final ManagementClient client;
    
        public MyService(ManagementClient client) {
            this.client = client;
        }
    
        public List<SipGateway> listSipGateways() {
            return client.sipGateway().list();
        }
        
    }
    
    @Configuration
    class JambonesConfig {
    
        @Value("${jambones.adminKey}")
        private String adminKey;
      
        @Value("${jambones.serverUrl}")
        private String serverUrl;
      
        @Bean
        public ManagementClient managementClient() {
          return ManagementClient.standard()
                .adminKey(adminKey)
                .endpoint(serverUrl)
                .build();
        }
    }
        
