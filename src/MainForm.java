import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class MainForm extends JFrame {
	CalcLogic calcLogic = new CalcLogic();
	boolean lockInput = false;
	final int maxInputSize = 10;
	private int state = 0;
	AdditionWnd wnd = new AdditionWnd();
	// 0 - first input
	// 1 - second input
	private JTextField textField;
	private JTextField LogPanel;
	public MainForm() {
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 10));
		this.setTitle("Калькулятор");
		Dimension formSize = new Dimension();
		formSize.width = 602;
		formSize.height = 376;
		this.setSize(new Dimension(660, 376));
		this.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JButton ClearBtn = new JButton("C");
		ClearBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("0.0");
				state = 0;
				LogPanel.setBackground(Color.gray);
				lockInput = false;
			}
		});
		ClearBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton ClearLeftBtn = new JButton("CL");
		ClearLeftBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().length() > 0){
					lockInput = false;
				textField.setText(textField.getText().substring(1));
				}
			}
		});
		ClearLeftBtn.setFont(new Font("Consolas", Font.PLAIN, 17));
		
		JButton btnCr = new JButton("CR");
		btnCr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int length = textField.getText().length();
				if (length > 0){
				--length;
				lockInput = false;
				textField.setText(textField.getText().substring(0,length));
				}
			}
		});
		btnCr.setFont(new Font("Consolas", Font.PLAIN, 17));
		
		JButton button = new JButton("=");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String input = textField.getText();
				input = input.replaceAll("SQRT", "#");
				input = input.replaceAll("LN", "$");
				
				CalcResult cl = calcLogic.Calculate(input);
				
				textField.setText(cl.Result+"");
				
				if (cl.State == PerformState.COMPLETE){
				LogPanel.setBackground(Color.GREEN);
				}else{
					LogPanel.setBackground(Color.RED);
				}
			}
		});
		button.setFont(new Font("Consolas", Font.PLAIN, 17));
		
		LogPanel = new JTextField();
		LogPanel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		LogPanel.setBackground(Color.gray);
		LogPanel.setEditable(false);
		LogPanel.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE))
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(ClearBtn, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
								.addComponent(ClearLeftBtn, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCr, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(LogPanel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(ClearBtn, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ClearLeftBtn, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCr, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addGap(60)
							.addComponent(LogPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		
		JButton OneBtn = new JButton("1");
		
		OneBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText() + "1");
			}
			}
		});
		OneBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton TwoBtn = new JButton("2");
		TwoBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"2");
			}
			}
		});
		TwoBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton ThreeBtn = new JButton("3");
		ThreeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"3");
			}
			}
		});
		ThreeBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton FourBtn = new JButton("4");
		FourBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"4");
			}
			}
		});
		FourBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton FiveBtn = new JButton("5");
		FiveBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"5");
			}
			}
		});
		FiveBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton SixBtn = new JButton("6");
		SixBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"6");
			}
			}
		});
		SixBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton SevenBtn = new JButton("7");
		SevenBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"7");
			}
			}
		});
		SevenBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton EightBtn = new JButton("8");
		EightBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"8");
			}
			}
		});
		EightBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton NineBtn = new JButton("9");
		NineBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"9");
			}
			}
		});
		NineBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton ZeroBtn = new JButton("0");
		ZeroBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"0");
			}
			}
		});
		ZeroBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton SeparateBtn = new JButton(".");
		SeparateBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+".");
			}
			}
		});
		SeparateBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton SwitchSignBtn = new JButton("+/-");
		SwitchSignBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				if (textField.getText().charAt(0) != '-'){
					textField.setText("-"+textField.getText());
				}else{
					textField.setText(textField.getText().substring(1));
				}
				}
			}
		});
		SwitchSignBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton AddBtn = new JButton("+");
		AddBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"+");
			}
			}
		});
		AddBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton SubBtn = new JButton("-");
		SubBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"-");
			}
			}
		});
		SubBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton MultipleBtn = new JButton("*");
		MultipleBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"*");
			}
			}
		});
		MultipleBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton DivBtn = new JButton("/");
		DivBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"/");
				}
			}
		});
		DivBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton PowerBtn = new JButton("^");
		PowerBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"^");
			}
			}
		});
		PowerBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton SqrtBtn = new JButton("Sqrt");
		SqrtBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"SQRT");
			}
			}
		});
		SqrtBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton LeftBrBtn = new JButton("(");
		LeftBrBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"(");
			}
			}
		});
		LeftBrBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton RightBrBtn = new JButton(")");
		RightBrBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+")");
			}
			}
		});
		RightBrBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton LogBtn = new JButton("Log");
		LogBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){		
				TextFieldChecker();
				textField.setText(textField.getText()+"LN");
			}
			}
		});
		LogBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton PiConst = new JButton("PI");
		PiConst.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"3.1415926");
			}
			}
		});
		PiConst.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton ExpBtn = new JButton("E");
		ExpBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!lockInput){
				TextFieldChecker();
				textField.setText(textField.getText()+"2.718281");
			}
			}
		});
		ExpBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton SpecFuncBtn = new JButton("SF");
		SpecFuncBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!wnd.isVisible()){
				wnd.setVisible(true);
				}
				}
		});
		SpecFuncBtn.setFont(new Font("Consolas", Font.PLAIN, 18));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(OneBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(TwoBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(ThreeBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(FourBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(FiveBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(SixBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(AddBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(SubBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(SevenBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(EightBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(NineBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(ZeroBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
										.addComponent(PiConst, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(ExpBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(SpecFuncBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(SeparateBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(SwitchSignBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)))))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(MultipleBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addComponent(LeftBrBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(RightBrBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addComponent(DivBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(PowerBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
						.addComponent(SqrtBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
						.addComponent(LogBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(PowerBtn, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(SqrtBtn, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
									.addComponent(AddBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
											.addComponent(ThreeBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
											.addComponent(TwoBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
											.addComponent(OneBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
												.addComponent(FourBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
												.addComponent(FiveBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
											.addComponent(SixBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))))
								.addComponent(SubBtn, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(SevenBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
										.addComponent(EightBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
										.addComponent(NineBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(ZeroBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
										.addComponent(SeparateBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
										.addComponent(SwitchSignBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
								.addComponent(MultipleBtn, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(DivBtn, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(LeftBrBtn, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(RightBrBtn, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(LogBtn, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(PiConst, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(ExpBtn, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(SpecFuncBtn, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(75, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		textField = new JTextField();		
		  String allowedChar = "1234567890+-=/()*^#%&$?.,";
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (textField.getText().length() < maxInputSize){
				
					if (state ==0){
						textField.setText("");			
						state = 1;
					}
					if (isAllowedChar(e.getKeyChar()+"",allowedChar)){
					textField.setText(textField.getText()+e.getKeyChar());
					}
					if (e.getKeyCode() == 8){
						int length = textField.getText().length();
						if (length > 0){
						--length;
						lockInput = false;
						textField.setText(textField.getText().substring(0,length));
						}else{
							state = 0;
							textField.setText("0.0");
						}
					}
					if (e.getKeyCode() == 27){
						textField.setText("0.0");
						state = 0;
						
					}
					if (e.getKeyCode() == 10){
						String input = textField.getText();
						input = input.replaceAll("SQRT", "#");
						input = input.replaceAll("LN", "$");
						
						CalcResult cl = calcLogic.Calculate(input);
						
						textField.setText(cl.Result+"");
						
						if (cl.State == PerformState.COMPLETE){
						LogPanel.setBackground(Color.GREEN);
						}else{
							LogPanel.setBackground(Color.RED);
						}
						
					}
				}
				}
		});
		textField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if (textField.getText().length() > maxInputSize){
					lockInput = true;
				}
			}
		});
		
		textField.setText("0.0");
		textField.setEditable(false);
		textField.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				
			}
		});
		textField.setFont(new Font("Consolas", Font.PLAIN, 28));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}
	
	private void TextFieldChecker(){
		if (state == 0){
			textField.setText("");
			state = 1;
		}
		
	}
	
	  private boolean isAllowedChar(String chr, String allowedChar)
	    {
	            if (allowedChar.indexOf(chr) == -1)
	            {
	                return false;
	            }
	        
	        return true;
	    }
	
	}
