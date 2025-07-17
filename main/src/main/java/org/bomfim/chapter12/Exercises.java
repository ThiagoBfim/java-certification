package org.bomfim.chapter12;

import org.bomfim.animal.AnimalA;
import org.bomfim.global.GlobalTask;
import org.bomfim.service.AnimalService;

import java.util.List;
import java.util.ServiceLoader;

public class Exercises {

    public static void main(String[] args) {
        new AnimalA();
        new GlobalTask();
        ServiceLoader<AnimalService> animalServiceLoader = ServiceLoader.load(org.bomfim.service.AnimalService.class);

//        new org.bomfim.serviceanimal.AnimalServiceImpl(); //DOES NOT COMPILE, not exported
        System.out.println(animalServiceLoader.stream().count());
        for (AnimalService animalService : animalServiceLoader) {
            String food = animalService.getFood();
            System.out.println(food);
        }

        List<AnimalService> services = ServiceLoader.load(AnimalService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .toList();

        List<ServiceLoader.Provider<AnimalService>> providers = ServiceLoader.load(AnimalService.class)
                .stream()
//                .map(ServiceLoader.Provider::get)
                .toList();
//        new org.bomfim.task.Task(); //DOES NOT COMPILE, not exported
    }
}
