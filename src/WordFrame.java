import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WordFrame extends JFrame implements Runnable {

    public static final int width = 420;
    public static final int height = 190;
    public static final String pathValue = "materials/words.txt";
    private Point offset;
    Font font = new Font("微软雅黑", Font.BOLD, 15);
    JLabel wordLabel;
    JLabel expLabel;
    JButton closeButton;
    WordRandom wr;
    Thread freshThread;
    int time;

    public WordFrame() {

        time=10;
        closeButton = new JButton();
        closeButton.setIcon(new ImageIcon("img/closeButton.png"));
        closeButton.setBorderPainted(false);
        closeButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                super.mouseClicked(e);
                System.exit(0);
            }

        });
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(width, height);
        this.setUndecorated(true);

        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration());
        // 取得底部边界高度，即任务栏高度
        int taskHeight = screenInsets.bottom;
        this.setLocation((int)screenSize.getWidth()-width, (int) screenSize.getHeight() - height - taskHeight);

        closeButton.setSize(22, 22);
        closeButton.setLocation(width - 20, 0);
        this.add(closeButton);

        wordLabel = new JLabel();
        expLabel = new JLabel();

        wordLabel.setSize(width / 4 - 10, height);
        expLabel.setSize(width * 3 / 4 +2, height);
        wordLabel.setLocation(8, 0);
        expLabel.setLocation(width / 4, 0);
        wordLabel.setFont(font);
        expLabel.setFont(font);

        try {
            wr = new WordRandom(pathValue);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        this.add(wordLabel);
        this.add(expLabel);
        this.setAlwaysOnTop(true);

        this.setOpacity((float) 0.78);
        
        
        wordLabel.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                super.mouseClicked(e);
                if(e.getClickCount()==2){
                    String word=wordLabel.getText();
                    wr.removeElement(word);                    
                    showText();
                    time=10;
                }
            }
            
        });
        
        expLabel.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                super.mouseClicked(e);
                if(e.getClickCount()==2){
                    String word=wordLabel.getText();
                    wr.removeElement(word);
                    showText();
                    time=10;
                }
         
            }
            
        });     

        
        freshThread = new Thread(this);
        freshThread.start();

    }

    public void showText() {

        String[] result = wr.generateRandom();
        wordLabel.setText(result[0]);
        expLabel.setText(result[1]);
        this.repaint();

    }



    public static void main(String[] args) {
        WordFrame wf = new WordFrame();
        wf.setVisible(true);
        wf.showText();

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            time=10;
            showText();
            while(time>0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                time--;
            }

        }
    }
}
