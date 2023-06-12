package base_classes;

import java.awt.*;

public abstract class Basic_object extends Super_object{

    private int port_offset = 3;
    protected int width, height;
    protected String object_name = "newObj";
    protected Basic_object_port ports[] = new Basic_object_port[4];

    public abstract void draw(Graphics shape);

    public void show_ports(Graphics shape){
        // when the object is selected, ports need to be shown
        for(int i = 0; i < ports.length; i++){
            shape.fillRect(ports[i].port.x, ports[i].port.y, ports[i].port.width, ports[i].port.height);
        }
    }

    public String inside(Point p){
        Point center = new Point((x1 + x2)/2, (y1 + y2)/2);

        Polygon r0 = new Polygon();
        r0.addPoint(x1, y1);
        r0.addPoint(x2, y1);
        r0.addPoint(center.x, center.y);

        Polygon r1 = new Polygon();
        r1.addPoint(x2, y1);
        r1.addPoint(x2, y2);
        r1.addPoint(center.x, center.y);

        Polygon r2 = new Polygon();
        r2.addPoint(x2, y2);
        r2.addPoint(x1, y2);
        r2.addPoint(center.x, center.y);

        Polygon r3 = new Polygon();
        r3.addPoint(x1, y2);
        r3.addPoint(x1, y1);
        r3.addPoint(center.x, center.y);

        Polygon[] regions = {r0, r1, r2, r3};

        for(int i = 0; i < regions.length; i++){
            if(regions[i].contains(p)){
                return Integer.toString(i);
            }
        }
        return null;
    }

    public void rename(String name){
        this.object_name = name;
    }

    public void move(int deltaX, int deltaY){
        int x1 = this.x1 + deltaX;
		int y1 = this.y1 + deltaY;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x1 + width;
		this.y2 = y1 + height;
        
        Point[] port_positions = {new Point((x1 + x2)/2, y1), 
                                  new Point(x2, (y1 + y2)/2), 
                                  new Point((x1 + x2)/2, y2), 
                                  new Point(x1, (y1 + y2)/2)};
        
        for(int i = 0; i < ports.length; i++){
            ports[i].set_port(port_positions[i].x, port_positions[i].y, port_offset);
            ports[i].reset_lines();
        }

    }

    protected void create_ports(){
        Point[] port_positions = {new Point((x1 + x2)/2, y1), 
                                  new Point(x2, (y1 + y2)/2), 
                                  new Point((x1 + x2)/2, y2), 
                                  new Point(x1, (y1 + y2)/2)};
        
        for(int i = 0; i < ports.length; i++){
            Basic_object_port p = new Basic_object_port();
            p.set_port(port_positions[i].x, port_positions[i].y, port_offset);
            ports[i] = p;
        }
    }

    public Basic_object_port get_ports(int index){
        return ports[index];
    }

    public int get_depth(){
        return this.depth;
    }

    public void set_depth(int dep){
        this.depth = dep;
    }

    public void set_name(String name){
        this.object_name = name;
    }

}