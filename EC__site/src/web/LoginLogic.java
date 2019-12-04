package web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginLogic {
	// loginメソッドを定義
	public LoginBean login(String login_cd, String login_pw) {

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

		LoginBean login = new LoginBean();

		// 例外あり
		try {
			// JDBCドライバ(MYSQL用)を登録する
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBCドライバの登録終了");

			// 引数(url,id,pass)をもとに、実際にDBに接続する。
			// connの代入結果としては、接続が成功したか失敗したかの結果が格納される。
			conn = DriverManager.getConnection(url, id, pass);
			System.out.println("DBMSとの接続完了");

			// 接続に成功した場合は、pstに接続情報を設定する。

			// PreparedStatementを使用するほうが理想
			// ※引数の数が変更することがないため
			// ※SQL文で取得するデータを限定すること
			String sql = "SELECT * FROM user WHERE login_cd = ? AND login_pw = ?";
			//
			pst = conn.prepareStatement(sql);

			// パラメータ1にlogin_cdをセット
			pst.setString(1, login_cd);
			// パラメータ2にlogin_pwをセット
			pst.setString(2, login_pw);

			// sql文を実行
			rs = pst.executeQuery();

			// 取得データ分繰り返し
			rs.next();
			// LoginBeanインスタンス化

			// LoginBeanにuserテーブルのデータを格納

			login.setLogin_cd(rs.getString(3));
			login.setLogin_pw(rs.getString(4));
			return login;

		} catch (ClassNotFoundException ex) {
			// 例外処理
			ex.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();

			// finally例外発生の有無に関わらず、必ず実行される
		} finally {
			try {
				// 一番最後に使ったものから順に消していくのが好ましい
				// rsをcloseする
				if (rs != null)
					rs.close();
				// pstをcloseする
				if (pst != null)
					pst.close();
				// connをcloseする
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				// 例外の情報をコンソールに表示する
				ex.printStackTrace();
			}
		}
		return login;
	}
}
