package controller.Photo;

import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Foto;
import model.PMF;

@SuppressWarnings("serial")
public class Remove extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q = pm.newQuery(Foto.class);
		if( req.getParameter("photo")!=null ){
			String photo = req.getParameter("photo");
			
			q.setFilter("photo == photoParam");
			q.declareParameters("String photoParam");	
			try{
				q.deletePersistentAll(photo);
				resp.getWriter().println("Se han borrado fotos.");
				resp.sendRedirect("/getList");
			}catch(Exception e){
					System.out.println(e);
					resp.getWriter().println("No se han podido borrar personas.");
					resp.sendRedirect("/index.jsp");
			}finally{
				q.closeAll();
				pm.close();
			}			
		}else {		
			resp.getWriter().println("No se ha especificado el color.");
			resp.sendRedirect("/index.jsp");
		}		
	}
}

