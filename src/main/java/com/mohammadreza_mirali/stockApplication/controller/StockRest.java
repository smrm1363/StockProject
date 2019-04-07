package com.mohammadreza_mirali.stockApplication.controller;

import com.mohammadreza_mirali.stockApplication.common.ApplicationException;
import com.mohammadreza_mirali.stockApplication.domain.stock.StockEntity;
import com.mohammadreza_mirali.stockApplication.domain.stock.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;
import java.util.List;

/**
 * This is a REST controller for stock entity, it support CRUD operations
 */
@RestController
@RequestMapping(path = "/api/stocks")
@Api(value = "stock Store API", description = "Simple CRUD operations for a stock store")
public class StockRest extends HttpServlet {
    private final StockService stockService;

    @Autowired
    public StockRest(StockService stockService) {
        this.stockService = stockService;
    }

    @ApiOperation(value = "Finds one available stock by it's ID", response = StockEntity.class)
    @GetMapping("/{id}")
    public ResponseEntity<StockEntity> getFruit(@ApiParam(value = "stock id from which stock object will retrieve", required = true) @PathVariable("id") Long id) throws ApplicationException {
        return ResponseEntity.ok(stockService.find(id));
    }

    @ApiOperation(value = "Finds all available Fruits", response = List.class)
    @GetMapping()
    public ResponseEntity<List<StockEntity>> getFruitList() {
        return ResponseEntity.ok(stockService.findAll());
    }

    @ApiOperation(value = "Inserts a stock. It also inserts or update a FruitTypeEntity", response = StockEntity.class)
    @PostMapping()
    public ResponseEntity<StockEntity> storeFruit(@ApiParam(value = "New StockEntity", required = true) @RequestBody @Valid StockEntity stockEntity) {
        return ResponseEntity.ok(stockService.insert(stockEntity));
    }

    @ApiOperation(value = "Updates a stock.")
    @PutMapping(path = "{id}")
    public ResponseEntity<StockEntity> update(@ApiParam(value = "Existed StockEntity", required = true) @RequestBody @Valid StockEntity stockEntity,
                                              @ApiParam(value = "stock id", required = true) @PathVariable("id") Long id) throws ApplicationException {
        stockEntity.setId(id);
        return ResponseEntity.ok(stockService.update(stockEntity,id));
    }



}