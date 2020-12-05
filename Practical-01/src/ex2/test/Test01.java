package ex2.test;

import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Test01 {
	public static void main(String[] args){
		JFrame jf = new JFrame("Solitary");
		jf.setBounds(200, 200, 400, 400);
		jf.setVisible(true);
		jf.setLayout(new GridLayout(3, 1));
		
		JLabel headerLabel = new JLabel("TheWorld",JLabel.CENTER );
		headerLabel.setSize(200, 200);
		jf.add(headerLabel);
		
	    JMenu fileMenu = new JMenu("文件");
	    JMenu editMenu = new JMenu("编辑");
	    
	    // fileMenu's items
	    JMenuItem file_item1 = new JMenuItem("保存");
	    JMenuItem file_item2 = new JMenuItem("导入");
	    fileMenu.add(file_item1);
	    fileMenu.add(file_item2);
	    
	    // MenuBar
	    JMenuBar menuBar1 = new JMenuBar();
	    menuBar1.add(fileMenu);
	    menuBar1.add(editMenu);
	    jf.setJMenuBar(menuBar1);
	    
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// https://www.yiibai.com/swing/swing_jmenuitem_control.html
		// layout  n.布局；设计；布置；安排；
	}
}
