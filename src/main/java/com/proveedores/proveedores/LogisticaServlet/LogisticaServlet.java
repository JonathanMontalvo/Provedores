package com.proveedores.proveedores.LogisticaServlet;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.proveedores.proveedores.ProductoServlet.Producto;
import com.proveedores.proveedores.UsuarioServlet.Usuario;
import com.proveedores.proveedores.util.MongoDBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/logistica/inventario", "/logistica/historial", "/logistica/reabastecimiento", "/logistica/compras" })
public class LogisticaServlet extends HttpServlet {
    private MongoCollection<Document> inventarioCollection;
    private MongoCollection<Document> historialCollection;
    private MongoCollection<Document> restockCollection;
    private MongoCollection<Document> productosCollection;

    @Override
    public void init() throws ServletException {
        super.init();
        MongoDatabase database = MongoDBUtil.getInstance().getDatabase();
        inventarioCollection = database.getCollection("Inventario_Proveedores");
        historialCollection = database.getCollection("Historial_Venta");
        restockCollection = database.getCollection("Restock_Proveedores");
        productosCollection = database.getCollection("Productos");
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
                case "/logistica/inventario":
                    consultarInventario(request, response);
                    break;

                case "/logistica/historial":
                    consultarHistorial(request, response);
                    break;

                case "/logistica/reabastecimiento":
                    redirigirReabastecimiento(request, response);
                    break;

                case "/logistica/compras":
                    consultarCompras(request, response);
                    break;

                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        }else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    private void consultarInventario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Inventario> inventarios = new ArrayList<>();
        Map<String, String> productoNombres = new HashMap<>();
        Map<String, String> productoProveedores = new HashMap<>();


        for (Document doc : inventarioCollection.find()) {
            //System.out.println(doc);
            Inventario inventario = new Gson().fromJson(doc.toJson(), Inventario.class);
            inventario.setId(doc.getObjectId("_id").toHexString());
            inventario.setIdProducto(doc.getObjectId("id_Producto"));

            //System.out.println(inventario);
            // Buscar el producto correspondiente
            Document productoDoc = productosCollection.find(new Document("_id", inventario.getIdProducto())).first();

            if (productoDoc != null) {
                Producto producto = new Gson().fromJson(productoDoc.toJson(), Producto.class);
                productoNombres.put(inventario.getId(), producto.getNombre());
                productoProveedores.put(inventario.getId(), producto.getProveedor());
            }
            inventarios.add(inventario);
        }

        request.setAttribute("inventarios", inventarios);
        request.setAttribute("productoNombres", productoNombres);
        request.setAttribute("productoProveedores", productoProveedores);
        request.getRequestDispatcher("/logistica/consultarInventario.jsp").forward(request, response);

    }

    private void consultarHistorial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HistorialVenta> historialVentas = new ArrayList<>();
        for (Document doc : historialCollection.find()) {
            HistorialVenta historialVenta = new Gson().fromJson(doc.toJson(), HistorialVenta.class);
            historialVenta.setId(doc.getObjectId("_id").toHexString());
            //System.out.println(doc);
            // Obtener la lista de productos vendidos
            List<Document> productosVendidosDocs = (List<Document>) doc.get("productos_Vendidos");

            // Convertir id_Producto a cadena hexadecimal para cada producto vendido
            List<ProductoVendido> productosVendidos = new ArrayList<>();
            for (Document productoVendidoDoc : productosVendidosDocs) {
                //System.out.println(productoVendidoDoc);
                ProductoVendido productoVendido = new Gson().fromJson(productoVendidoDoc.toJson(), ProductoVendido.class);
                productoVendido.setIdProducto(productoVendidoDoc.getObjectId("id_Producto"));
                productosVendidos.add(productoVendido);
            }

            // Asignar la lista de productos vendidos actualizada a historialVenta
            historialVenta.setProductosVendidos(productosVendidos);

            historialVentas.add(historialVenta);
        }
        request.setAttribute("historialVentas", historialVentas);
        request.getRequestDispatcher("/logistica/consultarHistorial.jsp").forward(request, response);
    }

    private void redirigirReabastecimiento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idInventario = request.getParameter("id");

        // Buscar en la colección Inventario_Proveedores para obtener id_Producto
        Document inventarioDoc = inventarioCollection.find(new Document("_id", new ObjectId(idInventario))).first();
        if (inventarioDoc != null) {
            ObjectId idProducto = inventarioDoc.getObjectId("id_Producto");

            // Buscar en la colección de productos para obtener el precio del producto
            Document productoDoc = productosCollection.find(new Document("_id", idProducto)).first();
            if (productoDoc != null) {
                double precio = productoDoc.getDouble("precio");

                // Calcular el costo con el 15% de descuento
                double costo = precio - (precio * 0.15);

                //Costo redondeado
                BigDecimal costoRedondeado = new BigDecimal(costo).setScale(2, RoundingMode.HALF_UP);

                // Enviar idInventario y costo como atributos de la solicitud
                request.setAttribute("idInventario", idInventario);
                request.setAttribute("costo", costoRedondeado.doubleValue());
            }
        }

        request.getRequestDispatcher("/logistica/reabastecimientoInventario.jsp").forward(request, response);
    }

    private void consultarCompras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RestockProveedores> compras = new ArrayList<>();
        for (Document doc : restockCollection.find()) {
            RestockProveedores restock = new Gson().fromJson(doc.toJson(), RestockProveedores.class);
            restock.setId(doc.getObjectId("_id").toHexString());
            restock.setIdInventario(doc.getObjectId("id_Inventario"));
            compras.add(restock);
        }
        request.setAttribute("compras", compras);
        request.getRequestDispatcher("/logistica/consultarCompras.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String servletPath = request.getServletPath();

        if ("/logistica/reabastecimiento".equals(servletPath)) {
            reabastecimiento(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void reabastecimiento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idInventario = request.getParameter("idInventario");
        int cantidadReabastecida = Integer.parseInt(request.getParameter("cantidad"));
        double costoRestock = Double.parseDouble(request.getParameter("costoRestock"));

        // Obtener la fecha actual utilizando ZonedDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String fechaRestock = ZonedDateTime.now(ZoneId.of("America/Mexico_City")).format(formatter);

        // Crear el documento para insertar en la colección RestockProveedores
        Document restockDoc = new Document()
                .append("id_Inventario", new ObjectId(idInventario))
                .append("cantidad", cantidadReabastecida)
                .append("costo_Restock", costoRestock)
                .append("fecha_Restock", fechaRestock);

        // Insertar el documento en la colección RestockProveedores
        restockCollection.insertOne(restockDoc);

        // Actualizar la cantidad en Inventario_Proveedores
        Document inventarioDoc = inventarioCollection.find(new Document("_id", new ObjectId(idInventario))).first();
        if (inventarioDoc != null) {
            int cantidadActual = inventarioDoc.getInteger("cantidad");
            int nuevaCantidad = cantidadActual + cantidadReabastecida;

            // Actualizar el documento en la colección Inventario_Proveedores
            Document updateDoc = new Document("$set", new Document("cantidad", nuevaCantidad).append("fecha_Actualizacion", fechaRestock));
            inventarioCollection.updateOne(new Document("_id", new ObjectId(idInventario)), updateDoc);
        }

        // Redirigir a una página de éxito
        response.sendRedirect(request.getContextPath() + "/logistica/inventario");
    }
}
