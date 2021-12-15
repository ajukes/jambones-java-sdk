package net.vibecoms.jambones;

import net.vibecoms.jambones.dto.SipGateway;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManagementClientTests {

    private static String JAMBONES_SERVER_URL = System.getenv("JAMBONES_SERVER_URL");
    private static String JAMBONES_ADMIN_KEY = System.getenv("JAMBONES_ADMIN_KEY");

//    @Test
    void loads() {

        ManagementClient client = ManagementClient.standard()
                .endpoint(JAMBONES_SERVER_URL)
                .adminKey(JAMBONES_ADMIN_KEY)
                .build();

        assertNotNull(client);

        List<SipGateway> gatewayList = client.sipGateway().list(null);

//        assertTrue(gatewayList.size() > 0);
    }
}
