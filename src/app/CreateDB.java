package app;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import bo.IeltsBo;

@Path("/createDB")
public class CreateDB {

	@GET
	public void create() {
		System.out.println("here?~!");
		IeltsBo bo = null;
		try {
			bo = new IeltsBo();
			// bo.createIeltsDB();
			// bo.createTable();

			bo.seeAll();

			System.out.println("done!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bo != null) {
				try {
					bo.disconnect();
				} catch (Exception e) {

				}
			}
		}
	}
}
