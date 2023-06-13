package user_mode;

import java.awt.Point;
import base_classes.Association_line;
import base_classes.Basic_object;
import base_classes.Class_object;
import base_classes.Composition_line;
import base_classes.Generalization_line;
import base_classes.Line;
import base_classes.Use_case;

public class Line_factory implements Object_factory_interface{
    private String lineType;
    public Line_factory(String lineType){
        this.lineType = lineType;
    }
    public Line createLine(int start_x, int start_y, int end_x, int end_y){
        if(lineType.equals("asso")){
            return new Association_line(start_x, start_y, end_x, end_y);
        }
        else if(lineType.equals("gen")){
            return new Generalization_line(start_x, start_y, end_x, end_y);
        }
        else if(lineType.equals("compo")){
            return new Composition_line(start_x, start_y, end_x, end_y);
        }
        return null;
    }
    public Basic_object createBasicObject(Point p, int start_x, int start_y){
        if(lineType.equals("useCase")){
            return new Use_case(p, start_x, start_y);
        }
        else if(lineType.equals("classObj")){
            return new Class_object(p, start_x, start_y);
        }
        return null;
    }
}
