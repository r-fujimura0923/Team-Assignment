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

@WebFilter("/*")
public class LoginFilter extends HttpFilter implements Filter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 1. リクエストされたURLの取得
        String requestURI = request.getRequestURI();

        // 2. ログイン画面、サーブレット、静的ファイル（CSS/JS/画像等）はチェックから除外
        if (requestURI.endsWith("/home.jsp") || 
            requestURI.endsWith("/LoginServlet") || 
            requestURI.endsWith("/login") ||
            requestURI.contains("/css/") || 
            requestURI.contains("/js/") || 
            requestURI.contains("/images/")) {
            
            chain.doFilter(request, response);
            return;
        }

        // 3. セッションの取得とログインチェック
        HttpSession session = request.getSession(false);
        // ※「loginUser」部分はServlet側の登録名と統一してください
        boolean isLogin = (session != null && session.getAttribute("loginUser") != null);

        if (!isLogin) {
            // 未ログインならログイン画面（home.jsp）にリダイレクト
            response.sendRedirect(request.getContextPath() + "/home.jsp");
            return;
        }

        // 4. ユーザーIDの取得と英字（属性）の抽出（IDdivideのロジックを統合）
        String userID = (String) session.getAttribute("loginUser");
        String userRole = ""; 
        
        if (userID != null) {
            // IDから英字部分だけを抽出（例: "s12345" -> "s" / "teacher01" -> "teacher"）
            userRole = userID.replaceAll("[^a-zA-Z]", "");
        }

        // 5. 権限（ロール）チェックとアクセス制限
        // 生徒（英字が "s" で始まる）が先生用ページに入ろうとした場合
        if (requestURI.contains("/Teacher_side/") && userRole.startsWith("s")) {
            response.sendRedirect(request.getContextPath() + "/Student_side/Mysituation.jsp");
            return;
        }

        // 先生（英字が "t" で始まる）が生徒用ページに入ろうとした場合
        if (requestURI.contains("/Student_side/") && userRole.startsWith("t")) {
            response.sendRedirect(request.getContextPath() + "/Teacher_side/Teacher.jsp");
            return;
        }

        // 6. すべてのチェックを通過したら次の処理へ
        chain.doFilter(request, response);
    }
}
