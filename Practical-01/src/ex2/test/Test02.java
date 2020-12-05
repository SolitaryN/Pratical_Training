package ex2.test;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Test02 {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel; 

   public Test02(){
      prepareGUI();
   }
   
   public static void main(String[] args){
       Test02 swingMenuDemo = new Test02();     
       swingMenuDemo.showMenuDemo();
   }
   private void prepareGUI(){
      mainFrame = new JFrame("Solitary");  // JFrame 面板的标题
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));

      headerLabel = new JLabel("",JLabel.CENTER );
      statusLabel = new JLabel("",JLabel.CENTER);        
      statusLabel.setSize(350,100);

      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }
   private void showMenuDemo(){
      //create a menu bar
      final JMenuBar menuBar = new JMenuBar();

      //create menus
      JMenu fileMenu = new JMenu("文件");
      JMenu editMenu = new JMenu("编辑"); 
      final JMenu aboutMenu = new JMenu("关于");
      final JMenu linkMenu = new JMenu("链接");

      //create menu items
      JMenuItem newMenuItem = new JMenuItem("新建");
      newMenuItem.setMnemonic(KeyEvent.VK_N);
      newMenuItem.setActionCommand("New");

      JMenuItem openMenuItem = new JMenuItem("打开");
      openMenuItem.setActionCommand("Open");

      JMenuItem saveMenuItem = new JMenuItem("保存");
      saveMenuItem.setActionCommand("Save");

      JMenuItem exitMenuItem = new JMenuItem("退出");
      exitMenuItem.setActionCommand("Exit");

      JMenuItem cutMenuItem = new JMenuItem("剪切");
      cutMenuItem.setActionCommand("Cut");

      JMenuItem copyMenuItem = new JMenuItem("复制");
      copyMenuItem.setActionCommand("Copy");

      JMenuItem pasteMenuItem = new JMenuItem("粘贴");
      pasteMenuItem.setActionCommand("Paste");

      MenuItemListener menuItemListener = new MenuItemListener();

      newMenuItem.addActionListener(menuItemListener);
      openMenuItem.addActionListener(menuItemListener);
      saveMenuItem.addActionListener(menuItemListener);
      exitMenuItem.addActionListener(menuItemListener);
      cutMenuItem.addActionListener(menuItemListener);
      copyMenuItem.addActionListener(menuItemListener);
      pasteMenuItem.addActionListener(menuItemListener);

      final JCheckBoxMenuItem showWindowMenu = new JCheckBoxMenuItem(
         "显示关于", true);

      showWindowMenu.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {

            if(showWindowMenu.getState()){
               menuBar.add(aboutMenu);
            } else {
               menuBar.remove(aboutMenu);
            }
         }
      });
      final JRadioButtonMenuItem showLinksMenu = new JRadioButtonMenuItem(
         "显示链接", true);

      showLinksMenu.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {

            if(menuBar.getMenu(3)!= null){
               menuBar.remove(linkMenu);
               mainFrame.repaint();
            } else {                   
               menuBar.add(linkMenu);
               mainFrame.repaint();
            }
         }
      });

      //add menu items to menus
      fileMenu.add(newMenuItem);
      fileMenu.add(openMenuItem);
      fileMenu.add(saveMenuItem);
      fileMenu.addSeparator();
      fileMenu.add(showWindowMenu);
      fileMenu.addSeparator();
      fileMenu.add(showLinksMenu);       
      fileMenu.addSeparator();
      fileMenu.add(exitMenuItem);        
      editMenu.add(cutMenuItem);
      editMenu.add(copyMenuItem);
      editMenu.add(pasteMenuItem);

      //add menu to menubar
      menuBar.add(fileMenu);
      menuBar.add(editMenu);
      menuBar.add(aboutMenu);       
      menuBar.add(linkMenu);

      //add menubar to the frame
      mainFrame.setJMenuBar(menuBar);
      mainFrame.setVisible(true);     
   }

   class MenuItemListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {            
         statusLabel.setText(e.getActionCommand() + " JMenuItem clicked.");
      }    
   }
}

