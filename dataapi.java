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
@WebServlet(name = "dataapi", urlPatterns = {"/dataapi"})
public class dataapi extends HttpServlet {

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
            //   out1.print("getparttblbatchwisegetparttblbatchwise");
            if (null != action) {
                switch (action) {
                    case "getdailyreportcheck": {

                        String entrydate = request.getParameter("entrydate");
                        java.util.Date today = new java.util.Date();
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        String Sql = "";

                        Sql = Sql + " select id from dailyclosedata "
                                + "where  entrydate='" + entrydate + "'";

                        try {

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;

                            if (rs.next()) {
                                i++;
                                out1.print("1");

                            } else {
                                out1.print("0");
                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "gettank": {
                        String maintanktype = request.getParameter("maintanktype");

                        try {
                            String Sql = "select tanktype,p1,p2,p3,p4,t1,t2,t3,t4 from tanktype ";

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            out1.print("  <option value=''>-PUMP-</option> ");

                            while (rs.next()) {
                                i++;
                                out1.print("  <option "
                                        + "value='" + rs.getString(1) + " || " + rs.getString(2) + " -- " + rs.getString(3) + " -- " + rs.getString(4) + " -- " + rs.getString(5)
                                        + " || " + rs.getString(6) + " -- " + rs.getString(7) + " -- " + rs.getString(8) + " -- " + rs.getString(9) + " '>" + rs.getString(1) + "</option> ");

                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "getdensityat15": {
                        String hydrometerreading = request.getParameter("hydrometerreading");
                        String temperature = request.getParameter("temperature");

                        try {
                            String Sql = "select  * from densitychart1 where temperature=" + temperature + " ";

                            System.out.println(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;

                            if (rs.next()) {
                                i++;
                                out1.print(rs.getString(hydrometerreading));

                            } else {
                                out1.print("0");
                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "getbaseattank": {
                        String tankid = request.getParameter("tankid");

                        try {
                            String Sql = "select  base from tankreceivingentry where tankid=" + tankid + " order by id desc limit 1 ";

                            System.out.println(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;

                            if (rs.next()) {
                                i++;
                                out1.print(rs.getString("base"));

                            } else {
                                out1.print("0");
                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "getactualstockfromdip": {
                        String dipdora = request.getParameter("dipdora");
                        String tankdip = request.getParameter("tankdip");
                        String chartj = request.getParameter("chartj");
                        try {
                            String Sql = "SELECT * FROM `tankchart`  where chartname='" + chartj + "' and dip=" + tankdip + " ";

                            System.out.println(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;

                            if (rs.next()) {
                                i++;
                                System.out.println(rs.getDouble("litres") + "  " + dipdora + "  " + rs.getDouble("dipdora"));
                                Double dipval = rs.getDouble("litres") + (rs.getDouble("dipdora") * (Double.valueOf(dipdora) / 10));
                                out1.print(dipval);

                            } else {
                                out1.print("0");
                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;

                    case "getchart": {
                        String entryid = request.getParameter("entryid");
                        try {
                            String Sql = "SELECT id,tankname,pm.product,pm.id productid,availablestock,chart FROM "
                                    + "`tankmaster` where id =(select tankid from  tankdipentry where id=" + entryid + ") ";

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;

                            while (rs.next()) {
                                i++;
                                out1.print(rs.getString("chart"));

                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "getmaintank": {
                        String maintanktype = request.getParameter("maintanktype");

                        try {
                            String Sql = "SELECT tm.id tankid,tankname,pm.product,pm.id productid,availablestock,chart FROM "
                                    + "`tankmaster` tm left join (select product,id from productmaster) pm on (pm.id=tm.product)"
                                    + "left join (select availablestock,tankid  as stankid from tankcurrentstock) cs on (cs.stankid=tm.id) ";

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            out1.print("  <option value=''>-TANK-</option> ");

                            while (rs.next()) {
                                i++;
                                out1.print("  <option "
                                        + "value='" + rs.getString("tankid") + " -- " + rs.getString("tankname") + " -- " + rs.getString("product") + " -- " + rs.getString("productid") + " -- " + rs.getString("availablestock")
                                        + " -- " + rs.getString("chart") + "'>" + rs.getString("tankname") + "</option> ");

                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;

                    case "getproduct": {
                        String maintanktype = request.getParameter("maintanktype");

                        try {
                            String Sql = "select product,id from productmaster ";

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            out1.print("  <option value=''>-PRODUCT-</option> ");

                            while (rs.next()) {
                                i++;
                                out1.print("  <option "
                                        + "value='" + rs.getString(2) + "'>" + rs.getString(1) + "</option> ");

                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "gettankmaster": {
                        String maintanktype = request.getParameter("maintanktype");

                        try {
                            String Sql = "select tankname,id from tankmaster ";

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            out1.print("  <option value=''>-Tank-</option> ");

                            while (rs.next()) {
                                i++;
                                out1.print("  <option "
                                        + "value='" + rs.getString(2) + "'>" + rs.getString(1) + "</option> ");

                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "getoil": {
                        String maintanktype = request.getParameter("maintanktype");

                        try {
                            String Sql = "select lubricantname,rate from lubricantmaster order by lubricantname asc";

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;

                            while (rs.next()) {
                                i++;
                                out1.print("  <li "
                                        + "onclick=\"getdata('" + rs.getString(1) + "','" + rs.getString(2) + "')\">" + rs.getString(1) + "</li> ");

                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "getparty": {
                        String maintanktype = request.getParameter("maintanktype");

                        try {
                            String Sql = "select partyname from partymaster order by partyname asc";

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;

                            while (rs.next()) {
                                i++;
                                out1.print("  <li "
                                        + "onclick=\"getdata('" + rs.getString(1) + "')\">" + rs.getString(1) + "</li> ");

                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "getop": {
                        String pump = request.getParameter("pump");

                        try {
                            String Sql = "SELECT tanktype, round(op1) op1, round(op2) op2, round(op3) op3, round(op4) op4 \n"
                                    + "FROM (\n"
                                    + "\n"
                                    + "SELECT d.tanktype, ifnull( op.op1, d.label1 ) op1, ifnull( op.op2, d.label2 ) op2,"
                                    + "ifnull( op.op3, d.label3 ) op3, ifnull( op.op4, d.label4 ) op4\n"
                                    + "FROM dataentrytable d\n"
                                    + "LEFT JOIN (\n"
                                    + "\n"
                                    + "SELECT tanktype, op1, op2, op3, op4\n"
                                    + "FROM openingvaluemaster\n"
                                    + "WHERE forcepull = 'Yes'\n"
                                    + ")op ON ( op.tanktype = d.tanktype )\n"
                                    + "WHERE d.tanktype = '" + pump + "'\n"
                                    + "UNION ALL\n"
                                    + "SELECT tanktype, op1, op2, op3, op4\n"
                                    + "FROM openingvaluemaster\n"
                                    + "WHERE forcepull = 'Yes'\n"
                                    + "AND tanktype = '" + pump + "'\n"
                                    + ") AS a\n"
                                    + "LIMIT 1 ";

                            //out.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;

                            while (rs.next()) {
                                i++;
                                out1.print(rs.getString(2) + " -- " + rs.getString(3) + " -- " + rs.getString(4) + " -- " + rs.getString(5));

                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "getrate": {
                        String product = request.getParameter("product");
                        String[] productdata = product.split(" ");
                        java.util.Date today = new java.util.Date();
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        String Sql = "";

                        for (int i = 0; i < productdata.length; i++) {
                            Sql = Sql + " select rate from todaysrate tr inner join (select max(effectedfrom) effectedfrom,product from todaysrate where product='" + productdata[i] + "' and  effectedfrom< '" + DbEnteredDate + "' ) tr1 "
                                    + " on tr1.product=tr1.product and tr.effectedfrom=tr1.effectedfrom where tr.product='" + productdata[i] + "'"
                                    + "";
                            if (i == (productdata.length - 1)) {

                            } else {
                                Sql = Sql + " union all";
                            }

                        }
                        try {

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;

                            while (rs.next()) {
                                i++;
                                out1.print(rs.getString(1) + " -- ");

                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "getoildebit": {
                        String shifttype = request.getParameter("shifttype");
                        String pump = request.getParameter("pump");
                        String entrydate = request.getParameter("entrydate");
                        java.util.Date today = new java.util.Date();
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        String Sql = "";

                        Sql = Sql + " select ifnull(round(sum(rate*qty)),0) as oil,'oil' dataf from oildata  "
                                + "where pump='" + pump + "' and shifttype='" + shifttype + "' and entrydate='" + entrydate + "' "
                                + " union all  select ifnull(round(sum(amount)),0) as oil ,'debit' dataf from debitdata  "
                                + "where pump='" + pump + "' and shifttype='" + shifttype + "' and entrydate='" + entrydate + "' "
                                + "  union all  select ifnull(round(sum(cash+digitalamt)),0) as oil ,'jama' dataf from jummadata  "
                                + "where pump='" + pump + "' and shifttype='" + shifttype + "' and entrydate='" + entrydate + "'";

                        try {

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;

                            while (rs.next()) {
                                i++;
                                out1.print(rs.getString(1) + " -- " + rs.getString(2) + " || ");

                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "Dailyreport": {
                        String entrydate = request.getParameter("entrydate");
                        String reportsgeneratd = request.getParameter("reportsgeneratd");
                        Double total = 0.0;
                        Double totalwithgst = 0.0;
                        out1.print("<div  id='printcontent'>        <div class=\"d-flex flex-row justify-content-between\">\n"
                                + "                                            <h4 class=\"card-title mb-1\">DAILY REPORT  </h4></div>");
                        out1.print(" <div class='row' >  <div class=\"col-lg-7\" >\n");
                        StringBuilder table1str = new StringBuilder();
                        String table2str = "";
                        table1str.append("  <table class=\"table table-dark table-bordered \" style=\" width: 100%\"><thead><tr><th >PRODUCT</th>"
                                + "<th>CASH IN LTRS</th>"
                                + "<th>DEBIT IN LTRS</th>"
                                + "<th>TOTAL IN LTRS</th>"
                                + "<th>RS</th></tr></thead> <tbody>");
                        try {
                            String Sql = "select product,cashinltr,debitinltr,totalinltr,cash,debit,total,totalsale,testing,jama,cmout,cashsale,"
                                    + "paytm,icici,ioc,expense,partydebit,ioc,icici,paytm,cashinhandprevday,"
                                    + "cashinhandcurrent,bankdeposit,cashinhandleft"
                                    + " from productwisedailysummaryreport "
                                    + " where entrydate='" + entrydate + "'";
                            System.out.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;

                                table1str.append(" <tr>"
                                        + "<td >" + rs.getString("product") + "</td>"
                                        + "<td>" + rs.getString("cashinltr") + "</td>"
                                        + "<td>" + rs.getString("debitinltr") + "</td>"
                                        + "<td>" + rs.getString("totalinltr") + "</td>"
                                        + "<td>" + rs.getString("total") + "</td>"
                                        + "</tr>\n");

                                table2str = " <div class=\"col-lg-5\" >\n"
                                        + "\n"
                                        + "                    <table class=\"table table-dark table-bordered \"  style=\" width: 100%\">\n"
                                        + "\n"
                                        + "                        <tbody>\n"
                                        + "<tr><td>" + rs.getString("totalsale") + "</td><td><b>TOTAL SALE</b></td></tr>"
                                        + "<tr><td>" + rs.getString("testing") + "</td><td><b>TESTING</b></td></tr>"
                                        + "<tr><td>" + rs.getString("jama") + "</td><td><b>CREDIT/JAMA</b></td></tr>"
                                        + "<tr><td>" + rs.getString("cmout") + "</td><td><b>C M - OUT</b></td></tr>"
                                        + "<tr><td>" + rs.getString("cashsale") + "</td><td><b>CASH SALE</b></td></tr>"
                                        + "<tr><td>" + rs.getString("expense") + "</td><td><b>EXPENSE</b></td></tr>"
                                        + "<tr><td>" + rs.getString("partydebit") + "</td><td><b>PARTY DEBIT SALE</b></td></tr>"
                                        + "<tr><td>" + rs.getString("ioc") + "</td><td><b>IOC SALE</b></td></tr>"
                                        + "<tr><td>" + rs.getString("icici") + "</td><td><b>ICICI SALE</b></td></tr>"
                                        + "<tr><td>" + rs.getString("paytm") + "</td><td><b>PAYTM SALE</b></td></tr>"
                                        + "                        </tbody>\n"
                                        + "                    </table>     <br><br>   "
                                        + " <table class=\"table table-dark table-bordered \"  style=\" width: 100%\"> "
                                        + "                        <tbody>\n"
                                        + "<tr><td>" + rs.getString("cashinhandprevday") + "</td><td><b> CASH IN HAND- PREVIOUS DAY</b></td></tr>"
                                        + "<tr><td  id='cashinhand'>" + rs.getString("cashinhandcurrent") + "</td><td><b> TOTAL CASH IN HAND RIGHT NOW</b></td></tr>"
                                        + "<tr><td><input id='bankdeposit' type='text' class='form-control' value='" + rs.getString("bankdeposit") + "' onkeyup=\"calculatedcashleft()\"></td><td><b>BANK DEPOSIT</b></td></tr>"
                                        + "<tr><td id='cashleft'>" + rs.getString("cashinhandleft") + "</td><td><b> CASH IN HAND LEFT TODAY</b></td></tr>"
                                        + "                        </tbody>\n"
                                        + "                    </table> "
                                        + "  </div> ";

                            }
                            out1.print(table1str + "</tbody></table></div>" + table2str + "</div></div>");
                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "Entrydetailslist": {
                        String entryid = request.getParameter("entryid");

                        Double total = 0.0;
                        Double totalwithgst = 0.0;
                        try {
                            String Sql = "select entryStatus,dataentrytype,shift,op1,op2,op3,op4,label1 as label1act,label2 as label2act,label3 as label3act,label4 as label4act,tanktype,(label1-op1)  label1,(label2-op2) label2,(label3-op3)  label3,"
                                    + "(label4-op4) as label4,((label1-op1)*rate1) as label1val,rate1,rate2,rate3,rate4,jama,"
                                    + "((label2-op2)*rate2) as label2val,((label3-op3)*rate3) as label3val,((label4-op4)*rate4) as label4val,"
                                    + "(((label1-op1)*rate1)+((label2-op2)*rate2)+((label3-op3)*rate3)+((label4-op4)*rate4)+oil+jama) ptotal,oil,"
                                    + "rate1,rate2,rate3,rate4,debit,paytm,icici,ioc,expense,500s,200s,100s,50s,20s,"
                                    + "(500s*500) fivehun,(200s*200) twohun,"
                                    + "(100s*100) hun,(50s*50) fiftys,(20s*20) twentys,(10s*10) tens,forwardingcash,entrydate, DATE_FORMAT(entrydate,'%d-%m-%Y') formatedentrydate,enteredby,entereddate,"
                                    + "20s,10s,amtchange,debit+paytm+icici+ioc+expense+(500s*500)+(200s*200)+(100s*100)+(50s*50)+(20s*20)+(10s*10)+amtchange+forwardingcash as total"
                                    + " from dataentrytable "
                                    + " where id=" + entryid + "";
                            System.out.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                String dataentrytype = rs.getString("dataentrytype");
                                String[] splitdata = rs.getString("tanktype").split(" ");

                                out1.print("    <div  id='printcontent'>   <div class=\"d-flex flex-row justify-content-between\">\n"
                                        + "                                            <h4 class=\"card-title mb-1\">Nozzle-" + rs.getString("tanktype") + "   </h4>");

                                if (rs.getString("entryStatus").equals("Matching")) {
                                    out1.print("                                            <p class=\"badge badge-primary mb-1\">" + rs.getString("entryStatus") + "</p>\n"
                                            + "                                       ");
                                } else {
                                    out1.print("                                            <p class=\"badge badge-danger mb-1\">" + rs.getString("entryStatus") + "</p>\n"
                                            + "                                        ");
                                    out1.print("<span>Shift-<u>" + rs.getString("shift") + "</u>  Date-<u>" + rs.getString("formatedentrydate") + "</u></span> <button class='btn btn-secondary' onclick='printDiv()'>Print</button></div>\n");
                                }
                                out1.print(" <div class='row mt-3'  >");

                                if (splitdata.length == 2) {
                                    out1.print("   <div class=\"col-lg-6 mr-2\" >\n"
                                            + "       <div class=\"row\" >             <table class=\"table table-dark table-bordered \">\n"
                                            + "                        <thead><tr><th>Pump</th><th>" + splitdata[0] + "</th><th>" + splitdata[1] + "</th><tr></thead>\n"
                                            + "                        <tbody><tr><td>Opening</td><td >" + rs.getString("op1") + "</td><td>" + rs.getString("op1") + "</td></tr>"
                                            + "<tr><td>Closing</td><td >" + rs.getString("label1act") + "</td><td>" + rs.getString("label2act") + "</td></tr>"
                                            + "<td>Difference</td><td >" + rs.getString("label1") + "</td><td>" + rs.getString("label2") + "</td></tr></tbody>\n"
                                            + "                    </table></div>\n");
                                } else {
                                    out1.print("   <div class=\"col-lg-6 mr-2\" >\n"
                                            + "           <div class=\"row\" >          <table class=\"table table-dark table-bordered \">\n"
                                            + "                        <thead><tr><th>Pump</th><th>" + splitdata[0] + "</th><th>" + splitdata[1] + "</th><th>" + splitdata[2] + "</th><th>" + splitdata[3] + "</th><tr></thead>\n"
                                            + "                        <tbody><tr><td>Opening</td><td >" + rs.getString("op1") + "</td><td>" + rs.getString("op2") + "</td><td>" + rs.getString("op3") + "</td><td>" + rs.getString("op4") + "</td></tr>"
                                            + "<tr><td>Closing</td><td >" + rs.getString("label1act") + "</td><td>" + rs.getString("label2act") + "</td><td>" + rs.getString("label3act") + "</td><td>" + rs.getString("label4act") + "</td></tr>"
                                            + "<td>Difference</td><td >" + rs.getString("label1") + "</td><td>" + rs.getString("label2") + "</td><td>" + rs.getString("label3") + "</td><td>" + rs.getString("label4") + "</td></tr></tbody>\n"
                                            + "                    </table></div>\n");
                                }
                                if (splitdata.length == 2) {

                                    out1.print("       <div class=\"row mt-3\" >                                            <table class=\"table table-dark table-bordered \" style=\" width: 100%\">\n"
                                            + "\n"
                                            + "                                                    <tbody>\n"
                                            + "                                                        <tr><td >" + splitdata[0] + "</td><td>" + rs.getString("label1") + "</td><td>X " + rs.getString("rate1") + "</td><td>" + rs.getString("label1val") + "</td></tr>\n"
                                            + "                                                        <tr><td >" + splitdata[1] + "</td><td>" + rs.getString("label2") + "</td><td>X " + rs.getString("rate2") + "</td><td>" + rs.getString("label2val") + "</td></tr>\n"
                                            + "                                                        <tr><td ><a href='#' onclick=\"opendatamodal('jama')\"><u>Jamma</u></a></td><td></td><td></td><td>" + rs.getString("jama") + "</td></tr>\n"
                                            + "                                                        <tr><td ><a href='#' onclick=\"opendatamodal('oil')\"><u>Oil</u></a></td><td></td><td></td><td>" + rs.getString("oil") + "</td></tr>\n"
                                            + "                                                    </tbody>\n"
                                            + "                                                    <tfoot>\n"
                                            + "                                                        <tr><td ></td><td></td><td></td><td>" + rs.getString("ptotal") + "</td></tr>\n"
                                            + "\n"
                                            + "                                                    </tfoot>\n"
                                            + "                                                </table>\n"
                                            + "\n"
                                            + "                                            </div></div>");

                                } else {
                                    out1.print("  <div class=\"row\" >   "
                                            + "                                                <table class=\"table table-dark table-bordered \" style=\" width: 100%\">\n"
                                            + "\n"
                                            + "                                                    <tbody>\n"
                                            + "  <tr><td >" + splitdata[0] + "</td><td>" + rs.getString("label1") + "</td><td>X " + rs.getString("rate1") + "</td><td>" + rs.getString("label1val") + "</td></tr>\n"
                                            + " <tr><td >" + splitdata[1] + "</td><td>" + rs.getString("label2") + "</td><td>X " + rs.getString("rate2") + "</td><td>" + rs.getString("label2val") + "</td></tr>\n"
                                            + "  <tr><td >" + splitdata[2] + "</td><td>" + rs.getString("label3") + "</td><td>X " + rs.getString("rate3") + "</td><td>" + rs.getString("label3val") + "</td></tr>\n"
                                            + " <tr><td >" + splitdata[3] + "</td><td>" + rs.getString("label4") + "</td><td>X " + rs.getString("rate4") + "</td><td>" + rs.getString("label4val") + "</td></tr>\n"
                                            + "<tr><td ><a href='#' onclick=\"opendatamodal('jama')\"><u>Jamma</u></a></td><td></td><td></td><td>" + rs.getString("jama") + "</td></tr>\n"
                                            + " <tr><td ><a href='#' onclick=\"opendatamodal('oil')\"><u>Oil</u></a></td><td></td><td></td><td>" + rs.getString("oil") + "</td></tr>\n"
                                            + "                                                    </tbody>\n"
                                            + "                                                    <tfoot>\n"
                                            + "                                                        <tr><td ></td><td></td><td></td><td>" + rs.getString("ptotal") + "</td></tr>\n"
                                            + "\n"
                                            + "                                                    </tfoot>\n"
                                            + "                                                </table>\n"
                                            + "\n"
                                            + "                                            </div></div>");
                                }

                                out1.print("<div class=\"col-lg-6 ml-2\" > <div class=\"row\" > \n"
                                        + "\n"
                                        + "                    <table class=\"table table-dark table-bordered \"  style=\" width: 100%\">\n"
                                        + "\n"
                                        + "                        <tbody>\n"
                                        + "                            <tr><td ><a href='#' onclick=\"opendatamodal('debit')\"><u>Debit</u></a></td><td></td><td>" + rs.getString("debit") + "</td></tr>\n"
                                        + "                            <tr><td >Paytm</td><td></td><td>" + rs.getString("paytm") + "</td></tr>\n"
                                        + "                            <tr><td >ICICI</td><td></td><td>" + rs.getString("icici") + "</td></tr>\n"
                                        + "                            <tr><td >IOC</td><td></td><td>" + rs.getString("ioc") + "</td></tr>\n"
                                        + "                            <tr><td >Expense</td><td></td><td>" + rs.getString("expense") + "</td></tr>\n"
                                        + "                            <tr><td >500 X</td><td>" + rs.getString("500s") + "</td><td>" + rs.getString("fivehun") + "</td></tr>\n"
                                        + "                            <tr><td >200 X</td><td>" + rs.getString("200s") + "</td><td>" + rs.getString("twohun") + "</td></tr>\n"
                                        + "                            <tr><td >100 X</td><td>" + rs.getString("100s") + "</td><td>" + rs.getString("hun") + "</td></tr>\n"
                                        + "                            <tr><td >50 X</td><td>" + rs.getString("50s") + "</td><td>" + rs.getString("fiftys") + "</td></tr>\n"
                                        + "                            <tr><td >20 X</td><td>" + rs.getString("20s") + "</td><td>" + rs.getString("twentys") + "</td></tr>\n"
                                        + "                            <tr><td >10 X</td><td>" + rs.getString("10s") + "</td><td>" + rs.getString("tens") + "</td></tr>\n"
                                        + "                            <tr><td >Change</td><td></td><td>" + rs.getString("amtchange") + "</td></tr>\n"
                                        + "                            <tr><td >Forwarding Cash</td><td></td><td>" + rs.getString("forwardingcash") + "</td></tr>\n"
                                        + "\n"
                                        + "                        </tbody>\n"
                                        + "                        <tfoot>  <tr><th ></th><th >Total</th><th>" + rs.getString("total") + "</th></tr> </tfoot>\n"
                                        + "                    </table>\n"
                                        + "              \n"
                                        + "\n"
                                        + "                </div> </div></div>");

                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "Entrylist": {
                        String status = request.getParameter("status");
                        String entrydate = request.getParameter("entrydate");
                        try {
                            out1.print("   <table class=\"table table-dark table-bordered dataTables-example\" >\n"
                                    + "                                                <thead>\n"
                                    + "                                                    <tr>\n"
                                    + "\n"
                                    + "\n"
                                    + "                                                        <th> S.NO </th>\n"
                                    + "                                                        <th> Status </th>\n"
                                    + "                                                        <th> Nozzle </th>\n"
                                    + "                                                        <th> Shift </th>\n"
                                    + "                                                        <th> Entry Type </th>\n"
                                    + "                                                        <th> Entered Details </th>\n"
                                    + "                                                        <th> Action </th>\n"
                                    + "\n"
                                    + "                                                    </tr>\n"
                                    + "                                                </thead>\n"
                                    + "                                                <tbody>");
                            String Sql = "select de.id,dataentrytype,shift,tanktype,entrystatus,entrydate,enteredby,DATE_FORMAT(entereddate,'%d-%m-%Y %T') entereddate,firstname "
                                    + "from dataentrytable de "
                                    + "Left join (select id,firstname from usermaster)um on "
                                    + " (um.id=de.enteredby) where entrydate like '%" + entrydate + "%' and entrystatus like '%" + status + "%'";

                            //out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                if ("Not Matching".equals(rs.getString("entrystatus").trim())) {
                                    out1.print("  <tr>\n"
                                            + " <td>" + i + "</td>\n"
                                            + "  <td><span class='badge badge-danger'>" + rs.getString("entrystatus") + " </span></td>\n");
                                } else {
                                    out1.print("  <tr>\n"
                                            + " <td>" + i + "</td>\n"
                                            + "  <td><span class='badge badge-primary'>" + rs.getString("entrystatus") + " </span> </td>\n");
                                }

                                out1.print("  <td>" + rs.getString("tanktype") + " </td>\n"
                                        + "  <td>" + rs.getString("shift") + " </td>\n"
                                        + "  <td>" + rs.getString("dataentrytype") + " </td>\n"
                                        + "  <td>" + rs.getString("firstname") + " || " + rs.getString("entereddate") + " </td>\n"
                                        + "  <td> <a href=\"#\" class=\"btn btn-primary\" onclick=\"openexistingdetails('" + rs.getString("id") + "','" + rs.getString("tanktype") + "','" + rs.getString("entrydate") + "','" + rs.getString("shift") + "')\"> View</a></td>\n"
                                        + "                                                    </tr> ");

                            }
                            out1.print("</tbody></table>");
                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "OilEntrylist": {
                        String nozzle = request.getParameter("nozzle");
                        String entrydate = request.getParameter("entrydate");
                        String shifttype = request.getParameter("shifttype");
                        out1.print("   <table class=\"table table-dark table-bordered dataTables-example\" >\n"
                                + "                                                <thead>\n"
                                + "                                                    <tr>\n"
                                + "\n"
                                + "\n"
                                + "                                                        <th> S.NO </th>\n"
                                + "                                                        <th> Lubricant </th>\n"
                                + "                                                        <th> Quantity </th>\n"
                                + "                                                        <th> Rate </th>\n"
                                + "                                                        <th> Total</th>\n"
                                + "                                                        <th> EntryDetails</th>\n"
                                + "                                                        <th> Action </th>\n"
                                + "\n"
                                + "                                                    </tr>\n"
                                + "                                                </thead>\n"
                                + "                                                <tbody>");
                        try {
                            String Sql = "select pump,shifttype,entrydate,lubricant,rate,qty,rate*qty as total,enteredby,entereddate,id "
                                    + "from oildata  where entrydate='" + entrydate + "' and pump='" + nozzle + "' and shifttype='" + shifttype + "' order by id asc";

                            out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                out1.print("  <tr>\n"
                                        + " <td>" + i + "</td>\n"
                                        + "  <td>" + rs.getString("lubricant") + " </td>\n"
                                        + "  <td>" + rs.getString("qty") + " </td>\n"
                                        + "  <td>" + rs.getString("rate") + " </td>\n"
                                        + "  <td>" + rs.getString("total") + " </td>\n"
                                        + "  <td>" + rs.getString("enteredby") + " || " + rs.getString("entereddate") + " </td>\n"
                                        + "  <td> <a href=\"#\" class=\"btn btn-primary\" "
                                        + "onclick=\"openexistingdetails('" + rs.getString("id") + "','" + rs.getString("pump") + "',"
                                        + "'" + rs.getString("shifttype") + "','" + rs.getString("entrydate") + "','" + rs.getString("lubricant") + "',"
                                        + "'" + rs.getString("rate") + "','" + rs.getString("qty") + "',"
                                        + "'" + rs.getString("total") + "')\"> Edit</a></td>\n"
                                        + "                                                    </tr> ");

                            }
                            out1.print("</tbody></table>");
                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "DebitEntrylist": {
                        String nozzle = request.getParameter("nozzle");
                        String entrydate = request.getParameter("entrydate");
                        String shifttype = request.getParameter("shifttype");
                        out1.print("   <table class=\"table table-dark table-bordered dataTables-example\" >\n"
                                + "                                                <thead>\n"
                                + "                                                    <tr>\n"
                                + "\n"
                                + "\n"
                                + "                                                        <th> S.NO </th>\n"
                                + "                                                        <th> Partyname </th>\n"
                                + "                                                        <th> Product </th>\n"
                                + "                                                        <th> BillNo </th>\n"
                                + "                                                        <th> Amount </th>\n"
                                + "                                                        <th> EntryDetails</th>\n"
                                + "                                                        <th> Action </th>\n"
                                + "\n"
                                + "                                                    </tr>\n"
                                + "                                                </thead>\n"
                                + "                                                <tbody>");
                        try {
                            String Sql = "select pump,shifttype,DATE_FORMAT(entrydate,'%d-%m-%Y') Formatedentrydate,entrydate,partyname,billno,amount,product,enteredby,"
                                    + "entereddate,id from debitdata   where entrydate='" + entrydate + "' and pump='" + nozzle + "' and shifttype='" + shifttype + "' "
                                    + "order by id asc";

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                out1.print("  <tr>\n"
                                        + " <td>" + i + "</td>\n"
                                        + "  <td>" + rs.getString("partyname") + " </td>\n"
                                        + "  <td>" + rs.getString("product") + " </td>\n"
                                        + "  <td>" + rs.getString("billno") + " </td>\n"
                                        + "  <td>" + rs.getString("amount") + " </td>\n"
                                        + "  <td>" + rs.getString("enteredby") + " || " + rs.getString("entereddate") + " </td>\n"
                                        + "  <td> <a href=\"#\" class=\"btn btn-primary\" "
                                        + "onclick=\"openexistingdetails('" + rs.getString("id") + "','" + rs.getString("pump") + "',"
                                        + "'" + rs.getString("shifttype") + "','" + rs.getString("entrydate") + "','" + rs.getString("partyname") + "',"
                                        + "'" + rs.getString("billno") + "','" + rs.getString("amount") + "',"
                                        + "'" + rs.getString("product") + "')\"> Edit</a></td>\n"
                                        + "                                                    </tr> ");

                            }
                            out1.print("</tbody></table>");

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;

                    case "jammaEntrylist": {
                        String nozzle = request.getParameter("nozzle");
                        String entrydate = request.getParameter("entrydate");
                        String shifttype = request.getParameter("shifttype");
                        out1.print("   <table class=\"table table-dark table-bordered dataTables-example\" >\n"
                                + "                                                <thead>\n"
                                + "                                                    <tr>\n"
                                + "\n"
                                + "\n"
                                + "                                                        <th> S.NO </th>\n"
                                + "                                                        <th> Entry Date </th>\n"
                                + "                                                        <th> Chequeno </th>\n"
                                + "                                                        <th> Chequeamount </th>\n"
                                + "                                                        <th> Cash </th>\n"
                                + "                                                        <th> Cash Receiptno</th>\n"
                                + "                                                        <th> Digitalamt </th>\n"
                                + "                                                        <th> EntryDetails</th>\n"
                                + "                                                        <th> Action </th>\n"
                                + "\n"
                                + "                                                    </tr>\n"
                                + "                                                </thead>\n"
                                + "                                                <tbody>");
                        try {
                            String Sql = "select pump,shifttype,DATE_FORMAT(entrydate,'%d-%m-%Y') Formattedentrydate,entrydate,chequeno,chequeamount,cash,cashreceiptno,digitalamt,enteredby,"
                                    + "entereddate,id from jummadata   where entrydate='" + entrydate + "' and pump='" + nozzle + "' and shifttype='" + shifttype + "' "
                                    + "order by id asc";

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                out1.print("  <tr>\n"
                                        + " <td>" + i + "</td>\n"
                                        + "  <td>" + rs.getString("Formattedentrydate") + " </td>\n"
                                        + "  <td>" + rs.getString("chequeno") + " </td>\n"
                                        + "  <td>" + rs.getString("chequeamount") + " </td>\n"
                                        + "  <td>" + rs.getString("cash") + " </td>\n"
                                        + "  <td>" + rs.getString("cashreceiptno") + " </td>\n"
                                        + "  <td>" + rs.getString("digitalamt") + " </td>\n"
                                        + "  <td>" + rs.getString("enteredby") + " || " + rs.getString("entereddate") + " </td>\n"
                                        + "  <td> <a href=\"#\" class=\"btn btn-primary\" "
                                        + "onclick=\"openexistingdetails('" + rs.getString("id") + "','" + rs.getString("pump") + "',"
                                        + "'" + rs.getString("shifttype") + "','" + rs.getString("entrydate") + "','" + rs.getString("chequeno") + "',"
                                        + "'" + rs.getString("chequeamount") + "','" + rs.getString("cash") + "',"
                                        + "'" + rs.getString("cashreceiptno") + "','" + rs.getString("digitalamt") + "')\"> Edit</a></td>\n"
                                        + "                                                    </tr> ");

                            }
                            out1.print("</tbody></table>");

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "densityreport": {
                        String tank = request.getParameter("product");
                        String month = request.getParameter("month");
                        //  String entrydate = request.getParameter("entrydate");
                        // String shifttype = request.getParameter("shifttype");
                        out1.print("   <table class=\"table table-dark table-bordered dataTables-example\" >\n"
                                + "                                                <thead>\n"
                                + "                                                    <tr>\n"
                                + "\n"
                                + "\n"
                                + "                                                        <th> S.NO </th>\n"
                                + "                                                        <th> Date </th>\n"
                                + "                                                        <th> Tank </th>\n"
                                + "                                                        <th> Morning Density </th>\n"
                                + "                                                        <th> Morning Density Temperature </th>\n"
                                + "                                                        <th> Morning Density @15C </th>\n"
                                + "                                                        <th> Billno </th>\n"
                                + "                                                        <th> Litres</th>\n"
                                + "                                                        <th> Receipt Density </th>\n"
                                + "                                                        <th> Receipt Density Temperature</th>\n"
                                + "                                                        <th> Receipt Density @15C</th>\n"
                                + "                                                        <th> Bill Density </th>\n"
                                + "                                                        <th> Diff</th>\n"
                                + "                                                        <th> Closing Density </th>\n"
                                + "                                                        <th> Closing Density Temperature</th>\n"
                                + "                                                        <th> Closing Density @15C</th>\n"
                                + "\n"
                                + "                                                    </tr>\n"
                                + "                                                </thead>\n"
                                + "                                                <tbody>");
                        try {
                            String Sql = "select DATE_FORMAT(entrydate,'%d-%m-%Y') entrydate,tankname,morningdensity,morningdensitytemp,"
                                    + "morningdensityat15,Billno,Litres,receiptdensity,receiptdensitytemp,"
                                    + "receiptdensityat15,Billdensity,Diff,closingdensity,closingdensitytemp,closingdensityat15"
                                    + " from densityregister where tankid like '%" + tank + "%' and  Month(entrydate)=" + month + " "
                                    + "order by id asc";

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                out1.print("  <tr>\n"
                                        + " <td>" + i + "</td>\n"
                                        + "  <td>" + rs.getString("entrydate") + " </td>\n"
                                        + "  <td>" + rs.getString("tankname") + " </td>\n"
                                        + "  <td>" + rs.getString("morningdensity") + " </td>\n"
                                        + "  <td>" + rs.getString("morningdensitytemp") + " </td>\n"
                                        + "  <td>" + rs.getString("morningdensityat15") + " </td>\n"
                                        + "  <td>" + rs.getString("Billno") + " </td>\n"
                                        + "  <td>" + rs.getString("Litres") + " </td>\n"
                                        + "  <td>" + rs.getString("receiptdensity") + " </td>\n"
                                        + "  <td>" + rs.getString("receiptdensitytemp") + " </td>\n"
                                        + "  <td>" + rs.getString("receiptdensityat15") + " </td>\n"
                                        + "  <td>" + rs.getString("Billdensity") + " </td>\n"
                                        + "  <td>" + rs.getString("Diff") + " </td>\n"
                                        + "  <td>" + rs.getString("closingdensity") + " </td>\n"
                                        + "  <td>" + rs.getString("closingdensitytemp") + " </td>\n"
                                        + "  <td>" + rs.getString("closingdensityat15") + " </td>\n"
                                        + "                                                    </tr> ");

                            }
                            out1.print("</tbody></table>");

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "dsrreport": {
                        String product = request.getParameter("product");
                        String month = request.getParameter("month");
                        //  String entrydate = request.getParameter("entrydate");
                        // String shifttype = request.getParameter("shifttype");
                        out1.print("   <table class=\"table table-dark table-bordered dataTables-example\" >\n"
                                + "                                                <thead>\n"
                                + "                                                    <tr>\n"
                                + "\n"
                                + "\n"
                                + "                                                        <th> S.NO </th>\n"
                                + "                                                        <th> Date </th>\n"
                                + "                                                        <th> Product </th>\n"
                                + "                                                        <th>Tank1 Dip </th>\n"
                                + "                                                        <th> Tank1 waterdip </th>\n"
                                + "                                                        <th>Tank2 Dip </th>\n"
                                + "                                                        <th> Tank2 waterdip </th>\n"
                                + "                                                        <th> OpeningStock </th>\n"
                                + "                                                        <th> Receipt</th>\n"
                                + "                                                        <th> Total </th>\n"
                                + "                                                        <th>P1</th>\n"
                                + "                                                        <th>P2</th>\n"
                                + "                                                        <th>P3</th>\n"
                                + "                                                        <th>P4</th>\n"
                                + "                                                        <th>P5</th>\n"
                                + "                                                        <th>P6</th>\n"
                                + "                                                        <th>P7</th>\n"
                                + "                                                        <th>P8</th>\n"
                                + "                                                        <th>P9</th>\n"
                                + "                                                        <th>P10</th>\n"
                                + "                                                        <th>P11</th>\n"
                                + "                                                        <th>P12</th>\n"
                                + "                                                        <th>P13</th>\n"
                                + "                                                        <th>P14</th>\n"
                                + "                                                        <th>P15</th>\n"
                                + "                                                        <th>Testing</th>\n"
                                + "                                                        <th>Sales</th>\n"
                                + "                                                        <th>Cummulative Sales</th>\n"
                                + "\n"
                                + "                                                    </tr>\n"
                                + "                                                </thead>\n"
                                + "                                                <tbody>");
                        try {
                            String Sql = "select DATE_FORMAT(entrydate,'%d-%m-%Y') entrydate,product,tank1dip,"
                                    + "tank1waterdip,tank2dip,tank2waterdip,openingstock,receipt,"
                                    + "total,op_p1,op_p2,op_p3,op_p4,op_p5,op_p6,op_p7,op_p8,op_p9,"
                                    + "op_p10,op_p11,op_p12,op_p13,op_p14,op_p15,testing,sales,cumsales from dsrreport"
                                    + " where productid like '%" + product + "%'  and Month(entrydate)=" + month + "  "
                                    + "order by id asc";

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                out1.print("  <tr>\n"
                                        + " <td>" + i + "</td>\n"
                                        + "  <td>" + rs.getString("entrydate") + " </td>\n"
                                        + "  <td>" + rs.getString("product") + " </td>\n"
                                        + "  <td>" + rs.getString("tank1dip") + " </td>\n"
                                        + "  <td>" + rs.getString("tank1waterdip") + " </td>\n"
                                        + "  <td>" + rs.getString("tank2dip") + " </td>\n"
                                        + "  <td>" + rs.getString("tank2waterdip") + " </td>\n"
                                        + "  <td>" + rs.getString("openingstock") + " </td>\n"
                                        + "  <td>" + rs.getString("receipt") + " </td>\n"
                                        + "  <td>" + rs.getString("total") + " </td>\n"
                                        + "  <td>" + rs.getString("op_p1") + " </td>\n"
                                        + "  <td>" + rs.getString("op_p2") + " </td>\n"
                                        + "  <td>" + rs.getString("op_p3") + " </td>\n"
                                        + "  <td>" + rs.getString("op_p4") + " </td>\n"
                                        + "  <td>" + rs.getString("op_p5") + " </td>\n"
                                        + "  <td>" + rs.getString("op_p6") + " </td>\n"
                                        + "  <td>" + rs.getString("op_p7") + " </td>\n"
                                        + "  <td>" + rs.getString("op_p8") + " </td>\n"
                                        + "  <td>" + rs.getString("op_p9") + " </td>\n"
                                        + "  <td>" + rs.getString("op_p10") + " </td>\n"
                                        + "  <td>" + rs.getString("op_p11") + " </td>\n"
                                        + "  <td>" + rs.getString("op_p12") + " </td>\n"
                                        + "  <td>" + rs.getString("op_p13") + " </td>\n"
                                        + "  <td>" + rs.getString("op_p14") + " </td>\n"
                                        + "  <td>" + rs.getString("op_p15") + " </td>\n"
                                        + "  <td>" + rs.getString("testing") + " </td>\n"
                                        + "  <td>" + rs.getString("sales") + " </td>\n"
                                        + "  <td>" + rs.getString("cumsales") + " </td>\n"
                                        + "                                                    </tr> ");

                            }
                            out1.print("</tbody></table>");

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;

                    case "patrakreport": {
                        String product = request.getParameter("product");
                        String month = request.getParameter("month");
                        //  String entrydate = request.getParameter("entrydate");
                        // String shifttype = request.getParameter("shifttype");
                        out1.print("   <table class=\"table table-dark table-bordered dataTables-example\" >\n"
                                + "                                                <thead>\n"
                                + "                                                    <tr>\n"
                                + "\n"
                                + "\n"
                                + "                                                        <th> S.NO </th>\n"
                                + "                                                        <th> Date </th>\n"
                                + "                                                        <th> Product </th>\n"
                                + "                                                        <th>Opening</th>\n"
                                + "                                                        <th> Receiving</th>\n"
                                + "                                                        <th>Total </th>\n"
                                + "                                                        <th> Sale </th>\n"
                                + "                                                        <th>Closing </th>\n"
                                + "                                                        <th> Remarks</th>\n"
                                + "\n"
                                + "                                                    </tr>\n"
                                + "                                                </thead>\n"
                                + "                                                <tbody>");
                        try {
                            String Sql = "select DATE_FORMAT(entrydate,'%d-%m-%Y') entrydate,productname,opening,receiving,total,sale,closing,remarks"
                                    + " from patrakreport where productid like '%" + product + "%'  and month(entrydate)=" + month + "  "
                                    + "order by id asc";

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                out1.print("  <tr>\n"
                                        + " <td>" + i + "</td>\n"
                                        + "  <td>" + rs.getString("entrydate") + " </td>\n"
                                        + "  <td>" + rs.getString("productname") + " </td>\n"
                                        + "  <td>" + rs.getString("opening") + " </td>\n"
                                        + "  <td>" + rs.getString("receiving") + " </td>\n"
                                        + "  <td>" + rs.getString("total") + " </td>\n"
                                        + "  <td>" + rs.getString("sale") + " </td>\n"
                                        + "  <td>" + rs.getString("closing") + " </td>\n"
                                        + "  <td>" + rs.getString("remarks") + " </td>\n"
                                        + "                                                    </tr> ");

                            }
                            out1.print("</tbody></table>");

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "nozzletypemaster": {
                        String companyid = request.getParameter("companyid");
                        String vendor = request.getParameter("vendor");
                        try {
                            String Sql = "select maintype,tanktype,id from tanktype order by id desc";

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                out1.print("  <a href=\"#\" class=\"item\" >\n"
                                        + "                    <div class=\"detail\">\n"
                                        + "                       \n"
                                        + "                        <div>\n"
                                        + "                            <strong>" + rs.getString("tanktype") + "</strong>\n"
                                        + "<p> <button class='btn btn-sm btn-danger ml-3' onclick=\"commondelete('" + rs.getString("id") + "','tanktype')\">Delete</button></p>\n"
                                        + "                        </div>\n"
                                        + "                    </div>\n"
                                        + "                    <div class=\"right\">\n"
                                        + "                        <div class=\"price text-danger\">  <span class=\"badge badge-success\"> " + rs.getString("maintype") + "</span></div>\n"
                                        + "                    </div>\n"
                                        + "                \n"
                                        + "                    \n"
                                        + "                </a>");

                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "ratemaster": {
                        String companyid = request.getParameter("companyid");
                        String vendor = request.getParameter("vendor");
                        try {
                            String Sql = "select rate,product,id,DATE_FORMAT(effectedfrom, '%m/%d/%Y %H:%i') effectedfrom from todaysrate order by id desc";

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                out1.print("  <a href=\"#\" class=\"item\" >\n"
                                        + "                    <div class=\"detail\">\n"
                                        + "                       \n"
                                        + "                        <div>\n"
                                        + "                           \n"
                                        + "                            <strong>" + rs.getString("product") + "</strong>\n"
                                        + "                             <p>INR- " + rs.getString("rate") + "    <button class='btn btn-sm btn-danger ml-3' onclick=\"commondelete('" + rs.getString("id") + "','todaysrate')\">Delete</button></p>\n"
                                        + "                        </div>\n"
                                        + "                    </div>\n"
                                        + "                    <div class=\"right\">\n"
                                        + "                        <div >   " + rs.getString("effectedfrom") + "</div>\n"
                                        + "                    </div>\n"
                                        + "                \n"
                                        + "                    \n"
                                        + "                </a>");

                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;

                    case "openingvaluemaster": {
                        String companyid = request.getParameter("companyid");
                        String vendor = request.getParameter("vendor");
                        try {
                            String Sql = "select td.tanktype,ifnull(op1,'') op1,ifnull(op2,'') op2,ifnull(op3,'') op3,ifnull(op4,'') op4,"
                                    + "ifnull(forcepull,'NA') forcepull,ifnull(op.id,'0') id  from tanktype  td left join "
                                    + " (select tanktype,op1,op2,op3,op4,forcepull,id from openingvaluemaster) op on (op.tanktype=td.tanktype) "
                                    + " group by tanktype";

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                out1.print("  <a href=\"#\" class=\"item\" >\n"
                                        + "                    <div class=\"detail\">\n"
                                        + "                       \n"
                                        + "                        <div>\n"
                                        + "                           \n"
                                        + "                            <strong>" + rs.getString("tanktype") + "</strong>\n"
                                        + "                             <p>O/p Val- " + rs.getString("op1") + " " + rs.getString("op2") + " " + rs.getString("op3") + " " + rs.getString("op4") + "    <button class='btn btn-sm btn-primary ml-3' "
                                        + "onclick=\"openexistingdetails('" + rs.getString("id") + "','" + rs.getString("tanktype") + "','" + rs.getString("op1") + "','" + rs.getString("op2") + "','" + rs.getString("op3") + "','" + rs.getString("op4") + "','" + rs.getString("forcepull") + "')\">Edit</button></p>\n"
                                        + "                        </div>\n"
                                        + "                    </div>\n"
                                        + "                    <div class=\"right\">\n"
                                        + "                        <div class=\"price text-danger\">  <span class=\"badge badge-success\"> FP-" + rs.getString("forcepull") + "</span></div>\n"
                                        + "                    </div>\n"
                                        + "                \n"
                                        + "                    \n"
                                        + "                </a>");

                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "usermaster": {
                        String companyid = request.getParameter("companyid");
                        String vendor = request.getParameter("vendor");
                        try {
                            String Sql = "select concat_ws (' ',firstname,lastname) as name,phone_No,id,role from usermaster order by id desc";

                            // out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                out1.print("  <a href=\"#\" class=\"item\" >\n"
                                        + "                    <div class=\"detail\">\n"
                                        + "                       \n"
                                        + "                        <div>\n"
                                        + "                            <strong>" + rs.getString("name") + "</strong>\n"
                                        + "                             <p> " + rs.getString("phone_No") + "<button class='btn btn-sm btn-danger ml-3' onclick=\"commondelete('" + rs.getString("id") + "','usermaster')\">Delete</button></p>\n"
                                        + "                        </div>\n"
                                        + "                    </div>\n"
                                        + "                    <div class=\"right\">\n"
                                        + "                        <div class=\"price text-danger\">  <span class=\"badge badge-success\"> " + rs.getString("role") + "</span></div>\n"
                                        + "                    </div>\n"
                                        + "                \n"
                                        + "                    \n"
                                        + "                </a>");

                            }

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "poitemlist": {
                        String pono = request.getParameter("pono");

                        Double total = 0.0;
                        Double totalwithgst = 0.0;
                        try {
                            String Sql = "select iom.id,ponumber,company,podate,itemcode,itemname,purchaseqty,lastpurchaserate,reason,gst,hsncode,actaltotal,purchasedfor,purchasedforid,"
                                    + "entereddate,active,concat_ws(' ',um1.firstname,DATE_FORMAT(iom.entereddate,'%d-%b-%y %T'))  as enteredby,"
                                    + "concat_ws(' ',um2.firstname,DATE_FORMAT(cast(iom.updateddate AS datetime),'%d-%b-%y %T'))  as updatedby,pototal from poentrytable iom "
                                    + "Left join (select id,firstname from usermaster group by id) um1 on (um1.id=iom.enteredby)\n"
                                    + "Left join (select id,firstname from usermaster group by id) um2 on (um2.id=iom.enteredby)  where ponumber='" + pono + "'"
                                    + "  order by iom.id asc";
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                out1.print("  <a href=\"#\" class=\"item\" onclick=\"openentry('" + rs.getString("id") + "','" + rs.getString("itemcode") + "','" + rs.getString("itemname") + "',"
                                        + "'" + rs.getString("purchaseqty") + "','" + rs.getString("lastpurchaserate") + "',"
                                        + "'" + rs.getString("reason") + "','" + rs.getString("hsncode") + "','" + rs.getString("gst") + "','" + rs.getString("purchasedforid") + "',"
                                        + "'" + rs.getString("enteredby") + "','" + rs.getString("entereddate") + "'"
                                        + ")\">\n"
                                        + "                    <div class=\"detail\">\n"
                                        + "                       \n"
                                        + "                        <div>\n"
                                        + "                            <strong>" + rs.getString("itemname") + "/" + rs.getString("itemcode") + "</strong>\n"
                                        + "                             <p> Qty-" + rs.getString("purchaseqty") + " -Rate" + rs.getString("lastpurchaserate") + " </p>\n"
                                        + "                        </div>\n"
                                        + "                    </div>\n"
                                        + "                    <div class=\"right\">" + rs.getString("pototal") + "</div>\n"
                                        + "                    </div>\n"
                                        + "                \n"
                                        + "                    \n"
                                        + "                </a>");
                                total = total + rs.getDouble("pototal");
                                totalwithgst = totalwithgst + rs.getDouble("actaltotal");
                            }

                            total = Math.round(total * 100.0) / 100.0;
                            totalwithgst = Math.round(totalwithgst * 100.0) / 100.0;
                            Double gstval = totalwithgst - total;
                            gstval = Math.round(gstval * 100.0) / 100.0;

                            out1.print(" ||  <br><li>\n"
                                    + "                            <strong>Sub Total (INR)</strong>\n"
                                    + "\n"
                                    + "                    <strong>" + total + "</strong>"
                                    + "\n"
                                    + "                        </li>  <li>\n"
                                    + "                            <strong>GST % (INR)</strong>\n"
                                    + "\n"
                                    + "                    <strong>" + gstval + "</strong>"
                                    + "\n"
                                    + "                        </li>"
                                    + " <li>\n"
                                    + "                            <strong> Total (INR)</strong>\n"
                                    + "\n"
                                    + "                    <strong>" + totalwithgst + "</strong>"
                                    + "\n"
                                    + "                        </li>");
                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;

                    case "getdensity": {
                        String entrydate = request.getParameter("entrydate");
                        String tank = request.getParameter("tank");
                        out1.print("   <table class=\"table table-dark table-bordered dataTables-example\" >\n"
                                + "                                                <thead>\n"
                                + "                                                    <tr>\n"
                                + "\n"
                                + "\n"
                                + "                                                        <th> S.NO </th>\n"
                                + "                                                        <th> Tank </th>\n"
                                + "                                                        <th> Product </th>\n"
                                + "                                                        <th> Status </th>\n"
                                + "                                                        <th> Entrytime </th>\n"
                                + "                                                        <th> Hydrometer Reading </th>\n"
                                + "                                                        <th> Temperature </th>\n"
                                + "                                                        <th> Density @15 </th>\n"
                                + "                                                        <th> EntryDetails</th>\n"
                                + "                                                        <th> Action </th>\n"
                                + "\n"
                                + "                                                    </tr>\n"
                                + "                                                </thead>\n"
                                + "                                                <tbody>");
                        try {
                            String Sql = "SELECT tankname,Productname,tstatus,DATE_FORMAT(entrytime,'%d-%m-%Y %h:%i %p') entrytime,"
                                    + "hydrometerreading,temperature,densityat15,enteredby,entereddate,id FROM `tankdensityentry`  "
                                    + " where entrytime like '%" + entrydate + "%' and   tankid like '%" + tank + "%'";    //out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                out1.print("  <tr>\n"
                                        + " <td>" + i + "</td>\n"
                                        + "  <td>" + rs.getString("tankname") + " </td>\n"
                                        + "  <td>" + rs.getString("Productname") + " </td>\n"
                                        + "  <td>" + rs.getString("tstatus") + " </td>\n"
                                        + "  <td>" + rs.getString("entrytime") + " </td>\n"
                                        + "  <td>" + rs.getString("hydrometerreading") + " </td>\n"
                                        + "  <td>" + rs.getString("temperature") + " </td>\n"
                                        + "  <td>" + rs.getString("densityat15") + " </td>\n"
                                        + "  <td>" + rs.getString("enteredby") + " || " + rs.getString("entereddate") + " </td>\n"
                                        + "  <td> <a href=\"#\" class=\"btn btn-primary\" "
                                        + "onclick=\"openexistingdetails('" + rs.getString("id") + "','" + rs.getString("tankname") + "',"
                                        + "'" + rs.getString("Productname") + "','" + rs.getString("tstatus") + "','" + rs.getString("entrytime") + "',"
                                        + "'" + rs.getString("hydrometerreading") + "','" + rs.getString("temperature") + "','" + rs.getString("densityat15") + "')\"> View</a></td>\n"
                                        + "                                                    </tr> ");

                            }
                            out1.print("</tbody></table>");

                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "gettankreceivingentry": {
                        String transactionid = request.getParameter("transactionid");
                        String entrydate = request.getParameter("entrydate");
                        String tank = request.getParameter("tank");
                        out1.print("   <table class=\"table table-dark table-bordered dataTables-example\" >\n"
                                + "                                                <thead>\n"
                                + "                                                    <tr>\n"
                                + "\n"
                                + "\n"
                                + "                                                        <th> S.NO </th>\n"
                                + "                                                        <th> Tank </th>\n"
                                + "                                                        <th> Product </th>\n"
                                + "                                                        <th> Status </th>\n"
                                + "                                                        <th> Entrytime </th>\n"
                                + "                                                        <th> Hydrometer Reading </th>\n"
                                + "                                                        <th> Temperature </th>\n"
                                + "                                                        <th> Receiving Density @15 </th>\n"
                                + "                                                        <th> Quantity </th>\n"
                                + "                                                        <th> Bill No </th>\n"
                                + "                                                        <th> Bill Density </th>\n"
                                + "                                                        <th> Base </th>\n"
                                + "                                                        <th> EntryDetails</th>\n"
                                + "                                                        <th> Action </th>\n"
                                + "\n"
                                + "                                                    </tr>\n"
                                + "                                                </thead>\n"
                                + "                                                <tbody>");
                        try {
                            String Sql = "SELECT  id,tankid,tankname,productid,productname,DATE_FORMAT(entrytime,'%d-%m-%Y %h:%i %p') entrytime,quantity,hydrometerreading,"
                                    + "temperature,receivingdensityat15,billno,billdensityat15,base,enteredby,entereddate,updatedby,updateddate,tstatus "
                                    + " FROM tankreceivingentry  where entrytime like '%" + entrydate + "%' and   tankid like '%" + tank + "%'";    //out1.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                out1.print("  <tr>\n"
                                        + " <td>" + i + "</td>\n"
                                        + "  <td>" + rs.getString("tankname") + " </td>\n"
                                        + "  <td>" + rs.getString("Productname") + " </td>\n"
                                        + "  <td>" + rs.getString("tstatus") + " </td>\n"
                                        + "  <td>" + rs.getString("entrytime") + " </td>\n"
                                        + "  <td>" + rs.getString("hydrometerreading") + " </td>\n"
                                        + "  <td>" + rs.getString("temperature") + " </td>\n"
                                        + "  <td>" + rs.getString("receivingdensityat15") + " </td>\n"
                                        + "  <td>" + rs.getString("quantity") + " </td>\n"
                                        + "  <td>" + rs.getString("billno") + " </td>\n"
                                        + "  <td>" + rs.getString("billdensityat15") + " </td>\n"
                                        + "  <td>" + rs.getString("base") + " </td>\n"
                                        + "  <td>" + rs.getString("enteredby") + " || " + rs.getString("entereddate") + " </td>\n"
                                        + "  <td> <a href=\"#\" class=\"btn btn-primary\" "
                                        + "onclick=\"openexistingdetails('" + rs.getString("id") + "','" + rs.getString("tankname") + "',"
                                        + "'" + rs.getString("Productname") + "','" + rs.getString("tstatus") + "','" + rs.getString("entrytime") + "',"
                                        + "'" + rs.getString("hydrometerreading") + "','" + rs.getString("temperature") + "',"
                                        + "'" + rs.getString("receivingdensityat15") + "','" + rs.getString("quantity") + "','" + rs.getString("billno") + "'"
                                        + ",'" + rs.getString("billdensityat15") + "','" + rs.getString("base") + "')\"> View</a></td>\n"
                                        + "                                                    </tr> ");

                            }
                            out1.print("</tbody></table>");
                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "getankdip": {
                        String transactionid = request.getParameter("transactionid");
                        String entrydate = request.getParameter("entrydate");
                        String tank = request.getParameter("tank");
                        out1.print("   <table class=\"table table-dark table-bordered dataTables-example\" >\n"
                                + "                                                <thead>\n"
                                + "                                                    <tr>\n"
                                + "\n"
                                + "\n"
                                + "                                                        <th> S.NO </th>\n"
                                + "                                                        <th> Tank </th>\n"
                                + "                                                        <th> Product </th>\n"
                                + "                                                        <th> Status </th>\n"
                                + "                                                        <th> Entrytime </th>\n"
                                + "                                                        <th> Tank Dip</th>\n"
                                + "                                                        <th> Dip Dora </th>\n"
                                + "                                                        <th> Stock </th>\n"
                                + "                                                        <th> EntryDetails</th>\n"
                                + "                                                        <th> Action </th>\n"
                                + "\n"
                                + "                                                    </tr>\n"
                                + "                                                </thead>\n"
                                + "                                                <tbody>");
                        try {
                            String Sql = "SELECT  id,tankid,tankname,productid,productname,DATE_FORMAT(entrytime,'%d-%m-%Y %h:%i %p') entrytime,tankdip,dipdora,"
                                    + "stock,enteredby,entereddate,updatedby,updateddate,tstatus"
                                    + " FROM tankdipentry  where entrytime like '%" + entrydate + "%' and tankid like '%" + tank + "%'";
                            System.out.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                out1.print("  <tr>\n"
                                        + " <td>" + i + "</td>\n"
                                        + "  <td>" + rs.getString("tankname") + " </td>\n"
                                        + "  <td>" + rs.getString("Productname") + " </td>\n"
                                        + "  <td>" + rs.getString("tstatus") + " </td>\n"
                                        + "  <td>" + rs.getString("entrytime") + " </td>\n"
                                        + "  <td>" + rs.getString("tankdip") + " </td>\n"
                                        + "  <td>" + rs.getString("dipdora") + " </td>\n"
                                        + "  <td>" + rs.getString("stock") + " </td>\n"
                                        + "  <td>" + rs.getString("enteredby") + " || " + rs.getString("entereddate") + " </td>\n"
                                        + "  <td> <a href=\"#\" class=\"btn btn-primary\" "
                                        + "onclick=\"openexistingdetails('" + rs.getString("id") + "','" + rs.getString("tankname") + "',"
                                        + "'" + rs.getString("Productname") + "','" + rs.getString("tstatus") + "','" + rs.getString("entrytime") + "',"
                                        + "'" + rs.getString("tankdip") + "','" + rs.getString("dipdora") + "',"
                                        + "'" + rs.getString("stock") + "')\"> View</a></td>\n"
                                        + "                                                    </tr> ");

                            }
                            out1.print("</tbody></table>");
                            rs.close();
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;

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
            if (null != action) {
                switch (action) {
                    case "Createmonthlyreport": {
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        String Sql = "";
                        String entrydate = request.getParameter("entrydate");
                        try {
                            Sql = "Delete from  productwisedailysummaryreport where entrydate='" + entrydate + "' ";

                            PreparedStatement ps = con.prepareStatement(Sql);
                            int k = ps.executeUpdate();

                            Sql = "update `debitdata` dd set"
                                    + " rate=(select rate from todaysrate where product=dd.product and effectedfrom<dd.entereddate order by effectedfrom desc Limit 1) where rate=0";
                            ps = con.prepareStatement(Sql);
                            ps.executeUpdate();

                            Sql = "update `debitdata` set  qty=amount/rate where qty=0";
                            ps = con.prepareStatement(Sql);
                            ps.executeUpdate();
                            String Sql1 = "insert into productwisedailysummaryreport "
                                    + "(product,cash,debit,total,entrydate) select product,0,0,0,'" + entrydate + "' from productmaster";

                            ps = con.prepareStatement(Sql1);
                            k = ps.executeUpdate();
                            if (k > 0) {

                                Sql1 = "update productwisedailysummaryreport pdr inner join (select sum(p1ltr) as p1ltr,sum(p1val) as  p1val,p1 "
                                        + "from (SELECT sum((label1-op1)) as p1ltr,sum((label1-op1)*rate1) as p1val,sum((label2-op2)*rate1) as p2val,sum((label3-op3)*rate3) as p3val,"
                                        + "sum((label4-op4)*rate4) as p4val,p1,p2,p3,p4 FROM `dataentrytable` de Left join "
                                        + "(SELECT tanktype,p1,p2,p3,p4 FROM `tanktype` )tt on (tt.tanktype=de.tanktype)"
                                        + " where entrydate='" + entrydate + "' group by de.tanktype) as a  group by p1) pttbl"
                                        + " on (pdr.product=pttbl.p1) set cash=cash+p1val,cashinltr=cashinltr+p1ltr";
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();

                                Sql1 = "update productwisedailysummaryreport pdr inner join (select sum(p2ltr) as p1ltr,sum(p2val) as  p1val,p2 "
                                        + "from (SELECT sum((label1-op1)*rate1) as p1val,sum((label2-op2)) as p2ltr,sum((label2-op2)*rate1) as p2val,sum((label3-op3)*rate3) as p3val,"
                                        + "sum((label4-op4)*rate4) as p4val,p1,p2,p3,p4 FROM `dataentrytable` de Left join "
                                        + "(SELECT tanktype,p1,p2,p3,p4 FROM `tanktype` )tt on (tt.tanktype=de.tanktype)"
                                        + " where entrydate='" + entrydate + "' group by de.tanktype) as a group by p2) pttbl"
                                        + " on (pdr.product=pttbl.p2) set cash=cash+p1val,cashinltr=cashinltr+p1ltr";
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();

                                Sql1 = "update productwisedailysummaryreport pdr inner join (select sum(p3ltr) as p1ltr,sum(p3val) as  p1val,p3 "
                                        + "from (SELECT sum((label1-op1)*rate1) as p1val,sum((label2-op2)*rate1) as p2val,sum((label3-op3)) as p3ltr,sum((label3-op3)*rate3) as p3val,"
                                        + "sum((label4-op4)*rate4) as p4val,p1,p2,p3,p4 FROM `dataentrytable` de Left join "
                                        + "(SELECT tanktype,p1,p2,p3,p4 FROM `tanktype` )tt on (tt.tanktype=de.tanktype) "
                                        + "where entrydate='" + entrydate + "' group by de.tanktype) as a group by p3) pttbl"
                                        + " on (pdr.product=pttbl.p3) set cash=cash+p1val,cashinltr=cashinltr+p1ltr";
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();

                                Sql1 = "update productwisedailysummaryreport pdr inner join (select sum(p4ltr) as p1ltr,sum(p4val) as  p1val,p4 "
                                        + "from (SELECT sum((label1-op1)*rate1) as p1val,sum((label2-op2)*rate1) as p2val,sum((label3-op3)*rate3) as p3val,"
                                        + "sum((label4-op4)) as p4ltr,sum((label4-op4)*rate4) as p4val,p1,p2,p3,p4 FROM `dataentrytable` de Left join "
                                        + "(SELECT tanktype,p1,p2,p3,p4 FROM `tanktype` )tt on (tt.tanktype=de.tanktype) "
                                        + "where entrydate='" + entrydate + "' group by de.tanktype) as a group by p4) pttbl"
                                        + " on (pdr.product=pttbl.p4) set cash=cash+p1val,cashinltr=cashinltr+p1ltr";
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();
                                Sql1 = "  update productwisedailysummaryreport pdr inner join "
                                        + "(SELECT product,sum(amount) debitamt,sum(qty) debitqty  FROM `debitdata` where entrydate='" + entrydate + "' group by product) pttbl on "
                                        + "(pdr.product=pttbl.product) set debit=debit+debitamt,debitinltr=debitinltr+debitqty  where entrydate='" + entrydate + "'";

                                System.out.println("Debit save" + Sql1);
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();

                                Sql1 = "insert into productwisedailysummaryreport (product,cash,debit,total,entrydate)"
                                        + " select lubricant,sum(rate*qty),0,0,'" + entrydate + "' from oildata where entrydate='" + entrydate + "' group by lubricant ";

                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();

                                Sql1 = "  update productwisedailysummaryreport set  total=debit+cash,totalinltr=debitinltr+cashinltr where entrydate='" + entrydate + "' ";
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();
                                Sql1 = "  update productwisedailysummaryreport pd inner join  (select cashinhandleft,entrydate from productwisedailysummaryreport ) pd2 on "
                                        + " (pd2.entrydate=DATE_SUB('" + entrydate + "', INTERVAL 1 DAY)  ) set pd.cashinhandprevday=pd2.cashinhandleft"
                                        + " where pd.entrydate='" + entrydate + "' ";
                                System.out.println("cash prevday save" + Sql1);
                                ps = con.prepareStatement(Sql1);
                                
                                k = ps.executeUpdate();
                                Sql1 = "   update productwisedailysummaryreport pdr inner join "
                                        + "(select sum(debit) debit,sum(jama) jama,sum(paytm) paytm,sum(ioc) ioc,sum(icici) icici ,"
                                        + "sum(expense) expense,sum(totalsale) totalsale,sum(cashsale) cashsale,entrydate,id from dataentrytable group by entrydate   ) "
                                        + "dt on (dt.entrydate=pdr.entrydate) set pdr.totalsale=dt.totalsale,\n"
                                        + "pdr.jama=dt.jama,pdr.cashsale=dt.cashsale,pdr.expense=dt.expense,pdr.partydebit=dt.debit,pdr.ioc=dt.ioc,pdr.icici=dt.icici,pdr.paytm=dt.paytm,pdr.cashinhandcurrent=dt.cashsale where pdr.entrydate='" + entrydate + "'";
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();

                                out1.print("Saved Successfully");

                            }
                            //  }

                            ps.close();
                        } catch (SQLException ex) {
                            System.out.println("error--" + ex);
                            out1.print(ex);
                        }

                    }
                    break;

                    case "closingreports": {
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        String Sql = "";
                        String entrydate = request.getParameter("entrydate");
                        String bankdeposit = request.getParameter("bankdeposit");
                        try {
                            Sql = "update productwisedailysummaryreport set bankdeposit='" + bankdeposit + "',cashinhandleft=cashinhandcurrent-" + bankdeposit + " where entrydate='" + entrydate + "' ";

                            PreparedStatement ps = con.prepareStatement(Sql);
                            int k = ps.executeUpdate();

                            Sql = "delete from  densityregister  where entrydate='" + entrydate + "' ";

                            ps = con.prepareStatement(Sql);
                            k = ps.executeUpdate();
                            Sql = "delete from  dsrreport  where entrydate='" + entrydate + "' ";

                            ps = con.prepareStatement(Sql);
                            k = ps.executeUpdate();
                            Sql = "delete from  patrakreport  where entrydate='" + entrydate + "' ";

                            ps = con.prepareStatement(Sql);
                            k = ps.executeUpdate();
                            Sql = "insert into densityregister (tankid,tankname,entrydate,morningdensity,"
                                    + "morningdensitytemp,morningdensityat15) select tankid,tankname,entrytime,hydrometerreading,"
                                    + "temperature,densityat15 from tankdensityentry where date(entrytime)='" + entrydate + "' and  date(entrytime) not in "
                                    + "(select distinct entrydate from densityregister)   "
                                    + "group by tankid order by entrytime asc ";

                            ps = con.prepareStatement(Sql);
                            k = ps.executeUpdate();

                            if (k > 0) {
                                String Sql1 = "update densityregister dr inner join  (select tankid,quantity,hydrometerreading,temperature,"
                                        + "receivingdensityat15,billno,billdensityat15,base, billdensityat15-receivingdensityat15 as diff "
                                        + "from tankreceivingentry where Date(entrytime)='" + entrydate + "') tr on (tr.tankid=dr.tankid) set "
                                        + "dr.Billno=tr.Billno,dr.Litres=quantity,dr.receiptdensity=tr.hydrometerreading,"
                                        + "dr.receiptdensitytemp=temperature,dr.receiptdensityat15=receivingdensityat15,"
                                        + "dr.Billdensity=billdensityat15,dr.Diff=tr.diff where entrydate='" + entrydate + "'";

                                System.out.print(Sql1);
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();
                                Sql1 = "update densityregister dr inner join  "
                                        + "(select tankid,tankname,entrytime,hydrometerreading,temperature,densityat15 "
                                        + "from tankdensityentry where Date(entrytime)='" + entrydate + "' order by entrytime asc ) tr on "
                                        + "(tr.tankid=dr.tankid) set dr.closingdensity=hydrometerreading,dr.closingdensitytemp=temperature,"
                                        + "dr.closingdensityat15=tr.densityat15 where entrydate='" + entrydate + "'";
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();

                                out1.print("Report Generated Successfully");

                            } else {
                                out1.print("Already reports available in same date, please delete previous report");
                            }
                            //  }

                            ps.close();
                        } catch (SQLException ex) {

                            out1.print(ex);
                        }

                    }
                    break;
                    case "closingreportsDSR": {
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        String Sql = "";
                        String entrydate = request.getParameter("entrydate");
                        try {
                            Sql = "insert INTO `dsrreport` (productid,product,entrydate) select id,product,'" + entrydate + "' from productmaster ";

                            PreparedStatement ps = con.prepareStatement(Sql);
                            int k = ps.executeUpdate();

                            if (k > 0) {
                                String Sql1 = "update dsrreport ds inner join (select product,id,ifnull(tankdip,0) tankdip,ifnull(dipdora,0) dipdora,ifnull(stock,0) stock from tankmaster  tm LEft join (select tankid,tankname,productname,productid,tankdip,dipdora,stock \n"
                                        + "from tankdipentry where Date(entrytime)='" + entrydate + "' group by productid ) tde on (tde.productid=tm.product) order by id desc) td on (td.product=ds.productid) "
                                        + "set tank1id=td.id,tank1dip=tankdip,tank1waterdip=dipdora,openingstock=stock where entrydate='" + entrydate + "'";

                                System.out.print(Sql1);
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();
                                Sql1 = " update dsrreport ds inner join (select product,id,ifnull(tankdip,0) tankdip,ifnull(dipdora,0) dipdora,ifnull(stock,0) stock from tankmaster  tm LEft join (select tankid,tankname,productname,productid,tankdip,dipdora,stock \n"
                                        + "      from tankdipentry where Date(entrytime)='" + entrydate + "' and tankid not in (select tank1id from dsrreport) group by productid ) tde on (tde.productid=tm.product) "
                                        + " where id not in (select tank1id from dsrreport where entrydate='" + entrydate + "')) td on (td.product=ds.productid) "
                                        + "set tank2id=td.id,tank2dip=tankdip,tank2waterdip=dipdora,openingstock=openingstock+stock where entrydate='" + entrydate + "'";
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();
                                Sql1 = "   update dsrreport ds inner join (select sum(quantity) totalqty,productid from tankreceivingentry where Date(entrytime)='" + entrydate + "' group by productid) tr on (tr.productid=ds.productid) \n"
                                        + "set receipt=totalqty,total=totalqty+openingstock where entrydate='" + entrydate + "'";
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();
                                String Sql2 = "select productid,product,entrydate from dsrreport  where entrydate='" + entrydate + "' order by id desc";

                                ps = con.prepareStatement(Sql2);
                                ResultSet rs = ps.executeQuery();
                                int i = 0;
                                while (rs.next()) {
                                    i = 0;
                                    for (int b = 1; b < 5; b++) {
                                        Sql2 = "SELECT tt.tanktype,p1,p2,p3,p4,ifnull(op1,0) op1,ifnull(op2,0) op2,ifnull(op3,0) op3,ifnull(op4,0) op4 "
                                                + "FROM `tanktype`  tt Left join (SELECT tanktype,op1,op2,op3,op4 FROM `dataentrytable` where entrydate='" + entrydate + "')de on (de.tanktype=tt.tanktype) "
                                                + " where p" + b + "='" + rs.getString("product") + "' group by tt.tanktype order by id asc";
                                        ps = con.prepareStatement(Sql2);
                                        ResultSet rs2 = ps.executeQuery();
                                        while (rs2.next()) {
                                            i++;
                                            Sql1 = " update dsrreport  set op_p" + i + "='" + rs2.getString("op" + b) + "' where product='" + rs.getString("product") + "' and entrydate='" + entrydate + "'";
                                            ps = con.prepareStatement(Sql1);
                                            k = ps.executeUpdate();
                                        }
                                    }

                                }

                                Sql1 = "   update dsrreport ds inner join (select sum(sales) cumsaleval,productid from dsrreport where entrydate<'" + entrydate + "' group by productid) tr on (tr.productid=ds.productid) \n"
                                        + "set sales=op_p1+op_p2+op_p3+op_p4+op_p5+op_p6+op_p7+op_p8+op_p9+op_p10+op_p11+op_p12+op_p13+op_p14+op_p15,"
                                        + "cumsales=op_p1+op_p2+op_p3+op_p4+op_p5+op_p6+op_p7+op_p8+op_p9+op_p10+op_p11+op_p12+op_p13+op_p14+op_p15+cumsaleval where entrydate='" + entrydate + "'";
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();

                                Sql1 = " insert into  `patrakreport` (productid,productname,entrydate,opening,receiving,total,sale,closing,remarks) select productid,product,entrydate,openingstock,\n"
                                        + "receipt,total,cumsales,total+cumsales,''  from dsrreport where entrydate='" + entrydate + "'";
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();
                                out1.print("Report Generated Successfully");
                                Sql1 = " insert into  `dailyclosedata` (entrydate,allok) values('" + entrydate + "',1) ";
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();

                            } else {
                                out1.print("Already reports available in same date, please delete previous report");
                            }
                            //  }

                            ps.close();
                        } catch (SQLException ex) {

                            out1.print(ex);
                        }

                    }
                    break;
                    case "Savepetroldata": {

                        String dataentrytype = request.getParameter("dataentrytype");
                        String shifttype = request.getParameter("shifttype");
                        String pump = request.getParameter("pump");
                        String rate1 = request.getParameter("rate1");
                        String rate2 = request.getParameter("rate2");
                        String rate3 = request.getParameter("rate3");
                        String rate4 = request.getParameter("rate4");
                        String label1 = request.getParameter("label1");
                        String label2 = request.getParameter("label2");
                        String label3 = request.getParameter("label3");
                        String label4 = request.getParameter("label4");
                        String op1 = request.getParameter("op1");
                        String op2 = request.getParameter("op2");
                        String op3 = request.getParameter("op3");
                        String op4 = request.getParameter("op4");

                        String debit = request.getParameter("debit");
                        String jama = request.getParameter("jama");
                        String paytm = request.getParameter("paytm");
                        String icici = request.getParameter("icici");

                        String ioc = request.getParameter("ioc");
                        String expense = request.getParameter("expense");
                        String oil = request.getParameter("oil");
                        String fivehun = request.getParameter("fivehun");
                        String twohun = request.getParameter("twohun");
                        String hun = request.getParameter("hun");
                        String fivty = request.getParameter("fivty");
                        String twenty = request.getParameter("twenty");
                        String ten = request.getParameter("ten");
                        String user = request.getParameter("user");
                        String change = request.getParameter("change");
                        String entrydate = request.getParameter("entrydate");
                        String entryStatus = request.getParameter("entryStatus");
                        String forwardingcash = request.getParameter("forwardingcash");
                        String t1 = request.getParameter("t1");
                        String t2 = request.getParameter("t2");
                        String t3 = request.getParameter("t3");
                        String t4 = request.getParameter("t4");

                        String iocsettlementno = request.getParameter("iocsettlementno");
                        String paytmsettlementno = request.getParameter("paytmsettlementno");
                        String icicisettlementno = request.getParameter("icicisettlementno");
                        java.util.Date today = new java.util.Date();
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        String Sql = "";

                        try {

                            String Sql1 = "insert into dataentrytable (dataentrytype,entrydate,entryStatus,shift,tanktype,op1,op2,op3,op4,rate1,rate2,rate3,rate4,label1,label2,label3,label4"
                                    + ",debit,jama,paytm,icici,ioc,expense,oil,500s,200s,100s,50s,20s,10s,amtchange,enteredby,entereddate,forwardingcash,"
                                    + "t1,t2,t3,t4,iocsettlementno,paytmsettlementno,icicisettlementno) values "
                                    + "( '" + dataentrytype + "','" + entrydate + "','" + entryStatus + "', '" + shifttype + "','" + pump + "',"
                                    + "'" + op1 + "','" + op2 + "','" + op3 + "','" + op4 + "','" + rate1 + "','" + rate2 + "'"
                                    + ",'" + rate3 + "','" + rate4 + "','" + label1 + "',"
                                    + "'" + label2 + "','" + label3 + "','" + label4 + "','" + debit + "','" + jama + "','" + paytm + "','" + icici + "',"
                                    + "'" + ioc + "','" + expense + "','" + oil + "',"
                                    + "'" + fivehun + "','" + twohun + "','" + hun + "',"
                                    + "'" + fivty + "','" + twenty + "','" + ten + "','" + change + "',"
                                    + "'" + user + "','" + DbEnteredDate + "','" + forwardingcash + "','" + t1 + "','" + t2 + "','" + t3 + "',"
                                    + "'" + t4 + "','" + iocsettlementno + "','" + paytmsettlementno + "','" + icicisettlementno + "')";

                            System.out.print(Sql1);
                            PreparedStatement ps = con.prepareStatement(Sql1);
                            int k = ps.executeUpdate();
                            if (k > 0) {

                                Sql1 = "Update tankcurrentstock set availablestock=availablestock-(" + label1 + "-" + op1 + ") where tankid='" + t1 + "'";
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();
                                Sql1 = "Update tankcurrentstock set availablestock=availablestock-(" + label2 + "-" + op2 + ") where tankid='" + t2 + "'";
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();
                                Sql1 = "Update tankcurrentstock set availablestock=availablestock-(" + label3 + "-" + op3 + ") where tankid='" + t3 + "'";
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();
                                Sql1 = "Update tankcurrentstock set availablestock=availablestock-(" + label4 + "-" + op4 + ") where tankid='" + t4 + "'";
                                ps = con.prepareStatement(Sql1);
                                k = ps.executeUpdate();
                                out1.print("Saved Successfully");

                            }
                            //  }

                            ps.close();
                        } catch (SQLException ex) {

                            out1.print(ex);
                        }
                    }

                    break;
                    case "Savedensity": {

                        String hydrometerreading = request.getParameter("hydrometerreading");

                        String temperature = request.getParameter("temperature");
                        String densityat15 = request.getParameter("densityat15");

                        String user = request.getParameter("user");
                        java.util.Date today = new java.util.Date();
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        String Sql = "";
                        String Id = request.getParameter("entryid");
                        try {
                            String Sql1 = "";

                            Sql1 = "update tankdensityentry set hydrometerreading='" + hydrometerreading + "',"
                                    + "temperature='" + temperature + "',densityat15='" + densityat15 + "',updatedby='" + user + "',updateddate='" + DbEnteredDate + "' where id=" + Id + "";

                            System.out.print(Sql1);
                            PreparedStatement ps = con.prepareStatement(Sql1);
                            ps.executeUpdate();

                            //  }
                            out1.print("Saved Successfully");

                            ps.close();
                        } catch (SQLException ex) {

                            out1.print(ex);
                        }
                    }
                    break;
                    case "Savereceiving": {

                        String quantity = request.getParameter("quantity");
                        String hydrometerreading = request.getParameter("hydrometerreading");
                        String currentstock = request.getParameter("currentstock");
                        String temperature = request.getParameter("temperature");
                        String recevingdensity = request.getParameter("receivingdensityat15");
                        String billno = request.getParameter("billno");
                        String billdensity = request.getParameter("billdensityat15");

                        String user = request.getParameter("user");
                        java.util.Date today = new java.util.Date();
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        String Sql = "";
                        String Id = request.getParameter("entryid");
                        try {
                            String Sql1 = "";

                            Sql1 = "update tankreceivingentry set quantity='" + quantity + "',hydrometerreading='" + hydrometerreading + "',"
                                    + "temperature='" + temperature + "',receivingdensityat15='" + recevingdensity + "',billno='" + billno + "',"
                                    + "billdensityat15='" + billdensity + "',updatedby='" + user + "',updateddate='" + DbEnteredDate + "' where id=" + Id + "";

                            System.out.print(Sql1);
                            PreparedStatement ps = con.prepareStatement(Sql1);
                            ps.executeUpdate();

                            //  }
                            out1.print("Saved Successfully");

                            ps.close();
                        } catch (SQLException ex) {

                            out1.print(ex);
                        }
                    }
                    break;
                    case "Savedip": {
                        String Id = request.getParameter("entryid");

                        String dipdora = request.getParameter("dipdora");
                        String tankdip = request.getParameter("tankdip");

                        String stock = request.getParameter("stock");

                        String user = request.getParameter("user");
                        java.util.Date today = new java.util.Date();
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        String Sql = "";

                        try {
                            String Sql1 = "";

                            Sql1 = "update tankdipentry set tankdip='" + tankdip + "',dipdora='" + dipdora + "',"
                                    + "stock='" + stock + "',updatedby='" + user + "',updateddate='" + DbEnteredDate + "' where id=" + Id + "";

                            System.out.print(Sql1);
                            PreparedStatement ps = con.prepareStatement(Sql1);
                            ps.executeUpdate();

                            //  }
                            out1.print("Saved Successfully");

                            ps.close();
                        } catch (SQLException ex) {

                            out1.print(ex);
                        }
                    }
                    break;
                    case "Savenozzle": {

                        String maintype = request.getParameter("maintype");
                        String nozzletype = request.getParameter("nozzletype");

                        java.util.Date today = new java.util.Date();
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        String Sql = "";

                        try {

                            String Sql1 = "insert into tanktype (maintype,tanktype,enteredby) values "
                                    + "( '" + maintype + "','" + nozzletype + "','1')";

                            System.out.print(Sql1);
                            PreparedStatement ps = con.prepareStatement(Sql1);
                            ps.executeUpdate();

                            //  }
                            out1.print("Saved Successfully");

                            ps.close();
                        } catch (SQLException ex) {

                            out1.print(ex);
                        }
                    }

                    break;
                    case "Saverate": {

                        String product = request.getParameter("product");

                        String effectedfrom = request.getParameter("effectedfrom");
                        String rate = request.getParameter("rate");
                        java.util.Date today = new java.util.Date();
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        String Sql = "";

                        try {

                            String Sql1 = "insert into todaysrate (product,rate,effectedfrom) values "
                                    + "( '" + product + "','" + rate + "','" + effectedfrom + "')";

                            System.out.print(Sql1);
                            PreparedStatement ps = con.prepareStatement(Sql1);
                            ps.executeUpdate();

                            //  }
                            out1.print("Saved Successfully");

                            ps.close();
                        } catch (SQLException ex) {

                            out1.print(ex);
                        }
                    }

                    break;
                    case "Saveoil": {
                        String entrydate = request.getParameter("entrydate");

                        String pump = request.getParameter("pump");
                        String shifttype = request.getParameter("shifttype");
                        String oil = request.getParameter("oil");

                        String rate = request.getParameter("rate");
                        String qty = request.getParameter("qty");
                        String paymentmode = request.getParameter("paymentmode");
                        java.util.Date today = new java.util.Date();
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        String Sql = "";

                        try {

                            String Sql1 = "insert into oildata (pump,shifttype,lubricant,entrydate,rate,qty,enteredby,entereddate,paymentmode) values "
                                    + "( '" + pump + "','" + shifttype + "','" + oil + "','" + entrydate + "','" + rate + "',"
                                    + "'" + qty + "','1','" + DbEnteredDate + "','" + paymentmode + "')";

                            // System.out.print(Sql1);
                            PreparedStatement ps = con.prepareStatement(Sql1);
                            ps.executeUpdate();

                            //  }
                            out1.print("Saved Successfully");

                            ps.close();
                        } catch (SQLException ex) {

                            out1.print(ex);
                        }
                    }

                    break;
                    case "Savejumma": {
                        String entrydate = request.getParameter("entrydate");

                        String pump = request.getParameter("pump");
                        String shifttype = request.getParameter("shifttype");
                        //   String partyname = request.getParameter("partyname");
                        String chequeno = request.getParameter("chequeno");
                        String chequeamt = request.getParameter("chequeamt");
                        String cashamt = request.getParameter("cashamt");
                        String cashreceiptno = request.getParameter("cashreceiptno");
                        String digitalamt = request.getParameter("digitalamt");
                        java.util.Date today = new java.util.Date();
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        String Sql = "";

                        try {

                            String Sql1 = "insert into jummadata (pump,shifttype,entrydate,chequeno,chequeamount,cash,cashreceiptno,digitalamt,enteredby,entereddate) values "
                                    + "( '" + pump + "','" + shifttype + "','" + entrydate + "','" + chequeno + "',"
                                    + "'" + chequeamt + "','" + cashamt + "','" + cashreceiptno + "','" + digitalamt + "','1','" + DbEnteredDate + "')";

                            System.out.print(Sql1);
                            PreparedStatement ps = con.prepareStatement(Sql1);
                            ps.executeUpdate();

                            //  }
                            out1.print("Saved Successfully");

                            ps.close();
                        } catch (SQLException ex) {

                            out1.print(ex);
                        }
                    }

                    break;
                    case "Savedebit": {
                        String entrydate = request.getParameter("entrydate");

                        String pump = request.getParameter("pump");
                        String shifttype = request.getParameter("shifttype");
                        String partyname = request.getParameter("partyname");
                        String product = request.getParameter("product");
                        String rate = request.getParameter("rate");
                        String billno = request.getParameter("billno");
                        java.util.Date today = new java.util.Date();
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        String Sql = "";

                        try {

                            String Sql1 = "insert into debitdata (pump,shifttype,partyname,entrydate,amount,billno,enteredby,entereddate,product) values "
                                    + "( '" + pump + "','" + shifttype + "','" + partyname + "','" + entrydate + "','" + rate + "',"
                                    + "'" + billno + "','1','" + DbEnteredDate + "','" + product + "')";

                            System.out.print(Sql1);
                            PreparedStatement ps = con.prepareStatement(Sql1);
                            ps.executeUpdate();

                            //  }
                            out1.print("Saved Successfully");

                            ps.close();
                        } catch (SQLException ex) {

                            out1.print(ex);
                        }
                    }

                    break;

                    case "Saveop": {
                        String entryid = request.getParameter("entryid");
                        String pump = request.getParameter("pump");
                        String op1 = request.getParameter("op1");
                        String op2 = request.getParameter("op2");
                        String op3 = request.getParameter("op3");
                        String op4 = request.getParameter("op4");
                        String forcepull = request.getParameter("forcepull");

                        java.util.Date today = new java.util.Date();
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        String Sql = "";

                        try {
                            if (entryid.equals("0")) {
                                Sql = "insert into openingvaluemaster (tanktype,op1,op2,op3,op4,forcepull) values "
                                        + "( '" + pump + "','" + op1 + "','" + op2 + "','" + op3 + "','" + op4 + "','" + forcepull + "')";
                            } else {
                                Sql = "update openingvaluemaster set op1='" + op1 + "',op2='" + op2 + "',op3='" + op3 + "',"
                                        + "op4='" + op4 + "',forcepull='" + forcepull + "'  where id=" + entryid + "";
                            }

                            System.out.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ps.executeUpdate();

                            //  }
                            out1.print("Saved Successfully");

                            ps.close();
                        } catch (SQLException ex) {

                            out1.print(ex);
                        }
                    }

                    break;
                    case "commondelete": {

                        String id = request.getParameter("Id");
                        String tbl = request.getParameter("Tbl");

                        String DepId = "0";
                        String Username = "Praveen";
                        String Sql = "";
                        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
                        try {

                            Sql = "Delete  from " + tbl + " where id=" + id + "";

                            PreparedStatement ps = con.prepareStatement(Sql);
                            int Success = ps.executeUpdate();
                            if (Success > 0) {

                                out1.print("Deleted successfully");

                            }

                        } catch (Exception e) {
                            out1.print(e);

                        }
                        break;
                    }
                    case "deletetable": {
                        String username = request.getParameter("EmpCode");
                        String table = request.getParameter("tbl");
                        String ID = request.getParameter("Id");
                        String sql = "";
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();

                        sql = "delete from " + table + "  where id=" + ID + "";
                        try {
                            PreparedStatement ps = con.prepareStatement(sql);

                            int success = ps.executeUpdate();
                            if (success > 0) {
                                out1.print(" Deleted Successfully");
                            }
                            ps.close();
                        } catch (SQLException e) {
                            out1.print(e);
                        }
                    }
                    break;
                }
            }
            connectionPool.free(con);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public String createexid(String year, String Location) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String newTestcode = "";
        try {
            String Sql = "Select count(id) from k_tinv_issueprocessmain "
                    + " where locationid='" + Location + "' and Financialyear='" + year + "'   order by id desc LIMIT 1 ";

            pst = con.prepareStatement(Sql);
            // System.out.print(Sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1) + 1;
                // String[] part = Testcode.split("(?<=\\D)(?=\\d)");

                String digtCount = String.format("%07d", count);
                newTestcode = "PPI/" + Location + "/" + year + "/" + digtCount;
            } else {
                String initial = "";

                newTestcode = "PPI/" + Location + "/" + year + "/" + "0000001";
            }
            //out1.print(Sql);
            rs.close();
            pst.close();
        } catch (NumberFormatException | SQLException e) {
            System.out.print(e);
            newTestcode = e.getMessage();

        }
        return newTestcode;
    }

    public Double getdensityfortank(String tankid) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Double newTestcode = 0.0;
        try {
            String Sql = "SELECT densityat15 FROM `tankdensityentry`  "
                    + " where tankid='" + tankid + "'   order by id desc LIMIT 1 ";

            pst = con.prepareStatement(Sql);
            // System.out.print(Sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                newTestcode = rs.getDouble(1);

                return newTestcode;
            } else {
                return newTestcode;
            }
            //out1.print(Sql);

        } catch (NumberFormatException | SQLException e) {
            System.out.print(e);

        }
        return newTestcode;
    }

    private void updateremainingqty_ppi(String transactionid) {
        String sql = "";

        sql = "  update `k_tinv_productionrecieptdetailbatch` t1 inner join (select erpcode,batchno,qty,fromproductionlineid,fromprocessid from k_tinv_issueprocessdetailsbatch where transactionid='" + transactionid + "')t2 \n"
                + "               on (t1.batchno=t2.batchno and t1.erpcode=t2.erpcode and t1.productionlineid=t2.fromproductionlineid and t1.processid=t2.fromprocessid)\n"
                + "                set issuedqty=t2.qty,balanceqty=balanceqty-t2.qty  where t1.transaction !='Rej' and t1.balanceqty>0";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            //out1.print(sql);
            int success = ps.executeUpdate();
            if (success > 0) {
                // out1.print("Authorised successfully");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.print(e);
        }

    }

    private void updateremainingqty_spi(String transactionid) {
        String sql = "";

        sql = "  update `k_tinv_recieptdetailbatch` t1 inner join (select erpcode,batchno,qty,store from k_tinv_issueproductiondetailbatch where transactionid='" + transactionid + "')t2 \n"
                + "               on (t1.batchno=t2.batchno and t1.erpcode=t2.erpcode and t1.store=t2.store)\n"
                + "                set issuedqty=t2.qty,issuetype='Issue',balanceqty=balanceqty-t2.qty  where  t1.balanceqty>0 ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            //out1.print(sql);
            int success = ps.executeUpdate();
            if (success > 0) {
                // out1.print("Authorised successfully");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.print(e);
        }

    }
}
