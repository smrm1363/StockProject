package com.mohammadreza_mirali.stockApplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohammadreza_mirali.stockApplication.domain.stock.StockEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockIntegrationRestTest {
    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    @Test
    public void insert() {
        StockEntity stockEntity = new StockEntity("stock1", BigDecimal.valueOf(100));
        HttpEntity<StockEntity> entity = new HttpEntity<>(stockEntity, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(""),
                HttpMethod.POST, entity, String.class);
        String actual = response.getBody();
        assertTrue(Objects.requireNonNull(actual).contains("stock1"));
    }

    @Test
    public void update() throws IOException {

        StockEntity stockEntity = new StockEntity("stock1", BigDecimal.valueOf(100));
        HttpEntity<StockEntity> entity = new HttpEntity<>(stockEntity, headers);
        ResponseEntity<String> responseSave = restTemplate.exchange(
                createURLWithPort(""),
                HttpMethod.POST, entity, String.class);
        String actual = responseSave.getBody();
        HashMap<String,Object> result =
                new ObjectMapper().readValue(actual, HashMap.class);

        StockEntity stockEntity2 = new StockEntity("stock1Updated", BigDecimal.valueOf(10));
        HttpEntity<StockEntity> entity2 = new HttpEntity<>(stockEntity2, headers);
        ResponseEntity<String> responseUpdate = restTemplate.exchange(
                createURLWithPort("/"+result.get("id")),
                HttpMethod.PUT, entity2, String.class);
        String actualUpdate = responseUpdate.getBody();
        assertTrue(Objects.requireNonNull(actualUpdate).contains(result.get("id").toString()));
        assertTrue(actualUpdate.contains("stock1Updated"));
    }

    @Test
    public void find() throws IOException {
        StockEntity stockEntity = new StockEntity("stock1", BigDecimal.valueOf(100));
        HttpEntity<StockEntity> entity = new HttpEntity<>(stockEntity, headers);
        ResponseEntity<String> responseSave = restTemplate.exchange(
                createURLWithPort(""),
                HttpMethod.POST, entity, String.class);
        String actual = responseSave.getBody();
        HashMap<String,Object> result =
                new ObjectMapper().readValue(actual, HashMap.class);
        ResponseEntity<String> responseFind = restTemplate.exchange(
                createURLWithPort("/"+result.get("id")),
                HttpMethod.GET, new HttpEntity<String>(null, headers), String.class);
        String actualFind = responseFind.getBody();
        assertTrue(Objects.requireNonNull(actualFind).contains(result.get("id").toString()));
    }

    @Test
    public void findAll() throws IOException {
        StockEntity stockEntity = new StockEntity("stock1", BigDecimal.valueOf(100));
        HttpEntity<StockEntity> entity = new HttpEntity<>(stockEntity, headers);
        ResponseEntity<String> responseSave = restTemplate.exchange(
                createURLWithPort(""),
                HttpMethod.POST, entity, String.class);
        String actual = responseSave.getBody();
        HashMap<String,Object> result =
                new ObjectMapper().readValue(actual, HashMap.class);
        ResponseEntity<String> responseFind = restTemplate.exchange(
                createURLWithPort(""),
                HttpMethod.GET, new HttpEntity<String>(null, headers), String.class);
        String actualFind = responseFind.getBody();
        assertTrue(Objects.requireNonNull(actualFind).contains(result.get("id").toString()));
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/api/stocks"+uri;
    }
}