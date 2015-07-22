import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class WordFrame extends JFrame{

	public static final int width=300;
	public static final int height=150;
	JButton closeButton;
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
		
		this.setOpacity((float) 0.4);
		
	}
	
	public static void main(String[] args) {
		WordFrame wf=new WordFrame();
		wf.setVisible(true);
		
		
	}
}
