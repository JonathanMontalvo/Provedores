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

@WebServlet(urlPatterns = {"/usuario/editar", "/usuario/cambiarPassword"})
public class UsuarioServlet extends HttpServlet {
    private MongoCollection<Document> usuariosCollection;

    @Override
    public void init() throws ServletException {
        super.init();
        MongoDatabase database = MongoDBUtil.getInstance().getDatabase();
        usuariosCollection = database.getCollection("Usuarios_Proveedores");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("user");

        if (usuario != null) {
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/usuario/editarUsuario.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String servletPath = request.getServletPath();
        if ("/usuario/editar".equals(servletPath)) {
            editarUsuario(request, response);
        } else if ("/usuario/cambiarPassword".equals(servletPath)) {
            cambiarPassword(request, response);
        }
    }

    private void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("user");

        if (usuario != null) {
            String telefono = request.getParameter("telefono");
            Document existingUserByPhone = usuariosCollection.find(new Document("telefono", telefono).append("status", 1)).first();
            if (existingUserByPhone != null) {
                response.sendRedirect(request.getContextPath() + "/usuario/editar?updateFailed=true");
                return; // Detener la ejecución para evitar el segundo redirect }
            }

            usuario.setNombre(request.getParameter("nombre"));
            usuario.setApellidoPaterno(request.getParameter("apellidoPaterno"));
            usuario.setApellidoMaterno(request.getParameter("apellidoMaterno"));
            usuario.setTelefono(request.getParameter("telefono"));

            // Actualizar los datos en la base de datos
            Document updateDoc = new Document("$set", new Document("nombre", usuario.getNombre())
                    .append("apellido_Paterno", usuario.getApellidoPaterno())
                    .append("apellido_Materno", usuario.getApellidoMaterno())
                    .append("telefono", usuario.getTelefono()));
            usuariosCollection.updateOne(new Document("correo", usuario.getCorreo()), updateDoc);

            session.setAttribute("user", usuario); // Actualizar la sesión con los datos modificados
        }

        response.sendRedirect(request.getContextPath() + "/usuario/editar?updateSuccess=true");
    }

    private void cambiarPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("user");

        if (usuario != null) {
            String nuevaPassword = request.getParameter("newPassword");
            String confirmarPassword = request.getParameter("confirmNewPassword");

            if (nuevaPassword != null && !nuevaPassword.isEmpty() && nuevaPassword.equals(confirmarPassword)) {
                usuario.setPassword(nuevaPassword);

                // Actualizar la contraseña en la base de datos
                Document updateDoc = new Document("$set", new Document("password", nuevaPassword));
                usuariosCollection.updateOne(new Document("correo", usuario.getCorreo()), updateDoc);

                session.setAttribute("user", usuario); // Actualizar la sesión con los datos modificados
            }
        }

        response.sendRedirect(request.getContextPath() + "/usuario/editar?passwordChangeSuccess=true");
    }
}
