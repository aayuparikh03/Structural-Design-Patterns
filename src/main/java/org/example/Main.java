package org.example;

import java.util.ArrayList;
import java.util.List;

// Component
interface FileSystemComponent {
    void showDetails();
}

// Leaf
class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }
}

// Composite
class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Folder: " + name);
        for (FileSystemComponent component : components) {
            component.showDetails();
        }
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        // Create files
        File file1 = new File("Document1.txt");
        File file2 = new File("Image1.png");
        File file3 = new File("Video1.mp4");

        // Create folders and add files to them
        Folder rootFolder = new Folder("Root");
        Folder subFolder1 = new Folder("SubFolder1");
        Folder subFolder2 = new Folder("SubFolder2");

        rootFolder.addComponent(file1);
        subFolder1.addComponent(file2);
        subFolder2.addComponent(file3);

        rootFolder.addComponent(subFolder1);
        rootFolder.addComponent(subFolder2);

        // Show details of the entire structure
        rootFolder.showDetails();
    }
}
