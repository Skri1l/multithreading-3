package org.example.state;


public class readyState implements conveyorState {
    @Override
    public void handle() {
        System.out.println("Конвейер в состоянии готовности.");
    }
}
