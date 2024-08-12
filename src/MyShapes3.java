import java.util.LinkedList;

public class MyShapes3 {

    //boolean moveable;
    int objectCount=0;
    double[] center= new double[2]; //0-> x, 1-> y

    double mass=1,rotation=0,speed=0,radius=2,direction=0,coTimer=0;




    public MyShapes3(double centerX, double centerY){
        this.center[0]=centerX+radius/2;        this.center[1]=centerY+radius/2;

        PhysicsHandler.MyObjects.add(new double[]{this.center[0], this.center[1], mass, radius,speed,direction,coTimer});
        PhysicsHandler.objectCount++;
    }
    public MyShapes3(double centerX, double centerY, double radius){
        this.radius=radius;
        this.center[0]=centerX+this.radius/2;        this.center[1]=centerY+this.radius/2;

        PhysicsHandler.MyObjects.add(new double[]{this.center[0], this.center[1], mass, radius,speed,direction,coTimer});
        PhysicsHandler.objectCount++;
    }

    public MyShapes3(double centerX, double centerY, double radius,double color){
        this.radius=radius;
        this.center[0]=centerX+this.radius/2;        this.center[1]=centerY+this.radius/2;

        PhysicsHandler.MyObjects.add(new double[]{this.center[0], this.center[1], mass, radius,speed,direction,coTimer,color});
        PhysicsHandler.objectCount++;
    }
//    public MyShapes3(double centerX, double centerY, double radius, double mass){
//        this.radius=radius;
//        this.center[0]=centerX+this.radius/2;        this.center[1]=centerY+this.radius/2;        this.mass=mass;
//
//        PhysicsHandler.MyObjects.add(new double[]{this.center[0], this.center[1], mass, radius,speed,direction,coTimer});
//        PhysicsHandler.objectCount++;
//    }
    public MyShapes3(double centerX, double centerY, double radius, double mass, double speed, double direction){
        this.radius=radius;
        this.center[0]=centerX+this.radius/2;        this.center[1]=centerY+this.radius/2;        this.mass=mass;
        this.speed=speed;              this.direction=direction;

        PhysicsHandler.MyObjects.add(new double[]{this.center[0], this.center[1], mass, radius,speed,direction,coTimer});
        PhysicsHandler.objectCount++;
    }




}
