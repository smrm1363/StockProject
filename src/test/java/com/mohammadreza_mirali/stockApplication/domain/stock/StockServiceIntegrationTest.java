package com.mohammadreza_mirali.stockApplication.domain.stock;

import com.mohammadreza_mirali.stockApplication.common.ApplicationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class StockServiceIntegrationTest {

    @Autowired
    StockService stockService;
    @Test
    public void insert() {

        StockEntity stockEntity = new StockEntity("stock1", BigDecimal.valueOf(100));
        StockEntity returnedStockEntity = stockService.insert(stockEntity);
        assertTrue(returnedStockEntity.getId()>=0);
    }

    @Test
    public void update() throws ApplicationException {
        StockEntity stockEntity = new StockEntity("stock1", BigDecimal.valueOf(100));
        StockEntity returnedStockEntity = stockService.insert(stockEntity);
        returnedStockEntity.setName("updatedStock");
        StockEntity updatedStockEntity = stockService.update(returnedStockEntity,returnedStockEntity.getId());
        assertEquals(updatedStockEntity.getName(),"updatedStock");
    }

    @Test
    public void findAll() {
        StockEntity stockEntity = new StockEntity("stock1", BigDecimal.valueOf(100));
        StockEntity returnedStockEntity = stockService.insert(stockEntity);
        List<StockEntity> stockEntityList = stockService.findAll();
        assertTrue(stockEntityList.contains(returnedStockEntity));
    }
}