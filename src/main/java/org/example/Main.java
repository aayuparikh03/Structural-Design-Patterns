package org.example;

import java.util.HashMap;
import java.util.Map;

// Flyweight
class TreeType {
    private final String name;
    private final String color;
    private final String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void draw(int x, int y) {
        System.out.println("Drawing a " + name + " tree of color " + color + " with texture " + texture + " at (" + x + ", " + y + ")");
    }
}

// Flyweight Factory
class TreeTypeFactory {
    private static final Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + "-" + color + "-" + texture;
        if (!treeTypes.containsKey(key)) {
            treeTypes.put(key, new TreeType(name, color, texture));
        }
        return treeTypes.get(key);
    }
}

// Context
class Tree {
    private final int x;
    private final int y;
    private final TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw() {
        type.draw(x, y);
    }
}

// Client
class Forest {
    private final Map<Integer, Tree> trees = new HashMap<>();
    private int treeCount = 0;

    public void plantTree(int x, int y, String name, String color, String texture) {
        TreeType type = TreeTypeFactory.getTreeType(name, color, texture);
        Tree tree = new Tree(x, y, type);
        trees.put(treeCount++, tree);
    }

    public void drawTrees() {
        for (Tree tree : trees.values()) {
            tree.draw();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Forest forest = new Forest();

        // Planting trees
        forest.plantTree(1, 1, "Oak", "Green", "Rough");
        forest.plantTree(2, 3, "Pine", "Dark Green", "Smooth");
        forest.plantTree(3, 5, "Oak", "Green", "Rough");
        forest.plantTree(4, 7, "Pine", "Dark Green", "Smooth");
        forest.plantTree(5, 9, "Oak", "Green", "Rough");

        // Drawing the trees
        forest.drawTrees();
    }
}
