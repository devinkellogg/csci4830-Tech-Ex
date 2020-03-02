
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/insert")
public class OutageInsert extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public OutageInsert() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String device = request.getParameter("device");
      String outageStatus = request.getParameter("outageStatus");
      String description = request.getParameter("description");

      Connection connection = null;
      String insertSql = " INSERT INTO TechTableKellogg (id, DEVICE, OUTAGESTATUS, DESCRIPTION) values (default, ?, ?, ?)";

      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, device);
         preparedStmt.setString(2, outageStatus);
         preparedStmt.setString(3, description);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Device</b>: " + device + "\n" + //
            "  <li><b>Outage Status</b>: " + outageStatus + "\n" + //
            "  <li><b>Description</b>: " + description + "\n" + //

            "</ul>\n");

      out.println("<a href=/webproject-tech-ex-Kellogg/outage_insert.html>Insert Outage</a> <br>");
      out.println("<a href=/webproject-tech-ex-Kellogg/outage_search.html>Search Outage</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
