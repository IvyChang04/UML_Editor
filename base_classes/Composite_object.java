package base_classes;

import java.awt.*;
import java.util.ArrayList;


public class Composite_object extends Super_object {

    private ArrayList<Super_object> objects = new ArrayList<Super_object>();
    private Rectangle composite_area = new Rectangle();
    private Super_object selected_object = null;

    @Override
    public void draw(Graphics shape){
        for(int i = 0; i < objects.size(); i++){
            Super_object obj = objects.get(i);
            obj.draw(shape);
        }
        if(selected_object != null){
            show_ports(shape);
        }
    }

    @Override
    public void show_ports(Graphics shape){
        // show composite object area and selected object's ports
        int alpha = 70;
        int margin = 10;
        int recX = composite_area.x - margin, recY = composite_area.y - margin;
        int rec_width = composite_area.width + margin*2, rec_height = composite_area.height + margin*2;
    
        shape.setColor(new Color(155, 212, 222, alpha));
        shape.fillRect(recX, recY, rec_width, rec_height);
        shape.setColor(new Color(155, 212, 222));
        shape.drawRect(recX, recY, rec_width, rec_height);
        shape.setColor(Color.black);

        if(selected_object != null){
            selected_object.show_ports(shape);
        }
    }

    @Override
    public void move(int deltaX, int deltaY){
        for(int i = 0; i < objects.size(); i++){
            Super_object obj = objects.get(i);
            obj.move(deltaX, deltaY);
        }
        move_area(deltaX, deltaY);
    }

    @Override
    public String inside(Point p){
        // check if point is inside composite object
        for(int i = objects.size() - 1; i >= 0; i--){
            Super_object obj = objects.get(i);
            String flag = obj.inside(p);
            if(flag != null){
                selected_object = obj;
                return "inside composite";
            }
        }
        return null;
    }

    @Override
    public void rename(String name){
        selected_object.rename(name);
    }

    @Override
    public void clean_selected(){
        if(selected_object != null){
            if(selected_object.get_selected_obj() != null){
                selected_object.clean_selected();
            }
        }
        selected_object = null;
    }

    @Override
    public Super_object get_selected_obj(){
        return selected_object;
    }

    @Override
    public ArrayList<Super_object> get_objs(){
        return objects;
    }
    
    public int get_size(){
        return objects.size();
    }

    public void set_area(){
        // set composite object area based on basic objects inside
        Point upper_left, bottom_right;
		int leftX = Integer.MAX_VALUE, rightX = Integer.MIN_VALUE;
		int upperY = Integer.MAX_VALUE, bottomY = Integer.MIN_VALUE;

		for (int i = 0; i < objects.size(); i++) {
			Super_object obj = objects.get(i);
			if (obj.get_x1() < leftX) {
				leftX = obj.get_x1();
			}
			if (obj.get_x2() > rightX) {
				rightX = obj.get_x2();
			}
			if (obj.get_y1() < upperY) {
				upperY = obj.get_y1();
			}
			if (obj.get_y2() > bottomY) {
				bottomY = obj.get_y2();
			}
		}

		upper_left = new Point(leftX, upperY);
		bottom_right = new Point(rightX, bottomY);
		composite_area.setBounds(upper_left.x, upper_left.y, 
                                 Math.abs(upper_left.x - bottom_right.x), 
                                 Math.abs(upper_left.y - bottom_right.y));

		x1 = composite_area.x;
		y1 = composite_area.y;
		x2 = composite_area.x + composite_area.width;
		y2 = composite_area.y + composite_area.height;
    }

    protected void move_area(int deltaX, int deltaY){
        // move composite area
        composite_area.setLocation(composite_area.x + deltaX, composite_area.y + deltaY);
        x1 = composite_area.x;
        y1 = composite_area.y;
        x2 = composite_area.x + composite_area.width;
        y2 = composite_area.y + composite_area.height;
    }

    public void add_obj(Super_object obj){
        objects.add(obj);
    }
}
