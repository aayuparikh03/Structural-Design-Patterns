package org.example;
// Subject Interface
interface Image {
    void display();
}
// Real Subject
class RealImage implements Image {
    private final String filename;
    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }
    private void loadFromDisk() {
        System.out.println("Loading image from disk: " + filename);
    }
    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

// Proxy
class ProxyImage implements Image {
    private final String filename;
    private RealImage realImage;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename); // Load the image only when needed
        }
        realImage.display();
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.png");

        System.out.println("Accessing image1 for the first time:");
        image1.display(); // Image is loaded from disk

        System.out.println("\nAccessing image1 again:");
        image1.display(); // Image is not loaded again

        System.out.println("\nAccessing image2 for the first time:");
        image2.display(); // Image is loaded from disk
    }
}
