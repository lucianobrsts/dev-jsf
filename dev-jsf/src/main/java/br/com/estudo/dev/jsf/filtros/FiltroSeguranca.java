package br.com.estudo.dev.jsf.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.estudo.dev.jsf.controle.ControleLogin;

public class FiltroSeguranca implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession sessao = httpRequest.getSession();
		
		String contextPath = httpRequest.getContextPath();
		ControleLogin controleLogin = (ControleLogin) sessao.getAttribute("controleLogin");
		
		if(controleLogin == null || controleLogin.getUsuarioLogado() == null)
			httpResponse.sendRedirect(contextPath + "/login.xhtml");
		else {
			String pagina = httpRequest.getRequestURL().toString();
//			System.out.println("Pagina acessada: " + pagina);
			if(pagina.contains("/privado/Funcionario")) {
				if(!controleLogin.getUsuarioLogado().getGrupo().getNome().equals("Administradores"))
					httpResponse.sendRedirect(contextPath + "/naoAutorizado.xhtml");
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {}

}
