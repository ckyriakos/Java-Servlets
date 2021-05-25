package Util;

import Entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.*;
import java.io.PrintWriter;

import java.util.Scanner;
import java.io.*;
import java.sql.*;
import java.util.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;


public class ProductMain extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			  throws IOException, ServletException{

		String pbarcode=request.getParameter("pbarcode");
		String pname=request.getParameter("pname");
		String pcolor=request.getParameter("pcolor");
		String pdescription=request.getParameter("pdescription");

		List<String> result = new ArrayList<>(); 
		PrintWriter pwriter = response.getWriter();

		try {
			Product product = new Product(pbarcode,pcolor,pname,pdescription);
			

			SessionFactory factory = new Configuration().configure()
									.addAnnotatedClass(Product.class)
									.buildSessionFactory();
			
			Session session = factory.getCurrentSession();
                        session.beginTransaction();
			
			session.save(product);

			try {
				
				session.getTransaction().commit();
			//}catch(javax.persistence.PersistenceException exc) {

				
				//request.setAttribute("details",result);
				//RequestDispatcher view = request.getRequestDispatcher("result.jsp");
				//view.forward(request,response);
			}
			finally{}
			result.add(pbarcode);
			result.add(pname);
			result.add(pcolor);
			result.add(pdescription);

			request.setAttribute("details",result);

			RequestDispatcher view = request.getRequestDispatcher("result.jsp");

			view.forward(request,response);

			factory.close();
			
		}catch(Exception exc){
                        pwriter.println("<p>Error occured: Item already exists in the database</p>"); 
			System.err.println("An error occurred while connecting PostgreSQL database: products");
			exc.printStackTrace();
			System.out.println("\nError again my friend...");
		}
		pwriter.close();
        }
}