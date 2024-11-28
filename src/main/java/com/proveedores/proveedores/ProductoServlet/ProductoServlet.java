package com.proveedores.proveedores.ProductoServlet;

import com.google.gson.Gson;
import com.jcraft.jsch.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.proveedores.proveedores.UsuarioServlet.Usuario;
import com.proveedores.proveedores.util.MongoDBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/productos/consultar", "/productos/agregar", "/productos/actualizar", "/productos/borrar"})
@MultipartConfig // Esta anotación es necesaria para manejar los archivos subidos
public class ProductoServlet extends HttpServlet {
    private MongoCollection<Document> productosCollection;
    private MongoCollection<Document> inventarioCollection;
    private static final String UPLOAD_DIRECTORY = "C:/xampp/htdocs/ServidorArchivos/Proveedor/img"; // Cambiar esta ruta por el servidor bueno

    @Override
    public void init() throws ServletException {
        super.init();
        MongoDatabase database = MongoDBUtil.getInstance().getDatabase();
        productosCollection = database.getCollection("Productos");
        inventarioCollection = database.getCollection("Inventario_Proveedores");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String servletPath = request.getServletPath();
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("user");

        if (usuario != null) {
            if ("/productos/consultar".equals(servletPath)) {
                List<Producto> productos = new ArrayList<>();
                for (Document doc : productosCollection.find()) {
                    Producto producto = new Gson().fromJson(doc.toJson(), Producto.class);
                    producto.setId(doc.getObjectId("_id").toHexString()); // Asignar el ID del documento

                    productos.add(producto);
                }
                request.setAttribute("productos", productos);
                request.getRequestDispatcher("/productos/consultarProducto.jsp").forward(request, response);
            } else if ("/productos/agregar".equals(servletPath)) {
                request.getRequestDispatcher("/productos/agregarProducto.jsp").forward(request, response);
            } else if ("/productos/actualizar".equals(servletPath)) {
                String id = request.getParameter("id");
                Document productoDoc = productosCollection.find(new Document("_id", new ObjectId(id))).first();
                Producto producto = new Gson().fromJson(productoDoc.toJson(), Producto.class);
                producto.setId(id); // Asignar el ID del documento
                request.setAttribute("producto", producto);
                request.getRequestDispatcher("/productos/actualizarProducto.jsp").forward(request, response);
            } else if ("/productos/borrar".equals(servletPath)) {
                String id = request.getParameter("id");
                Document updateDoc = new Document("$set", new Document("status", 0));
                productosCollection.updateOne(new Document("_id", new ObjectId(id)), updateDoc);
                response.sendRedirect(request.getContextPath() + "/productos/consultar");
            }
        }else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String servletPath = request.getServletPath();

        if ("/productos/agregar".equals(servletPath)) {
            // Lógica para agregar un nuevo producto
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            double precio = Double.parseDouble(request.getParameter("precio"));
            String dimensiones = request.getParameter("dimensiones");
            double capacidad = Double.parseDouble(request.getParameter("capacidad"));
            String proveedor = request.getParameter("proveedor");
            int status = 1;
            Part imagenPart = request.getPart("imagen");

            Producto nuevoProducto = new Producto();
            nuevoProducto.setNombre(nombre);
            nuevoProducto.setDescripcion(descripcion);
            nuevoProducto.setPrecio(precio);
            nuevoProducto.setDimensiones(dimensiones);
            nuevoProducto.setCapacidad(capacidad);
            nuevoProducto.setProveedor(proveedor);
            nuevoProducto.setStatus(status); // Producto activo o inactivo

            // Insertar el producto sin la imagen
            Document productoDoc = Document.parse(new Gson().toJson(nuevoProducto));
            productosCollection.insertOne(productoDoc);

            // Obtener el ID del producto recién insertado
            ObjectId productId = productoDoc.getObjectId("_id");
            nuevoProducto.setId(productId.toHexString());

            // Subir la imagen al servidor SFTP si existe
            if (imagenPart != null && imagenPart.getSize() > 0) {
                String fileName = imagenPart.getSubmittedFileName();
                String fileExtension = fileName.substring(fileName.lastIndexOf("."));
                String newFileName = productId.toHexString() + fileExtension;

                try (InputStream input = imagenPart.getInputStream()) {
                    try {
                        uploadFileToSFTP(newFileName, input); // Subir el archivo al servidor SFTP
                    } catch (JSchException e) {
                        throw new RuntimeException(e);
                    } catch (SftpException e) {
                        throw new RuntimeException(e);
                    }
                }

                // Actualizar la base de datos con la nueva ruta de la imagen en el servidor SFTP
                String imagePath = "http://10.228.2.88/icons/Proveedores/" + newFileName; // Cambiar la ruta a tu servidor SFTP
                productosCollection.updateOne(Filters.eq("_id", productId),
                        new Document("$set", new Document("imagen", imagePath))); // Actualiza la imagen en la base de datos
            }

            // Inserción en la colección Inventario_Proveedores
            String ubicacion = nombre.length() % 2 == 0 ? "Almacen A" : "Almacen B";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            String fechaActualizacion = ZonedDateTime.now(ZoneId.of("America/Mexico_City")).format(formatter);

            Document inventarioDoc = new Document()
                    .append("id_Producto", productId)
                    .append("cantidad", 0)
                    .append("ubicacion", ubicacion)
                    .append("fecha_Actualizacion", fechaActualizacion);
            inventarioCollection.insertOne(inventarioDoc);

            // Redirigir a la página de consulta de productos después de agregar el producto
            response.sendRedirect(request.getContextPath() + "/productos/consultar");
        } else if ("/productos/actualizar".equals(servletPath)) {
            // Lógica para actualizar un producto existente
            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
                // Manejar el caso en que el ID no se haya pasado correctamente
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID del producto no se ha proporcionado.");
                return;
            }

            Document productoDoc = productosCollection.find(new Document("_id", new ObjectId(id))).first();

            if (productoDoc != null) {
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                double precio = Double.parseDouble(request.getParameter("precio"));
                String dimensiones = request.getParameter("dimensiones");
                double capacidad = Double.parseDouble(request.getParameter("capacidad"));
                String proveedor = request.getParameter("proveedor");
                int status = Integer.parseInt(request.getParameter("status"));

                Document updateDoc = new Document("$set", new Document("nombre", nombre)
                        .append("descripcion", descripcion)
                        .append("precio", precio)
                        .append("dimensiones", dimensiones)
                        .append("capacidad", capacidad)
                        .append("proveedor", proveedor)
                        .append("status", status));

                Part imagenPart = request.getPart("imagen");
                if (imagenPart != null && imagenPart.getSize() > 0) {
                    String fileName = imagenPart.getSubmittedFileName();
                    String fileExtension = fileName.substring(fileName.lastIndexOf("."));
                    String newFileName = id + fileExtension;

                    try (InputStream input = imagenPart.getInputStream()) {
                        uploadFileToSFTP(newFileName, input); // Subir el archivo al servidor SFTP
                    } catch (JSchException e) {
                        throw new RuntimeException(e);
                    } catch (SftpException e) {
                        throw new RuntimeException(e);
                    }

                    // Actualizar la base de datos con la nueva ruta de la imagen
                    updateDoc.append("$set", new Document("imagen", "http://10.228.2.88/icons/Proveedores/" + newFileName)); // Cambia la ruta a tu servidor SFTP
                }

                productosCollection.updateOne(new Document("_id", new ObjectId(id)), updateDoc);

                // Redirigir a la página de consulta de productos después de actualizar el producto
                response.sendRedirect(request.getContextPath() + "/productos/consultar");
            } else {
                // Manejar el caso en que el producto no se encuentra
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "El producto no se encuentra.");
            }
        }
    }

    // Método para subir el archivo al servidor SFTP
    private void uploadFileToSFTP(String fileName, InputStream inputStream) throws JSchException, SftpException, IOException {
        // Credenciales directamente dentro del método
        String SFTP_HOST = "10.228.2.88";
        int SFTP_PORT = 22;
        String SFTP_USER = "administrador";
        String SFTP_PASSWORD = "Abcd2024";
        String REMOTE_DIR = "Proveedores/";

        JSch jsch = new JSch();
        Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
        session.setPassword(SFTP_PASSWORD);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();

        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();

        try {
            channelSftp.cd(REMOTE_DIR);
        } catch (SftpException e) {
            channelSftp.mkdir(REMOTE_DIR);
            channelSftp.cd(REMOTE_DIR);
        }

        channelSftp.put(inputStream, fileName);

        channelSftp.disconnect();
        session.disconnect();
    }

}
