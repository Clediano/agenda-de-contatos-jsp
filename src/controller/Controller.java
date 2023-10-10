package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		if ("/main".equals(action)) {
			contatos(request, response);
		} else if ("/insert".equals(action)) {
			novoContato(request, response);
		} else if("/select".equals(action)) {
			buscarContato(request, response);
		} else if("/update".equals(action)) {
			atualizarContato(request, response);
		} else if("/delete".equals(action)) {
			removerContato(request, response);
		} else if("/report".equals(action)) {
			imprimir(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<JavaBeans> contatos = dao.findAll();
		
		request.setAttribute("contatos", contatos);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		
		rd.forward(request, response);
	}


	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		contato.setNome(request.getParameter("nome"));
		contato.setEmail(request.getParameter("email"));
		contato.setFone(request.getParameter("fone"));
		
		dao.create(contato);
		
		response.sendRedirect("main");
	}

	protected void atualizarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setEmail(request.getParameter("email"));
		contato.setFone(request.getParameter("fone"));
		
		dao.update(contato);
		
		response.sendRedirect("main");
	}


	protected void buscarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idcon = request.getParameter("idcon");
		
		JavaBeans result = dao.findOne(idcon);

		request.setAttribute("idcon", idcon);
		request.setAttribute("nome", result.getNome());
		request.setAttribute("fone", result.getFone());
		request.setAttribute("email", result.getEmail());
		
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");

		rd.forward(request, response);
	}
	


	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idcon = request.getParameter("idcon");
		
		dao.remove(idcon);
		
		response.sendRedirect("main");
	}
	

	protected void imprimir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		
		try {
			response.setContentType("apllication/pdf");
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
			PdfWriter.getInstance(documento, response.getOutputStream());
			documento.open();
			documento.add(new Paragraph("Lista de contatos:"));
			documento.add(new Paragraph(" "));
			
			PdfPTable tabela = new PdfPTable(3);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			List<JavaBeans> lista = dao.findAll();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}

}
