package org.example;

public final class ShapeHelper {

    public ShapeHelper() {
    }

    double distance2D(double[] point1, double[] point2) throws Exception {
        if (point1.length != 2 || point2.length != 2) {
            throw new Exception("Wrong point coordinates data. Should be use 2d points with 2 coordinates X and Y");
        }
        return Math.sqrt(
                Math.pow(point1[0]-point2[0],2) + Math.pow(point1[1]-point2[1],2)
        );
    }
}
