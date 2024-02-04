package org.example;

import org.jetbrains.annotations.NotNull;

public class Rectangle extends Parallelogram implements ShapeMath {

    //create as 2 another side point
    public Rectangle(double @NotNull [] point1, double @NotNull [] point3) throws Exception {
        super();
        if ( point1.length != this.dimensionsCount || point3.length != this.dimensionsCount) {
            throw new Exception("Wrong vector coordinates data. Rectangle should have 2 point with 2 coordinates X and Y");
        }
        this.setPoints(new double[][]{point1,secondPointCalculation(point1, point3),point3,fourPointCalculation(point1, point3)});
    }

    //to compatible with children constructor
    protected Rectangle() throws Exception {
        super();
    }

    @Override
    public double getArea() throws Exception {
        double a = shapeHelper.distance2D(points[0], points[1]);
        double b = shapeHelper.distance2D(points[1], points[2]);
        return a*b;
    }

    private double[] secondPointCalculation(double[] point1, double[] point3) {
        return new double[] {point1[0],point3[1]};
    }

    private double[] fourPointCalculation(double[] point1, double[] point3) {
        return new double[] {point3[0],point1[1]};
    }
}
