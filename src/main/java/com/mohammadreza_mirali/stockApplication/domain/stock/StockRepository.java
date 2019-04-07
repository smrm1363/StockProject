package com.mohammadreza_mirali.stockApplication.domain.stock;

import com.mohammadreza_mirali.stockApplication.common.Repository;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * This is a {@link Repository} for {@link StockEntity}
 */
@Component
public class StockRepository implements Repository<StockEntity> {
    /**
     * I used ConcurrentHashMap for it's concurrency support
     */
    private Map<Long,StockEntity> stockEntityMap = new ConcurrentHashMap<>();
    private Long automaticId = 0L;

    /**
     * This method stores a {@link StockEntity} in stockEntityMap. It fills Id filed automatically
     * @param stockEntity
     * @return
     */
    @Override
    @Validated
    public StockEntity save(@Valid StockEntity stockEntity)
    {
        if(stockEntity.getId()==null)
            stockEntity.setId(automaticId++);
        stockEntityMap.put(stockEntity.getId(),stockEntity);
        return stockEntity;
    }

    /**
     * This method finds a {@link StockEntity} from stockEntityMap
     * @param id
     * @return
     */
    @Override
    public Optional<StockEntity> findById(Long id) {
        if(id == null)
            return Optional.empty();
        return Optional.ofNullable(stockEntityMap.get(id));
    }

    /**
     * This method returns all elements of stockEntityMap
     * @return
     */
    @Override
    public List<StockEntity> findAll() {
        return stockEntityMap.values().stream().collect(Collectors.toList());
    }
}