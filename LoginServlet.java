/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import connection.ConnectionPool;
import connection.dbdata;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author praveen
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

   Connection con = null;
    java.util.Date today = new java.util.Date();
    String dblocation = dbdata.getdblocation();
    String dbUser = dbdata.getdbuser();
    String dbPass = dbdata.getdbpassword();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out1 = response.getWriter();
         response.addHeader("Access-Control-Allow-Methods", "POST, GET"); // also added header to allow POST, GET method to be available
        response.addHeader("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain

        try {
            ConnectionPool connectionPool = new ConnectionPool(
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://" + dblocation + "", "" + dbUser + "", "" + dbPass + "",
                    1, 50, true);

            con = connectionPool.getConnection();
            HttpSession session = request.getSession();
            String action = request.getParameter("Action");
            // out1.print(action);
            if (null != action) {
                switch (action) {
                    case "checklogin": {
                        String Username = request.getParameter("Email");
                        String Password = request.getParameter("Password");

                        try {
                            // PreparedStatement ps = con.prepareStatement("Select user_id,user_name,password,Role from users where user_name=? and password=? ");
                            PreparedStatement ps = con.prepareStatement("Select id,FirstName,"
                                    + "SUBSTRING(LastName, 1, 1) as  LastName,Role,Password "
                                    + " from usermaster  where phone_no=? and Password=?  ");
                            ps.setString(1, Username);
                            ps.setString(2, Password);
                            ResultSet rs = ps.executeQuery();
                            if (rs.next()) {
                                out1.print("Logged in Successfully" + " -- " + rs.getString("id")
                                        + " -- " + rs.getString("FirstName") + " " + rs.getString("LastName")
                                        + " -- " + rs.getString("Role") );

                            } else {
                                out1.print("Invalid Username or Password");
                            }
                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "getData": {
                        String usermasterid = request.getParameter("usermasterid");
                        try {
                            String Sql = "select um.id as id,um.EmpCode as EmpCode,UserName,UserEmail,"
                                    + "em.Branch as Branch,ifnull(rm.Region,'') as Zone,role.RoleId as Role,Password from usermaster um "
                                    + " left join employeemaster em on(um.EmpCode = em.EmpCode) "
                                    + " left join regionmaster rm on (rm.id = um.ZoneIncharge) "
                                    + " left join rolemaster role on(role.id = um.Role) where um.EmpCode='" + usermasterid + "' ";
                            //  out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                out1.print(rs.getString("EmpCode") + " -- " + rs.getString("UserName") + " -- " + rs.getString("UserEmail")
                                        + " -- " + rs.getString("Branch") + " -- " + rs.getString("Zone") + " -- " + rs.getString("Role")
                                        + " -- " + rs.getString("Password") + " -- " + rs.getString("id"));
                            }
                            rs.close();
                            ps.close();
                        } catch (Exception e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "getMasters": {
                        PrintWriter out = response.getWriter();
                        String mainmasterid = request.getParameter("mainmasterid");
                        try {

                            PreparedStatement ps = con.prepareStatement("SELECT * FROM pagecreation where groupid='" + mainmasterid + "' ");
                            int i = 0;
                            ResultSet rs = ps.executeQuery();
                            String MainDocdescription = "";
                            while (rs.next()) {
                                out.print(" <li class=\"sidebar-item\" > <u><a style=\" color: black\" class=\"sidebar-link waves-effect waves-dark sidebar-link\" aria-expanded=\"false\" "
                                        + " onClick=\"openmaster('" + rs.getString("Docid") + "', '" + rs.getString("tablename") + "', '" + rs.getString("formname") + "', '" + rs.getString("formtype") + "', 'aforms', 'mastersnav')\">\n"
                                        + "                                    <span class=\"hide-menu\">" + rs.getString("Description") + "</span></a></u></li>");

                                MainDocdescription = mainmasterid + " Setup";
                            }

                            out.print(" -- " + MainDocdescription);
                        } catch (SQLException e) {
                            out.print(e);
                        }
                        break;
                    }

                }

            }
            connectionPool.free(con);

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            ConnectionPool connectionPool = new ConnectionPool(
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://" + dblocation + "", "" + dbUser + "", "" + dbPass + "",
                    1, 50, true);

            con = connectionPool.getConnection();
             response.addHeader("Access-Control-Allow-Methods", "POST, GET"); // also added header to allow POST, GET method to be available
        response.addHeader("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain

            String action = request.getParameter("Action");
            //out.print(action);
            if (null != action) {
                switch (action) {

                    case "update_profile": {

                        String Pass = request.getParameter("Pass");

                        String Id = request.getParameter("id");

                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();

                        try {
                            String Sql = "";

                            Sql = "Update usermaster set Password='" + Pass + "' where id=" + Id + "";

                            PreparedStatement ps = con.prepareStatement(Sql);
                            int Success = ps.executeUpdate();
                            if ("0".equals(Id) && Success > 0) {
                                out.print("Saved Successfully");
                            } else {
                                out.print("Updated successfully");
                            }
                            ps.close();
                        } catch (Exception e) {
                            out.print(e);
                        }

                    }
                    break;
                    case "saveExpensefun": {

                        String pagename = request.getParameter("pagename");
                        String EmpName = request.getParameter("EmpName");
                        String expensedate = request.getParameter("expensedate");
                        String AccountHead = request.getParameter("AccountHead");
                        String Amount = request.getParameter("Amount");
                        String wbsno = request.getParameter("wbsno");
                        String serviceno = request.getParameter("serviceno");
                        String servicelineno = request.getParameter("servicelineno");
                        String remarks = request.getParameter("remarks");
                        String narration = request.getParameter("narration");
                        String Id = request.getParameter("Id");

                        String EmpCode = request.getParameter("EmpCode");
                        String Sql = "";
                        String Expenseid = request.getParameter("ExpenseId");
                        // out.print(Expenseid + " - " + EmpName + " - " + zoneid + " - " + CostCenterNo + " - " + FromDate + " - " + ToDate + " - " + AccountHead + " - " + Amount + " - " + wbsno + " - " + serviceno + " - " + servicelineno + " - " + remarks + " - " + narration + " - " + Branch);
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        try {
                            // String Transid = createtestid();

                            if (Id.equals("0")) {

                                Sql = "INSERT INTO expense_entry "
                                        + "(ExpenseId,Date,EmployeeCode,AccountHead,FromDate,ToDate,AccountHead,Amount,WBSNo,ServiceNo,ServiceLineItemNo,Remarks,Narration,EnteredBy,EnteredDate) VALUES "
                                        + "('" + Expenseid + "','" + expensedate + "','" + EmpCode + "','" + AccountHead + "', '" + Amount + "','" + wbsno + "','" + serviceno + "','" + servicelineno + "',"
                                        + "'" + remarks + "','" + narration + "','" + EmpCode + "','" + DbEnteredDate + "')";

                            } else {
                                Sql = "UPDATE expense_entry set Date='" + expensedate + "',EmployeeCode='" + EmpCode + "',AccountHead='" + AccountHead + "',Amount='" + Amount + "',"
                                        + "WBSNo='" + wbsno + "',ServiceNo='" + serviceno + "',ServiceLineItemNo='" + servicelineno + "',Remarks='" + remarks + "',"
                                        + "Narration='" + narration + "',UpdatedBy='" + EmpCode + "',UpdatedDate='" + DbEnteredDate + "' ";
                            }
                            PreparedStatement ps = con.prepareStatement(Sql);

                            int Success = ps.executeUpdate();
                            //out.print(Sql);
                            if (Success > 0) {
                                out.print("Successfully Entered!!");
                            } else {
                                out.print("Successfully Updated!!");
                            }
                            PreparedStatement ps1 = con.prepareStatement(Sql);
                            out.print(Sql);

                        } catch (SQLException e) {
                            out.print(e);

                        }
                        break;
                    }
                    case "CreateId": {
                        String EmpName = request.getParameter("EmpName");
                        String zoneid = request.getParameter("zoneid");
                        String CostCenterNo = request.getParameter("CostCenterNo");
                        String Sql = "";
                        try {
                            String Expenseid = createexid();

                            //out.print(Transid);
                            Sql = "INSERT INTO expense_entry "
                                    + "(ExpenseId,EmpName,CostCenterNo,Zone) VALUES "
                                    + "('" + Expenseid + "','" + EmpName + "','" + zoneid + "','" + CostCenterNo + "' ";

                            PreparedStatement ps = con.prepareStatement(Sql);
                            int success = ps.executeUpdate();
                            if (success > 1) {
                                out.print("Expense Id created");
                            } else {
                                out.print("Expense Id not created");
                            }

                        } catch (SQLException e) {
                            out.print(e);

                        }
                        break;
                    }

                }

            }
            connectionPool.free(con);

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public String createexid() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String newTestcode = "";
        try {
            String Sql = "Select ExpenseId from usermaster order by id desc LIMIT 1";

            pst = con.prepareStatement(Sql);
            System.out.print(Sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String Testcode = rs.getString("ExpenseId");
                String[] part = Testcode.split("(?<=\\D)(?=\\d)");

                int count = Integer.parseInt(part[1]) + 1;
                String digtCount = String.format("%07d", count);
                newTestcode = part[0] + digtCount;
            } else {
                newTestcode = "E0000001";
            }
        } catch (NumberFormatException | SQLException e) {
            newTestcode = e.getMessage();

        }
        return newTestcode;
    }

}
