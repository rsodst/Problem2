import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.KeyStore.Entry;
import java.util.Timer;
import java.util.TimerTask;

public class LoginWnd extends JFrame {
	private JTextField loginField;
	private JTextField passwordField;
	private boolean firstLoginEnter = true;
	private boolean firstPasswordEnter = true;
	private String password = "1234";
	private String login = "Root";
	private int errorCount = 0;
	private int maxErrorCount = 2;
	int time = 10;
	LoginWnd base = this;
	private boolean complete = false;
	public LoginWnd() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("\u0410\u0432\u0442\u043E\u0440\u0438\u0437\u0430\u0446\u0438\u044F");
		setType(Type.UTILITY);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		
		Dimension formSize = new Dimension();
		formSize.width = 245;
		formSize.height = 139;
		this.setSize(new Dimension(245, 140));
		
		loginField = new JTextField();
		loginField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (firstLoginEnter){
					loginField.setText("");
					firstLoginEnter = false;
				}
			}
		});
		loginField.setText("\u041B\u043E\u0433\u0438\u043D");
		loginField.setToolTipText("\u041B\u043E\u0433\u0438\u043D");
		loginField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (firstPasswordEnter){
					passwordField.setText("");
					firstPasswordEnter = false;
				}
			}
		});
		passwordField.setText("\u041F\u0430\u0440\u043E\u043B\u044C");
		passwordField.setToolTipText("\u041F\u0430\u0440\u043E\u043B\u044C");
		passwordField.setColumns(10);
		
		JButton Entry = new JButton("\u041E\u0442\u043A\u0440\u044B\u0442\u044C");
		Entry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!complete){
				if (errorCount >= maxErrorCount){
					JOptionPane.showMessageDialog(null, "Студенты: Елейник и Грачева не могут допуститься к работе с программой!", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
					System.exit(EXIT_ON_CLOSE);
				}
				if (loginField.getText().equals(login)
					&& passwordField.getText().equals(password)){
					OpenMainForm();
				}else{
					firstLoginEnter = true;
					firstPasswordEnter = true;
					++errorCount;
					JOptionPane.showMessageDialog(null, "Введеные Вами данные не верны!", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
				}
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
								.addComponent(loginField, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(80)
							.addComponent(Entry)))
					.addContainerGap(444, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(loginField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(Entry)
					.addContainerGap(244, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	
	
	private void OpenMainForm(){
		
		loginField.setEnabled(false);
		
		passwordField.setEnabled(false);
		complete = true;
		
		new java.util.Timer().schedule(
		        new TimerTask() {
		            public void run() {
		            	base.setTitle("Программа запуститься через:"+time);
		            	--time;
		                if (time < 0){
		                	OpenForm();
		                	this.cancel();
		                }
		            }
 
		        }, 
		    time,1000);	
			
	
	}
	
	private void OpenForm(){
		MainForm form = new MainForm();
		form.setVisible(true);
		this.setVisible(false);
	}

}
