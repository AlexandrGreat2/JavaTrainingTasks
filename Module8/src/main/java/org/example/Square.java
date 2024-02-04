package org.example;

import org.jetbrains.annotations.NotNull;

public class Square extends Rectangle implements ShapeMath {

//    public Square(double @NotNull [] point1, double @NotNull [] point3) throws Exception {
//        super(point1, point3);
//        double a = shapeHelper.distance2D(points[0], points[1]);
//        double b = shapeHelper.distance2D(points[1], points[2]);
//        if (a != b) {
//            throw new Exception("It is not Square. It is Rectangle");
//        }
//    }

    public Square(double @NotNull [] bottomLeftPoint, double sideLength) throws Exception {
        super();
        if ( bottomLeftPoint.length != this.dimensionsCount) {
            throw new Exception("Wrong vector coordinates data. Square should have 2 point with 2 coordinates X and Y");
        }
        if ( sideLength <= 0) {
            throw new Exception("It is empty square with 0 side length. Square should have length bigger than 0");
        }
        //this.sideLength = sideLength;
        this.setPoints(new double[][]{
            bottomLeftPoint,
            {bottomLeftPoint[0], bottomLeftPoint[1] + sideLength},
            {bottomLeftPoint[0] + sideLength, bottomLeftPoint[1] + sideLength},
            {bottomLeftPoint[0] + sideLength, bottomLeftPoint[1]}
        });
    }

    //todo: Override getArea and getPerimeter to fastest calculations as Square not Rectangle
}
