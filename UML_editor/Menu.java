package UML_editor;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Menu extends JMenuBar{
    private Canvas canvas;
    public Menu(){
        canvas = Canvas.get_canvas();
        JMenu file, edit;
        JMenuItem change_name, group, ungroup;

        file = new JMenu("File");
        add(file);

        edit = new JMenu("Edit");
        add(edit);

        change_name = new JMenuItem("Change name");
        change_name.addActionListener(new Change_name_listener());
        edit.add(change_name);

        group = new JMenuItem("Group");
        group.addActionListener(new Group_listener());
        edit.add(group);

        ungroup = new JMenuItem("Ungroup");
        ungroup.addActionListener(new Ungroup_listener());
        edit.add(ungroup);
    }

    private void change_name_from_input(){
        JFrame input_text_window = new JFrame("change object name");
        input_text_window.setSize(300, 150);
        input_text_window.getContentPane().setLayout(new GridLayout(0, 1));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JTextField text =  new JTextField("Object Name");
		panel.add(text);
		input_text_window.getContentPane().add(panel);

        panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                canvas.rename(text.getText());
                input_text_window.dispose();
            }
        });
		panel.add(ok);

        JButton cancle = new JButton("Cancle");
        cancle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                input_text_window.dispose();
            }
        });
        panel.add(cancle);

        input_text_window.getContentPane().add(panel);
        input_text_window.setLocationRelativeTo(null);
        input_text_window.setVisible(true);
    }

    class Change_name_listener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            change_name_from_input();
        }
    }

    class Group_listener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            canvas.create_composite();
        }
    }

    class Ungroup_listener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            canvas.cancle_composite();
        }
    }
}
