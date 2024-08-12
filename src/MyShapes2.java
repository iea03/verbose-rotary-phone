import java.awt.*;

public class MyShapes2 {
    double[][]coordinates=new double[2][10]; //00 10 are center
    double scale=10;
    int points;


    public MyShapes2(double centerX,double centerY,double scale){
        coordinates[0][0]=centerX;
        coordinates[1][0]=centerY;
        this.scale = scale;






    }


    public void toRectangle(double centerX,double centerY,double offset){
        points=4;


        coordinates[0][1]=centerX+scale*offset;
        coordinates[1][1]=centerY+scale;

        coordinates[0][2]=centerX+scale*offset;
        coordinates[1][2]=centerY-scale;

        coordinates[0][3]=centerX-scale*offset;
        coordinates[1][3]=centerY-scale;

        coordinates[0][4]=centerX-scale*offset;
        coordinates[1][4]=centerY+scale;
    }
    public void toRectangle(double centerX,double centerY){
        points=4;


        coordinates[0][1]=centerX+scale;
        coordinates[1][1]=centerY+scale;

        coordinates[0][2]=centerX+scale;
        coordinates[1][2]=centerY-scale;

        coordinates[0][3]=centerX-scale;
        coordinates[1][3]=centerY-scale;

        coordinates[0][4]=centerX-scale;
        coordinates[1][4]=centerY+scale;
    }

    public Polygon toPolygon(){
        int[] tempX= new int[points];
        int[] tempY= new int[points];
        for(int i=0;i<points;i++){
            tempX[i]= (int) coordinates[0][i+1];
            tempY[i]= (int) coordinates[1][i+1];
        }

        return new Polygon(tempX,tempY,points);
    }

    public void doRotation(double rotation){



          for(int i=0;i<points;i++) {
            double temp = coordinates[0][i+1];


            coordinates[0][i+1] = (coordinates[0][0]-coordinates[0][i+1]) * Math.cos(Math.toRadians(rotation)) - (coordinates[1][0]-coordinates[1][i+1]) * Math.sin(Math.toRadians(rotation))+coordinates[0][0];
            coordinates[1][i+1] = (coordinates[0][0]-temp) * Math.sin(Math.toRadians(rotation)) + (coordinates[1][0]-coordinates[1][i+1])* Math.cos(Math.toRadians(rotation))+coordinates[1][0];

        }


        /*
        double temp = coordinates[0][1];
        coordinates[0][1] = coordinates[0][1] * Math.cos(Math.toRadians(rotation)) - coordinates[1][1] * Math.sin(Math.toRadians(rotation));
        coordinates[1][1] = temp * Math.sin(Math.toRadians(rotation)) + coordinates[1][1]* Math.cos(Math.toRadians(rotation));

        temp = coordinates[0][2];
        coordinates[0][i+1] = coordinates[0][i+1] * Math.cos(Math.toRadians(rotation)) - coordinates[1][i+1] * Math.sin(Math.toRadians(rotation));
        coordinates[1][i+1] = temp * Math.sin(Math.toRadians(rotation)) + coordinates[1][i+1]* Math.cos(Math.toRadians(rotation));

        temp = coordinates[0][3];
        coordinates[0][i+1] = coordinates[0][i+1] * Math.cos(Math.toRadians(rotation)) - coordinates[1][i+1] * Math.sin(Math.toRadians(rotation));
        coordinates[1][i+1] = temp * Math.sin(Math.toRadians(rotation)) + coordinates[1][i+1]* Math.cos(Math.toRadians(rotation));

        temp = coordinates[0][4];
        coordinates[0][i+1] = coordinates[0][i+1] * Math.cos(Math.toRadians(rotation)) - coordinates[1][i+1] * Math.sin(Math.toRadians(rotation));
        coordinates[1][i+1] = temp * Math.sin(Math.toRadians(rotation)) + coordinates[1][i+1]* Math.cos(Math.toRadians(rotation));*/


    }
    public void doRotation(double rotation, double offset){

        for(int i=0;i<points;i++) {
            double temp = coordinates[0][i+1]+offset;


            coordinates[0][i+1] = (coordinates[0][0]-coordinates[0][i+1]) * Math.cos(Math.toRadians(rotation)) - (coordinates[1][0]-coordinates[1][i+1]-offset) * Math.sin(Math.toRadians(rotation))+coordinates[0][0];
            coordinates[1][i+1] = (coordinates[0][0]-temp) * Math.sin(Math.toRadians(rotation)) + (coordinates[1][0]-coordinates[1][i+1]-offset)* Math.cos(Math.toRadians(rotation))+coordinates[1][0];

        }

    }





}
