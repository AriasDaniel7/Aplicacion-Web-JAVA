package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.productos;
import modelo.productosDAO;

/**
 *
 * @author Daniel Arias
 */
@WebServlet(name = "productosController", urlPatterns = {"/productosController"})
public class productosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        productosDAO productosDAO = new productosDAO();
        String accion;
        RequestDispatcher dispatcher = null;

        accion = request.getParameter("accion");

        if (accion == null || accion.isEmpty()) {
            dispatcher = request.getRequestDispatcher("productos/index.jsp");
            ArrayList<productos> listaProductos = productosDAO.listarProductos();
            request.setAttribute("lista", listaProductos);
        } else if ("nuevo".equals(accion)) {
            dispatcher = request.getRequestDispatcher("productos/nuevo.jsp");
            
        } else if ("insert".equals(accion)) {
            
            String codigo = request.getParameter("codigo").intern();
            String nombre = request.getParameter("nombre").intern();
            Double precio = Double.parseDouble(request.getParameter("precio"));
            int existencia = Integer.parseInt(request.getParameter("existencia"));

            productos producto = new productos(0, codigo, nombre, precio, existencia);

            productosDAO.insertar(producto);
            ArrayList<productos> listaProductos = productosDAO.listarProductos();
            request.setAttribute("lista", listaProductos);
            dispatcher = request.getRequestDispatcher("productos/index.jsp");
        } else if ("modificar".equals(accion)) {
            
            dispatcher = request.getRequestDispatcher("productos/modificar.jsp");
            int id = Integer.parseInt(request.getParameter("id"));
            productos producto = productosDAO.mostrarProducto(id);
            request.setAttribute("producto", producto);
            
        } else if ("actualizar".equals(accion)) {
            
            int id = Integer.parseInt(request.getParameter("id"));
            String codigo = request.getParameter("codigo").intern();
            String nombre = request.getParameter("nombre").intern();
            Double precio = Double.parseDouble(request.getParameter("precio"));
            int existencia = Integer.parseInt(request.getParameter("existencia"));

            productos producto = new productos(id, codigo, nombre, precio, existencia);

            productosDAO.actualizar(producto);
            dispatcher = request.getRequestDispatcher("productos/index.jsp");
            ArrayList<productos> listaProductos = productosDAO.listarProductos();
            request.setAttribute("lista", listaProductos);
            
        } else if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));

            productosDAO.eliminar(id);
            dispatcher = request.getRequestDispatcher("productos/index.jsp");
            ArrayList<productos> listaProductos = productosDAO.listarProductos();
            request.setAttribute("lista", listaProductos);
        }else{
            dispatcher = request.getRequestDispatcher("productos/index.jsp");
            ArrayList<productos> listaProductos = productosDAO.listarProductos();
            request.setAttribute("lista", listaProductos);
        }
        dispatcher.forward(request, response);
}

    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
        public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
