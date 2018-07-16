package controller.Login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class Logout extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			 throws IOException {
		UserService us = UserServiceFactory.getUserService();
		 User user = us.getCurrentUser();
		 if(user == null){
			 try {
				req.getRequestDispatcher("/WEB-INF/Index/index.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 else {
		
			resp.sendRedirect(us.createLogoutURL("/login"));			
		
		 }
		}
  			
 }