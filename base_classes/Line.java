package base_classes;

import java.awt.*;
// import java.awt.geom.Line2D;

public abstract class Line extends Super_object {

    protected Basic_object_port[] ports = new Basic_object_port[2];

    public abstract void draw(Graphics shape);

    public void set_port(Basic_object_port p1, Basic_object_port p2){
        ports[0] = p1;
        ports[1] = p2;
    }
    
    public void show(Graphics shape){
        this.draw(shape);
    }

    public void move(){
        this.x1 = (int) ports[0].port.getCenterX();
        this.x2 = (int) ports[1].port.getCenterX();
        this.y1 = (int) ports[0].port.getCenterY();
        this.y2 = (int) ports[1].port.getCenterY();
    }

}
