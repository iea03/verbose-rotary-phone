import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, ePressed, qPressed,pPressed,gPressed;
    public boolean plusPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;}
        if (code == KeyEvent.VK_S) {
            downPressed = true;}
        if (code == KeyEvent.VK_A) {
            leftPressed = true;}
        if (code == KeyEvent.VK_D) {
            rightPressed = true;}
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;}
        if (code == KeyEvent.VK_E) {
            ePressed = true;}
        if (code == KeyEvent.VK_Q) {
            qPressed = true;}
        if (code == KeyEvent.VK_UP) {
            upPressed = true;}
        if (code == KeyEvent.VK_DOWN) {
            downPressed = true;}
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;}
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;}
        if (code == KeyEvent.VK_ADD) {
            plusPressed = true;}
        if (code == KeyEvent.VK_P) {
            pPressed = true;}
        if (code == KeyEvent.VK_G) {
            gPressed = true;}

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;}
        if (code == KeyEvent.VK_S) {
            downPressed = false;}
        if (code == KeyEvent.VK_A) {
            leftPressed = false;}
        if (code == KeyEvent.VK_D) {
            rightPressed = false;}
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;}
        if (code == KeyEvent.VK_E) {
            ePressed = false;}
        if (code == KeyEvent.VK_Q) {
            qPressed = false;}
        if (code == KeyEvent.VK_UP) {
            upPressed = false;}
        if (code == KeyEvent.VK_DOWN) {
            downPressed = false;}
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;}
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;}
        if (code == KeyEvent.VK_ADD) {
            plusPressed = false;}
        if (code == KeyEvent.VK_P) {
            pPressed = false;}
        if (code == KeyEvent.VK_G) {
            gPressed = false;}
    }

    }
