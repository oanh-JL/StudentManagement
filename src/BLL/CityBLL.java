package BLL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAL.City;
import DAL.DATABASE;

public class CityBLL {
	DATABASE db = new DATABASE();

	public List<City> DocDanhSach() {
		List<City> lstCity = new ArrayList<City>();
		String query = "select * from CITY";
		try {
			PreparedStatement stmt = db.Connect().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				City ct = new City();
				ct.setMaTinh(rs.getInt("CITYID"));
				ct.setTenTinh(rs.getString("CITYNAME"));
				lstCity.add(ct);
			}
			db.CloseConnect();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCity;
	}

	public String LayTenTinh(int MaTinh) {
		String query = "select CITYNAME from CITY where CITYID='" + MaTinh + "'";
		String tentinh = "";
		try {
			PreparedStatement stmt = db.Connect().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				tentinh = rs.getString("CITYNAME");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tentinh;
	}

	public int DocMaTinhTheoTen(String ten) {
		int MaTinh = 0;
		for (int i = 0; i < DocDanhSach().size(); i++) {
			City e = new City();
			   e=DocDanhSach().get(i);
			if (e.getTenTinh().equals(ten)) {
				MaTinh = e.getMaTinh();
			}
		}
		return MaTinh;
	}
}
