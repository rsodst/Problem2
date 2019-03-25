import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToggleButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdditionWnd extends JFrame {
	private JTextField textField;
	public AdditionWnd() {	
		this.setTitle("\u0420\u0430\u0441\u0447\u0435\u0442 \u043A\u043E\u043D\u0446\u0435\u043D\u0442\u0440\u0430\u0446\u0438\u0438 \u0430\u043B\u043A\u043E\u0433\u043E\u043B\u044F");
		Dimension formSize = new Dimension();
		formSize.width = 602;
		formSize.height = 376;
		this.setSize(new Dimension(406, 285));
		this.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		textField = new JTextField();
		textField.setBackground(Color.GRAY);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 56));
		textField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(66)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		
		JLabel label = new JLabel("\u0423\u043A\u0430\u0436\u0438\u0442\u0435 \u043F\u043E\u043B:");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u041C\u0443\u0436\u0441\u043A\u043E\u0439", "\u0416\u0435\u043D\u0441\u043A\u0438\u0439"}));
		
		JSpinner time = new JSpinner();
		
		JLabel label_1 = new JLabel("\u0412\u0440\u0435\u043C\u044F(\u0447\u0430\u0441):");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel label_2 = new JLabel("\u041C\u0430\u0441\u0441\u0430(\u043A\u0433):");
		label_2.setToolTipText("\u041C\u0430\u0441\u0441\u0430 \u0442\u0435\u043B\u0430");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JSpinner weight = new JSpinner();
		
		JLabel label_3 = new JLabel("\u0412\u044B\u043F\u0438\u0442\u043E(\u0433):");
		label_3.setToolTipText("\u0412\u044B\u043F\u0438\u0442\u043E \u0441\u043F\u0438\u0440\u0442\u043D\u043E\u0433\u043E \u0432 \u0433\u0440\u0430\u043C\u043C\u0430\u0445");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JSpinner capacity = new JSpinner();
		
		JButton perform = new JButton("\u0420\u0430\u0441\u0447\u0438\u0442\u0430\u0442\u044C");
		perform.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int A = (int)capacity.getValue();
				A *=0.4*0.79384;
				int P = (int)weight.getValue();
				int T = (int)time.getValue();
				float r = comboBox.getSelectedIndex() == 0 ? 0.7f : 0.6f;
				float b60 = 0.1f;
				float result = (A/(P*r)-b60*T);
				String formattedDouble = String.format("%.2f", result);
				
				if (result > 0.2 && result < 1){
					textField.setBackground(Color.GREEN);
				}else if (result > 1 && result < 3){
					textField.setBackground(Color.YELLOW);
				}else if (result > 5){
					textField.setBackground(Color.RED);	
				}
				if (result < 0){
					textField.setBackground(Color.GRAY);	
					result = 0;
				}
				
				textField.setText(formattedDouble);
				}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(time, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(label_3)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(capacity, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
										.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
											.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(weight, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(44)
							.addComponent(perform))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(label)))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(time, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(weight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(capacity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(perform)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}
}
