package ui;

import common.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by feng on 2015/8/7.
 */
public class MainUI extends JFrame{
    String host;
    public MainUI(String host){
        this.host=host;
        this.setSize(250, 100);
        Toolkit tk = Toolkit.getDefaultToolkit();
        setLocation((tk.getScreenSize().height - this.HEIGHT)/2,
                (tk.getScreenSize().width - this.WIDTH)/2);
        this.setLayout();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Controller.getInstance().cmdCancelShutDown();
            }
        });
    }

    private void setLayout(){
        GridLayout layout=new GridLayout();
        Container container=getContentPane();
        container.setLayout(layout);
        Label label=new Label();
        label.setText("Your ip address is:");
        container.add(label);
        JTextField textField=new JTextField();
        textField.setText(host);
        container.add(textField);
    }
}
