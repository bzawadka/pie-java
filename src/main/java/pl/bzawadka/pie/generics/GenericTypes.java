package pl.bzawadka.pie.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericTypes {
    class Shape {
    }

    class Triangle extends Shape {
    }

    class Rectangle extends Shape {
    }

    public void runShowcase() {
        anySubclassIsFine();
        producerExtendsConsumerSuper();
        typeErasure();
    }

    private void anySubclassIsFine() {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Triangle());
        shapes.add(new Rectangle());
        shapes.add(new Shape());
        System.out.println("we've got some shapes here: " + shapes.size());
    }

    private void producerExtendsConsumerSuper() {
        producerExtends();
        consumerSuper();
    }

    private void producerExtends() {
        // Case 1: You want to go through the collection and do things with each item. Then the list is a producer
        List<? extends Number> numbers = List.of(42, 3.1415, 7);
        for (Number n : numbers) {
            System.out.println(n);
        }
    }

    private void consumerSuper() {
        // Case 2: You want to add things to the collection. Then the list is a consumer
        List<? super Shape> shapeSubclasses = new ArrayList<>();
        shapeSubclasses.add(new Triangle());
        shapeSubclasses.add(new Rectangle());
        shapeSubclasses.add(new Shape());
    }

    private void typeErasure() {
        List<Integer> integers = new ArrayList<>();
        List<Shape> shapes = new ArrayList<>();
        if (integers.getClass() == shapes.getClass()) {
            System.out.println("Integers are new shapes!");
        }
    }

    public static void main(String[] args) {
        new GenericTypes().runShowcase();
    }
}
