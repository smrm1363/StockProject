package com.mohammadreza_mirali.stockApplication.domain.stock;

import com.mohammadreza_mirali.stockApplication.common.Repository;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class StockRepositoryTest {

    private Repository<StockEntity> stockRepository;
    @Before
    public void executedBeforeEach() {
       stockRepository = new StockRepository();
    }
    @Test
    public void save() {
        StockEntity stockEntity = new StockEntity("stock1", BigDecimal.valueOf(100));
        StockEntity returnedStockEntity = stockRepository.save(stockEntity);
        assertTrue(returnedStockEntity.getId()>=0);
        assertEquals(returnedStockEntity.getName(), stockEntity.getName());
        StockEntity returnedStockEntity2 = stockRepository.save(new StockEntity("stock2",BigDecimal.valueOf(200)));
        assertNotEquals(returnedStockEntity.getId(),returnedStockEntity2.getId());

    }

    @Test
    public void findById() {
        StockEntity stockEntity = new StockEntity("stock1", BigDecimal.valueOf(100));
        StockEntity returnedStockEntity = stockRepository.save(stockEntity);
        Optional<StockEntity> foundStockEntity = stockRepository.findById(returnedStockEntity.getId());
        assertTrue(foundStockEntity.isPresent());
        assertEquals(foundStockEntity.get().getId(), returnedStockEntity.getId());
    }

    @Test
    public void findAll() {
        StockEntity returnedStockEntity = stockRepository.save(new StockEntity("stock1", BigDecimal.valueOf(100)));
        stockRepository.save(new StockEntity("stock2",BigDecimal.valueOf(200)));
        List<StockEntity> stockEntityList = stockRepository.findAll();
        assertTrue(stockEntityList.contains(returnedStockEntity));
        assertEquals(2, stockEntityList.size());
    }
}