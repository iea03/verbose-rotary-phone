import javax.swing.JFrame;



public class Main {
    public static void main(String[] args)  {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Hello World");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

        PhysicsHandler physicsHandler = new PhysicsHandler(gamePanel.getScreenWidthDepth());
        physicsHandler.startPhysicsThread();



        return;







    }
}