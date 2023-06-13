package UML_editor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import user_mode.*;


public class Tool_bar extends JToolBar{
    private int tool_numbers = 6;
    private JButton hold_bttn = null;
    private ImageIcon prev_image = null;
    private Canvas canvas;

    public Tool_bar(){

        setBackground(Color.white);
        canvas = Canvas.get_canvas();
        setLayout(new GridLayout(tool_numbers, 1));

        Tool_bttn_create select_bttn = new Tool_bttn_create("img/select.png",
                                                            new Select_mode(),
                                                            "img/select_copy.png");
        add(select_bttn);

        Tool_bttn_create asso_bttn = new Tool_bttn_create("img/association_line.png",
                                                          new Create_line_mode("asso"),
                                                          "img/association_line_copy.png");
        add(asso_bttn);

        Tool_bttn_create gen_bttn = new Tool_bttn_create("img/generalization_line.png",
                                                         new Create_line_mode("gen"),
                                                         "img/generalization_line_copy.png");
        add(gen_bttn);

        Tool_bttn_create comp_bttn = new Tool_bttn_create("img/composition_line.png",
                                                          new Create_line_mode("compo"),
                                                          "img/composition_line_copy.png");
        add(comp_bttn);

        Tool_bttn_create class_bttn = new Tool_bttn_create("img/class.png",
                                                           new Create_basic_object_mode("classObj"),
                                                           "img/class_copy.png");
        add(class_bttn);

        Tool_bttn_create use_case_bttn = new Tool_bttn_create("img/use_case.png",
                                                              new Create_basic_object_mode("useCase"),
                                                              "img/use_case_copy.png");
        add(use_case_bttn);
    }

    public class Tool_bttn_create extends JButton{
        Mode tool_mode;
        public Tool_bttn_create(String icon_file_name, Mode tool_mode, String selected_icon_file_name){
            ImageIcon icon = new ImageIcon(icon_file_name);
            ImageIcon selected = new ImageIcon(selected_icon_file_name);

            canvas = Canvas.get_canvas();
            this.tool_mode = tool_mode;
            setIcon(icon);
            setFocusable(false);
            setBorderPainted(false);
            addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(hold_bttn != null){
                        hold_bttn.setIcon(prev_image);
                    }
                    prev_image = icon;
                    hold_bttn = (JButton) e.getSource();
                    hold_bttn.setIcon(selected);
                    canvas.current_mode = tool_mode;
                    canvas.set_mode();
                    canvas.reset_selection();
                    canvas.repaint();
                }
            });
        }
    }
}

