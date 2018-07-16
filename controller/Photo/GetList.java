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

import model.Foto;
import model.PMF;

	@SuppressWarnings("serial")
	public class GetList extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			//resp.setContentType("text/plain");
			
			final PersistenceManager pm = PMF.get().getPersistenceManager();
			final Query q = pm.newQuery(Foto.class);
			
			if( req.getParameter("photo")!=null ){
				
				String photo = req.getParameter("photo");
				//q.setOrdering("key ascending");
				q.setOrdering("key descending");
				q.setRange(0, 10);
				q.setFilter("photo == photoParam");
				q.declareParameters("String photoParam");
				
				try{
					
					@SuppressWarnings("unchecked")
					List<Foto> fotos = (List<Foto>) q.execute(photo);
					req.setAttribute("fotos", fotos);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Photo/list.jsp");
					rd.forward(req, resp);
				
				}catch(Exception e){
					System.out.println(e);
				}finally{
					q.closeAll();
					pm.close();
				}
				
			}else {
				q.setOrdering("key descending");
				q.setRange(0, 10);
						 
				try{
					@SuppressWarnings("unchecked")
					List<Foto> fotos = (List<Foto>) q.execute();
					req.setAttribute("fotos", fotos);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Photo/list.jsp");
					rd.forward(req, resp);
				}catch(Exception e){
					System.out.println(e);
				}finally{
					q.closeAll();
					pm.close();
				}
			}			
		}
	}

	