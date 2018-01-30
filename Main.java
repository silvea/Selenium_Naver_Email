package selenium_;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField ID;
	private JPasswordField passwordField;
	private JTextField rece;
	private JTextField point;
	private JTextField title;
	static Start start;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Selenium Naver Mail Send");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ID = new JTextField();
		ID.setBounds(90, 15, 279, 26);
		contentPane.add(ID);
		ID.setColumns(10);
		
		JLabel lblId = new JLabel("아이디");
		lblId.setBounds(12, 20, 57, 15);
		contentPane.add(lblId);
		
		JLabel lblPassword = new JLabel("비밀번호");
		lblPassword.setBounds(12, 56, 82, 15);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(90, 51, 279, 26);
		contentPane.add(passwordField);
		
		rece = new JTextField();
		rece.setBounds(90, 129, 388, 26);
		contentPane.add(rece);
		rece.setColumns(10);
		
		JLabel label = new JLabel("받는사람 ");
		label.setBounds(12, 131, 66, 20);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("참조");
		label_1.setBounds(12, 173, 57, 15);
		contentPane.add(label_1);
		
		point = new JTextField();
		point.setBounds(90, 168, 388, 26);
		contentPane.add(point);
		point.setColumns(10);
		
		JLabel label_2 = new JLabel("제목");
		label_2.setBounds(12, 212, 57, 15);
		contentPane.add(label_2);
		
		title = new JTextField();
		title.setBounds(90, 207, 390, 26);
		contentPane.add(title);
		title.setColumns(10);
		
		final JTextArea content = new JTextArea();
		JScrollPane pane = new JScrollPane();
		content.setBounds(12, 247, 466, 221);
		pane.setBounds(12, 247, 466, 221);
		pane.setViewportView(content);
		contentPane.add(pane);
		
		JButton btnNewButton = new JButton("시작 ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				@SuppressWarnings("deprecation")
				String[] input_value = {ID.getText(),passwordField.getText(),rece.getText(),point.getText(),title.getText(),content.getText()};
				Value val_check = new Value();
				boolean Sel_Check = true;
				
				for(int i=0 ; i<input_value.length ; i++) {
					boolean data = val_check.emt_check(input_value[i]);
					if(i==3) {  
						if( !input_value[i].equals("") && !val_check.email_check(input_value[i])) 
						{ 
							JOptionPane.showConfirmDialog(null, "참조가 이메일 형식이 아닙니다.", "경고", JOptionPane.WARNING_MESSAGE); 
							Sel_Check = false; 
							break; 
						} 
						continue; 
					}
					else if( (i==2)) { 
						if(!val_check.email_check(input_value[i])) {
							JOptionPane.showConfirmDialog(null, "수신자가 이메일 형식이 아닙니다.", "경고", JOptionPane.WARNING_MESSAGE); 
							Sel_Check = false; 
							break; 
						}
					}
					else if(!data) { 
						Focus(i); 
						Sel_Check = false; 
						break; 
					}
				}				
				
				if(Sel_Check) {
					start = new Start();
					String result = start.connect() ? start.Login(input_value[0], input_value[1]) ? 
									start.Send_write(input_value[2], input_value[3], input_value[4], input_value[5]) ? 
											"발송성공" : "발송실패" : "로그인실패" : "연결실패";
					start.Result_Exit(result);	
				}
			}
		});
		btnNewButton.setBounds(381, 15, 97, 62);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Selenium Study | Email Send | made by Silvea");
		lblNewLabel.setBounds(12, 92, 466, 15);
		contentPane.add(lblNewLabel);
	}
	
	public void Focus(int Number) {
		String[] Input_value = {"아이디","비밀번호","받는사람","참조","제목","내용"};
		JOptionPane.showConfirmDialog(null, Input_value[Number]+"값을 입력해 주세요", "경고", JOptionPane.WARNING_MESSAGE); 
	}
}
