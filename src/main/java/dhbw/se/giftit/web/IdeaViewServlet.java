/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.web;

import dhbw.se.giftit.ejb.IdeaBean;
import dhbw.se.giftit.ejb.RoomBean;
import dhbw.se.giftit.ejb.UserBean;
import dhbw.se.giftit.jpa.IdeaEntry;
import dhbw.se.giftit.jpa.RoomEntry;
import dhbw.se.giftit.jpa.UserEntry;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author Viktoria
 */
@WebServlet(name = "IdeaViewServlet", urlPatterns = {"/secure/IdeaView"})
public class IdeaViewServlet extends HttpServlet {

    @EJB
    IdeaBean ideaBean;
    @EJB
    RoomBean roomBean;
    @EJB
    UserBean userBean;

    IdeaEntry idea = new IdeaEntry();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        long id = Long.parseLong(request.getParameter("id"));
        IdeaEntry idea = ideaBean.findIdea(id);
        if (idea == null) {
            response.sendRedirect(request.getContextPath() + "/secure/Error?textId=3");
        }
        long idr = idea.getRoom().getId();

        UserEntry user = userBean.getUser();
        String uname = user.getUsername();
        boolean accessallowed = false;

        for (UserEntry userentry : idea.getRoom().getUsers()) {
            if (userentry.getUsername().equals(uname)) {
                accessallowed = true;
            }
        }
        if (!accessallowed) {
            response.sendRedirect(request.getContextPath() + "/secure/Error?textId=2");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("idr", idr);
            session.setAttribute("idea_name", idea.getName());
            session.setAttribute("price", idea.getPrice());
            session.setAttribute("link", idea.getLink());
            session.setAttribute("description", idea.getDescription());

            // Anfrage an dazugerhörige JSP weiterleiten
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Idea/IdeaView.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        RoomEntry room = new RoomEntry();
        long idr = idea.getRoom().getId();
        String button = request.getParameter("button");
        long id = Long.parseLong(request.getParameter("id"));

        switch (button) {
            case "deleteIdea":
                ideaBean.deleteIdea(Long.parseLong(request.getParameter("id")));
                response.sendRedirect(request.getContextPath() + "/secure/RoomView?id=" + idr);
                break;
        }

    }

}
