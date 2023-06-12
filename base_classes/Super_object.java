package base_classes;

import java.awt.*;
import java.util.ArrayList;


public abstract class Super_object {
    protected int x1, y1, x2, y2;

    protected int initial_depth;
    protected int depth;

    public int get_x1(){
        return x1;
    }

    public int get_y1(){
        return y1;
    }

    public int get_x2(){
        return x2;
    }

    public int get_y2(){
        return y2;
    }

    public abstract void draw(Graphics shape);

    public void move() {
        // implemented in line object
    }

    public void move(int deltaX, int deltaY){
        // implemented in basic object and composite object.
    }
    
    public void rename(String name){
        // implemented in basic object
    }

    public void show_ports(Graphics shape){
        // implemented in basic object and composite object.
    }

    public int get_initial_depth(){
        return initial_depth;
    }

    public void set_initial_depth(int depth){
        this.initial_depth = depth;
    }

    public void set_current_depth(int depth){
        this.depth = depth;
    }

    public int get_current_depth(){
        return depth;
    }

    public String inside(Point p){
        return null;
    }
    
    // Composite
    public ArrayList<Super_object> get_objs(){
        return null;
    }

    public Super_object get_selected_obj() {
        return null;
    }

    public void clean_selected(){}

    // Basic object
    public Basic_object_port get_ports(int index){
        return null;
    }

    public void set_name(String name){

    }

    // Line object
    public void set_port(Basic_object_port p1, Basic_object_port p2){}
}