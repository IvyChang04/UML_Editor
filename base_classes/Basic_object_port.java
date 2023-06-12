package base_classes;

import java.util.ArrayList;
import java.awt.*;

public class Basic_object_port {

    protected ArrayList<Super_object> connected_lines = new ArrayList<Super_object>();
    public Rectangle port = new Rectangle();


    public Point get_center_point() {
        Point center_point = new Point((int) (port.getCenterX()), (int) (port.getCenterY()));
        return center_point;
    }

    public void set_port(int center_x, int center_y, int offset){
        int x = center_x - offset;
        int y = center_y - offset;
        // square, width = hight = offset * 2
        int width = offset * 2, height = offset * 2;
        port.setBounds(x, y, width, height);
    }

    public void add_lines(Super_object line){
        connected_lines.add(line);
    }

    public void reset_lines(){
        for(int i = 0; i < connected_lines.size(); i++){
            Super_object tmp = connected_lines.get(i);
            tmp.move();
        }
    }

}
