package es.uvigo.mei.jpainicial;

import es.uvigo.mei.jpainicial.entidades.Articulo;
import es.uvigo.mei.jpainicial.entidades.Cliente;
import es.uvigo.mei.jpainicial.entidades.Direccion;
import es.uvigo.mei.jpainicial.entidades.Familia;
import es.uvigo.mei.jpainicial.entidades.LineaPedido;
import es.uvigo.mei.jpainicial.entidades.Pedido;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainPedido {

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
			Articulo a3 = new Articulo("codo plastico 90", "codo de plastico 90", f, 20.0);
			em.persist(a3);

			Direccion d = new Direccion("calle", "localidad", "12345", "provincia", "123456789");
			Cliente c = new Cliente("12345678A", "juan rey rey", d);
			em.persist(c);

			Double descuento = 0.9;

			Pedido p = new Pedido(Calendar.getInstance().getTime(), c);
			p.anadirLineaPedido(new LineaPedido(p, 2, a1, a1.getPrecioUnitario()));
			p.anadirLineaPedido(new LineaPedido(p, 5, a2, a2.getPrecioUnitario()));
			p.anadirLineaPedido(new LineaPedido(p, 1, a3, descuento * a3.getPrecioUnitario()));
			em.persist(p);

			System.out.println("PEDIDO " + p.toString());
			for (LineaPedido lp : p.getLineas()) {
				System.out.println("   " + lp.toString() + "   [total : " + lp.getImporteTotal() + "]");
			}
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
