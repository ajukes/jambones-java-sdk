package net.vibecoms.jambones;

public class MainApplication {
    public static void main(String[] args) {
        ManagementClient client = ManagementClient.standard()
                .endpoint("http://15.236.86.78:3000")
                .login("admin", "V1bep@55")
                .build();

        client.sipGateway().list().forEach(System.out::println);
    }
}
