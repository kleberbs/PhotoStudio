package controller.User;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import model.Acces;
import model.PMF;
import model.Resource;
import model.Role;
import model.UserX;

@SuppressWarnings("serial")
public class Edit extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		

		UserService us = UserServiceFactory.getUserService();
		User userx = us.getCurrentUser();
		 if(userx == null){		
			 RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/AccesDenied/accesDenied.jsp");
			 try {
				rd.forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
		 else {
			 if(us.isUserAdmin() || accesResource(userx.getEmail(),"/editUser")){
				 final PersistenceManager pm = PMF.get().getPersistenceManager();
				String id = req.getParameter("id");
				Long idLong = Long.parseLong(id);
				Query query = pm.newQuery(Role.class);
				
				@SuppressWarnings("unchecked")
				List<Role> roles = (List<Role>)query.execute("select from Role");
				
				
			
				try{
					UserX user=pm.getObjectById(UserX.class,idLong);
					req.setAttribute("user", user);
					req.setAttribute("roles", roles);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/User/edit.jsp");
					rd.forward(req, resp);
				}catch(Exception e){
						System.out.println(e);
						
				}finally{
					pm.close();
				}}
			 else {
				 RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/AccesDenied/accesDenied.jsp");
				 try {
					rd.forward(req, resp);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			 }
		 }
	
		
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		String id = req.getParameter("id");
		Long idLong = Long.parseLong(id);
		try{
			UserX user=pm.getObjectById(UserX.class,idLong);
			user.setName(req.getParameter("name"));
			user.setLastName(req.getParameter("lastName"));
			user.setEmail(req.getParameter("email"));
			user.setBirth(req.getParameter("birth"));
			user.setRoleId(Long.parseLong(req.getParameter("role")));
			req.setAttribute("user", user);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/User/view.jsp");
			rd.forward(req, resp);
		}catch(Exception e){
				System.out.println(e);
				
		}finally{
			pm.close();
		}
	}
	@SuppressWarnings("unchecked")
	private boolean accesResource(String gmail,String url){

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(UserX.class);
		query.setFilter("email == idParam");
		query.declareParameters("String idParam");

		List<UserX> array = (List<UserX>) query.execute(gmail);

		if(array.size()> 0 ){

			UserX usuario = array.get(0);
			Query query1 = pm.newQuery(Resource.class);
			query1.setFilter("url == idParam");
			query1.declareParameters("String idParam");

			List<Resource> arrayRecurso = (List<Resource>)query1.execute(url);


			if(arrayRecurso.size() > 0 ){

				Resource recurso = arrayRecurso.get(0);

				Long roleId = usuario.getRoleId();
				Long resourceId = recurso.getId();

				Query query2 = pm.newQuery(Acces.class);
				query2.setFilter("roleId == idParam && resourceId == idParam2");
				query2.declareParameters("Long idParam , Long idParam2");
				
				List<Acces> arrayAcceso = (List<Acces>)query2.execute(roleId,resourceId);

				if(arrayAcceso.size()>0){
					
					pm.close();
					return arrayAcceso.get(0).getStatus()&&recurso.isStatus();
				}
			}

		}
		pm.close();
		return false;
	}
}
