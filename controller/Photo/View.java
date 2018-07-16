package controller.Photo;

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
import model.Foto;
import model.PMF;
import model.Resource;
import model.UserX;

@SuppressWarnings("serial")
public class View extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");		
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
			 if(us.isUserAdmin() || accesResource(user.getEmail(),"/viewPhoto")){
		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q = pm.newQuery(Foto.class);
		if( req.getParameter("photo")!=null ){
			String photo = req.getParameter("photo");
			
			try{
				@SuppressWarnings("unchecked")
				List<Foto> fotos =  (List<Foto>) q.execute(photo);
				Foto foto=fotos.get(0);
				for (Foto f : fotos) {
					if(f.getPhoto().equals(photo)){
						foto=f;
					}
				}
				req.setAttribute("foto", foto);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Photo/view.jsp");
				rd.forward(req, resp);
			}catch(Exception e){
					System.out.println(e);
					resp.getWriter().println("No se pudo eliminar foto.");
					resp.sendRedirect("/index.jsp");
			}finally{
				q.closeAll();
				pm.close();
			}			
		}else {		
			resp.getWriter().println("No se encontro foto.");
			resp.sendRedirect("/index.jsp");
		}}
			 RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/AccesDenied/accesDenied.jsp");
			 try {
				rd.forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
								return arrayAcceso.get(0).getStatus();
							}
						}

					}
					pm.close();
					return false;
				}

}

