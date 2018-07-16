package controller.Login;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import model.PMF;
import model.UserX;

@SuppressWarnings("serial")
public class GmailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			 throws IOException {
		try {
			req.getRequestDispatcher("/WEB-INF/Index/index.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 public void doPost(HttpServletRequest req, HttpServletResponse resp)
 throws IOException {
	 PersistenceManager pm = PMF.get().getPersistenceManager();
	 resp.setContentType("text/html");
	 UserService us = UserServiceFactory.getUserService();
	 User user = us.getCurrentUser();
 	
	 if(user == null){
		 resp.sendRedirect(us.createLoginURL(req.getRequestURI()));
 	}else{
 		Query query = pm.newQuery(UserX.class);
 		query.setFilter("email == idParam");
		query.declareParameters("String idParam");
		
		@SuppressWarnings("unchecked")
		List<UserX> array = (List<UserX>) query.execute(user.getEmail());
		Query q = pm.newQuery(UserX.class);
		@SuppressWarnings("unchecked")	
		List<UserX> users = (List<UserX>)q.execute("select from UserX");
		
 		if(array.size()>0) {
 			req.setAttribute("user", user);
 			try {
 				req.setAttribute("users", users);
 				req.getRequestDispatcher("/WEB-INF/User/list.jsp").forward(req, resp);
 				query.closeAll();
 			} catch (ServletException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 			finally {
 				pm.close();
 			}
 		}
 		else {
 			resp.sendRedirect(us.createLogoutURL("/accesDenied")); 			 			
 		}
 		
 		}
	 }
 }