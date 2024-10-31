package com.proveedores.proveedores.ProductoServlet;

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
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/producto", "/productos/consultar", "/productos/agregar", "/productos/actualizar", "/productos/borrar"})
public class ProductoServlet extends HttpServlet {
    private List<Producto> productos;

    @Override
    public void init() throws ServletException {
        super.init();
        Gson gson = new Gson();
        Type productoListType = new TypeToken<List<Producto>>() {}.getType();
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/productos.json")), "UTF-8")) {
            productos = gson.fromJson(reader, productoListType);
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

        if ("/productos/consultar".equals(servletPath)) {
            request.setAttribute("productos", productos);
            request.getRequestDispatcher("/productos/consultarProducto.jsp").forward(request, response);
        } else if ("/productos/agregar".equals(servletPath)) {
            request.getRequestDispatcher("/productos/agregarProducto.jsp").forward(request, response);
        } else if ("/productos/actualizar".equals(servletPath)) {
            String nombre = request.getParameter("nombre");
            Producto producto = productos.stream().filter(p -> p.getNombre().equals(nombre)).findFirst().orElse(null);
            request.setAttribute("producto", producto);
            request.getRequestDispatcher("/productos/actualizarProducto.jsp").forward(request, response);
        } else if ("/productos/borrar".equals(servletPath)) {
            String nombre = request.getParameter("nombre");
            productos = productos.stream().filter(p -> !p.getNombre().equals(nombre)).collect(Collectors.toList());
            response.sendRedirect(request.getContextPath() + "/productos/consultar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String servletPath = request.getServletPath();

        if ("/producto".equals(servletPath)) {
            // Lógica para agregar un nuevo producto
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            double precio = Double.parseDouble(request.getParameter("precio"));
            String dimensiones = request.getParameter("dimensiones");
            double capacidad = Double.parseDouble(request.getParameter("capacidad"));
            String marca = request.getParameter("marca");

            Producto nuevoProducto = new Producto();
            nuevoProducto.setNombre(nombre);
            nuevoProducto.setDescripcion(descripcion);
            nuevoProducto.setPrecio(precio);
            nuevoProducto.setDimensiones(dimensiones);
            nuevoProducto.setCapacidad(capacidad);
            nuevoProducto.setMarca(marca);

            productos.add(nuevoProducto);

            // Redirigir a la página de consulta de productos después de agregar el producto
            response.sendRedirect(request.getContextPath() + "/productos/consultar");
        } else if ("/productos/actualizar".equals(servletPath)) {
            // Lógica para actualizar un producto existente
            String nombre = request.getParameter("nombre");
            Producto producto = productos.stream().filter(p -> p.getNombre().equals(nombre)).findFirst().orElse(null);

            if (producto != null) {
                producto.setDescripcion(request.getParameter("descripcion"));
                producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
                producto.setDimensiones(request.getParameter("dimensiones"));
                producto.setCapacidad(Double.parseDouble(request.getParameter("capacidad")));
                producto.setMarca(request.getParameter("marca"));
            }

            // Redirigir a la página de consulta de productos después de actualizar el producto
            response.sendRedirect(request.getContextPath() + "/productos/consultar");
        }
    }
}
