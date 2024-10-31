package com.proveedores.proveedores.VehiculoServlet;

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

@WebServlet(urlPatterns = {"/vehiculo", "/vehiculos/consultar", "/vehiculos/agregar", "/vehiculos/actualizar", "/vehiculos/borrar"})
public class VehiculoServlet extends HttpServlet {
    private List<Vehiculo> vehiculos;

    @Override
    public void init() throws ServletException {
        super.init();
        Gson gson = new Gson();
        Type vehiculoListType = new TypeToken<List<Vehiculo>>() {}.getType();
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/vehiculos.json")), "UTF-8")) {
            vehiculos = gson.fromJson(reader, vehiculoListType);
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

        if ("/vehiculos/consultar".equals(servletPath)) {
            request.setAttribute("vehiculos", vehiculos);
            request.getRequestDispatcher("/vehiculo/consultarVehiculo.jsp").forward(request, response);
        } else if ("/vehiculos/agregar".equals(servletPath)) {
            request.getRequestDispatcher("/vehiculo/agregarVehiculo.jsp").forward(request, response);
        } else if ("/vehiculos/actualizar".equals(servletPath)) {
            String placa = request.getParameter("placa");
            Vehiculo vehiculo = vehiculos.stream().filter(v -> v.getPlaca().equals(placa)).findFirst().orElse(null);
            request.setAttribute("vehiculo", vehiculo);
            request.getRequestDispatcher("/vehiculo/actualizarVehiculo.jsp").forward(request, response);
        } else if ("/vehiculos/borrar".equals(servletPath)) {
            String placa = request.getParameter("placa");
            vehiculos = vehiculos.stream().filter(v -> !v.getPlaca().equals(placa)).collect(Collectors.toList());
            response.sendRedirect(request.getContextPath() + "/vehiculos/consultar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String servletPath = request.getServletPath();

        if ("/vehiculo".equals(servletPath)) {
            // Lógica para agregar un nuevo vehículo
            String placa = request.getParameter("placa");
            String marca = request.getParameter("marca");
            String modelo = request.getParameter("modelo");
            double capacidad = Double.parseDouble(request.getParameter("capacidad"));
            String dimensiones = request.getParameter("dimensiones");
            int anio = Integer.parseInt(request.getParameter("anio"));
            String tipo = request.getParameter("tipo");
            String fechaAdquisicion = request.getParameter("fechaAdquisicion");
            String estado = request.getParameter("estado");
            String combustible = request.getParameter("combustible");
            String ultimoMantenimiento = request.getParameter("ultimoMantenimiento");
            String ubicacion = request.getParameter("ubicacion");

            Vehiculo nuevoVehiculo = new Vehiculo();
            nuevoVehiculo.setPlaca(placa);
            nuevoVehiculo.setMarca(marca);
            nuevoVehiculo.setModelo(modelo);
            nuevoVehiculo.setCapacidad(capacidad);
            nuevoVehiculo.setDimensiones(dimensiones);
            nuevoVehiculo.setAnio(anio);
            nuevoVehiculo.setTipo(tipo);
            nuevoVehiculo.setFechaAdquisicion(fechaAdquisicion);
            nuevoVehiculo.setEstado(estado);
            nuevoVehiculo.setCombustible(combustible);
            nuevoVehiculo.setUltimoMantenimiento(ultimoMantenimiento);
            nuevoVehiculo.setUbicacion(ubicacion);

            vehiculos.add(nuevoVehiculo);

            // Redirigir a la página de consulta de vehículos después de agregar el vehículo
            response.sendRedirect(request.getContextPath() + "/vehiculos/consultar");
        } else if ("/vehiculos/actualizar".equals(servletPath)) {
            // Lógica para actualizar un vehículo existente
            String placa = request.getParameter("placa");
            Vehiculo vehiculo = vehiculos.stream().filter(v -> v.getPlaca().equals(placa)).findFirst().orElse(null);

            if (vehiculo != null) {
                vehiculo.setMarca(request.getParameter("marca"));
                vehiculo.setModelo(request.getParameter("modelo"));
                vehiculo.setCapacidad(Double.parseDouble(request.getParameter("capacidad")));
                vehiculo.setDimensiones(request.getParameter("dimensiones"));
                vehiculo.setAnio(Integer.parseInt(request.getParameter("anio")));
                vehiculo.setTipo(request.getParameter("tipo"));
                vehiculo.setFechaAdquisicion(request.getParameter("fechaAdquisicion"));
                vehiculo.setEstado(request.getParameter("estado"));
                vehiculo.setCombustible(request.getParameter("combustible"));
                vehiculo.setUltimoMantenimiento(request.getParameter("ultimoMantenimiento"));
                vehiculo.setUbicacion(request.getParameter("ubicacion"));
            }

            // Redirigir a la página de consulta de vehículos después de actualizar el vehículo
            response.sendRedirect(request.getContextPath() + "/vehiculos/consultar");
        }
    }
}
