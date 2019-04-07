package com.mohammadreza_mirali.stockApplication.domain.stock;

import com.mohammadreza_mirali.stockApplication.common.ApplicationException;
import com.mohammadreza_mirali.stockApplication.common.Repository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {

    private StockService stockService;
    @Mock
    StockRepository stockRepositoryMock;
    @Mock
    Environment envMock;
    @Before
    public void setUp() {
        stockService = new StockService(stockRepositoryMock,envMock);
    }

    @Test
    public void insert() {
        StockEntity stockEntity = new StockEntity("stock1", BigDecimal.valueOf(100));
        StockEntity stockEntity2 = new StockEntity("stock1", BigDecimal.valueOf(100));
        stockEntity2.setId(1L);
        when(stockRepositoryMock.save(stockEntity)).thenReturn(stockEntity2);
        StockEntity resultStockEntity1 = stockService.insert(stockEntity);
        assertEquals(1, (long) resultStockEntity1.getId());
        assertEquals(resultStockEntity1.getName(), stockEntity.getName());
        assertEquals(resultStockEntity1.getCurrentPrice(), stockEntity.getCurrentPrice());
            }

    @Test
    public void update() throws ApplicationException {
        StockEntity stockEntity = new StockEntity("stock1", BigDecimal.valueOf(100));
        stockEntity.setId(1L);
        when(stockRepositoryMock.findById((long) 1)).thenReturn(Optional.of(stockEntity));
        when(stockRepositoryMock.save(stockEntity)).thenReturn(stockEntity);
        StockEntity resultStockEntity1 = stockService.update(stockEntity, (long) 1);
        assertEquals(1, (long) resultStockEntity1.getId());
        assertEquals(resultStockEntity1.getName(), stockEntity.getName());
        assertEquals(resultStockEntity1.getCurrentPrice(), stockEntity.getCurrentPrice());
    }

    @Test(expected = ApplicationException.class)
    public void updateExceptionExpected() throws ApplicationException {
        StockEntity stockEntity = new StockEntity("stock1", BigDecimal.valueOf(100));
        stockEntity.setId(1L);
        when(stockRepositoryMock.findById((long) 1)).thenReturn(Optional.empty());
        stockService.update(stockEntity, (long) 1);
    }
    @Test
    public void find() throws ApplicationException {
        StockEntity stockEntity = new StockEntity("stock1", BigDecimal.valueOf(100));
        stockEntity.setId(1L);
        when(stockRepositoryMock.findById((long) 1)).thenReturn(Optional.of(stockEntity));
        StockEntity resultStockEntity1 = stockService.find((long) 1);
        assertEquals(1, (long) resultStockEntity1.getId());
        assertEquals(resultStockEntity1.getName(), stockEntity.getName());
        assertEquals(resultStockEntity1.getCurrentPrice(), stockEntity.getCurrentPrice());
    }

    @Test(expected = ApplicationException.class)
    public void findExceptionExpected() throws ApplicationException {
        StockEntity stockEntity = new StockEntity("stock1", BigDecimal.valueOf(100));
        stockEntity.setId(1L);
        when(stockRepositoryMock.findById((long) 1)).thenReturn(Optional.empty());
        stockService.find((long) 1);
    }
    @Test
    public void findAll() {
        List<StockEntity> stockEntityList = new ArrayList<>();
        StockEntity stockEntity = new StockEntity("stock1", BigDecimal.valueOf(100));
        stockEntity.setId(1L);
        StockEntity stockEntity2 = new StockEntity("stock2", BigDecimal.valueOf(100));
        stockEntity.setId(2L);
        stockEntityList.add(stockEntity);
        stockEntityList.add(stockEntity2);
        when(stockRepositoryMock.findAll()).thenReturn(stockEntityList);
        List<StockEntity> returnedStockEntityList = stockService.findAll();
        assertEquals(returnedStockEntityList.size(),stockEntityList.size());
        assertEquals(returnedStockEntityList.get(0),stockEntityList.get(0));

    }
}