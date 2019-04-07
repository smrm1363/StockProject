package com.mohammadreza_mirali.stockApplication.domain.stock;

import com.mohammadreza_mirali.stockApplication.common.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This is our business logic for stock entity
 */
@Service
public class StockService {
    private final StockRepository stockRepository;
    private final Environment env;

    @Autowired
    public StockService(StockRepository stockRepository, Environment env) {
        this.stockRepository = stockRepository;
        this.env = env;
    }

    /**
     * This method inserts a {@link StockEntity}
     * @param stockEntity
     * @return with ID
     */
    public StockEntity insert(StockEntity stockEntity) {
        stockEntity.setLastUpdate(LocalDateTime.now());
        return stockRepository.save(stockEntity);
    }

    /**
     * This method updates a {@link StockEntity}
     * @param stockEntity
     * @param id
     * @return
     * @throws ApplicationException if {@link StockEntity} does not exist
     */
    public StockEntity update(StockEntity stockEntity,Long id) throws ApplicationException {
        StockEntity foundStockEntity = find(id);
        foundStockEntity.setName(stockEntity.getName());
        foundStockEntity.setCurrentPrice(stockEntity.getCurrentPrice());
        foundStockEntity.setLastUpdate(LocalDateTime.now());
        return stockRepository.save(foundStockEntity);
    }

    /**
     * This method finds an {@link StockEntity} by ID
     * @param id
     * @return
     * @throws ApplicationException if {@link StockEntity} does not exist
     */
    public StockEntity find(Long id) throws ApplicationException {
        return stockRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(env.getProperty("domain.Stock.StockNotFound")));
    }

    /**
     * This method find all saved {@link StockEntity}
     * @return
     */
    public List<StockEntity> findAll() {
        return stockRepository.findAll();
    }

}
