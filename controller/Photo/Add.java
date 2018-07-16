package controller.Photo;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PMF;
import model.Resource;

import model.UserX;


import javax.servlet.RequestDispatcher;


import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import model.Acces;
import model.Foto;



@SuppressWarnings("serial")
public class Add extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
			 if(us.isUserAdmin() || accesResource(user.getEmail(),req.getRequestURI())){
				 RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/Photo/add.jsp");
				 try {
				
					 rd.forward(req, resp);
					 } 
				 catch (ServletException e) {			
					 // TODO Auto-generated catch block
					 e.printStackTrace();
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
		

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		 List<BlobKey> blobKeys = blobs.get("photo");
		 String photo="/serve?blob-key=" + blobKeys.get(0).getKeyString();
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Foto p = new Foto(name, description, photo);
		try{
			pm.makePersistent(p);
			resp.getWriter().println("Datos grabados correctamente.");
			resp.sendRedirect("/listPhoto");
		}catch(Exception e){
			System.out.println(e);
			resp.getWriter().println("Ocurrió un error, vuelva a intentarlo.");
			resp.sendRedirect("/index.jsp");
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
