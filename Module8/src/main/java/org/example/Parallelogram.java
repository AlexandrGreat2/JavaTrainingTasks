package org.example;

import org.jetbrains.annotations.NotNull;

public class Parallelogram extends Quadrangle implements ShapeMath {
    //build Parallelogram by 2 points and [0.0] point

    public Parallelogram(double @NotNull [] vector1, double @NotNull [] vector2) throws Exception {
        super();

        if ( vector1.length != this.dimensionsCount || vector2.length != this.dimensionsCount) {
            throw new Exception("Wrong vector coordinates data. Parallelogram should have 2 vector with 2 coordinates X and Y");
        }
        double[][] points = new double[4][this.dimensionsCount];
        points[0][0] = 0;
        points[0][1] = 0;
        points[1] = vector1;
        points[2] = thirdPointCalculation(vector1, vector2);
        points[3] = vector2;
        this.setPoints(points);
    }

    //to compatible with children constructors
    protected Parallelogram() throws Exception {
        super();
    }

//    private double[] allSidesLengthCalculator() throws Exception {
//        double[] result = new double[4];
//        double[] lastPoint = points[points.length-1];
//        for (int i = 0; i < points.length; i++) {
//            result[i] = shapeHelper.distance2D(lastPoint, points[i]);
//        }
//        return result;
//    }

    //calculate by 2 vectors
    private double[] thirdPointCalculation(double[] vector1, double[] vector2) {
        double[] result = new double[2];
        result[0] = vector1[0] + vector2[0];
        result[1] = vector1[1] + vector2[1];
        return result;
    }

    //calculate corner in radians between vector
    private double alphaCornerCalculation(double[][] points) {
        return Math.acos(
                (points[1][0]*points[3][0] + points[1][1]*points[3][1])
                        / (
                        Math.sqrt(Math.pow(points[1][0],2) + Math.pow(points[1][1],2))
                                * Math.sqrt(Math.pow(points[3][0],2) + Math.pow(points[3][1],2))
                )
        );
    }

    @Override
    public double getArea() throws Exception {
        double alpha = alphaCornerCalculation(points);
        double a = shapeHelper.distance2D(points[0], points[1]);
        double b = shapeHelper.distance2D(points[0], points[3]);
        return a*b*Math.sin(alpha);
    }
}
