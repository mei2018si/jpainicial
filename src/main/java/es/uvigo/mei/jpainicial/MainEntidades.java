package es.uvigo.mei.jpainicial;

import es.uvigo.mei.jpainicial.entidades.Almacen;
import es.uvigo.mei.jpainicial.entidades.Articulo;
import es.uvigo.mei.jpainicial.entidades.ArticuloAlmacen;
import es.uvigo.mei.jpainicial.entidades.Cliente;
import es.uvigo.mei.jpainicial.entidades.Direccion;
import es.uvigo.mei.jpainicial.entidades.Familia;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainEntidades {

	public static final void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpainicial.PU");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Familia f = new Familia("tubos", "tubos de todas clases");
			em.persist(f);
			Articulo a1 = new Articulo("tubo acero", "tubo de acero", f, 10.0);
			em.persist(a1);
			Articulo a2 = new Articulo("tubo plastico", "tubo de plastico", f, 5.0);
			em.persist(a2);

			Direccion d = new Direccion("calle", "localidad", "12345", "provincia", "123456789");
			Cliente c = new Cliente("12345678A", "juan rey rey", d);
			em.persist(c);

			Almacen a = new Almacen("principal", "almacen principal", d);
			em.persist(a);

			ArticuloAlmacen aa1 = new ArticuloAlmacen(a1, a, 10);
			ArticuloAlmacen aa2 = new ArticuloAlmacen(a2, a, 10);
			em.persist(aa1);
			em.persist(aa2);

			tx.commit();

		} catch (Exception e) {
			System.err.println("Error");
			e.printStackTrace(System.err);

			if ((tx != null) && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		emf.close();

	}

}
