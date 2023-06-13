package user_mode;

import java.awt.event.MouseEvent;
import java.awt.*;
import base_classes.Basic_object;

public class Create_basic_object_mode extends Mode{
    private Object_factory factory;

    public Create_basic_object_mode(String ObjectType){
        factory = new Object_factory(ObjectType);
    }

    public void mousePressed(MouseEvent m){
        Point p = new Point(m.getX(), m.getY());
        Basic_object obj = factory.createBasicObject(p);
        canvas.add_objects(obj);
        canvas.repaint();
    }
}
