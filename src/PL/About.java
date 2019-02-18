package PL;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

public class About {
	private final ImageIcon img = new ImageIcon("img/btlJava.png");
	private final ImageIcon img2 = new ImageIcon("img/logo.png");
	private final ImageIcon img3 = new ImageIcon("img/label.png");
	private final JLabel icon3 = new JLabel(img3);
	private final JLabel icon = new JLabel(img);
	private final JLabel icon2 = new JLabel(img2);

	private final JButton btnClose = new JButton("Close");
	private final JTextArea txa = new JTextArea();
	private final GridBagConstraints gbc = new GridBagConstraints();
	private final GridBagLayout gb = new GridBagLayout();

	public About(String title) {
		final JFrame mainFrame = new JFrame("About");
		mainFrame.setSize(new Dimension(600, 400));
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();

		panel1.setLayout(gb);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel1.add(icon3, gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel1.add(icon, gbc);
		panel1.setSize(new Dimension(img.getIconWidth(), img.getIconHeight()));

		panel2.setLayout(new BorderLayout());
		icon2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		panel2.add(icon2, BorderLayout.NORTH);

		txa.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		txa.setEditable(false);
		txa.setFont(new Font("Time new Romans", Font.BOLD, 13));
		txa.setText(
				"                 Chương trình thực hiện                  \n\n\n                 GVHD:Bùi văn Phúc\n                 Sinh Viên Tham Gia\n  Phạm Thị lan Oanh\n  Đào Thị Lý\n  Phạm Thị thu Trang");
		panel2.add(txa, BorderLayout.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel2.add(btnClose, BorderLayout.SOUTH);
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				mainFrame.setVisible(false);
			}
		});
		mainFrame.add(panel1, BorderLayout.WEST);
		mainFrame.add(panel2, BorderLayout.EAST);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);

	}

}
