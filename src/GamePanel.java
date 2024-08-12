import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int screenWidth= 1200;
    final int screenHeight=900;
    public static int FPS = 120;
    boolean spawnLock= false,pauseLock=false,gravityLock=false;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    boolean stopThread=false;


    int temp=1;
    double commonCounter=0;
    int stepCounter=0;
    int spawnTiming=0;


    public GamePanel () {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }
    public void stopGameThread(){
        stopThread=true;
    }


    public void run(){

        double startingTime= System.nanoTime();
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime()+drawInterval;


//        getBlueBalls();
//        getHelloWorldBalls();
//        spawnWanderingBall();


        while(gameThread != null && !stopThread){

            if(commonCounter==50){
                getHelloWorldBalls();
            }

            if(commonCounter<900&&(commonCounter+2)%300<2){
                spawnWanderingBall(800);
            }

            if(commonCounter==1500){
                PhysicsHandler.doGravity=true;
            }

            if(commonCounter>2650&&commonCounter<3700&&(commonCounter+2)%150<2){
                spawnWanderingBall(100,0.3);
            }
            if(commonCounter==4000){
                getHelloWorldHello();
            }
            if(commonCounter==4800){
                //PhysicsHandler.FPS/=2;
                PhysicsHandler.lessCollision=true;
            }
            if(commonCounter==4700){
                getHelloWorldWorld();
            }
            if(commonCounter==5000){
                PhysicsHandler.FPS/=2;
            }

            if(commonCounter==5300){
                keyH.spacePressed=true;
            }
            if(commonCounter==5322){
                keyH.spacePressed=false;
            }

            if(commonCounter>5800&&(commonCounter+2)%300<2*Math.random()){
                spawnWanderingBall(20+250*Math.random(),Math.random(),0.2+Math.random()*2);
            }
            if(PhysicsHandler.objectCount==270){
                PhysicsHandler.temp123+=50;
            }
            if(PhysicsHandler.objectCount==290){
                PhysicsHandler.temp123+=50;
            }
            if(PhysicsHandler.objectCount==320){
                PhysicsHandler.temp123+=50;
            }
            if(PhysicsHandler.objectCount==350){
                PhysicsHandler.FPS=45;
            }
            if(PhysicsHandler.objectCount==360){
                PhysicsHandler.FPS=30;
            }
            if(PhysicsHandler.objectCount==380){
                stopGameThread();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
//            if(PhysicsHandler.objectCount==400){
//                PhysicsHandler.FPS=30;
//            }
//            if(PhysicsHandler.objectCount==410){
//                PhysicsHandler.FPS=24;
//            }


            update();
            repaint();


            //System.out.println(++runcount);
            //System.out.println(rotation);
            commonCounter++;
            ;
            if(commonCounter%60==0){
                //System.out.println("step :"+stepCounter);
                stepCounter++;
                System.out.println(PhysicsHandler.objectCount);
                double totalSpeed=0;
                double averageSpeed;
                for(int i=0;i<PhysicsHandler.objectCount;i++){
                    totalSpeed+=PhysicsHandler.MyObjects.get(i)[4];
                }
                System.out.println("totalSpeed  :"+totalSpeed);
                averageSpeed=totalSpeed/PhysicsHandler.objectCount;
                System.out.println("averageSpeed  :"+averageSpeed);

            }

            try {
                double remainingTime = (nextDrawTime - System.nanoTime())/1000000;
                if(remainingTime<0){
                    remainingTime=0;
                }

                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public void update(){


        if(keyH.plusPressed&&!spawnLock||keyH.spacePressed){
            spawnLock=true;
            new MyShapes3((temp=temp+50)%1000+ +10+Math.random()*15,800-(((double) (PhysicsHandler.objectCount) /20)*106+10+Math.random()*15),30);
            System.out.println("center x= "+PhysicsHandler.MyObjects.get(PhysicsHandler.objectCount-1)[0]);  //objectCount -1 = index
            System.out.println("center y= "+PhysicsHandler.MyObjects.get(PhysicsHandler.objectCount-1)[1]);

        }else if(!keyH.plusPressed){
            spawnLock=false;
        }




        if(keyH.qPressed == true){
            PhysicsHandler.lessCollision=true;
        }
        if(keyH.ePressed == true){
            spawnTiming++;
            if(spawnTiming%20==0){
            spawnWanderingBall(20+860*Math.random());}
        }

        if(keyH.pPressed == true && !pauseLock){
            PhysicsHandler.pause=!PhysicsHandler.pause;
            pauseLock=true;
        }else if(keyH.pPressed == false){
            pauseLock=false;
        }

        if(keyH.gPressed == true && !gravityLock){
            PhysicsHandler.doGravity=!PhysicsHandler.doGravity;
            gravityLock=true;
        }else if(keyH.gPressed == false){
            gravityLock=false;
        }

    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);

        for(int i=0; i<PhysicsHandler.objectCount;i++){

            if(PhysicsHandler.MyObjects.get(i).length>7){
                switch ((int) PhysicsHandler.MyObjects.get(i)[7]){
                    case 1:
                        g2.setColor(Color.red);
                        break;
                    case 2:
                        g2.setColor(Color.green);
                        break;
                    case 3:
                        g2.setColor(Color.blue);
                        break;
                    case 4:
                        g2.setColor(Color.cyan);
                        break;
                    case 5:
                        g2.setColor(Color.MAGENTA);
                        break;
                    case 6:
                        g2.setColor(Color.orange);
                        break;
                    case 7:
                        g2.setColor(Color.yellow);
                        break;
                }
            }else{
                g2.setColor(Color.gray);
            }
            g2.fillOval((int)(PhysicsHandler.MyObjects.get(i)[0]-PhysicsHandler.MyObjects.get(i)[3]/2),(int)(PhysicsHandler.MyObjects.get(i)[1]-PhysicsHandler.MyObjects.get(i)[3]/2),(int)PhysicsHandler.MyObjects.get(i)[3],(int)PhysicsHandler.MyObjects.get(i)[3]);
            if(g2.getColor()==Color.yellow){
                g2.setColor(Color.blue);
            }else{
            g2.setColor(Color.white);}
            g2.drawOval((int)(PhysicsHandler.MyObjects.get(i)[0]-PhysicsHandler.MyObjects.get(i)[3]/2),(int)(PhysicsHandler.MyObjects.get(i)[1]-PhysicsHandler.MyObjects.get(i)[3]/2),(int)PhysicsHandler.MyObjects.get(i)[3],(int)PhysicsHandler.MyObjects.get(i)[3]);
        }
        //g2.drawOval(15,15,1,1);
        g2.dispose();
    }
    public double[] getScreenWidthDepth(){
        return new double[] {this.screenWidth,this.screenHeight};
    }

    public void getHelloWorldBalls(){

        //              H          H          H           H
        int temp1=50;
        int temp2=50;
        int spacing=80;
        int temp3,temp4,temp5;
        int radius1=30;
        int radius2=20;

        for(int a=0;a<5;a++) {
            new MyShapes3(temp1,temp2+a*(radius1+1) , radius1,4);
            new MyShapes3(temp1+90,temp2+a*(radius1+1) , radius1,4);
        }
        new MyShapes3(temp1+30,temp2+65 , radius2,7);
        new MyShapes3(temp1+50,temp2+65 , radius2,7);
        new MyShapes3(temp1+70,temp2+65 , radius2,7);




//        //      E        E           E          E          E           E

        temp3=temp1+90+spacing;
        for(int a=0;a<5;a++) {
            new MyShapes3(temp3,temp2+a*(radius1+1) , radius1,3);
            if(a%2==0){
                new MyShapes3(temp3+radius1+1,temp2+a*31 , 29,3);
                new MyShapes3(temp3+(radius1+1)*2,temp2+a*31, 29,5);
            }
        }

//              L               L               L               L               L  x2

        temp3=temp1+220+spacing;
        for(int a=0;a<5;a++) {
            new MyShapes3(temp3, temp2 + a * (radius1 + 1), radius1,7);
        }
        new MyShapes3(temp3+radius1+1, temp2 + 4 * (radius1 + 1), radius1,4);
        new MyShapes3(temp3+(radius1+1)*2, temp2 + 4 * (radius1 + 1), radius1,4);

        temp3=temp3+70+spacing;
        for(int a=0;a<5;a++) {
            new MyShapes3(temp3, temp2 + a * (radius1 + 1), radius1,5);
        }
        new MyShapes3(temp3+radius1+1, temp2 + 4 * (radius1 + 1), radius1,3);
        new MyShapes3(temp3+(radius1+1)*2, temp2 + 4 * (radius1 + 1), radius1,3);

//        //      O       O       O       O       O
        temp3=temp3+70+spacing;
        for(int a=1;a<4;a++) {
            new MyShapes3(temp3, temp1 + a * (radius1 + 1), radius1,4);
        }
        new MyShapes3(temp3+radius1-1, temp1 + 4 * (radius1 + 1)+4, radius1,3);
        new MyShapes3(temp3+(radius1+2)*2, temp1 + 4 * (radius1 + 1)+4, radius1,3);

        new MyShapes3(temp3+radius1-1, temp1 + 0 * (radius1 + 1)-4, radius1,7);
        new MyShapes3(temp3+(radius1+2)*2, temp1 + 0 * (radius1 + 1)-4, radius1,7);

        for(int a=1;a<4;a++) {
            new MyShapes3(temp3+(radius1+1)*3, temp1 + a * (radius1 + 1), radius1,5);
        }



//        //      W           W           W           W           W           W           W

        temp1+=200;

        for(int a=0;a<5;a++) {
            new MyShapes3(temp1-80+12*a,temp1+a*(radius1+1) , radius1,3);
            new MyShapes3(temp1+140-12*a,temp1+a*(radius1+1) , radius1,3);
        }
        for(int a=2;a<6;a++) {
            new MyShapes3(temp1+45+6*a,temp1+a*(radius2+1)+10 , radius2,3);
            new MyShapes3(temp1+25-6*a,temp1+a*(radius2+1)+10 , radius2,3);
        }
        new MyShapes3(temp1+32,temp1+(radius1+1)-10, radius2,3);
        new MyShapes3(temp1+32,temp1+(radius1+1)-10+radius2+1, radius2,3);

//        //      O       O       O       O       O
        temp3-=200;
        temp2+=200;
        for(int a=1;a<4;a++) {
            new MyShapes3(temp3, temp1 + a * (radius1 + 1), radius1,7);
        }
        new MyShapes3(temp3+radius1-1, temp1 + 4 * (radius1 + 1)+4, radius1,7);
        new MyShapes3(temp3+(radius1+2)*2, temp1 + 4 * (radius1 + 1)+4, radius1,7);

        new MyShapes3(temp3+radius1-1, temp1 + 0 * (radius1 + 1)-4, radius1,7);
        new MyShapes3(temp3+(radius1+2)*2, temp1 + 0 * (radius1 + 1)-4, radius1,7);

        for(int a=1;a<4;a++) {
            new MyShapes3(temp3+(radius1+1)*3, temp1 + a * (radius1 + 1), radius1,7);
        }

        //      R           R           R               R               R

        temp3=temp1+280+spacing;


        for(int a=0;a<5;a++) {
            new MyShapes3(temp3, temp2 + a * (radius1 + 1), radius1,5);
        }
        new MyShapes3(temp3+radius1+1, temp2  , radius1,5);
        new MyShapes3(temp3+(radius1+1)*2, temp2+15 , radius1,5);
        new MyShapes3(temp3+(radius1+1)*2, temp2+15+radius1+1 , radius1,5);
        new MyShapes3(temp3+(radius1+1)+5, temp2+10+(radius1)*2 , radius1,5);
        new MyShapes3(temp3+(radius1)*2, temp2+(radius1)*3+2, radius1,5);
        new MyShapes3(temp3+(radius1)*3-10, temp2+(radius1)*4-1 , radius1,5);

        //              L               L               L               L               L

        temp3=temp1+430+spacing;
        for(int a=0;a<5;a++) {
            new MyShapes3(temp3, temp2 + a * (radius1 + 1), radius1,4);
        }
        new MyShapes3(temp3+radius1+1, temp2 + 4 * (radius1 + 1), radius1,4);
        new MyShapes3(temp3+(radius1+1)*2, temp2 + 4 * (radius1 + 1), radius1,4);

//      D           D           D           D           D           D

        temp3+=spacing+60;
        for(int a=0;a<5;a++) {
            new MyShapes3(temp3, temp2 + a * (radius1 + 1), radius1,7);
        }
        new MyShapes3(temp3+radius1+1, temp2 , radius1,7);
        new MyShapes3(temp3+(radius1)*2, temp2+15 , radius1-5,7);

        new MyShapes3(temp3+(radius1+9)*2, temp2 + 1 * (radius1 + 7), radius2,7);
        new MyShapes3(temp3+(radius1+9)*2, temp2 + 2 * (radius1 + 1), radius2,7);
        new MyShapes3(temp3+(radius1+9)*2, temp2 + 3 * (radius1 + 1)-5, radius2,7);

        new MyShapes3(temp3+(radius1)*2, temp2 + 4 * (radius1 + 2)-20, radius1-3,7);
        new MyShapes3(temp3+radius1+1, temp2 + 4 * (radius1 + 1), radius1,7);
    }

//    public void getBlueBalls(){
//
//        for(int a=0;a<60;a++) {
//            new MyShapes3(20+(a%30)*40+(a/30)*3,800+20-(a%30)+(a/30)*30,30,3);
//        }
//
//
//    }

    public void spawnWanderingBall(double yLevel){
        MyShapes3 wanderingBall =new MyShapes3(450+Math.random()*150, yLevel+Math.random()*50,20 ,1);
        System.out.println(PhysicsHandler.objectCount+"aloo");
        PhysicsHandler.MyObjects.get(PhysicsHandler.objectCount-1)[new PhysicsShortcuts().getSpeed(PhysicsHandler.objectCount-1)]=500;
        PhysicsHandler.MyObjects.get(PhysicsHandler.objectCount-1)[new PhysicsShortcuts().getDirection(PhysicsHandler.objectCount-1)]=360*Math.random();

    }
    public void spawnWanderingBall(double yLevel,double speedScale){
        MyShapes3 wanderingBall =new MyShapes3(450+Math.random()*150, yLevel+Math.random()*50,20 ,1);
        System.out.println(PhysicsHandler.objectCount+"aloo");
        PhysicsHandler.MyObjects.get(PhysicsHandler.objectCount-1)[new PhysicsShortcuts().getSpeed(PhysicsHandler.objectCount-1)]=500*speedScale;
        PhysicsHandler.MyObjects.get(PhysicsHandler.objectCount-1)[new PhysicsShortcuts().getDirection(PhysicsHandler.objectCount-1)]=360*Math.random();

    }
    public void spawnWanderingBall(double yLevel,double speedScale,double sizeScale){
        MyShapes3 wanderingBall =new MyShapes3(450+Math.random()*150, yLevel+Math.random()*50,20*sizeScale ,(int)(1.1+2*Math.random()));
        System.out.println(PhysicsHandler.objectCount+"aloo");
        PhysicsHandler.MyObjects.get(PhysicsHandler.objectCount-1)[new PhysicsShortcuts().getSpeed(PhysicsHandler.objectCount-1)]=500*speedScale;
        PhysicsHandler.MyObjects.get(PhysicsHandler.objectCount-1)[new PhysicsShortcuts().getDirection(PhysicsHandler.objectCount-1)]=360*Math.random();

    }
    public void getHelloWorldHello(){
        //              H          H          H           H
        int temp1=50;
        int temp2=50;
        int spacing=80;
        int temp3,temp4,temp5;
        int radius1=30;
        int radius2=20;

        for(int a=0;a<5;a++) {
            new MyShapes3(temp1,temp2+a*(radius1+1) , radius1,4);
            new MyShapes3(temp1+90,temp2+a*(radius1+1) , radius1,4);
        }
        new MyShapes3(temp1+30,temp2+65 , radius2,7);
        new MyShapes3(temp1+50,temp2+65 , radius2,7);
        new MyShapes3(temp1+70,temp2+65 , radius2,7);

//        //      E        E           E          E          E           E

        temp3=temp1+90+spacing;
        for(int a=0;a<5;a++) {
            new MyShapes3(temp3,temp2+a*(radius1+1) , radius1,3);
            if(a%2==0){
                new MyShapes3(temp3+radius1+1,temp2+a*31 , 29,3);
                new MyShapes3(temp3+(radius1+1)*2,temp2+a*31, 29,5);
            }
        }

//              L               L               L               L               L  x2

        temp3=temp1+220+spacing;
        for(int a=0;a<5;a++) {
            new MyShapes3(temp3, temp2 + a * (radius1 + 1), radius1,7);
        }
        new MyShapes3(temp3+radius1+1, temp2 + 4 * (radius1 + 1), radius1,4);
        new MyShapes3(temp3+(radius1+1)*2, temp2 + 4 * (radius1 + 1), radius1,4);

        temp3=temp3+70+spacing;
        for(int a=0;a<5;a++) {
            new MyShapes3(temp3, temp2 + a * (radius1 + 1), radius1,5);
        }
        new MyShapes3(temp3+radius1+1, temp2 + 4 * (radius1 + 1), radius1,3);
        new MyShapes3(temp3+(radius1+1)*2, temp2 + 4 * (radius1 + 1), radius1,3);

//        //      O       O       O       O       O
        temp3=temp3+70+spacing;
        for(int a=1;a<4;a++) {
            new MyShapes3(temp3, temp1 + a * (radius1 + 1), radius1,4);
        }
        new MyShapes3(temp3+radius1-1, temp1 + 4 * (radius1 + 1)+4, radius1,3);
        new MyShapes3(temp3+(radius1+2)*2, temp1 + 4 * (radius1 + 1)+4, radius1,3);

        new MyShapes3(temp3+radius1-1, temp1 + 0 * (radius1 + 1)-4, radius1,7);
        new MyShapes3(temp3+(radius1+2)*2, temp1 + 0 * (radius1 + 1)-4, radius1,7);

        for(int a=1;a<4;a++) {
            new MyShapes3(temp3+(radius1+1)*3, temp1 + a * (radius1 + 1), radius1,5);
        }

    }

    public void getHelloWorldWorld(){

        int temp1=50;
        int temp2=50;
        int spacing=80;
        int temp3,temp4,temp5;
        int radius1=30;
        int radius2=20;

        temp3=temp1+90+spacing;
        temp3=temp1+220+spacing;
        temp3=temp3+70+spacing;
        temp3=temp3+70+spacing;


        temp1+=200;

        for(int a=0;a<5;a++) {
            new MyShapes3(temp1-80+12*a,temp1+a*(radius1+1) , radius1,3);
            new MyShapes3(temp1+140-12*a,temp1+a*(radius1+1) , radius1,3);
        }
        for(int a=2;a<6;a++) {
            new MyShapes3(temp1+45+6*a,temp1+a*(radius2+1)+10 , radius2,3);
            new MyShapes3(temp1+25-6*a,temp1+a*(radius2+1)+10 , radius2,3);
        }
        new MyShapes3(temp1+32,temp1+(radius1+1)-10, radius2,3);
        new MyShapes3(temp1+32,temp1+(radius1+1)-10+radius2+1, radius2,3);

//        //      O       O       O       O       O
        temp3-=200;
        temp2+=200;
        for(int a=1;a<4;a++) {
            new MyShapes3(temp3, temp1 + a * (radius1 + 1), radius1,7);
        }
        new MyShapes3(temp3+radius1-1, temp1 + 4 * (radius1 + 1)+4, radius1,7);
        new MyShapes3(temp3+(radius1+2)*2, temp1 + 4 * (radius1 + 1)+4, radius1,7);

        new MyShapes3(temp3+radius1-1, temp1 + 0 * (radius1 + 1)-4, radius1,7);
        new MyShapes3(temp3+(radius1+2)*2, temp1 + 0 * (radius1 + 1)-4, radius1,7);

        for(int a=1;a<4;a++) {
            new MyShapes3(temp3+(radius1+1)*3, temp1 + a * (radius1 + 1), radius1,7);
        }

        //      R           R           R               R               R

        temp3=temp1+280+spacing;


        for(int a=0;a<5;a++) {
            new MyShapes3(temp3, temp2 + a * (radius1 + 1), radius1,5);
        }
        new MyShapes3(temp3+radius1+1, temp2  , radius1,5);
        new MyShapes3(temp3+(radius1+1)*2, temp2+15 , radius1,5);
        new MyShapes3(temp3+(radius1+1)*2, temp2+15+radius1+1 , radius1,5);
        new MyShapes3(temp3+(radius1+1)+5, temp2+10+(radius1)*2 , radius1,5);
        new MyShapes3(temp3+(radius1)*2, temp2+(radius1)*3+2, radius1,5);
        new MyShapes3(temp3+(radius1)*3-10, temp2+(radius1)*4-1 , radius1,5);

        //              L               L               L               L               L

        temp3=temp1+430+spacing;
        for(int a=0;a<5;a++) {
            new MyShapes3(temp3, temp2 + a * (radius1 + 1), radius1,4);
        }
        new MyShapes3(temp3+radius1+1, temp2 + 4 * (radius1 + 1), radius1,4);
        new MyShapes3(temp3+(radius1+1)*2, temp2 + 4 * (radius1 + 1), radius1,4);

//      D           D           D           D           D           D

        temp3+=spacing+60;
        for(int a=0;a<5;a++) {
            new MyShapes3(temp3, temp2 + a * (radius1 + 1), radius1,7);
        }
        new MyShapes3(temp3+radius1+1, temp2 , radius1,7);
        new MyShapes3(temp3+(radius1)*2, temp2+15 , radius1-5,7);

        new MyShapes3(temp3+(radius1+9)*2, temp2 + 1 * (radius1 + 7), radius2,7);
        new MyShapes3(temp3+(radius1+9)*2, temp2 + 2 * (radius1 + 1), radius2,7);
        new MyShapes3(temp3+(radius1+9)*2, temp2 + 3 * (radius1 + 1)-5, radius2,7);

        new MyShapes3(temp3+(radius1)*2, temp2 + 4 * (radius1 + 2)-20, radius1-3,7);
        new MyShapes3(temp3+radius1+1, temp2 + 4 * (radius1 + 1), radius1,7);
    }

    }










