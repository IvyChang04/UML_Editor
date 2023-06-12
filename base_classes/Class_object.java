package base_classes;

import java.awt.*;

public class Class_object extends Basic_object{

    public Class_object(Point p, int width, int height){
        this.width = width;
        this.height = height;
        this.x1 = p.x;
        this.y1 = p.y;
        this.x2 = p.x + width;
        this.y2 = p.y + height;
        create_ports();
    }
 
    public void draw(Graphics shape){

        FontMetrics metrics = shape.getFontMetrics();
        shape.setColor(Color.black);
        shape.drawRect(x1, y1, width, height);
        shape.setColor(Color.white);
        shape.fillRect(x1, y1, width, height);
        shape.setColor(Color.black);

        int interval = height/3;
        shape.drawLine(x1, y1 + interval, x2, y1 + interval);
        shape.drawLine(x1, y2-interval, x2, y2-interval);

        int string_x = x1 + (width - metrics.stringWidth(object_name)) / 2;
        int string_y = y1 + ((interval - metrics.getHeight()) / 2) + metrics.getAscent();
        shape.drawString(object_name, string_x, string_y);
    }
}
