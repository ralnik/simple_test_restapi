package ru.example.sbertest.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import ru.example.sbertest.Application;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseConnectionTest {
    @Autowired
    protected TestRestTemplate restTemplate;

    @LocalServerPort
    private int port = 8080;

    protected String getRootUrl() {
        return "http://localhost:" + port;
    }
}
