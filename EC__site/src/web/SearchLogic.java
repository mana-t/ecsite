package web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchLogic {

	public ArrayList<SearchBean> search(int cat_id, String cat_name) {

		// DBに接続するために必要な情報を変数定義している。
		// 接続する場所を定義(URLとして)
		String url = "jdbc:mysql://localhost/ecsite";
		// 接続する際のIDを定義
		String id = "root";
		// 接続する際のパスワードを定義
		String pass = "password";

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		// UserBeanを格納するArrayListを定義
		ArrayList<SearchBean> list = new ArrayList<SearchBean>();

		// DBに接続
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pass);

			// PreparedStatementを使用する方が理想
			// ※引数の数が変更することがないため
			// ※SQL文で取得するデータを限定すること
			String sql = "SELECT * FROM category WHERE cat_id = ? AND cat_name = ?";
			pst = conn.prepareStatement(sql);

			pst.setInt(1,cat_id);
			pst.setString(2,cat_name);

			rs = pst.executeQuery();

			while(rs.next()) {
				// SearchBeanにcategoryテーブルのデータを格納
				SearchBean search = new SearchBean();
				search.setCat_id(rs.getInt(1));
				search.setCat_name(rs.getString(2));
				// ArrayListに格納
				list.add(search);
			}

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return list;

	}
}