package user_mode;

import java.awt.event.MouseEvent;


import java.awt.*;

import base_classes.Class_object;
import base_classes.Super_object;

public class Class_mode extends Mode{
    public void mousePressed(MouseEvent m){
        Point p = new Point(m.getX(), m.getY());
        Super_object class_obj = new Class_object(p, 100, 200);
        canvas.add_objects(class_obj);
        canvas.repaint();
    }
}
