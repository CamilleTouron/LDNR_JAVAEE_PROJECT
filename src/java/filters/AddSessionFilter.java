/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.FlashMessage;

/**
 *
 * @author Groupe 1
 */
@WebFilter(urlPatterns = {"/*"})
public class AddSessionFilter implements Filter {
    /**
     * Constructeur du filtre.
     */
    public AddSessionFilter() {
    }
    /**
     * Actions avant de proceder.
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException 
     */
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        request.getServletContext().log("AddSessionFilter : Do Before Processing");
        //Créer une session ou la récupérer :
        HttpSession session = ((HttpServletRequest) request).getSession();
        if (session.getAttribute("flash") == null) {
            FlashMessage flash = new FlashMessage();
            flash.add("ok", "Bienvenu sur le site BookHostal.");
            session.setAttribute("flash", flash);
        }
    }
    /**
     * Action après avoir procéder.
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException 
     */
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        request.getServletContext().log("AddSessionFilter : Do After Processing");
    }

    /**
     * Enchainement des actions.
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        doBeforeProcessing(request, response);
        chain.doFilter(request, response);
        doAfterProcessing(request, response);
    }
    /**
     * 
     * @param filterConfig
     * @throws ServletException 
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    /**
     * 
     */
    @Override
    public void destroy() {
    }

}
