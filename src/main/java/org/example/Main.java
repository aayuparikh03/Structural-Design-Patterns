package org.example;

// Subsystems
class CPU {
    public void freeze() {
        System.out.println("CPU freezing...");
    }

    public void execute() {
        System.out.println("CPU executing instructions...");
    }
}

class Memory {
    public void load(long position, String data) {
        System.out.println("Loading data into memory at position " + position + ": " + data);
    }
}

class HardDrive {
    public String read(long lba, int size) {
        return "Boot data";
    }
}

// Facade
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void startComputer() {
        System.out.println("Starting the computer...");
        cpu.freeze();
        String bootData = hardDrive.read(0, 512);
        memory.load(0, bootData);
        cpu.execute();
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.startComputer();
    }
}
