package base_classes;

import java.awt.*;

public class Use_case extends Basic_object{
    public Use_case(Point p, int width, int height){
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
        shape.drawOval(x1, y1, width, height);
        shape.setColor(Color.white);
        shape.fillOval(x1, y1, width, height);
        shape.setColor(Color.black);
        int string_x = x1 + (width - metrics.stringWidth(object_name)) / 2;
        int string_y = y1 + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        shape.drawString(object_name, string_x, string_y); 
    }
}
