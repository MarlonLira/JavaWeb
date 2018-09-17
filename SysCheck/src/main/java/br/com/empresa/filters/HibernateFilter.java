package br.com.empresa.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.empresa.utils.HibernateUtil;

@WebFilter(urlPatterns = { "*.jsf" })
public class HibernateFilter implements Filter {
	private SessionFactory sessionFactory;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Transaction transaction = null;

		try {
			transaction = currentSession.beginTransaction();
			chain.doFilter(servletRequest, servletResponse);
			transaction.commit();
			if (currentSession.isOpen()) {
				currentSession.close();
			}
		} catch (Throwable e) {
			try {
				if (transaction.isActive()) {
					transaction.rollback();
				}
			} catch (Throwable ex) {
				ex.printStackTrace();
			}
			throw new ServletException(e);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.sessionFactory = HibernateUtil.getSessionFactory();
	}
}
