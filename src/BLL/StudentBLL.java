package BLL;

import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAL.*;

public class StudentBLL {
	DATABASE db = new DATABASE();

	public List<Student> DocDanhSach() {
		List<Student> lstStudent = new ArrayList<Student>();
		String query = "select * from STUDENT";
		try {
			PreparedStatement stmt = db.Connect().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Student st = new Student();
				st.setMaSV(rs.getInt("ID"));
				st.setTenSV(rs.getString("NAME"));
				st.setMaTinh(rs.getInt("CITYID"));
				st.setNgaySinh(rs.getString("DATEOFBIRTH"));
				st.setGioiTinh(rs.getBoolean("SEX"));
				st.setToan(rs.getFloat("MATH"));
				st.setLy(rs.getFloat("PHYSICAL"));
				st.setHoa(rs.getFloat("CHEMISTRY"));
				lstStudent.add(st);
			}
			db.CloseConnect();
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstStudent;
	}

	public List<Student> TimKiemTheoDC(String place) {
		String query = "Select"
				+ " STUDENT.ID,STUDENT.NAME,STUDENT.CITYID,DATEOFBIRTH,SEX,MATH,PHYSICAL,CHEMISTRY from STUDENT inner join CITY on STUDENT.CITYID=CITY.CITYID where CITYNAME like '"
				+ place + "'";
		List<Student> lstStudent = new ArrayList<Student>();
		try {
			PreparedStatement stmt = db.Connect().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Student st = new Student();
				st.setMaSV(rs.getInt("ID"));
				st.setTenSV(rs.getString("NAME"));
				st.setMaTinh(rs.getInt("CITYID"));
				st.setNgaySinh(rs.getString("DATEOFBIRTH"));
				st.setGioiTinh(rs.getBoolean("SEX"));
				st.setToan(rs.getFloat("MATH"));
				st.setLy(rs.getFloat("PHYSICAL"));
				st.setHoa(rs.getFloat("CHEMISTRY"));
				lstStudent.add(st);
			}
			db.CloseConnect();
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lstStudent;
	}
	

	public List<Student> TimKiemTheoMSV(int MaSV) {
		List<Student> lstStudent = new ArrayList<Student>();
		for (int i = 0; i < DocDanhSach().size(); i++) {
			Student e = DocDanhSach().get(i);
			if (e.getMaSV() == MaSV) {
				lstStudent.add(e);
			}
		}
		return lstStudent;
	}

	public List<Student> TimKiemChung(int MaSV, String place) {
		String query = "Select * from STUDENT inner join CITY on STUDENT.CITYID=CITY.CITYID where CITYNAME = '"
				+ place + "' and ID='" + MaSV + "'";
		List<Student> lstStudent = new ArrayList<Student>();
		try {
			PreparedStatement stmt = db.Connect().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Student st = new Student();
				st.setMaSV(rs.getInt("ID"));
				st.setTenSV(rs.getString("NAME"));
				st.setMaTinh(rs.getInt("CITYID"));
				st.setNgaySinh(rs.getString("DATEOFBIRTH"));
				st.setGioiTinh(rs.getBoolean("SEX"));
				st.setToan(rs.getFloat("MATH"));
				st.setLy(rs.getFloat("PHYSICAL"));
				st.setHoa(rs.getFloat("CHEMISTRY"));
				lstStudent.add(st);
			}
			db.CloseConnect();
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lstStudent;
	}

	public int ThemSinhVien(Student e) {
		int k = 0;
		String sql = "insert into STUDENT(ID,NAME,CITYID,DATEOFBIRTH,SEX,MATH,PHYSICAL,CHEMISTRY) values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = db.Connect().prepareStatement(sql);
			pstmt.setInt(1, e.getMaSV());
			pstmt.setString(2, e.getTenSV());
			pstmt.setInt(3, e.getMaTinh());
			pstmt.setString(4, e.getNgaySinh());
			pstmt.setBoolean(5, e.getGioiTinh());
			pstmt.setFloat(6, e.getToan());
			pstmt.setFloat(7, e.getLy());
			pstmt.setFloat(8, e.getHoa());
			k = pstmt.executeUpdate();
			db.CloseConnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return k;
	}

	public int SuaSinhVien(Student e) {
		int k = 0;
		String query = "Update STUDENT set NAME=?,CITYID=?,DATEOFBIRTH=?,SEX=?,MATH=?,PHYSICAL=?,CHEMISTRY=? WHERE ID=?";
		try {
			PreparedStatement pstmt = db.Connect().prepareStatement(query);
			pstmt.setString(1, e.getTenSV());
			pstmt.setInt(2, e.getMaTinh());
			pstmt.setString(3, e.getNgaySinh());
			pstmt.setBoolean(4, e.getGioiTinh());
			pstmt.setFloat(5, e.getToan());
			pstmt.setFloat(6, e.getLy());
			pstmt.setFloat(7, e.getHoa());
			pstmt.setInt(8, e.getMaSV());
			k = pstmt.executeUpdate();
			db.CloseConnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return k;
	}

	public int XoaSinhVien(int MaSV) {
		int k = 0;
		String query = "Delete STUDENT where ID=?";
		try {
			PreparedStatement pstmt = db.Connect().prepareStatement(query);
			pstmt.setInt(1, MaSV);
			k = pstmt.executeUpdate();
			db.CloseConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return k;
	}
	public Student DocSinhVienTheoMa(int ID)
	{
		int vt=-1;
		for(int i=0;i<DocDanhSach().size();i++)
		{
			if(DocDanhSach().get(i).getMaSV()==ID)
			{
				vt=i;
			}
		}
		if(vt==-1)
		{
			return null;
		}
		else
		{
			return DocDanhSach().get(vt);
		}
	}
	public int NextID()
	{
		int next=0;
		String query="select top 1 ID from Student order by ID desc";
		try {
			PreparedStatement pstmt=db.Connect().prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				next=rs.getInt("ID");
			}
			rs.close();
			pstmt.close();
			db.CloseConnect();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return next+1;
	}
}
