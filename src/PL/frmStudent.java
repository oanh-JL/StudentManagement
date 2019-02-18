package PL;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import DAL.*;
import BLL.*;

public class frmStudent extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtbpl;
	private JTextField txtstd;
	private JMenuBar menubar;
	private JMenu menuFile;
	private JMenu menuAbout;
	private JMenuItem menuItemOpen, menuItemClose, menuItemExit, menuItemAbout;
	private JButton btnfl;
	private JTable table;
	private DefaultTableModel tableModel;
	String[] colsname = { "ID", "Name", "Place", "Date", "Sex", "Math", "Physical", "Chemistry" };
	private JTextField txtID,txtName,txtMath,txtPhysic,txtDate,txtPlace,txtChem,txtTotal;
	private JButton btnisrt;
	private JButton btndel;
	private JButton btnok;
	private JButton btncel;
	private JButton btnedit;
	private JRadioButton btnMale;
	private JRadioButton btnFemale;
	private GridBagConstraints gbc;
	private GridBagLayout gb;
	private JPanel controlPanel;
	StudentBLL studentBLL = new StudentBLL();
	CityBLL cityBLL = new CityBLL();
	public static int ID;
	/*
	 * constructor
	 */
	public frmStudent() {
		createGUI();
	}

	/*
	 * create Frame
	 */
	private void createGUI() {
		setTitle("Student Management");
		setLocationRelativeTo(null);
		setSize(960, 700);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menubar = createJMenuBar();

		setJMenuBar(menubar);
		controlPanel = createJPanel();
		add(controlPanel, BorderLayout.CENTER);
		setLocation(300, 20);
		HienThiDuLieu(studentBLL.DocDanhSach());
		setResizable(false);

	}

	/*
	 * create MainMenu
	 */
	private JMenuBar createJMenuBar() {
		menubar = new JMenuBar();
		menuFile = new JMenu("File");
		menuAbout = new JMenu("About");
		menuItemOpen = new JMenuItem("Open....");
		menuItemClose = new JMenuItem("Close");
		menuItemExit = new JMenuItem("Exit");
		menuItemAbout = new JMenuItem("About me");

		/*
		 * addactionlistener
		 */
		menuItemOpen.addActionListener(this);
		menuItemClose.addActionListener(this);
		menuItemExit.addActionListener(this);
		menuItemAbout.addActionListener(this);
		/*
		 * display
		 */
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemClose);
		menuFile.addSeparator();
		menuFile.add(menuItemExit);
		menuAbout.add(menuItemAbout);
		menubar.add(menuFile);
		menubar.add(menuAbout);
		return menubar;
	}

	/*
	 * create MainPanel
	 */
	private JPanel createJPanel() {
		JPanel panel = new JPanel();
		gb = new GridBagLayout();
		panel.setLayout(gb);

		// create student filtre
		Box filtreBox = Box.createVerticalBox();
		JPanel panel1 = new JPanel();
		panel1.setLayout(gb);
		panel1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		txtID=createJTextField();
		txtName=createJTextField();
		txtChem=createJTextField();
		txtDate=createJTextField();
		txtMath=createJTextField();
		txtPlace=createJTextField();
		txtPhysic=createJTextField();
		txtTotal=createJTextField();
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(panel1, new JLabel("BirthPlace"), 0, 0, 50, 10);

		txtbpl = new JTextField("",15);
		add(panel1, txtbpl, 1, 0, 150, 10);

		add(panel1, new JLabel("   "), 2, 0, 10, 10);
		add(panel1, new JLabel("StudentID"), 3, 0, 50, 10);

		txtstd = new JTextField("",15);
		add(panel1, txtstd, 4, 0, 160, 10);

		add(panel1, new JLabel("   "), 5, 0, 10, 10);

		btnfl = createJButton("Filter");
		add(panel1, btnfl, 6, 0, 40, 10);
		filtreBox.add(panel1);
		filtreBox.setBorder(BorderFactory.createTitledBorder("Student Filter"));

		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(panel, filtreBox, 0, 1, 30, 30);

		// create list student
		Box liststudent = Box.createVerticalBox();
		table = createTable();
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(480, 300));
		liststudent.add(scroll);
		liststudent.setBorder(BorderFactory.createTitledBorder("List Student"));

		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(panel, liststudent, 0, 2, 300, 180);

		// create student infomation
		JPanel panel2 = new JPanel();
		panel2.setLayout(gb);
		Box studentinfor = Box.createVerticalBox();
		Box infor = Box.createHorizontalBox();

		gbc.insets = new Insets(0, 0, 0, 10);
		add(panel2, new JLabel("ID"), 0, 0, 0, 10);

		add(panel2, txtID, 1, 0,200, 10);

		add(panel2, new JLabel("Name"), 0, 1, 0, 10);
		add(panel2,txtName, 1, 1, 200, 10);

		add(panel2, new JLabel("BirthPlace"), 0, 2, 0, 10);

		add(panel2, txtPlace, 1, 2, 200, 10);

		add(panel2, new JLabel("Math"), 2, 0, 0, 10);

		
		add(panel2, txtMath, 3, 0, 200, 10);

		add(panel2, new JLabel("Physical"), 2, 1, 0, 10);

		add(panel2, txtPhysic, 3, 1, 200, 10);

		add(panel2, new JLabel("Date"), 0, 3, 0, 10);

		
		add(panel2, txtDate, 1, 3, 200, 10);

		add(panel2, new JLabel("Sex"), 0, 4, 0, 10);

		JPanel psex = new JPanel();
        psex.setLayout(new FlowLayout());
        btnMale = new JRadioButton("Male");
        btnFemale = new JRadioButton("Female");
        ButtonGroup sex = new ButtonGroup();
        sex.add(btnFemale);
        sex.add(btnMale);
        psex.add(btnMale);
        psex.add(btnFemale);
        add(panel2, psex, 1, 4, 80, 10);

		add(panel2, new JLabel("Chemistry"), 2, 2, 0, 10);

		add(panel2, txtChem, 3, 2,200, 10);

		add(panel2, new JLabel("Total"), 2, 3, 0, 10);

		add(panel2,txtTotal, 3, 3, 200, 10);
		panel2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		infor.add(panel2);
		infor.setBorder(BorderFactory.createTitledBorder(""));

		JPanel panel3 = new JPanel();
		panel3.setLayout(gb);
		btnisrt = createJButton("Insert");
	
		btndel = createJButton("Delete");
		
		btnedit = createJButton("Edit");
		
		btnok = createJButton("Refresh");
		
		btncel = createJButton("Cancel");
		

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 10, 0, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(panel3, btnisrt, 0, 0, 50, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(panel3, btndel, 2, 0, 50, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(panel3, btnedit, 4, 0, 50, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(panel3, btnok, 6, 0, 50, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(panel3, btncel, 8, 0, 50, 10);
		panel3.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		add(panel, panel3, 0, 4, 50, 5);

		studentinfor.add(infor);
		studentinfor.add(panel3);
		studentinfor.setBorder(BorderFactory.createTitledBorder("Student Information"));
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(panel, studentinfor, 0, 3, 50, 60);
		return panel;
	}


	/*
	 * create JButton
	 */
	private JButton createJButton(String title) {
		JButton btn = new JButton(title);
		btn.setPreferredSize(new Dimension(70, 25));
		btn.addActionListener(this);
		return btn;
	}

	/*
	 * create JTextField
	 */
	private JTextField createJTextField() {
		JTextField txt = new JTextField("", 15);
		txt.setEditable(false);
		txt.setForeground(Color.black);
		return txt;
	}

	/*
	 * setSize colunm of table
	 */
	private JTable createTable() {
		JTable tb = new JTable();
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(colsname);
		tb.setModel(tableModel);
		tb.getTableHeader().setResizingAllowed(false);
		tb.getTableHeader().setReorderingAllowed(false);
		tb.getTableHeader().setFont(new Font("Time New Romans", Font.BOLD, 12));
		tb.setFillsViewportHeight(true);
		tb.setRowHeight(23);
		tb.getColumnModel().getColumn(0).setPreferredWidth(50);
		tb.getColumnModel().getColumn(1).setPreferredWidth(120);
		tb.getColumnModel().getColumn(2).setPreferredWidth(100);
		tb.getColumnModel().getColumn(3).setPreferredWidth(100);
		tb.getColumnModel().getColumn(4).setPreferredWidth(30);
		tb.getColumnModel().getColumn(5).setPreferredWidth(50);
		tb.getColumnModel().getColumn(6).setPreferredWidth(50);
		tb.getColumnModel().getColumn(7).setPreferredWidth(50);
		tb.getTableHeader().setForeground(Color.BLACK);
		tb.setSelectionBackground(Color.cyan);
		// add MouseListener
		tb.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				RowClick();
			}
		});
//		// add KeyListener
//		tb.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent e) {
//				RowClick();
//			}
//		});
		tb.setColumnSelectionAllowed(false);
		tb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tb.setAutoCreateRowSorter(true);

		return tb;
	}

	private void add(Container ct, Component c, int col, int row, int ncol, int nrow) {
		gbc.gridx = col;
		gbc.gridy = row;

		gbc.ipadx = ncol;
		gbc.ipady = nrow;

		gb.setConstraints(c, gbc);

		ct.add(c);

	}

	private void RowClick() {
		try {
			String id = table.getValueAt(table.getSelectedRow(), 0).toString();
			String name = table.getValueAt(table.getSelectedRow(), 1).toString();
			String place = table.getValueAt(table.getSelectedRow(), 2).toString();
			String date = table.getValueAt(table.getSelectedRow(), 3).toString();
			String tmath = table.getValueAt(table.getSelectedRow(), 5).toString();
			String tphy = table.getValueAt(table.getSelectedRow(), 6).toString();
			String tchem = table.getValueAt(table.getSelectedRow(), 7).toString();
			txtID.setText(id);
			txtName.setText(name);
			txtPlace.setText(place);
			txtDate.setText(date);
			txtMath.setText(tmath);
			txtPhysic.setText(tphy);
			txtChem.setText(tchem);
			float math = Float.parseFloat(tmath);
			float physic = Float.parseFloat(tphy);
			float chem = Float.parseFloat(tchem);
			String str = "%.2f";
			txtTotal.setText(String.format(str, (math + physic + chem)));
			if (table.getValueAt(table.getSelectedRow(), 4).equals("Male")) {
				btnMale.setSelected(true);
			} else {
				btnFemale.setSelected(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void HienThiDuLieu(List<Student> lstStudent) {
		tableModel=(DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		for (int i = 0; i < lstStudent.size(); i++) {
			String[] rows = { String.valueOf(lstStudent.get(i).getMaSV()), lstStudent.get(i).getTenSV(),
					cityBLL.LayTenTinh(lstStudent.get(i).getMaTinh()), lstStudent.get(i).getNgaySinh(),
					lstStudent.get(i).getGioiTinh() == true ? "Male" : "Female",
					String.valueOf(lstStudent.get(i).getToan()), String.valueOf(lstStudent.get(i).getLy()),
					String.valueOf(lstStudent.get(i).getHoa()) };
			tableModel.addRow(rows);
		}
		table.setRowSelectionInterval(0, 0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnfl)) {
			String birthplace = txtbpl.getText().trim();
			String studentid = txtstd.getText().trim();
			if (birthplace.isEmpty() && studentid.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập gì đó");
				return;
			}

			else {
				if (!birthplace.isEmpty() && !studentid.isEmpty()) {
					HienThiDuLieu(studentBLL.TimKiemChung(Integer.parseInt(studentid), birthplace));
				} else {
					if (birthplace.isEmpty() && !studentid.isEmpty()) {
						HienThiDuLieu(studentBLL.TimKiemTheoMSV(Integer.parseInt(studentid)));
					} else {
						HienThiDuLieu(studentBLL.TimKiemTheoDC(birthplace));
					}
				}
			}
		}
		if (o.equals(btnisrt)) {
			new frmAddStudent().setVisible(true);
		}
		if (o.equals(btnedit)) {
			editActionPerformed();
		}
		if (o.equals(btndel)) {
			delActionPerformed();
		}
		if (o.equals(btnok)) {
			okActionPerformed();
		}
		if (o.equals(btncel)) {
			HienThiDuLieu(studentBLL.DocDanhSach());
			btndel.setEnabled(true);
			btnedit.setEnabled(true);
			btnok.setEnabled(false);
		}
		if (o.equals(menuItemAbout)) {
			new About("About me");
		}
		if (o.equals(menuItemExit)) {
			setVisible(false);
		}
	}

	private void okActionPerformed() {
		HienThiDuLieu(studentBLL.DocDanhSach());
	}

	private void delActionPerformed() {
		int index = table.getSelectedRow();
		if (index > 0) {
			String options[] = { "Yes", "No" };
			int answer = JOptionPane.showOptionDialog(null, "Bạn có chắc chắn muốn xoá sinh viên "+txtName.getText()+" có mã "+txtID.getText()+" không?", "Xác nhận xoá",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			if (answer == 0) {
				String str = table.getValueAt(table.getSelectedRow(), 0).toString().trim();
				int MaSV = Integer.parseInt(str);
				if (studentBLL.XoaSinhVien(MaSV) > 0) {
					JOptionPane.showMessageDialog(null, "Xoá thành công!");
					HienThiDuLieu(studentBLL.DocDanhSach());
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Xoá thất bại!");
		}
	}

	private void editActionPerformed() {
		if(txtID.getText().trim().equals("Mã SV")==false)
		{
			ID=Integer.parseInt(txtID.getText());
			new frmUpdateStudent().setVisible(true);
		}
	}


	

	public static void main(String[] args) {
		frmStudent frm = new frmStudent();
		frm.setVisible(true);
	}
}
