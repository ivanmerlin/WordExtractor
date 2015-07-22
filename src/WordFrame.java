import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class WordFrame extends JFrame implements Runnable{

	public static final int width=400;
	public static final int height=170;
	public static final String pathValue="materials/words2.txt";
	Font font=new Font("微软雅黑",Font.BOLD,15);
	JLabel wordLabel;
	JLabel expLabel;
	JButton closeButton;
	WordRandom wr;
	Thread freshThread;
	public WordFrame(){
	    
		closeButton=new JButton();
		closeButton.setIcon(new ImageIcon("img/closeButton.png"));
		closeButton.setBorderPainted(false);
		closeButton.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				System.exit(0);
			}
			
			
		});
		Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(width,height);
		this.setUndecorated(true);

        Insets   screenInsets   =   Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration());   
        //取得底部边界高度，即任务栏高度  
        int taskHeight=screenInsets.bottom;       
		this.setLocation((int)screenSize.getWidth()-width,(int)screenSize.getHeight()-height-taskHeight);
		
		closeButton.setSize(22,22);
		closeButton.setLocation(width-20,0);
		this.add(closeButton);
		
		 wordLabel=new JLabel();
		 expLabel=new JLabel();
		 
		 wordLabel.setSize(width/4-20,height);
		 expLabel.setSize(width*3/4-8,height);
		 wordLabel.setLocation(20,0);
		 expLabel.setLocation(width/4,0);
		 wordLabel.setFont(font);
		 expLabel.setFont(font);
		 
		 
		 this.add(wordLabel);
		 this.add(expLabel);
		 this.setAlwaysOnTop(true);
		this.setOpacity((float) 0.7);
		freshThread = new Thread(this);
		freshThread.start();
		
		
	}
	public void showText() throws IOException{
	    wr=new WordRandom(pathValue);
	   String[] result=wr.generateRandom();
	   wordLabel.setText(result[0]);
	   expLabel.setText(result[1]);
	   this.repaint();  
	    
	}
	
	
	public static void main(String[] args) throws IOException {
		WordFrame wf=new WordFrame();
		wf.setVisible(true);
		wf.showText();
		
	}
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true){
            try{
                showText();
                Thread.sleep(10000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
