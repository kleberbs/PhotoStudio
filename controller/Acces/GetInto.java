package controller.Acces;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import model.PMF;
import model.Role;
import model.UserX;

@SuppressWarnings("serial")
public class GetInto extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {		
			PersistenceManager pm = PMF.get().getPersistenceManager();
			 resp.setContentType("text/html");
			 Query query1 = pm.newQuery(UserX.class);
			 query1.setFilter("email == idParam");
				query1.declareParameters("String idParam");
				
				@SuppressWarnings("unchecked")
				List<UserX> ar = (List<UserX>) query1.execute("kleber.sik@gmail.com");
				if(ar.size()==0) {
			 	 final UserX ul=new UserX("Kleber","Baldarrago","kleber.sik@gmail.com","16/04/97");
				 final Role rol=new Role("admin", true);
				 final Role rol1=new Role("standard", true);
				 pm.makePersistent(rol);
				 pm.makePersistent(rol1);
				 ul.setRoleId(rol.getId());
				 pm.makePersistent(ul);
				}
			req.getRequestDispatcher("/WEB-INF/Index/index.jsp").forward(req, resp);
			} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
