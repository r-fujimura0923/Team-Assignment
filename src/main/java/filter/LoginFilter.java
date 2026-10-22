package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//AI頼りだから抜けている部分があると思うけど、そこは修正しよう！
@WebFilter("/*")
public class LoginFilter extends HttpFilter implements Filter{

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)throws IOException, ServletException {
		//ログインしている人のURLを取得
		String requestURI = request.getRequestURI();
		
		//ログイン画面などはチェックから外す
		if (requestURI.endsWith("/home.jsp") || requestURI.endsWith("/LoginServlet") || requestURI.endsWith("/login")) {
		    chain.doFilter(request, response); // そのまま通す
		    return;
		}
		
		//セッションを取得
		HttpSession session = request.getSession(false);
		
		//ログインしているかのチェック
		boolean isLogin = (session != null && session.getAttribute("loginUser←変更してもいい！Servletと同じ奴で！") != null);
		
		if(!isLogin) {
			//ログインできていなかったらログイン画面に強制送還
			response.sendRedirect(request.getContextPath() + "/home.jsp");
			return;
		}
		
		//ログインしている人のユーザIDを取得
		String userID = (String)session.getAttribute("loginUser←変更可能！");
		
		//生徒が先生に入ろうとしたときの処理
		if(requestURI.contains("/Teacher_side/") && userID.startsWith("s")) {
			response.sendRedirect(request.getContextPath() + "/Student_side/Mysituation.jsp");
			return;
		}
		
		//わからないけど一応先生が生徒に入ろうとしたときの処理
		if(requestURI.contains("/Student_side/") && userID.startsWith("t")) {
			response.sendRedirect(request.getContextPath() + "/Teacher_side/Teacher.jsp");
			return;
		}
		
		//すべての検査がクリアした時の処理、先に進め
		chain.doFilter(request, response);
	}

}
