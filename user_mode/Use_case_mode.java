package user_mode;

import java.awt.*;
import java.awt.event.MouseEvent;

import base_classes.Use_case;

public class Use_case_mode extends Mode{
    public void mousePressed(MouseEvent m){
        Point p = new Point(m.getX(), m.getY());
        Use_case use_case_obj = new Use_case(p, 200, 100);
        canvas.add_objects(use_case_obj);
        canvas.repaint();
    }
}
