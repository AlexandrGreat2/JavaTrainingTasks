package org.example;

public class Circle extends Shape implements ShapeMath {

    private double[] centerPoint;
    private double radius;

    public Circle(double[] centerPoint, double radius) throws Exception {
        if (centerPoint.length != dimensionsCount) {
            throw new Exception("Wrong point coordinates data. Circle should have center point with 2 coordinates X and Y");
        }
        if (radius <= 0) {
            throw new Exception("Radius should be bigger than 0");
        }
        this.centerPoint = centerPoint;
        this.radius = radius;
    }
    @Override
    public double getHeight() throws Exception {
        return 2*radius;
    }

    @Override
    public double getWidth() throws Exception {
        return 2*radius;
    }

    @Override
    public double getArea() throws Exception {
        return Math.PI*radius*radius;
    }

    @Override
    public double getPerimeter() throws Exception {
        return 2*Math.PI*radius;
    }
}
