package ex2.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import ex2.dao.UserDao;
import ex2.dao.impl.UserDaoImplForList;
import ex2.entity.User;
import ex2.gui.ModifyDialog;

public class Menu extends JFrame{
	private JMenuItem jmiRegiste, jmiSave, jmiLoad, jmiModify, jmiRemove, jmiAddright,jmiRemoveRight,jmiAssignright;
	private JMenuItem jmiSearchByEmail,jmiSearchAll;
	
	private JButton btnRegiste, btnEdit, btnRemove, btnSearch;
	
	private JFileChooser fileSave,fileLoad;
	
	private UserDao userDao;
	
	public static void main(String[] args) throws IOException{
		new Menu().showMe();
	}	  
	
	public Menu(){
	    userDao = new UserDaoImplForList();
	}
	
	private void createMenuBar(){
		fileSave = new JFileChooser();
		fileLoad = new JFileChooser();
		
		//创建菜单栏
		JMenuBar  menuBar = new  JMenuBar();
		//创建菜单
		JMenu registeMenu = new JMenu("注册(N)");
		//设置热键
		registeMenu.setMnemonic(KeyEvent.VK_N);
		//创建、并向菜单添加菜单项
		registeMenu.add(jmiRegiste = new JMenuItem("注册用户"));
		//设置快捷键
		jmiRegiste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		registeMenu.add(jmiSave = new JMenuItem("保存用户"));
		registeMenu.add(jmiLoad = new JMenuItem("导入用户"));
		//将菜单添加到菜单栏
		menuBar.add(registeMenu);
		
		JMenu editmenu = new JMenu("编辑用户信息(E)");
		editmenu.setMnemonic(KeyEvent.VK_E);
		menuBar.add(editmenu);
		editmenu.add(jmiModify = new JMenuItem("修改用户信息(M)",'M'));
		editmenu.add(jmiRemove = new JMenuItem("删除用户信息(R)",'R'));

		JMenu searchmenu = new JMenu("查询用户信息(S)");
		searchmenu.setMnemonic(KeyEvent.VK_S);
		menuBar.add(searchmenu);
		searchmenu.add(jmiSearchByEmail = new JMenuItem("按email查询(N)",'N'));
		searchmenu.add(jmiSearchAll = new JMenuItem("浏览所有用户(H)",'H'));
		
		JMenu rightmenu = new JMenu("权限管理(L)");
		menuBar.add(rightmenu);
		rightmenu.setMnemonic(KeyEvent.VK_L);
		rightmenu.add(jmiAddright = new JMenuItem("增加权限(N)",'N'));
		rightmenu.add(jmiRemoveRight = new JMenuItem("删除权限(R)",'R'));
		rightmenu.add(jmiAssignright = new JMenuItem("为用户分配权限(A)",'A'));
		
		this.setJMenuBar(menuBar);
	}
	
	private void createToolBar(){
		JToolBar toolBar = new JToolBar();  //创建工具栏		
		
		btnRegiste = new JButton("", new ImageIcon(this.getClass().getResource("../ico/add.gif")));
		btnRegiste.setToolTipText("注册用户");
		btnEdit = new JButton("", new ImageIcon(this.getClass().getResource("../ico/modify.gif")));
		btnEdit.setToolTipText("修改用户信息");
		btnRemove = new JButton("", new ImageIcon(this.getClass().getResource("../ico/remove.gif")));
		btnRemove.setToolTipText("删除用户信息");
		btnSearch = new JButton("", new ImageIcon(this.getClass().getResource("../ico/search.gif")));
		btnSearch.setToolTipText("浏览所有用户");
		
		toolBar.add(btnRegiste);  
		toolBar.add(btnEdit); 
		toolBar.add(btnRemove);
		toolBar.add(btnSearch);
		
		//将工具栏添加至JFrame，this为当前窗口对象
		this.add("North",toolBar);
	}
	private void init(){
		//1.创建菜单栏
		createMenuBar();  
		
		//2.创建工具栏
		createToolBar();  			
		
		//3.按钮面板
		JPanel panel = new JPanel();
	    panel.setPreferredSize(new Dimension(800,400));
		this.add("Center",panel);
	}
	
	public void showMe(){
		//设置窗口对象
		this.init();
		this.addEventHandler();
		this.setTitle("用户管理系统");
		this.setBounds(300,200,800,400);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addEventHandler(){
		jmiRegiste.addActionListener(new RegisteHandler());
		btnRegiste.addActionListener(new RegisteHandler());
		jmiSearchByEmail.addActionListener(new SearchByEmailHandler());
		jmiSearchAll.addActionListener(new SearchAllHandler());
		jmiSave.addActionListener(new SaveUsersHandler());
		btnSearch.addActionListener(new SearchAllHandler());
		jmiLoad.addActionListener(new LoadUsersHandler());
		jmiModify.addActionListener(new ModifyHandler());
		btnEdit.addActionListener(new ModifyHandler());
		btnRemove.addActionListener(new RemoveHandler());
		jmiRemove.addActionListener(new RemoveHandler());
		
		
	}
	
	private class RegisteHandler implements ActionListener{//注册
		public void actionPerformed(ActionEvent events) {
			new RegisterDialog(Menu.this, "注册用户", userDao).showMe(Menu.this);
		}  		
	}

	private class SearchByEmailHandler implements ActionListener{//按email查询
		public void actionPerformed(ActionEvent events) {
			new SearchDialog(Menu.this,"按email查询", userDao).showMe(Menu.this);
		}  		
	}

	private class SearchAllHandler implements ActionListener{  //查询所有用户
		public void actionPerformed(ActionEvent e){
			List<User> users = userDao.selectAll();
			if(users.size()!=0){
				new ShowDataTableDialog(Menu.this, "查询结果", users).showMe(Menu.this);  //显示所有用户				
			}else{
				JOptionPane.showMessageDialog( null, "没有用户的信息", "提示" ,JOptionPane.PLAIN_MESSAGE );
			}			
		}
	}
	
	private class SaveUsersHandler implements ActionListener{  //查询所有用户
		public void actionPerformed(ActionEvent e){
			List<User> users = userDao.selectAll();
			if(users.size()!=0){ //将用户信息写到文件
				FileOutputStream fos = null;
				ObjectOutputStream oos = null;
				
				try {
					fileSave.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
					fileSave.showDialog(new JLabel(), "选择");  
				    File file = fileSave.getSelectedFile();  
				     			        
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					
					oos.writeObject(users);
				}catch(IOException ee){
					
				}
			}
		}
	}
	
	private class LoadUsersHandler implements ActionListener{  //查询所有用户
		public void actionPerformed(ActionEvent e){
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			
			try {
				fileLoad.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
				fileLoad.showDialog(new JLabel(), "选择");  
			    File file = fileLoad.getSelectedFile();  
			     			        
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				
				List<User> users = (List)ois.readObject();
				userDao = new UserDaoImplForList(users);
				
				new ShowDataTableDialog(Menu.this, "当前用户列表", users).showMe(Menu.this);  //显示所有用户	
			}catch(IOException ee){
				
			}catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class ModifyHandler implements ActionListener{//修改
		public void actionPerformed(ActionEvent events) {
			new ModifyDialog(Menu.this, "修改用户信息", userDao).showMe(Menu.this);
		}  		
	}

	//查询所有用户
	private class RemoveHandler implements ActionListener{//删除
		public void actionPerformed(ActionEvent events) {
			new DeleteDialog(Menu.this,"删除用户信息", userDao).showMe(Menu.this);
		}  		
	}
}