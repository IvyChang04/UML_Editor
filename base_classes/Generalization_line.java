package base_classes;

import java.awt.*;

public class Generalization_line extends Line{

    int width = 10, height = 10;

    public Generalization_line(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void draw(Graphics shape){
        shape.drawLine(x1, y1, x2, y2);
        
        int deltaX = x2 - x1, deltaY = y2 - y1;
        double dist_p1_p2 = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        double dist_c_p1 = dist_p1_p2 - width;
        double sinθ = deltaY / dist_p1_p2, cosθ = deltaX / dist_p1_p2;
        Point A = new Point(), B = new Point();

        A.x = (int) (dist_c_p1 * cosθ - height * sinθ + x1);
        A.y = (int) (dist_c_p1 * sinθ + height * cosθ + y1);
        B.x = (int) (dist_c_p1 * cosθ + height * sinθ + x1);
        B.y = (int) (dist_c_p1 * sinθ - height * cosθ + y1);

        int[] x = {A.x, B.x, x2};
        int[] y = {A.y, B.y, y2};

        shape.fillPolygon(x, y, 3);

    }
}
