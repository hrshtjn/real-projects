package com.stockworks.stockdatasimulatorservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockData {
    private String name;
    private double price;

    public StockData(String name, double price) {
        this.name = name;
        this.price = price;
    }


}
