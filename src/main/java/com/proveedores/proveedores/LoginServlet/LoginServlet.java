package com.proveedores.proveedores.LoginServlet;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.proveedores.proveedores.UsuarioServlet.Usuario;
import com.proveedores.proveedores.util.MongoDBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.bson.Document;

import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginServlet extends HttpServlet {
    private MongoCollection<Document> usuariosCollection;

    @Override
    public void init() throws ServletException {
        super.init();
        MongoDatabase database = MongoDBUtil.getInstance().getDatabase();
        usuariosCollection = database.getCollection("Usuarios");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getServletPath();

        if ("/logout".equals(action)) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("/login/iniciarSesion.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        // Verificar usuario en MongoDB
        Document query = new Document("correo", correo).append("password", password);
        Document usuarioDoc = usuariosCollection.find(query).first();

        if (usuarioDoc != null) {
            // Usuario autenticado
            Usuario usuario = new Gson().fromJson(usuarioDoc.toJson(), Usuario.class);

            HttpSession session = request.getSession();
            session.setAttribute("user", usuario);
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            // Usuario no encontrado o contraseña incorrecta
            request.setAttribute("errorMessage", "Correo o contraseña incorrectos.");
            request.getRequestDispatcher("/login/iniciarSesion.jsp").forward(request, response);
        }
    }
}
