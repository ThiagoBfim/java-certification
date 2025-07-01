package org.bomfim.serviceanimal;

import org.bomfim.service.AnimalService;

public class AnimalServiceImpl implements AnimalService {
    @Override
    public String getFood() {
        return "Food from main module";
    }
}
