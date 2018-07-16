package controller.Reservation;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import model.PMF;
import model.Reservation;
import model.UserX;

@SuppressWarnings("serial")
public class Add extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {


		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();
		if(user == null){
			 resp.sendRedirect("/login");
		 }
		else {
			final PersistenceManager pm = PMF.get().getPersistenceManager();
			final Query q = pm.newQuery(UserX.class);
			q.setFilter("email == idParam");
			q.declareParameters("String idParam");
			@SuppressWarnings("unchecked")
			List<UserX> array = (List<UserX>) q.execute(user.getEmail());
			if(array.size()>0) {
				UserX userx=array.get(0);
				try {
					req.setAttribute("userx", userx);
					req.getRequestDispatcher("WEB-INF/Reservation/add.jsp").forward(req, resp);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			} 
			else {
				resp.sendRedirect("/addUser");
			}			
		 }
	}	 
		 
	


	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {		
		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Reservation us=new Reservation(req.getParameter("user"),req.getParameter("email"),req.getParameter("date"),req.getParameter("place"));
		pm.makePersistent(us);
		resp.getWriter().println("dtos guardados");
		pm.close();
		resp.sendRedirect("/listReservation");
	}
}
