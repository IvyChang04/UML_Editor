package user_mode;

import java.awt.Point;
import base_classes.Basic_object;
import base_classes.Line;

public interface Object_factory_interface {
    public Basic_object createBasicObject(Point p);
    public Line createLine(int start_x, int start_y, int end_x, int end_y);
}
