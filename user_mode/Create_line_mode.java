package user_mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import base_classes.Line;
import base_classes.Super_object;

public class Create_line_mode extends Mode{
    private ArrayList<Super_object> objects = new ArrayList<Super_object>();
    private Point starting_point = null, ending_point = null;
    private int port1 = -1, port2 = -1;
    private Super_object conn_obj1 = null, conn_obj2 = null;
    private Object_factory factory;

    public Create_line_mode(String lineType){
        factory = new Object_factory(lineType);
    }

    @Override
    public void mousePressed(MouseEvent m){
        objects = canvas.get_all_objects();
        starting_point = find_conn_port(m.getX(), m.getY(), objects, "start");
    }

    @Override
    public void mouseDragged(MouseEvent m){
        if(starting_point != null){
            Line line  = factory.createLine(starting_point.x, starting_point.y, m.getX(), m.getY());
            canvas.drag_line = line;
            canvas.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent m){
        if(starting_point != null){
            objects = canvas.get_all_objects();
            ending_point = find_conn_port(m.getX(), m.getY(), objects, "end");
            if(ending_point != null){
                Line line = factory.createLine(starting_point.x, starting_point.y, ending_point.x, ending_point.y);
                canvas.add_line(line);
                line.set_port(conn_obj1.get_ports(port1), conn_obj2.get_ports(port2));
                conn_obj1.get_ports(port1).add_lines(line);
                conn_obj2.get_ports(port2).add_lines(line);
            }
        }
        canvas.drag_line = null;
        starting_point = null;
        ending_point = null;

        canvas.repaint();
    }

    // need to be fixed in final
    public Point find_conn_port(int x, int y, ArrayList<Super_object> objects, String start_or_end){
        // int port_index;
        Point connect_point = null;

        for(int i = 0; i < objects.size(); i++){
            Super_object obj = objects.get(i);
            Point p = new Point(x, y);
            String if_inside = obj.inside(p);

            if(if_inside != null){
                connect_point = new Point();
                if(if_inside.equals("inside composite")){
                    // if it's composite object, then find the connect port again
                    connect_point = find_conn_port(x, y, obj.get_objs(), start_or_end);
                }
                else if(if_inside.equals("0")){
                    connect_point.x = (int) obj.get_ports(0).port.getCenterX();
                    connect_point.y = (int) obj.get_ports(0).port.getCenterY();
                    if(start_or_end.equals("start")){
                        conn_obj1 = obj;
                        port1 = 0;
                    }
                    else{
                        conn_obj2 = obj;
                        port2 = 0;
                    }
                }
                else if(if_inside.equals("1")){
                    connect_point.x = (int) obj.get_ports(1).port.getCenterX();
                    connect_point.y = (int) obj.get_ports(1).port.getCenterY();
                    if(start_or_end.equals("start")){
                        conn_obj1 = obj;
                        port1 = 1;
                    }
                    else{
                        conn_obj2 = obj;
                        port2 = 1;
                    }
                }
                else if(if_inside.equals("2")){
                    connect_point.x = (int) obj.get_ports(2).port.getCenterX();
                    connect_point.y = (int) obj.get_ports(2).port.getCenterY();
                    if(start_or_end.equals("start")){
                        conn_obj1 = obj;
                        port1 = 2;
                    }
                    else{
                        conn_obj2 = obj;
                        port2 = 2;
                    }
                }
                else if(if_inside.equals("3")){
                    connect_point.x = (int) obj.get_ports(3).port.getCenterX();
                    connect_point.y = (int) obj.get_ports(3).port.getCenterY();
                    if(start_or_end.equals("start")){
                        conn_obj1 = obj;
                        port1 = 3;
                    }
                    else{
                        conn_obj2 = obj;
                        port2 = 3;
                    }
                }
            }
        }
        return connect_point;
    }
}
