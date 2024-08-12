import java.awt.*;

public class MyShapes {
    int[] x= new int[3];
    int[] y= new int[3];
    int[][] square = new int[5][3];
    double weight;
    double speed;

    public MyShapes(int x1,int y1,int x2,int y2){

        create(x1,y1,x2,y2);





    }



    public void create(int x1,int y1,int x2,int y2) //line
    {
        x[0]=x1;
        x[1]=x2;
        y[0]=y1;
        y[1]=y2;


    }
    public void toRectangle(){
        int width = x[0]-x[1];
        int height = y[0]-y[1];
        int length = (int) Math.sqrt(Math.pow(width,2)+Math.pow(height,2));

        square[0][0]=x[0];
        square[0][1]=y[0];
        square[1][0]=x[0]+length;
        square[1][1]=y[0];
        square[2][0]=x[0]+length;
        square[2][1]=y[0]+length;
        square[3][0]=x[0];
        square[3][1]=y[0]+length;
        square[4][0]=x[0];
        square[4][1]=y[0];

    }





}
