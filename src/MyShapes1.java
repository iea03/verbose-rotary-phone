import java.awt.*;

public class MyShapes1 {

    double[] center = new double[2];
    double radius,offset;
    double rotation;
    int cornerCount;
    double speed,weight;
    Polygon polygon = new Polygon();





    public MyShapes1(double a,double b, int cc,double weight,double radius,int rotation,double offset) {
        center[0] = a+offset;
        center[1] = b+offset;
        this.radius = radius;
        cornerCount = cc;
        this.weight = weight;
        this.offset=offset;

        double temp;
        Polygon polygon = new Polygon();
        if (cc > 0) {
            temp = (double) 360 / cc;

            for (int i = 0; i < cc; i++) {

                this.polygon.addPoint((int) (center[0] + (radius) * Math.cos(Math.toRadians(rotation + i * temp))), (int) (center[1] + radius * Math.sin(Math.toRadians(rotation + i * temp))));
            }
        } else {

            temp = (double) 360 / 4;
            for (int i = 0; i < 4; i++) {// cc must be even

                this.polygon.addPoint((int) (center[0] + (radius) * Math.cos(Math.toRadians(rotation+22.5+45*(i%2) + i * temp))), (int) (center[1] + (radius) * Math.sin(Math.toRadians(rotation+22.5+45*(i%2) + i * temp))));


            }


            return;
        }


    }}