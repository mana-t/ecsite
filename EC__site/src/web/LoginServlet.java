package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		// パラメータを取得
		String login_cd = request.getParameter("login_cd");
		String login_pw = request.getParameter("login_pw");

		// パラメータがない場合
		if((login_cd.equals("") || login_cd == null) ||
				login_pw.equals("") || login_pw == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/loginnull.jsp");
			rd.forward(request, response);
			return;
		}

		// インスタンス作成
		LoginLogic login = new LoginLogic();
		// LoginLogicを定義
		LoginBean bean = null;
		// LogicLoginのloginを呼び出す
		// 戻り値はLogicBean
		bean = login.login(login_cd, login_pw);

//		System.out.println(bean.getLogin_cd());
//		System.out.println(bean.getLogin_pw());
		String beanCD = bean.getLogin_cd();
		String beanPW = bean.getLogin_pw();

		if(beanCD.equals(login_cd) && beanPW.equals(login_pw)) {
			// ログインに成功していた場合search.jspにディスパッチ
			RequestDispatcher rd = request.getRequestDispatcher("jsp/search.jsp");
			rd.forward(request, response);
		} else {
			// エラー画面にディスパッチ
			RequestDispatcher rd = request.getRequestDispatcher("jsp/loginerror.jsp");
			rd.forward(request, response);
		}


	}

}
