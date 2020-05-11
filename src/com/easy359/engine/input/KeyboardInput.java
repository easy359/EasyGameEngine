package com.easy359.engine.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyboardInput extends KeyAdapter {

    public static final int UP = KeyEvent.VK_UP;
    public static final int DOWN = KeyEvent.VK_DOWN;
    public static final int LEFT = KeyEvent.VK_LEFT;
    public static final int RIGHT = KeyEvent.VK_RIGHT;
    public static final int A = KeyEvent.VK_A;
    public static final int B = KeyEvent.VK_B;
    public static final int C = KeyEvent.VK_C;
    public static final int D = KeyEvent.VK_D;
    public static final int E = KeyEvent.VK_E;
    public static final int F = KeyEvent.VK_F;
    public static final int G = KeyEvent.VK_G;
    public static final int H = KeyEvent.VK_H;
    public static final int I = KeyEvent.VK_I;
    public static final int J = KeyEvent.VK_J;
    public static final int K = KeyEvent.VK_K;
    public static final int L = KeyEvent.VK_L;
    public static final int M = KeyEvent.VK_M;
    public static final int N = KeyEvent.VK_N;
    public static final int O = KeyEvent.VK_O;
    public static final int P = KeyEvent.VK_P;
    public static final int Q = KeyEvent.VK_Q;
    public static final int R = KeyEvent.VK_R;
    public static final int S = KeyEvent.VK_S;
    public static final int T = KeyEvent.VK_T;
    public static final int U = KeyEvent.VK_U;
    public static final int V = KeyEvent.VK_V;
    public static final int W = KeyEvent.VK_W;
    public static final int X = KeyEvent.VK_X;
    public static final int Y = KeyEvent.VK_Y;
    public static final int Z = KeyEvent.VK_Z;
    public static final int ONE = KeyEvent.VK_1;
    public static final int TWO = KeyEvent.VK_2;
    public static final int THREE = KeyEvent.VK_3;
    public static final int FOUR = KeyEvent.VK_4;
    public static final int FIVE = KeyEvent.VK_5;
    public static final int SIX = KeyEvent.VK_6;
    public static final int SEVEN = KeyEvent.VK_7;
    public static final int EIGHT = KeyEvent.VK_8;
    public static final int NINE = KeyEvent.VK_9;
    public static final int ZERO = KeyEvent.VK_0;
    public static final int TAB = KeyEvent.VK_TAB;
    public static final int ESCAPE = KeyEvent.VK_ESCAPE;
    public static final int ENTER = KeyEvent.VK_ENTER;
    public static final int SPACE = KeyEvent.VK_SPACE;
    public static final int CONTROL = KeyEvent.VK_CONTROL;
    public static final int ALT = KeyEvent.VK_ALT;

    private static KeyboardInput instance;

    private static ArrayList<Integer> pressedKeys;
    private static ArrayList<Integer> justPressedKeys;
    private static ArrayList<Integer> afterPressedKeys;

    private KeyboardInput() {
        pressedKeys = new ArrayList<Integer>();
        justPressedKeys = new ArrayList<Integer>();
        afterPressedKeys = new ArrayList<Integer>();
    }

    public static void create() {
        if (instance == null)
            instance = new KeyboardInput();
    }

    public static KeyboardInput getInstance() {
        create();
        return instance;
    }

    public static void update() {
        synchronized (pressedKeys) {
            for (int i = 0; i < pressedKeys.size(); i++) {
                if (!justPressedKeys.contains(pressedKeys.get(i)) && !afterPressedKeys.contains(pressedKeys.get(i))) {
                    justPressedKeys.add(pressedKeys.get(i));
                } else {
                    justPressedKeys.remove(Integer.valueOf(pressedKeys.get(i)));
                    afterPressedKeys.add(pressedKeys.get(i));
                }
            }
            justPressedKeys.retainAll(pressedKeys);
            afterPressedKeys.retainAll(pressedKeys);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        synchronized (pressedKeys) {
            if (!pressedKeys.contains(Integer.valueOf(e.getKeyCode()))) {
                pressedKeys.add(e.getKeyCode());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        synchronized (pressedKeys) {
            pressedKeys.remove(Integer.valueOf(e.getKeyCode()));
        }
    }

    public static boolean isKeyPressed(int keyCode) {
        return pressedKeys.contains(keyCode);
    }

    public static boolean isKeyJustPressed(int keyCode) {
        return justPressedKeys.contains(keyCode);
    }

}
