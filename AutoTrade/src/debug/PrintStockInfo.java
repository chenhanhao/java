package debug;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.JLabel;

public class PrintStockInfo {

	public static JFrame frame;
	static StockInfo stockInfo = new StockInfo();
	static Label[] labels = new Label[stockInfo.size * 8];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new PrintStockInfo();
					frame.setVisible(true);
					Thread thread = new Thread(new Run(labels));
					thread.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	

	/**
	 * Create the application.
	 */
	public PrintStockInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 10, 664, 36);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("股票名");
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("现价");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("涨幅");
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("时间");
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("涨停价");
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("跌停价");
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("昨收价");
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("今开价");
		panel.add(lblNewLabel_7);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 56, 664, 195);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(stockInfo.size, 8, 0, 0));
		
		
		for(int i = 0; i < stockInfo.size * 8; i++) {
			labels[i] = new Label("0");
			panel_1.add(labels[i]);
		}
	}
}
