package com.mohammadreza_mirali.stockApplication.domain.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Digits;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This is an entity for stock type
 */
@ApiModel(description = "All details about the StockEntity.")
public class StockEntity implements Serializable {

    @JsonIgnore
    @ApiModelProperty(notes = "The StockEntity ID")
    private Long id;
    @NotBlank
    @ApiModelProperty(notes = "The name of the StockEntity")
    private String name;
    @PositiveOrZero
    @Digits(integer = 12, fraction = 2)
    @ApiModelProperty(notes = "The current price of the StockEntity")
    private BigDecimal currentPrice;
    @JsonIgnore
    @FutureOrPresent
    private LocalDateTime lastUpdate;
    @JsonProperty
    public Long getId() {
        return id;
    }
    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
    @JsonProperty
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    @JsonIgnore
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public StockEntity(@NotBlank String name, @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal currentPrice) {
        this.name = name;
        this.currentPrice = currentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockEntity that = (StockEntity) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                currentPrice.equals(that.currentPrice) &&
                lastUpdate.equals(that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}