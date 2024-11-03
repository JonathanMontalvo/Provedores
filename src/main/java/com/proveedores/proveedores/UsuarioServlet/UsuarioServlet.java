package com.proveedores.proveedores.UsuarioServlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

@WebServlet(urlPatterns = {"/usuario/editar"})
public class UsuarioServlet extends HttpServlet {
    private List<Usuario> usuarios;

    @Override
    public void init() throws ServletException {
        super.init();
        Gson gson = new Gson();
        Type usuarioListType = new TypeToken<List<Usuario>>() {}.getType();
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/usuarios.json")), "UTF-8")) {
            usuarios = gson.fromJson(reader, usuarioListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String correo = request.getParameter("correo");
        //String correo = "juan@example.com";
        //editar?correo=juan%40example%2Ecom
        Usuario usuario = usuarios.stream().filter(u -> u.getCorreo().equals(correo)).findFirst().orElse(null);
        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("/usuario/editarUsuario.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // Lógica para actualizar un usuario existente
        String correo = request.getParameter("correo");
        Usuario usuario = usuarios.stream().filter(u -> u.getCorreo().equals(correo)).findFirst().orElse(null);

        if (usuario != null) {
            usuario.setNombre(request.getParameter("nombre"));
            usuario.setApellidoPaterno(request.getParameter("apellidoPaterno"));
            usuario.setApellidoMaterno(request.getParameter("apellidoMaterno"));
            usuario.setTelefono(request.getParameter("telefono"));
        }

        // Redirigir a la página de "No sé" de usuarios después de actualizar el usuario
        response.sendRedirect(request.getContextPath() + "/");
    }
}
