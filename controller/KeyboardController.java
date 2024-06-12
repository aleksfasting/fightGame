package controller;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

public class KeyboardController {
    private final static int X_BUTTON = 1;
    private final static int X_AXIS = 3;
    private final static int O_BUTTON = 2;
    private Controller cont;
    private boolean isXPressed;
    private boolean isOPressed;


    public KeyboardController() {
        isXPressed = false;
        isOPressed = false;
        try {
            Controllers.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Controllers.poll();
        for (int i = 0; i < Controllers.getControllerCount(); i++) {
            cont = Controllers.getController(i);
            if (cont.getName().equals("Wireless Controller")) {
                break;
            }
        }
    }

    public void pollController() {
        cont.poll();
    }

    public float getX() {
        return cont.getAxisValue(X_AXIS);
    }

    public boolean getUp() {
        if (cont.isButtonPressed(X_BUTTON)) {
            if (isXPressed) {
                return false;
            }
            isXPressed = true;
            return true;
        }
        isXPressed = false;
        return false;
    }

    public boolean getQAttack() {
        if (cont.isButtonPressed(O_BUTTON)) {
            if (isOPressed) {
                return false;
            }
            isOPressed = true;
            return true;
        }
        isOPressed = false;
        return false;
    }

}
