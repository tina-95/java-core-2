import javafx.scene.input.KeyCode;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;


public class Chat extends JFrame {
    private final JTextField textField = new JTextField();
    private final JTextField textField2 = new JTextField();

    public Chat() {
        setTitle("Chat_Lesson4");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(500, 50, 600, 800);


        setLayout(new GridBagLayout());

        GridBagConstraints b = new GridBagConstraints();
        Border empty = new EmptyBorder(50, 10, 10, 10);
        Border compound = new CompoundBorder(textField2.getBorder(), empty);
        textField2.setBorder(compound);
        b.fill = GridBagConstraints.HORIZONTAL;

        b.weightx = 1;
        b.gridx = 0;
        b.gridy = 0;
        add(textField2, b);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.9;
        c.gridx = 0;
        c.gridy = 9;
       // textField.addActionListener(this::reactToAction);
        textField.addKeyListener(new KeyListener() {
            @Override
        public void keyTyped(KeyEvent keyEvent) {


            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER){
                    String text = textField.getText();
                    textField2.setText(text);
                    textField.setText(" ");}

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
        add(textField, c);








        JButton button = new JButton("Say");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.1;
        c.gridx = 1;
        c.gridy = 9;
      button.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent actionEvent) {
              String text = textField.getText();
              textField2.setText(text);
              textField.setText(" ");

          }
      });
        add(button, c);

        setVisible(true);
    }

   // private void reactToAction(ActionEvent event) {
        //JOptionPane.showMessageDialog(null, textField.getText());

 //}






}
