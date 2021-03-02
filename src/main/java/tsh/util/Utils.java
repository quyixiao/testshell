package tsh.util;

import tsh.Interpreter;
import tsh.util.BshCanvas;

import java.awt.*;

public class Utils {

    static Window splashScreen;

    /*
        This could live in the desktop script.
        However we'd like to get it on the screen as quickly as possible.
    */
    public static void startSplashScreen() {
        int width = 275, height = 148;
        Window win = new Window(new Frame());
        win.pack();
        BshCanvas can = new BshCanvas();
        can.setSize(width, height); // why is this necessary?
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        win.setBounds(
                dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);
        win.add("Center", can);
        Image img = tk.getImage(
                Interpreter.class.getResource("/tsh/util/lib/splash.gif"));
        MediaTracker mt = new MediaTracker(can);
        mt.addImage(img, 0);
        try {
            mt.waitForAll();
        } catch (Exception e) {
        }
        Graphics gr = can.getBufferedGraphics();
        gr.drawImage(img, 0, 0, can);
        win.setVisible(true);
        win.toFront();
        splashScreen = win;
    }

    public static void endSplashScreen() {
        if (splashScreen != null)
            splashScreen.dispose();
    }

    public static boolean eq(String value, String ... str) {
        for (String s : str) {
            if (!s.equals(value)) {
                return false;
            }
        }
        return true;
    }

    public static boolean notEq(String value, String... str) {
       return !eq(value,str);
    }

    public static boolean eqOR(String value, String... str) {
        for (String s : str) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
