package com.hospital.animal.cl;

import com.hospital.animal.cl.dto.Client;
import com.hospital.animal.cl.dto.Coordinates;
import com.hospital.animal.cl.dto.Location;
import com.hospital.animal.cl.services.firebase.impl.ClientServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest()
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientTest {

    @Autowired
    ClientServiceImpl clientService;
    private static Client client;
    private static final Integer AGE=19;
    private static final String EMAIL="example@gmail.com";
    private static final String NAME="Cristobal";
    private static final Double LATITUDE=-73.12;
    private static final Double LONGITUDE=-42.342;
    private static final Coordinates COORDINATES = new Coordinates(LATITUDE,LONGITUDE);
    private static final Location LOCATION =new Location("ppm 10",COORDINATES);
    private static final String AVATAR ="https://ui-avatars.com/api/?name="+NAME;

    @BeforeAll
    public static void setup(){
      client= new Client();
        client.setAge(AGE);
        client.setEmail(EMAIL);
        client.setName(NAME);
        client.setLocation(LOCATION);
        client.setAvatar(AVATAR);

    }
    @Order(1)
    @Test
    void createClient() throws Exception {
        client = clientService.create(client);
        Assertions.assertNotNull(client.getUid());
    }
    @Order(2)
    @Test
    void getAllClient() throws InterruptedException, ExecutionException {
        List<Client> clients = clientService.getAll();
        Assertions.assertFalse(clients.isEmpty());
        clients.forEach((c)->{
            Assertions.assertEquals(AGE,c.getAge());
            Assertions.assertEquals(NAME,c.getName());
            Assertions.assertEquals(EMAIL,c.getEmail());
            Assertions.assertEquals(LOCATION,c.getLocation());
            Assertions.assertEquals(AVATAR,c.getAvatar());
            Assertions.assertEquals(LOCATION.getAddress(),c.getLocation().getAddress());
            Assertions.assertEquals(LATITUDE,c.getLocation().getCoordinates().getLat());
            Assertions.assertEquals(LONGITUDE,c.getLocation().getCoordinates().getLng());
            Assertions.assertNotNull(c.getUid());
        });
    }
    @Order(3)
    @Test
    void getClient() throws InterruptedException, ExecutionException {
        Client client1 = clientService.get(client.getUid());
        Assertions.assertEquals(AGE,client1.getAge());
        Assertions.assertEquals(NAME,client1.getName());
        Assertions.assertEquals(EMAIL,client1.getEmail());
        Assertions.assertEquals(LOCATION,client1.getLocation());
        Assertions.assertEquals(AVATAR,client1.getAvatar());
        Assertions.assertEquals(LOCATION.getAddress(),client1.getLocation().getAddress());
        Assertions.assertEquals(LATITUDE,client1.getLocation().getCoordinates().getLat());
        Assertions.assertEquals(LONGITUDE,client1.getLocation().getCoordinates().getLng());
        Assertions.assertNotNull(client1.getUid());
          Assertions.assertThrows(RuntimeException.class,()->{
             clientService.get(null);
          });
    }

    @Order(4)
    @Test
    void editClient() throws InterruptedException, ExecutionException {
        Integer age = client.getAge();
        Integer newAge = age+10;
        client.setAge(newAge);
        clientService.update(client);
        Client client1 = clientService.get(client.getUid());
        Assertions.assertEquals(client1.getAge(),newAge);
        client1.setAge(age);
        clientService.update(client1);
        Assertions.assertEquals(client1.getAge(),age);
    }

    @Order(5)
    @Test
    void deleteClient() throws InterruptedException {
        Assertions.assertTrue(clientService.delete(client.getUid()));
    }
}
