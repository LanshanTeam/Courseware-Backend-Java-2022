package com.example.springbootlesson.IOC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
    //@Autowired
    Engine engine;

    public Car() {
        engine = new EngineImpl();
    }

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }

   // @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void run() {
        engine.run();
    }

}

