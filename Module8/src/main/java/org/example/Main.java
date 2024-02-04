package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        Circle circle1 = new Circle(new double[]{5.0, 5.0},8);
        Parallelogram p1 = new Parallelogram(new double[]{2.0, 5.0},new double[]{8.0, 1.0});
        Rectangle r1 = new Rectangle(new double[]{1.0, 5.0},new double[]{8.0, 12.0});
        Square s1 = new Square(new double[]{4.0, 4.0}, 9);
        ShapePrinter.printShapeName(circle1);
        ShapePrinter.printShapeData(circle1);

        ShapePrinter.printShapeName(p1);
        ShapePrinter.printShapeData(p1);

        ShapePrinter.printShapeName(r1);
        ShapePrinter.printShapeData(r1);

        ShapePrinter.printShapeName(s1);
        ShapePrinter.printShapeData(s1);

    }
}