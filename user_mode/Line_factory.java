package user_mode;

import base_classes.Association_line;
import base_classes.Composition_line;
import base_classes.Generalization_line;
import base_classes.Line;

public class Line_factory {
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
}
