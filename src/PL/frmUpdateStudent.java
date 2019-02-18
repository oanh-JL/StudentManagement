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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import BLL.CityBLL;
import BLL.StudentBLL;
import DAL.City;
import DAL.Student;

public class frmUpdateStudent extends JFrame implements ActionListener {
	private JTextField txtID,txtName,txtDate,txtMath,txtPhysic,txtChem;
	private JRadioButton btnMale,btnFemale;
	private JButton btnUpdate,btnClose;
	private GridBagConstraints gbc;
	private GridBagLayout  gb;
	private JPanel controlPanel;
	private JComboBox<String> cboPlace;
	private static final long serialVersionUID = 1L;
	private CityBLL ctBLL= new CityBLL();
	private StudentBLL stBLL= new StudentBLL();
	
	public frmUpdateStudent() {
		createGui();
		formLoad();
	}
	
	private void createGui() {
		setSize(400, 530);
		setLocationRelativeTo(null);
		setTitle("Update student");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		controlPanel=createPanel();
		add(controlPanel, BorderLayout.CENTER);
	}

	private JPanel createPanel() {
		JPanel panel=new JPanel();
		gb= new GridBagLayout();
		panel.setLayout(gb);
		gbc= new GridBagConstraints();
		
		
		JPanel titlePanel= new JPanel();
		titlePanel.setLayout(gb);
		JLabel lblTitle=new JLabel("UPDATE STUDENT");
		lblTitle.setForeground(Color.GREEN);
		lblTitle.setFont(new Font("Courier New", Font.BOLD, 20));
		Add(titlePanel, lblTitle, 0, 0, 0, 20);
		
		JPanel infoPanel= new JPanel();
		infoPanel.setLayout(gb);
		gbc.insets=new Insets(0, 0, 10, 0);
		gbc.fill=GridBagConstraints.HORIZONTAL;
		Add(infoPanel, new JLabel("ID: "), 0, 0, 30, 10);
		txtID=new JTextField(String.valueOf(stBLL.NextID()),15);
		txtID.setEditable(false);
		Add(infoPanel, txtID, 1, 0, 50, 10);
		
		Add(infoPanel, new JLabel("Name:"),0, 1, 30, 10);
		txtName=new JTextField("",15);
		Add(infoPanel, txtName, 1, 1, 50, 10);
		
		Add(infoPanel, new JLabel("Sex:"),0, 2, 30, 10);
		JPanel psex = new JPanel();
		psex.setLayout(new FlowLayout());
		btnMale = new JRadioButton("Male");
		btnFemale = new JRadioButton("Female");
		ButtonGroup sex = new ButtonGroup();
		sex.add(btnMale);
		sex.add(btnFemale);
		psex.add(btnMale);
		psex.add(btnFemale);
		psex.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		Add(infoPanel, psex, 1, 2, 50, 10);
		
		
		Add(infoPanel, new JLabel("Place:"),0, 3, 30, 10);
		cboPlace=createJComBoBox();
		Add(infoPanel, cboPlace, 1, 3, 50, 10);
		
		Add(infoPanel, new JLabel("Date:"),0, 4, 30, 10);
		txtDate=new JTextField("",15);
		Add(infoPanel, txtDate, 1, 4, 50, 10);
		
		Add(infoPanel, new JLabel("Math:"),0, 5, 30, 10);
		txtMath=new JTextField("",15);
		Add(infoPanel, txtMath, 1, 5, 50, 10);
		
		Add(infoPanel, new JLabel("Physical:"),0, 6, 30, 10);
		txtPhysic=new JTextField("",15);
		Add(infoPanel, txtPhysic, 1, 6, 50, 10);
		
		Add(infoPanel, new JLabel("Chemistry:"),0, 7, 30, 10);
		txtChem=new JTextField("",15);
		Add(infoPanel, txtChem, 1, 7, 50, 10);
		
		JPanel btnAction= new JPanel();
		btnAction.setLayout(gb);
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.insets=new Insets(0, 5, 0, 0);
		btnUpdate= createJButton("Update");
		Add(btnAction, btnUpdate, 0, 0, 50, 10);
		btnClose=createJButton("Close");
		Add(btnAction, btnClose, 1, 0, 50, 10);
		
		gbc.fill=GridBagConstraints.HORIZONTAL;
		Add(panel, titlePanel, 0, 0, 200, 0);
		gbc.fill=GridBagConstraints.HORIZONTAL;
		Add(panel, infoPanel, 0, 1,0, 50);
		gbc.fill=GridBagConstraints.HORIZONTAL;
		Add(panel, btnAction, 0, 2, 200, 20);
		return panel;	
	}
	private void formLoad()
	{
		Student stSelect=stBLL.DocSinhVienTheoMa(frmStudent.ID);
		if(stSelect==null)
		{
			JOptionPane.showMessageDialog(null, "Opp! page not found");
			return;
		}
		else
		{
			txtID.setText(String.valueOf(stSelect.getMaSV()));
			txtName.setText(stSelect.getTenSV());
			txtDate.setText(stSelect.getNgaySinh());
			txtMath.setText(String.valueOf(stSelect.getToan()));
			txtPhysic.setText(String.valueOf(stSelect.getLy()));
			txtChem.setText(String.valueOf(stSelect.getHoa()));
			cboPlace.setSelectedItem(ctBLL.LayTenTinh(stSelect.getMaTinh()));
			if(stSelect.getGioiTinh()==true)
			{
				btnMale.setSelected(true);
			}
			else
			{
				btnFemale.setSelected(true);
			}
		}
	}
	private JComboBox<String> createJComBoBox() {
		JComboBox<String> cbo =new JComboBox<String>();
		for (City item : ctBLL.DocDanhSach()) {
			cbo.addItem(item.getTenTinh());
		}
		return cbo;
	}

	private JButton createJButton(String string) {
		JButton btn= new JButton(string);
		btn.addActionListener(this);
		btn.setPreferredSize(new Dimension(70, 20));
		return btn;
	}

	private void Add(Container ct, Component c, int col, int row, int ncol, int nrow) {
		gbc.gridx = col;
		gbc.gridy = row;

		gbc.ipadx = ncol;
		gbc.ipady = nrow;

		gb.setConstraints(c, gbc);

		ct.add(c);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o= e.getSource();
		
		if(o.equals(btnUpdate))
		{
			String options[] = { "Yes", "No" };
			int answer = JOptionPane.showOptionDialog(null, "Bạn có chắc chắn muốn sửa sinh viên có mã:"+frmStudent.ID+" không?", "Xác nhận sửa",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			if (answer == 0) {
				if (txtID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sinh viên");
					return;
				}
				if (txtName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập tên sinh viên");
					return;
				}
				Student st = new Student();
				st.setMaSV(frmStudent.ID);
				st.setTenSV(txtName.getText().trim());
				st.setMaTinh(ctBLL.DocMaTinhTheoTen(cboPlace.getSelectedItem().toString()));
				st.setNgaySinh(txtDate.getText().trim());
				st.setGioiTinh(GioiTinhSV());
				st.setToan(Float.parseFloat(txtMath.getText()));
				st.setLy(Float.parseFloat(txtPhysic.getText()));
				st.setHoa(Float.parseFloat(txtChem.getText()));
				if (stBLL.SuaSinhVien(st) > 0) {
					JOptionPane.showMessageDialog(null, "Sửa thành công!");
					
				} else {
					JOptionPane.showMessageDialog(null, "Sửa không thành công!");
				}
			}
		}
		if(o.equals(btnClose));
		{
			this.setVisible(false);
		}
	}
	private Boolean GioiTinhSV() {
		if (btnMale.isSelected()) {
			return true;
		}
		return false;
	}
}
