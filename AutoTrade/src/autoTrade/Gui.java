package autoTrade;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JLabel;

public class Gui {

	private JFrame frame;
	
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
					window.frame.setTitle("AutoTrade");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Gui() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 547, 574);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);/* 滚动条  *//*  bounds的值和下面textarea的bounds的保持一致 *//* 文本区域 */
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(340, 0, 202, 121);
		frame.getContentPane().add(textArea_1);
		StockInfo stockInfo = new StockInfo(textArea_1);
		
		textArea_1.append("  读取stock.txt文件成功!!!\r\n");
		Label[] labels = new Label[stockInfo.size * 5];/* 标签  */
		
		JScrollPane scrollPane = new JScrollPane();          
		scrollPane.setBounds(0, 0, 340, 300);              

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 340, 300);		
		scrollPane.setViewportView(textArea);	
		frame.getContentPane().add(scrollPane);
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 310, 511, 170);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("代码");
		lblNewLabel.setBounds(49, 10, 59, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("方法");
		lblNewLabel_1.setBounds(137, 10, 37, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("数量");
		lblNewLabel_2.setBounds(241, 10, 54, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("状态");
		lblNewLabel_3.setBounds(350, 10, 54, 15);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("运行日期");
		lblNewLabel_4.setBounds(431, 10, 54, 15);
		panel.add(lblNewLabel_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(38, 35, 511, 125);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(stockInfo.size , 5, 0, 0));
		
		JButton btnNewButton = new JButton("启动程序");
		btnNewButton.setBounds(10, 490, 511, 35);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread thread = new Thread(new Go(textArea,textArea_1,labels));
				thread.start();
				btnNewButton.setText("已启动程序");
			}
		});
		
		
		for(int i = 0; i < stockInfo.list.size(); i++) {
			Item item = stockInfo.list.get(i);
			labels[i * 5 + 0] = new Label(item.name);
			labels[i * 5 + 1] = new Label(item.method);
			labels[i * 5 + 2] = new Label(Integer.toString(item.num));
			labels[i * 5 + 3] = new Label("未成交");
			labels[i * 5 + 4] = new Label(item.date);
			
			panel_1.add(labels[i * 5 + 0]);
			panel_1.add(labels[i * 5 + 1]);
			panel_1.add(labels[i * 5 + 2]);
			panel_1.add(labels[i * 5 + 3]);
			panel_1.add(labels[i * 5 + 4]);
		}
		
	}
}
