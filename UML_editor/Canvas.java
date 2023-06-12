package UML_editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EventListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;

import base_classes.Super_object;
import user_mode.Mode;
import base_classes.Composite_object;
import javax.swing.JPanel;


public class Canvas extends JPanel{
    private int initial_depth = 0;
    
    public ArrayList<Super_object> objects = new ArrayList<Super_object>();
    public ArrayList<Super_object> selected_objects = new ArrayList<Super_object>();
    public Super_object drag_line = null;               // draw point to mouse
    public Rectangle selected_area = new Rectangle();
    private static Canvas canvas = null;
    // used in set mode
    public Mode current_mode = null;
    private EventListener listener;

    private Canvas(){}

    public static Canvas get_canvas(){
        if(canvas == null){
            canvas = new Canvas();
        }
        return canvas;
    }

    public ArrayList<Super_object> get_all_objects(){
        return objects;
    }

    public void add_objects(Super_object obj){
        obj.set_initial_depth(initial_depth++);
        obj.set_current_depth(obj.get_initial_depth());
        obj.set_name("Depth: "+Integer.toString(obj.get_initial_depth()));
        objects.add(obj);
    }

    // line doesn't have depth
    public void add_line(Super_object line){
        line.set_initial_depth(99);
        line.set_current_depth(99);
        objects.add(line);
    }

    public void create_composite(){
        // group all the selected objects
        // if there's only one object, then no need to group
        if(selected_objects.size() != 1){
            Composite_object composite = new Composite_object();
            Collections.sort(selected_objects, Comparator.comparing(Super_object::get_current_depth));
    
            for(int i = 0; i < selected_objects.size(); i++){
                composite.add_obj(selected_objects.get(i));
                objects.remove(selected_objects.get(i));
                selected_objects.remove(selected_objects.get(i));
                i--;
            }
            // objects.add(composite);
            canvas.add_objects(composite);
            ArrayList<Super_object> tmp = new ArrayList<Super_object>();
            tmp = composite.get_objs();
    
            // set elements' depth
            for(int i = 0; i < composite.get_size(); i++){
                tmp.get(i).set_current_depth(composite.get_initial_depth());
            }
    
            selected_objects.add(composite);
            composite.set_area();
            repaint();
        }
    }

    public void cancle_composite(){
        // ungroup selected composite object
        Super_object composite = selected_objects.get(0);
        ArrayList<Super_object> tmp = new ArrayList<Super_object>();
        tmp = composite.get_objs();

        // set back objects' depth
        for(int i = 0; i < tmp.size(); i++){
            objects.add(tmp.get(i));
            tmp.get(i).set_current_depth(tmp.get(i).get_initial_depth());
        }
        objects.remove(composite);
        selected_objects.clear();
        repaint();
    }

    public void rename(String name){
        selected_objects.get(0).rename(name);
        repaint();
    }

    public void paint(Graphics shape){
        // paint all objects on canvas
        // repaint() will call paint automatically

        // sort objects according to their depth value
        Collections.sort(objects, Comparator.comparing(Super_object::get_current_depth));

        Dimension dim = getSize();
        shape.setColor(Color.white);
        shape.fillRect(0, 0, dim.width, dim.height);
        shape.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) shape;
        g2d.setStroke(new BasicStroke(1));

        // paint all objects on canvas
        // if the object is selected, show it's ports
        for(int i = 0; i < objects.size(); i++){
            Super_object obj = objects.get(i);
            obj.draw(shape);
            if(selected_objects.contains(obj)){
                obj.show_ports(shape);
            }
        }

        // if currently dragging a line
        if(drag_line != null){
            drag_line.draw(shape);
        }

        // if selected area is not empty, paint the selected area;
        if(!selected_area.isEmpty()){
            int alpah = 50;
            shape.setColor(new Color(155, 212, 222, alpah));
            shape.fillRect(selected_area.x, selected_area.y, selected_area.width, selected_area.height);
            shape.setColor(new Color(155, 212, 222));
            shape.drawRect(selected_area.x, selected_area.y, selected_area.width, selected_area.height);
        }
    }

    public void set_mode(){
        // set current mode (select mode, class mode, composition line mode ...)
        removeMouseListener((MouseListener) listener);
        removeMouseMotionListener((MouseMotionListener) listener);
        listener = (EventListener) current_mode;
        addMouseListener((MouseListener) listener);
		addMouseMotionListener((MouseMotionListener) listener);
    }
    
    public void reset_selection(){
        // clean the selected object in composite object(s)
        for(int i = 0; i < selected_objects.size(); i++){
            selected_objects.get(i).clean_selected();
        }
        selected_objects.clear();
        selected_area.setBounds(0, 0, 0, 0);
    }

    public boolean inside_selected_area(Super_object obj){
        // check if object is in selected area
        Point upper_left = new Point(obj.get_x1(), obj.get_y1());
        Point lower_right = new Point(obj.get_x2(), obj.get_y2());
        if(selected_area.contains(upper_left) && selected_area.contains(lower_right)){
            return true;
        }
        return false;
    }
}
