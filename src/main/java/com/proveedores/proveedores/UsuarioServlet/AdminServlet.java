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
import jakarta.servlet.http.HttpSession;
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
        usuariosCollection = database.getCollection("Usuarios_Proveedores");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String servletPath = request.getServletPath();
        HttpSession session = request.getSession();
        Usuario currentUser = (Usuario) session.getAttribute("user");

        if (currentUser != null) {
            switch (servletPath) {
                case "/administrador/agregar":
                    request.getRequestDispatcher("/usuario/agregarUsuario.jsp").forward(request, response);
                    break;

                case "/administrador/editar":
                    String correo = request.getParameter("correo");
                    Document usuarioDoc = usuariosCollection.find(new Document("correo", correo)).first();
                    if (usuarioDoc != null) {
                        Usuario usuario = new Gson().fromJson(usuarioDoc.toJson(), Usuario.class);
                        request.setAttribute("usuario", usuario);
                        request.getRequestDispatcher("/usuario/statusUsuario.jsp").forward(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/administrador/consultar");
                    }
                    break;

                case "/administrador/consultar":
                    List<Usuario> usuarios = new ArrayList<>();
                    for (Document doc : usuariosCollection.find()) {
                        Usuario usuario = new Gson().fromJson(doc.toJson(), Usuario.class);
                        if (currentUser != null && !usuario.getCorreo().equals(currentUser.getCorreo())) {
                            usuarios.add(usuario);
                        }
                    }
                    request.setAttribute("usuarios", usuarios);
                    request.getRequestDispatcher("/usuario/consultarUsuarios.jsp").forward(request, response);
                    break;
            }
        }else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String servletPath = request.getServletPath();

        switch (servletPath) {
            case "/administrador/agregar":
                agregarUsuario(request, response);
                break;

            case "/administrador/editar":
                editarUsuario(request, response);
                break;
        }
    }

    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");

        // Verificar si el correo ya existe y que el status sea igual a 1
        Document existingUserByEmail = usuariosCollection.find(new Document("correo", correo).append("status", 1)).first();

        // Verificar si el teléfono ya existe y que el status sea igual a 1
        Document existingUserByPhone = usuariosCollection.find(new Document("telefono", telefono).append("status", 1)).first();

        if (existingUserByEmail != null) {
            request.setAttribute("errorMessage", "Usuario con este correo ya existe.");
            request.getRequestDispatcher("/usuario/agregarUsuario.jsp").forward(request, response);
        } else if (existingUserByPhone != null) {
            request.setAttribute("errorMessage", "Número de teléfono ya registrado.");
            request.getRequestDispatcher("/usuario/agregarUsuario.jsp").forward(request, response);
        } else {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(request.getParameter("nombre"));
            nuevoUsuario.setApellidoPaterno(request.getParameter("apellidoPaterno"));
            nuevoUsuario.setApellidoMaterno(request.getParameter("apellidoMaterno"));
            nuevoUsuario.setCorreo(correo);
            nuevoUsuario.setTelefono(telefono);
            nuevoUsuario.setPassword(request.getParameter("password"));
            nuevoUsuario.setRol(request.getParameter("rol"));
            nuevoUsuario.setStatus(1);

            usuariosCollection.insertOne(Document.parse(new Gson().toJson(nuevoUsuario)));

            response.sendRedirect(request.getContextPath() + "/administrador/consultar");
        }
    }

    private void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
