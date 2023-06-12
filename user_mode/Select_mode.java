package user_mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import base_classes.Super_object;


public class Select_mode extends Mode{
    
    private Point starting_point = new Point();
    private ArrayList<Super_object> objects = new ArrayList<Super_object>();
    private String if_inside = "";
    private boolean on_object;

    @Override
    public void mousePressed(MouseEvent m){
        starting_point = m.getPoint();
        objects = canvas.get_all_objects();

        // reset all selected object
        canvas.reset_selection();

        // if pressed on a object, then select the object
        for(int i = objects.size() - 1; i >= 0; i--){
            Super_object obj = objects.get(i);
            if_inside = obj.inside(starting_point);
            if(if_inside != null){
                canvas.selected_objects.add(obj);
                on_object = true;
                break;
            }
            else if(if_inside == null){
                on_object = false;
            }
        }
        canvas.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent m){
        int deltaX = m.getX() - starting_point.x;
        int deltaY = m.getY() - starting_point.y;

        // if pressed on object -> move the object
        if((canvas.selected_objects.size() != 0) && on_object){
            Super_object obj = canvas.selected_objects.get(0);
            obj.move(deltaX, deltaY);
            starting_point = m.getPoint();
        }

        // if pressed out side objects
        else{
            //handle selected bound
            if(m.getX() > starting_point.x){
                canvas.selected_area.setBounds(starting_point.x, starting_point.y, Math.abs(deltaX), Math.abs(deltaY));
            }
            else{
                canvas.selected_area.setBounds(m.getX(), m.getY(), Math.abs(deltaX), Math.abs(deltaY));
            }
            // if object is in selected area -> add object in selected objects
            // if object is not in selected area, but it's selected -> remove
            for(int i = 0; i < objects.size(); i++){
                Super_object obj = objects.get(i);
                if(canvas.inside_selected_area(obj) && !canvas.selected_objects.contains(obj)){
                    canvas.selected_objects.add(obj);
                }
                else if (canvas.inside_selected_area(obj) == false && canvas.selected_objects.contains(obj)){
                    canvas.selected_objects.remove(obj);
                }
            }
        }
        canvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent m){
        // if(canvas.selected_objects.size() == 1){
        //     canvas.selected_area.setSize(Math.abs(m.getX() - starting_point.x), Math.abs(m.getY() - starting_point.y));
        // }
        canvas.selected_area.setBounds(0, 0, 0, 0);
        canvas.repaint();
    }
}
