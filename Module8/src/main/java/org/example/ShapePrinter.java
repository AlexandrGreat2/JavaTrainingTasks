package org.example;

public class ShapePrinter {
    public static void printShapeName(Shape shape) {
        System.out.println(shape.getClass().getSimpleName());
    }

    public static void printShapeData(Shape shape) throws Exception {
        if (shape instanceof ShapeMath) {
            System.out.println(String.format(
                    "{type: %s; height: %f; width: %f; area: %f; perimeter: %f}",
                    shape.getClass().getSimpleName(),
                    ((ShapeMath) shape).getHeight(),
                    ((ShapeMath) shape).getWidth(),
                    ((ShapeMath) shape).getArea(),
                    ((ShapeMath) shape).getPerimeter()
            ));
        } else {
            System.out.println(String.format(
                    "Shape = {type: %s}",
                    shape.getClass().getCanonicalName()
            ));
        }
    }
}
