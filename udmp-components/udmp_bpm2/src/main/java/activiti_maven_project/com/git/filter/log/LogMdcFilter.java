package activiti_maven_project.com.git.filter.log;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import activiti_maven_project.com.git.user.Users;

public class LogMdcFilter implements Filter {

	static Logger logger = LogManager.getLogger();

	private final static String DEFAULT_USERID = "anonymous";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		if (session == null) {
			MDC.put("userId", DEFAULT_USERID);
		} else {
			Object object = session.getAttribute("user");
			if (object == null) {
				MDC.put("userId", DEFAULT_USERID);
			} else {
				Users user = (Users) session.getAttribute("user");
				if (user == null) {
					MDC.put("userId", DEFAULT_USERID);
				} else {
					MDC.put("userId", user.getUserCd());
				}
			}
		}
//		logger.debug("test for MDC.");

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
