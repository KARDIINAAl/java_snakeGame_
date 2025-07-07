import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception
     {
       int boardwidth =500;
       int boarheight =boardwidth;
       JFrame frame= new JFrame("snake");
       frame.setVisible(true);
       frame.setSize(boardwidth,boarheight);
       frame.setLocationRelativeTo(null);
       frame.setResizable(false);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       sneakegame sneak = new sneakegame(boardwidth, boarheight);
       frame.add(sneak);
       frame.pack();
       sneak.requestFocus();
    }
}
