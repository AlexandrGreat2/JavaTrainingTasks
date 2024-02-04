package org.example;

import java.util.Arrays;

import static java.util.Arrays.copyOf;

public abstract class Quadrangle extends Shape {

    //x is points[0][]
    //y is points[1][]
    protected double[][] points; //new int[4][2];
    //dimensions count (x,y)

    //todo check is this is real Quadrangle created
    public Quadrangle(double[][] points) throws Exception {
        this.setPoints(points);
    }

    //to compatible with children constructors
    protected Quadrangle() throws Exception {

    }

    public void setPoints(double[][] points) throws Exception {
        if (points.length != 4) {
            throw new Exception("Wrong point numbers. Quadrangle should have 4 points");
        }
        if (points[0].length != dimensionsCount) {
            throw new Exception("Wrong point coordinates data. Quadrangle should have 4 points with 2 coordinates X and Y");
        }

        this.points = points;
    }

    private double[][] getPoints() {
        return points;
    }

    private double[] getCoordinatesExtremum(int dimension) throws Exception {
        if (this.dimensionsCount <= dimension) {
            throw new Exception("Wrong dimension. It is only 2d shape, you should use only 0 for x, and 1 for y");
        }
        double[] coordinates = new double[4];
        for (int i=0; i < points.length; i++) {
            coordinates[i] = points[i][dimension];
        }
        double min = Arrays.stream(coordinates).min().getAsDouble();
        double max = Arrays.stream(coordinates).max().getAsDouble();
        return new double[] {min, max};
    }

    public double getHeight() throws Exception {
        double[] minimumAndMaximum = this.getCoordinatesExtremum(1);
        return (minimumAndMaximum[1] - minimumAndMaximum[0]);
    }

    public double getWidth() throws Exception {
        double[] minimumAndMaximum = this.getCoordinatesExtremum(0);
        return (minimumAndMaximum[1] - minimumAndMaximum[0]);
    }

    public double getPerimeter() throws Exception {
        double result = 0;
        double[] lastPoint = points[points.length-1];
        for (double[] point: points) {
            result += shapeHelper.distance2D(lastPoint, point);
        }
        return result;
    }
}
