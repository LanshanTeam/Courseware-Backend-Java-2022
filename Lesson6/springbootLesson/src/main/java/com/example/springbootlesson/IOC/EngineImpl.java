package com.example.springbootlesson.IOC;

import org.springframework.stereotype.Component;

//@Component
class EngineImpl implements Engine {
    @Override
    public void run() {
        System.out.println("engineImpl is running....");
    }

}
