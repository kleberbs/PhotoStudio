package controller.Photo;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import model.Foto;
import model.PMF;


@SuppressWarnings("serial")
public class Save extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
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
}