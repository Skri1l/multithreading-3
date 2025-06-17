package org.example.state;

public class workingState implements conveyorState {
    @Override
    public void handle() {
        System.out.println("Конвейер выполняет операцию.");
    }
}