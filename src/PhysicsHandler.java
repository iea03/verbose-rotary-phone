import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PhysicsHandler extends PhysicsShortcuts implements Runnable{

    Thread physicsThread;
    boolean physicsThreadRunning=false;
    public static boolean lessCollision=false;
    static LinkedList<double[]> MyObjects = new LinkedList<double[]>();  // center X, center Y, mass, radius, speed, direction, inner color
    static int objectCount=0;
    static double gravity=90.81;
    static boolean doGravity=false,pause=false;
    static boolean lock1=false,lock2=false;
    public static double FPS=120,temp=0;
    public static double[] boundsX= new double[2];
    public static double[] boundsY= new double[2];
    int tempCounter=0;
    double maxSpeed =3000;
    public static int temp123=0;

    KeyHandler keyH2 = new KeyHandler();



    public PhysicsHandler(double[] screenWH){
        boundsX[0]=0;   boundsY[0]=0;
        boundsX[1]=screenWH[0]; boundsY[1]=screenWH[1];
        System.out.println(boundsX[1]);
        System.out.println(boundsY[1]);

    }



    public void collisionSolve(int i, int j, double nearlimit,double distance){
        double deltaX =MyObjects.get(i)[getCenterX(i)]-MyObjects.get(j)[getCenterX(j)];
        double deltaY =MyObjects.get(i)[getCenterY(i)]-MyObjects.get(j)[getCenterY(j)];

        double eqVector =(Math.sqrt(Math.pow(deltaY,2)+Math.pow(deltaX,2)));
        double directionF =Math.toDegrees((Math.atan2(deltaY,deltaX)));
        double temp2;

        if(MyObjects.get(i)[getCenterY(i)]>850&&lessCollision){
                temp2=0.09;
        }else{
            if(MyObjects.get(i)[getCenterY(i)]>800&&lessCollision){
                temp2=0.015;
            }else{
                temp2=0;
            }
        }
        forceApply(Math.sqrt(Math.abs(Math.pow(eqVector-nearlimit+0.5,3))),directionF+Math.random()*2-1,i,0.996-temp2);
        forceApply(Math.sqrt(Math.abs(Math.pow(eqVector-nearlimit+0.5,3))),directionF+180,j,0.996-temp2);

//        MyObjects.get(i)[getCenterX(i)]+=deltaX/5;
//        MyObjects.get(j)[getCenterX(j)]-=deltaX/5;
//
//        MyObjects.get(i)[getCenterY(i)]+=deltaY/5;
//        MyObjects.get(j)[getCenterY(j)]-=deltaY/5;
    }

    public void collisionDetect() {
        tempCounter++;
        if (!lessCollision||tempCounter%4>0) {

            double distance = 0;
            double overlap = 0;
            double nearLimit = 0;
            for (int i = 0; i < PhysicsHandler.objectCount; i++) {
//            if((MyObjects.get(i)[getCenterX(i)]<(boundsX[0]+MyObjects.get(i)[getRadius(i)]))||false&&(MyObjects.get(i)[getCenterX(i)]>(boundsX[1]-MyObjects.get(i)[getRadius(i)]))){
//                //MyObjects.get(i)[getDirection(i)]=180+MyObjects.get(i)[getDirection(i)];
//            }
                if (MyObjects.get(i)[getCenterX(i)] < (boundsX[0] + MyObjects.get(i)[getRadius(i)] / 2)) { //left wall
                    MyObjects.get(i)[getCenterX(i)] = boundsX[0] + MyObjects.get(i)[getRadius(i)] / 2;
                    double temp1 = Math.abs(Math.cos(Math.toRadians(MyObjects.get(i)[getDirection(i)])));
                    double temp2 = Math.sin(Math.toRadians(MyObjects.get(i)[getDirection(i)]));
                    MyObjects.get(i)[getDirection(i)] = Math.toDegrees(Math.atan2(temp2, temp1));
                }
                if (MyObjects.get(i)[getCenterX(i)] > (boundsX[1] - MyObjects.get(i)[getRadius(i)] / 2)) { //right wall
                    MyObjects.get(i)[getCenterX(i)] = boundsX[1] - MyObjects.get(i)[getRadius(i)] / 2;
                    double temp1 = -Math.abs(Math.cos(Math.toRadians(MyObjects.get(i)[getDirection(i)])));
                    double temp2 = Math.sin(Math.toRadians(MyObjects.get(i)[getDirection(i)]));
                    MyObjects.get(i)[getDirection(i)] = Math.toDegrees(Math.atan2(temp2, temp1));
                }
//
//
//            if((MyObjects.get(i)[getCenterY(i)]<(boundsY[0]+MyObjects.get(i)[getRadius(i)]))||(MyObjects.get(i)[getCenterY(i)]>(boundsY[1]-MyObjects.get(i)[getRadius(i)]))){
//                MyObjects.get(i)[getDirection(i)]=-180+MyObjects.get(i)[getDirection(i)]*2;
//            }

//            if(MyObjects.get(i)[getCenterY(i)]<(boundsY[0]+MyObjects.get(i)[getRadius(i)])){
//            }

//            if(MyObjects.get(i)[getCenterY(i)]>(boundsY[1]-MyObjects.get(i)[getRadius(i)]/2)){ //bottom collision
//                double temp1= Math.cos(Math.toRadians(MyObjects.get(i)[getDirection(i)]));
//                double temp2= -Math.abs(Math.sin(Math.toRadians(MyObjects.get(i)[getDirection(i)])));
//                MyObjects.get(i)[getDirection(i)]=Math.toDegrees(Math.atan2(temp2,temp1));
//                //forceApply(Math.pow((MyObjects.get(i)[getCenterY(i)]-boundsY[1])/MyObjects.get(i)[getRadius(i)],2),90,i,1);

                //bottom
                if (MyObjects.get(i)[getCenterY(i)] > (boundsY[1] - MyObjects.get(i)[getRadius(i)] / 2)) {

                    MyObjects.get(i)[getCenterY(i)] = boundsY[1] - MyObjects.get(i)[getRadius(i)] / 2;


                    MyObjects.get(i)[getDirection(i)] = -Math.abs(MyObjects.get(i)[getDirection(i)]);
                    MyObjects.get(i)[getSpeed(i)] *= 0.95;


                }
                //top
                if (MyObjects.get(i)[getCenterY(i)] < (boundsY[0] + MyObjects.get(i)[getRadius(i)] / 2)) {
                    double temp1 = Math.cos(Math.toRadians(MyObjects.get(i)[getDirection(i)]));
                    double temp2 = Math.abs(Math.sin(Math.toRadians(MyObjects.get(i)[getDirection(i)] + 180)));
                    MyObjects.get(i)[getDirection(i)] = Math.toDegrees(Math.atan2(temp2, temp1));
                }


                for (int j = 0; j < PhysicsHandler.objectCount; j++) {


                    distance = Math.sqrt(Math.pow((MyObjects.get(i)[getCenterX(i)] - MyObjects.get(j)[getCenterX(j)]), 2) + Math.pow(MyObjects.get(i)[getCenterY(i)] - MyObjects.get(j)[getCenterY(j)], 2));
                    nearLimit = (MyObjects.get(i)[getRadius(i)] + MyObjects.get(j)[getRadius(j)]) / 2;
                    //overlap= distance - (MyObjects.get(i)[getRadius(i)]+MyObjects.get(j)[getRadius(j)])/2;

                    if (distance < nearLimit) {
                        if (i != j) {
                            collisionSolve(i, j, nearLimit, distance);
                        }
                    }

                }
            }
        }else if(tempCounter%2==0){
            double distance = 0;
            double overlap = 0;
            double nearLimit = 0;
            for (int i = 0; i < PhysicsHandler.objectCount; i++) {


                for (int j = 0; j < PhysicsHandler.objectCount; j++) {
                    if(MyObjects.get(i)[getCenterY(i)]>650-temp123){
                        System.out.println("Physics resolution below 600 are halved");
                        return;
                    }
                    System.out.println("bruh");


                    distance = Math.sqrt(Math.pow((MyObjects.get(i)[getCenterX(i)] - MyObjects.get(j)[getCenterX(j)]), 2) + Math.pow(MyObjects.get(i)[getCenterY(i)] - MyObjects.get(j)[getCenterY(j)], 2));
                    nearLimit = (MyObjects.get(i)[getRadius(i)] + MyObjects.get(j)[getRadius(j)]) / 2;
                    //overlap= distance - (MyObjects.get(i)[getRadius(i)]+MyObjects.get(j)[getRadius(j)])/2;

                    if (distance < nearLimit) {
                        if (i != j) {
                            collisionSolve(i, j, nearLimit, distance);
                        }
                    }

                }
            }
        }else{
            double distance = 0;
            double overlap = 0;
            double nearLimit = 0;
            for (int i = 0; i < PhysicsHandler.objectCount; i++) {


                for (int j = 0; j < PhysicsHandler.objectCount; j++) {
                    if(MyObjects.get(i)[getCenterY(i)]>800-temp123){
                        System.out.println("Physics resolution below 800 are halved");
                        return;
                    }
                    System.out.println("bruh");


                    distance = Math.sqrt(Math.pow((MyObjects.get(i)[getCenterX(i)] - MyObjects.get(j)[getCenterX(j)]), 2) + Math.pow(MyObjects.get(i)[getCenterY(i)] - MyObjects.get(j)[getCenterY(j)], 2));
                    nearLimit = (MyObjects.get(i)[getRadius(i)] + MyObjects.get(j)[getRadius(j)]) / 2;
                    //overlap= distance - (MyObjects.get(i)[getRadius(i)]+MyObjects.get(j)[getRadius(j)])/2;

                    if (distance < nearLimit) {
                        if (i != j) {
                            collisionSolve(i, j, nearLimit, distance);
                        }
                    }

                }
            }

        }
    }
    public void move(){
        for(int i=0;i<PhysicsHandler.objectCount;i++){

            if(doGravity){forceApply(forceGravity(i),90,i);}

//         MyObjects.get(i)[getCenterX(i)]+=MyObjects.get(i)[getSpeed(i)]*Math.cos(Math.toRadians(MyObjects.get(i)[getDirection(i)]));
//         MyObjects.get(i)[getCenterX(i)]+=MyObjects.get(i)[getSpeed(i)]*Math.sin(Math.toRadians(MyObjects.get(i)[getDirection(i)]));


           MyObjects.get(i)[getCenterX(i)] += (MyObjects.get(i)[getSpeed(i)] * Math.cos(Math.toRadians(MyObjects.get(i)[getDirection(i)])))/(FPS);
           MyObjects.get(i)[getCenterY(i)] += (MyObjects.get(i)[getSpeed(i)] * Math.sin(Math.toRadians(MyObjects.get(i)[getDirection(i)])))/(FPS);



        }
    }

    public double forceGravity(int objectCount){
        return MyObjects.get(objectCount)[getMass(objectCount)]*gravity;
    }
    public void forceApply(double Force, double direction,int i){

        double temp1= Math.sin(Math.toRadians(MyObjects.get(i)[getDirection(i)]))*MyObjects.get(i)[getSpeed(i)]*MyObjects.get(i)[getMass(i)];
        double temp2= Math.cos(Math.toRadians(MyObjects.get(i)[getDirection(i)]))*MyObjects.get(i)[getSpeed(i)]*MyObjects.get(i)[getMass(i)];

        temp1+=Math.sin(Math.toRadians(direction))*Force/(FPS);
        temp2+=Math.cos(Math.toRadians(direction))*Force/(FPS);

        MyObjects.get(i)[getDirection(i)]=Math.toDegrees(Math.atan2(temp1,temp2));
        if(Math.sqrt(Math.pow(temp1,2)+Math.pow(temp2,2))<maxSpeed){
        MyObjects.get(i)[getSpeed(i)]=Math.sqrt(Math.pow(temp1,2)+Math.pow(temp2,2));}else{
            MyObjects.get(i)[getSpeed(i)]=maxSpeed-0.001;
        }

//        double currentSpeed = MyObjects.get(i)[getSpeed(i)];
//        double currentDirection = Math.toRadians(MyObjects.get(i)[getDirection(i)]);
//
//        double tempX = currentSpeed * Math.cos(currentDirection);
//        double tempY = currentSpeed * Math.sin(currentDirection);
//
//        tempX += Force * Math.cos(Math.toRadians(direction)) / FPS;
//        tempY += Force * Math.sin(Math.toRadians(direction)) / FPS;
//
//        double eqVector = Math.sqrt(Math.pow(tempX, 2) + Math.pow(tempY, 2));
//        MyObjects.get(i)[getSpeed(i)] = eqVector;
//        MyObjects.get(i)[getDirection(i)] = Math.toDegrees(Math.atan2(tempY, tempX));
    }
    public void forceApply(double Force, double direction,int i,double efficiency) {


        double temp1 = Math.sin(Math.toRadians(MyObjects.get(i)[getDirection(i)])) * MyObjects.get(i)[getSpeed(i)] * MyObjects.get(i)[getMass(i)];
        double temp2 = Math.cos(Math.toRadians(MyObjects.get(i)[getDirection(i)])) * MyObjects.get(i)[getSpeed(i)] * MyObjects.get(i)[getMass(i)];

        temp1 += Math.sin(Math.toRadians(direction)) * Force ;
        temp2 += Math.cos(Math.toRadians(direction)) * Force ;
        MyObjects.get(i)[getDirection(i)] = Math.toDegrees(Math.atan2(temp1, temp2));
        MyObjects.get(i)[getSpeed(i)] = Math.sqrt(Math.pow(temp1, 2) + Math.pow(temp2, 2))*efficiency;
    }

    public void startPhysicsThread(){
        if (physicsThread == null) {
        physicsThread = new Thread(this);}
        physicsThread.start();
        physicsThreadRunning = true;


    }


    @Override
    public void run() {

        temp =System.nanoTime();
        tempCounter++;
        if((tempCounter%100)==0){
            System.out.println("aloo fizik");
        }


       while(physicsThreadRunning){
//            if(keyH2.pPressed&&!lock1){pause=!pause;     lock1=true;}else{
//            if(!keyH2.pPressed){lock1=false;}}
//
//            if(!keyH2.gPressed&&!lock1){doGravity=!doGravity;
//                System.out.println("alo1");    lock2=true;}else{
//            if(!keyH2.gPressed){lock2=false;}
//                System.out.println("alo2");}

            if(!pause){
                move();
                collisionDetect();
            }

           long rightNow = System.nanoTime();
           long deltaTime = (long) (rightNow -temp);
           long sleepTime = (long) ((1000000000 /FPS) -deltaTime);

           if (sleepTime > 0) {
               try {
                   Thread.sleep(sleepTime /1000000, (int) (sleepTime %1000000));
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
           temp = System.nanoTime();
       }
    }
}
