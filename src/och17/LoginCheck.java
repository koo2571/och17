package och17;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/sub2/*")
public class LoginCheck implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("LoginCheck doFilter Start...");
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		HttpSession session         = httpReq.getSession();
		System.out.println("LoginCheck doFilter session");
		//Login이 안되어 있으면
		if(session == null || session.equals("")) {
			httpRes.sendRedirect("../login.jsp");
		}
		String id = (String)session.getAttribute("id");
		if(id == null || id.equals("")) {
			httpRes.sendRedirect("../login.jsp");
		}
		chain.doFilter(request, response);
	}

}
