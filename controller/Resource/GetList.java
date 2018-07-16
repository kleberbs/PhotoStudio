package controller.Resource;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import model.Acces;
import model.PMF;
import model.Resource;
import model.UserX;

	@SuppressWarnings("serial")
	public class GetList extends HttpServlet{
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {

			UserService us = UserServiceFactory.getUserService();
			User user = us.getCurrentUser();
			 if(user == null){		
				 RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/AccesDenied/accesDenied.jsp");
				 try {
					rd.forward(req, resp);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			}
			 else {
				 if(us.isUserAdmin() || accesResource(user.getEmail(),"/listResource")){
					 PersistenceManager pm = PMF.get().getPersistenceManager();
						Query query = pm.newQuery(Resource.class);
						@SuppressWarnings("unchecked")
						List<Resource> resources = (List<Resource>)query.execute("select from Resource");
						
						req.setAttribute("resources", resources);
						try {
							req.getRequestDispatcher("/WEB-INF/Resource/list.jsp").forward(req, resp);
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