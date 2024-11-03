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

@WebServlet(urlPatterns = {"/administrador/consultar","/administrador/agregar","/administrador/editar"})
public class AdminServlet extends HttpServlet {
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

        String servletPath = request.getServletPath();

        if ("/administrador/agregar".equals(servletPath)) {
            request.getRequestDispatcher("/usuario/agregarUsuario.jsp").forward(request, response);
        } else if ("/administrador/editar".equals(servletPath)) {
            String correo = request.getParameter("correo");
            Usuario usuario = usuarios.stream().filter(u -> u.getCorreo().equals(correo)).findFirst().orElse(null);
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/usuario/statusUsuario.jsp").forward(request, response);
        } else if ("/administrador/consultar".equals(servletPath)) {
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
            // Lógica para agregar un nuevo usuario
            String nombre = request.getParameter("nombre");
            String apellidoPaterno = request.getParameter("apellidoPaterno");
            String apellidoMaterno = request.getParameter("apellidoMaterno");
            String correo = request.getParameter("correo");
            String telefono = request.getParameter("telefono");
            String password = request.getParameter("password");
            String rol = request.getParameter("rol");
            int status = 1;

            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setApellidoPaterno(apellidoPaterno);
            nuevoUsuario.setApellidoMaterno(apellidoMaterno);
            nuevoUsuario.setCorreo(correo);
            nuevoUsuario.setTelefono(telefono);
            nuevoUsuario.setPassword(password);
            nuevoUsuario.setRol(rol);
            nuevoUsuario.setStatus(status);

            usuarios.add(nuevoUsuario);

            // Redirigir a la página de "No sé" de usuarios después de agregar el usuario
            response.sendRedirect(request.getContextPath() + "/");
        }else if ("/administrador/editar".equals(servletPath)) {
            // Lógica para actualizar un usuario existente
            String correo = request.getParameter("correo");
            Usuario usuario = usuarios.stream().filter(u -> u.getCorreo().equals(correo)).findFirst().orElse(null);

            if (usuario != null) {
                usuario.setRol(request.getParameter("rol"));
                if ("1".equals(request.getParameter("status"))){
                    usuario.setStatus(1);
                }else {
                    usuario.setStatus(0);
                }
            }

            // Redirigir a la página de "No sé" de usuarios después de actualizar el usuario
            response.sendRedirect(request.getContextPath() + "/administrador/consultar");
        }
    }
}
