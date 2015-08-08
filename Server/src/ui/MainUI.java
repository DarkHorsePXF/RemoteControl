package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by feng on 2015/8/7.
 */
public class MainUI extends JFrame{
    String host;
    public MainUI(String host){
        this.host=host;
        this.setSize(200,100);
        Toolkit tk = Toolkit.getDefaultToolkit();
        setLocation((tk.getScreenSize().height - this.HEIGHT)/2,
                (tk.getScreenSize().width - this.WIDTH)/2);
        this.setLayout();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
