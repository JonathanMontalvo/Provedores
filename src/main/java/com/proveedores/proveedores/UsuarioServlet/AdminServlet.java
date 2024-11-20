package com.proveedores.proveedores.UsuarioServlet;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.proveedores.proveedores.util.MongoDBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/administrador/consultar", "/administrador/agregar", "/administrador/editar"})
public class AdminServlet extends HttpServlet {
    private MongoCollection<Document> usuariosCollection;

    @Override
    public void init() throws ServletException {
        super.init();
        MongoDatabase database = MongoDBUtil.getInstance().getDatabase();
        usuariosCollection = database.getCollection("Usuarios");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String servletPath = request.getServletPath();

        if ("/administrador/agregar".equals(servletPath)) {
            request.getRequestDispatcher("/usuario/agregarUsuario.jsp").forward(request, response);
        } else if ("/administrador/editar".equals(servletPath)) {
            String correo = request.getParameter("correo");
            Document usuarioDoc = usuariosCollection.find(new Document("correo", correo)).first();
            Usuario usuario = new Gson().fromJson(usuarioDoc.toJson(), Usuario.class);
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/usuario/statusUsuario.jsp").forward(request, response);
        } else if ("/administrador/consultar".equals(servletPath)) {
            List<Usuario> usuarios = new ArrayList<>();
            for (Document doc : usuariosCollection.find()) {
                usuarios.add(new Gson().fromJson(doc.toJson(), Usuario.class));
            }
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/usuario/consultarUsuarios.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String servletPath = request.getServletPath();

        if ("/administrador/agregar".equals(servletPath)) {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(request.getParameter("nombre"));
            nuevoUsuario.setApellidoPaterno(request.getParameter("apellidoPaterno"));
            nuevoUsuario.setApellidoMaterno(request.getParameter("apellidoMaterno"));
            nuevoUsuario.setCorreo(request.getParameter("correo"));
            nuevoUsuario.setTelefono(request.getParameter("telefono"));
            nuevoUsuario.setPassword(request.getParameter("password"));
            nuevoUsuario.setRol(request.getParameter("rol"));
            nuevoUsuario.setStatus(1);

            usuariosCollection.insertOne(Document.parse(new Gson().toJson(nuevoUsuario)));

            response.sendRedirect(request.getContextPath() + "/");
        } else if ("/administrador/editar".equals(servletPath)) {
            String correo = request.getParameter("correo");
            Document usuarioDoc = usuariosCollection.find(new Document("correo", correo)).first();

            if (usuarioDoc != null) {
                Document updateDoc = new Document("$set", new Document("rol", request.getParameter("rol"))
                        .append("status", "1".equals(request.getParameter("status")) ? 1 : 0));
                usuariosCollection.updateOne(new Document("correo", correo), updateDoc);
            }

            response.sendRedirect(request.getContextPath() + "/administrador/consultar");
        }
    }
}
