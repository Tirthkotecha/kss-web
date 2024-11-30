/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormsApi;

import connection.ConnectionPool;
import connection.dbdata;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "Formservlet", urlPatterns = {"/Formservlet"})
public class Formservlet extends HttpServlet {

    Connection con = null;
    java.util.Date today = new java.util.Date();
    String dblocation = "103.231.78.208";
    String dbUser = "saladmin";
    String dbPass = "23693sal";
    String dbName = "salclust_costing";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out1 = response.getWriter();
        java.util.Date today = new java.util.Date();
        String action = request.getParameter("Action");
        try {
            ConnectionPool connectionPool = new ConnectionPool(
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://" + dblocation + "/" + dbName + "", "" + dbUser + "", "" + dbPass + "",
                    1, 50, true);

            con = connectionPool.getConnection();
            if (null != action) {
                switch (action) {
                    case "generateFilter": {
                        String docid = request.getParameter("docid");
                        StringBuilder formbuilder = new StringBuilder();
                        String inputtype = "";
                        String inputids = "";
                        String dropdowntblname = "";
                        String dropdowndisplayvalue = "";
                        String dropdownsavevalue = "";
                        String dropdwncondfield = "";
                        String dropdwncondcol = "";
                        String dropcolname = "";
                        try {
                            String Sql = "Select docid,tablename,colname,coltype,label,labelclass,inputtype,inputplaceholder,inputid,inputclass,mandatory,validationmessage,dropdowntbl,displayvalue,"
                                    + "savevalue,dropdwncondfield,dropdwncondcol,gridlarge,gridmedium,gridsmall,inputorder,filter from formcreation where docid='" + docid + "' order by cast(inputorder AS UNSIGNED) asc";
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            formbuilder.append("<div class=\"form-group row pl-2\" > ");
                            while (rs.next()) {
                                StringBuilder colbuilder = new StringBuilder();
                                String labelstr = "";
                                String inputstr = "";
                                if (rs.getString("filter").equals("yes")) {
                                    i++;
                                    int grdlg = (12 / rs.getInt("gridlarge")) +1;
                                    int grdmd = (12 / rs.getInt("gridmedium")) +1;

                                    colbuilder.append("<div class='col-lg-" + grdlg + " col-md-" + grdmd + " col-sm-" + rs.getInt("gridsmall") + " form-group'>");
                                    labelstr += "<label class='whitelbl'>" + rs.getString("label") + "</label>";
                                    inputtype += " -- " + rs.getString("inputtype");
                                    inputids += " -- " + rs.getString("inputid") + "filter";
                                    dropdowntblname += " -- " + rs.getString("dropdowntbl");
                                    dropdowndisplayvalue += " -- " + rs.getString("displayvalue");
                                    dropdownsavevalue += " -- " + rs.getString("savevalue");
                                    dropdwncondfield += " -- " + rs.getString("dropdwncondfield");
                                    dropdwncondcol += " -- " + rs.getString("dropdwncondcol");
                                    dropcolname += " -- " + rs.getString("colname");
                                    inputstr = getinputstr(docid, rs.getString("inputtype"), rs.getString("inputid") + "filter", rs.getString("inputclass"), rs.getString("inputplaceholder"), "");
                                    colbuilder.append(labelstr);
                                    colbuilder.append(inputstr);
                                    colbuilder.append(" </div>");
                                }
                                formbuilder.append(colbuilder);
                            }

                            String formfooter = "";
                            if (i > 0) {
                                formfooter = " <div class=\"col-lg-2 col-md-2  col-sm-3 form-group\">\n"
                                        + "<label></label>"
                                        + "                                        <button type=\"button\" id='filter' class=\"btn btn-success\" style=\"width: 100%;\" "
                                        + "onclick=\"gettbldata('" + inputtype + "', '" + inputids + "')\">Search</button>\n"
                                        + "                                </div>\n";
                                formbuilder.append(formfooter);
                                formbuilder.append(" </div>");
                                String drpdwnstring = " `` " + inputtype + " `` " + inputids + " `` " + dropdowntblname + " `` " + dropdowndisplayvalue + " `` " + dropdownsavevalue + " `` " + dropdwncondfield + " `` " + dropdwncondcol + " `` " + dropcolname;
                                formbuilder.append(drpdwnstring);
                                out1.print(formbuilder);
                            }
                            rs.close();
                            ps.close();
                        } catch (Exception e) {
                            out1.print("filter exception -" + e);
                        }

                    }
                    break;
                    case "generateForm": {
                        String docid = request.getParameter("docid");
                        String generatedid = request.getParameter("generatedid");
                        StringBuilder formbuilder = new StringBuilder();
                        String inputtypefrsave = "";
                        String inputidvalfrsave = "";
                        String inputmandatoryfrsave = "";
                        String validationmsgfrsave = "";
                        String filterids = "";
                        String filterinputtype = "";
                        String dropdowntype = "";
                        String dropdownids = "";
                        String dropdowntblname = "";
                        String dropdowndisplayvalue = "";
                        String dropdownsavevalue = "";
                        String dropdwncondfield = "";
                        String dropdwncondcol = "";
                        String dropcolname = "";
                        String addfunction = "";
                        String samevalue = "";
                        formbuilder.append("<div class=\"form-group row pl-2\" > ");
                        try {
                            String Sql = "Select docid,tablename,colname,coltype,label,labelclass,inputtype,inputplaceholder,inputid,inputclass,mandatory,validationmessage,dropdowntbl,displayvalue,"
                                    + "savevalue,dropdwncondfield,dropdwncondcol,gridlarge,gridmedium,gridsmall,inputorder,filter,additionalfunction from formcreation "
                                    + " where docid='" + docid + "' order by cast(inputorder AS UNSIGNED) asc ";

                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                StringBuilder colbuilder = new StringBuilder();
                                String labelstr = "";
                                String inputstr = "";
                                if (rs.getString("filter").equals("yes")) {
                                    filterids += " -- " + rs.getString("inputid") + "filter";;
                                    filterinputtype += " -- " + rs.getString("inputtype");
                                }
                                if (rs.getInt("inputorder") == 0) {
                                    colbuilder.append("");
                                } else {
                                    colbuilder.append("<div class='col-lg-" + rs.getInt("gridlarge") + " col-md-" + rs.getInt("gridmedium") + " col-sm-" + rs.getInt("gridsmall") + " form-group'>");
                                    if (rs.getString("label").equals("na")) {

                                    } else {
                                        labelstr += "<label class='" + rs.getString("labelclass") + "'>" + rs.getString("label") + "</label>";
                                    }

                                    dropdowntype += " -- " + rs.getString("inputtype");
                                    dropdownids += " -- " + rs.getString("inputid");
                                    dropdowntblname += " -- " + rs.getString("dropdowntbl");
                                    dropdowndisplayvalue += " -- " + rs.getString("displayvalue");
                                    dropdownsavevalue += " -- " + rs.getString("savevalue");
                                    dropdwncondfield += " -- " + rs.getString("dropdwncondfield");
                                    dropdwncondcol += " -- " + rs.getString("dropdwncondcol");
                                    dropcolname += " -- " + rs.getString("colname");
                                    addfunction += " -- " + rs.getString("additionalfunction");

                                    if (rs.getString("inputtype").equals("generatedid")) {
                                        inputstr = getinputstr(docid, rs.getString("inputtype"), rs.getString("inputid"), rs.getString("inputclass"), rs.getString("inputplaceholder"), generatedid);
                                    } else {
                                        inputstr = getinputstr(docid, rs.getString("inputtype"), rs.getString("inputid"), rs.getString("inputclass"), rs.getString("inputplaceholder"), "");
                                    }
                                    colbuilder.append(labelstr);
                                    colbuilder.append(inputstr);
                                    colbuilder.append(" </div>");
                                    //for save edit
                                    if (rs.getString("inputid").equals("na")) {

                                    } else {
                                        inputtypefrsave += " -- " + rs.getString("inputtype");
                                        inputidvalfrsave += " -- " + rs.getString("inputid");
                                        inputmandatoryfrsave += " -- " + rs.getString("mandatory");
                                        validationmsgfrsave += " -- " + rs.getString("validationmessage");
                                        if (rs.getString("displayvalue").equals(rs.getString("savevalue"))) {
                                            samevalue += " -- " + "yes";

                                        } else {
                                            samevalue += " -- " + "no";
                                        }
                                    }
                                }
                                formbuilder.append(colbuilder);
                            }
                            formbuilder.append(" </div>");
                            String formfooter = "";
                            formfooter = "<div class=\"row form-group\">\n"
                                    + "                                    <div class=\"col-lg-4 col-md-4  col-sm-0 form-group\">\n"
                                    + "\n<label class='blacklbl'>Updated Details:</label><label class='blacklbl' id='formlastupdatedby'></label>"
                                    + "                                    </div>\n"
                                    + "\n"
                                    + "                                    <div class=\"col-lg-2 col-md-2  col-sm-6 form-group\">\n"
                                    + "                                        <button type=\"button\" id='formsavebtn' class=\"btn btn-primary\" style=\"width: 100%;\" "
                                    + "onclick=\"Saveform('" + inputtypefrsave + "','" + inputidvalfrsave + "','" + inputmandatoryfrsave + "','" + validationmsgfrsave + "','" + filterinputtype + "','" + filterids + "','" + samevalue + "');\">Save</button>\n"
                                    + "                                    </div>\n"
                                    + "                                    <div class=\"col-lg-2 col-md-2  col-sm-6 form-group\" hidden>\n"
                                    + "                                        <button type=\"button\" id='formeditbtn' class=\"btn btn-secondary\" style=\"width: 100%;\" onclick=\"Editform('" + inputtypefrsave + "','" + inputidvalfrsave + "');\">Edit</button>\n"
                                    + "                                    </div>\n"
                                    + "                                    <div class=\"col-lg-2 col-md-2  col-sm-6 form-group\">\n"
                                    + "                                        <button type=\"button\" class=\"btn btn-danger\" style=\"width: 100%;\" onclick=\"Deleteform();\">Delete</button>\n"
                                    + "                                    </div>\n"
                                    + "                                    <div class=\"col-lg-2 col-md-2  col-sm-6 form-group\">\n"
                                    + "                                        <button type=\"button\" class=\"btn btn-warning\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Close Modal\" data-original-title=\"Close Modal\" style=\"width: 100%;\" "
                                    + "onclick=\"Clearform('" + inputtypefrsave + "','" + inputidvalfrsave + "');\" id='formclearbtn'>Clear</button>\n"
                                    + "                                    </div>\n"
                                    + "                                </div>\n";
                            String drpdwnstring = " `` " + dropdowntype + " `` " + dropdownids + " `` " + dropdowntblname + " `` " + dropdowndisplayvalue + " `` " + dropdownsavevalue
                                    + " `` " + dropdwncondfield + " `` " + dropdwncondcol + " `` " + dropcolname + " `` " + addfunction;
                            formbuilder.append(formfooter + drpdwnstring);
                            out1.print(formbuilder);
                            rs.close();
                            ps.close();
                        } catch (Exception e) {
                            out1.print("table exception -" + e);
                        }

                    }
                    break;
                    case "generateTbl": {

                        String tblname = request.getParameter("tblname");
                        String docid = request.getParameter("docid");
                        String generatedid = request.getParameter("generatedid");
                        String filterdata = request.getParameter("filterdata");
                        //  System.out.print("filterdata -" + filterdata);
                        String qryandcol = gettblqry(docid, tblname, filterdata, generatedid);
                        System.out.println("qryandcol -" + qryandcol);
                        String[] qryandcolarray = qryandcol.split(" `` ");
                        String Sql = qryandcolarray[0];
                        System.out.print("tblsql -" + Sql);
                        String Strcolnames = qryandcolarray[1];
                        String Strheaders = qryandcolarray[2];
                        String Strinputcol = qryandcolarray[3];
                        String Strinputid = qryandcolarray[4];
                        String Strinputtype = qryandcolarray[5];
                        String Strsamevalue = qryandcolarray[6];

                        String[] Strcolnamesarray = Strcolnames.split(" -- ");
                        String[] Strheadersarray = Strheaders.split(" -- ");
                        String[] Strinputcolarray = Strinputcol.split(" -- ");

                        out1.print(" <table id=\"Table1\"  class=\"table table-dark table-bordered \" style=\"width: 100%;\"> \n"
                                + "        <thead>\n"
                                + "            <tr>\n");
                        for (int l = 0; l < Strheadersarray.length; l++) {
                            out1.print("                <th>" + Strheadersarray[l] + "</th>\n");
                        }
                        out1.print("                <th>Action</th>\n"
                                + "            </tr>\n"
                                + "        </thead>\n"
                                + "        <tbody>");

                        try {

                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;
                                String inputvaluestoshow = "";
                                out1.print("<tr>\n"
                                        + " <td>" + i + "</td>\n");
                                for (int l = 0; l < Strcolnamesarray.length; l++) {
                                    if (l == 0) {

                                    } else {
                                        out1.print("                <td>" + rs.getString(Strcolnamesarray[l]) + "</td>\n");
                                    }
                                }

                                for (int l = 0; l < Strinputcolarray.length; l++) {
                                    inputvaluestoshow += " -- " + rs.getString(Strinputcolarray[l]);

                                }
                                out1.print("<td><button class='btn btn-sm btn-secondary' "
                                        + "onclick=\"viewform('" + rs.getString(Strcolnamesarray[0]) + "','" + inputvaluestoshow + "','" + Strinputid + "','" + Strinputtype + "','" + Strsamevalue + "')\">"
                                        + "<i class=\"fa fa-edit\" style=\"font-size:18px;color: #46b8da;\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"View\" data-original-title=\"View\"></i>Edit</button></td>\n");
                                out1.print("</tr> \n");
                            }
                            rs.close();
                            ps.close();
                        } catch (Exception e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "generatedatatlist": {

                        String tblname = request.getParameter("tblname");
                        String docid = request.getParameter("docid");

                        String displayval = request.getParameter("displayval");
                        String saveval = request.getParameter("saveval");
                        String limit = request.getParameter("limit");
                        String dropdwncondfield = request.getParameter("dropdwncondfield");
                        String dropdwncondcol = request.getParameter("dropdwncondcol");
                        String condvalue = request.getParameter("condvalue");
                        String cond = "";

                        String searchvalue = request.getParameter("searchvalue");
                        if (dropdwncondfield.equals("na")) {

                        } else {
                            cond = " and " + dropdwncondcol + " like '%" + condvalue + "%' ";
                        }
                        try {
                            String Sql = "select " + displayval + "," + saveval + " from " + tblname + " where concat_ws(''," + displayval + "," + saveval + ") like '%" + searchvalue + "%' " + cond;
                            System.out.print(Sql);

                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                i++;

                                out1.print(" <option id='" + rs.getString("" + saveval + "") + "' value='" + rs.getString("" + displayval + "") + "'>");

                            }
                            rs.close();
                            ps.close();
                        } catch (Exception e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "generatenormaldropdown": {

                        String tblname = request.getParameter("tblname");
                        String docid = request.getParameter("docid");

                        String displayval = request.getParameter("displayval");
                        String saveval = request.getParameter("saveval");
                        String limit = request.getParameter("limit");
                        String dropdwncondfield = request.getParameter("dropdwncondfield");
                        String dropdwncondcol = request.getParameter("dropdwncondcol");
                        String condvalue = request.getParameter("condvalue");

                        String colname = request.getParameter("colname");
                        String onchangefunct = getchangefunction(docid, colname);

                        String cond = "";
                        if (dropdwncondfield.equals("na")) {

                        } else {
                            cond = " where " + dropdwncondcol + " = '" + condvalue + "' ";
                        }

                        try {
                            String Sql = "select " + displayval + "," + saveval + " from " + tblname + " " + cond;
                            // System.out.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;
                            out1.print(" <option  value=''>-- Select " + displayval + "--</option>");
                            while (rs.next()) {
                                i++;

                                out1.print(" <option  value='" + rs.getString("" + saveval + "") + "'>" + rs.getString("" + displayval + "") + "</option>");

                            }

                            out1.print(onchangefunct);

                            rs.close();
                            ps.close();
                        } catch (Exception e) {
                            out1.print(e);
                        }
                    }
                    break;
                    case "onblurfortext": {

                        String docid = request.getParameter("docid");

                        String colname = request.getParameter("colname");
                        String onchangefunct = getchangefunction(docid, colname);

                        out1.print(onchangefunct);

                    }
                    break;
                    case "gettextvalue": {

                        String tblname = request.getParameter("tblname");
                        String docid = request.getParameter("docid");

                        String displayval = request.getParameter("displayval");
                        String saveval = request.getParameter("saveval");
                        String limit = request.getParameter("limit");
                        String dropdwncondfield = request.getParameter("dropdwncondfield");
                        String dropdwncondcol = request.getParameter("dropdwncondcol");
                        String condvalue = request.getParameter("condvalue");

                        String colname = request.getParameter("colname");
                        //    String onchangefunct = getchangefunction(docid, colname);

                        String cond = "";
                        if (dropdwncondfield.equals("na")) {

                        } else {
                            cond = " where " + dropdwncondcol + " = '" + condvalue + "' ";
                        }

                        try {
                            String Sql = "select " + displayval + " as displayvalue," + saveval + " as savevalue from " + tblname + " " + cond;
                            System.out.print("text sql- " + Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();
                            int i = 0;

                            if (rs.next()) {
                                i++;

                                out1.print(rs.getString("savevalue"));

                            }

                            rs.close();
                            ps.close();
                        } catch (Exception e) {
                            out1.print(e);
                        }
                    }
                    break;

                    case "generatemultiselectdropdownwithexistingdata": {

                        String docid = request.getParameter("docid");

                        String selectedinputid = request.getParameter("selectedinputid");
                        String selectedinputvalues = request.getParameter("selectedinputvalues");
                        String getrequireddatafordropdown = getrequireddatafordropdown(docid, selectedinputid);
                        System.out.print(getrequireddatafordropdown);
                        String[] alldata = getrequireddatafordropdown.split(" -- ");

                        String tblname = alldata[1];
                        String displayval = alldata[2];
                        String saveval = alldata[3];
                        //  String limit = request.getParameter("limit");
                        String dropdwncondfield = alldata[4];
                        String dropdwncondcol = alldata[5];
                        String condvalue = "";

                        String colname = alldata[6];
                        String[] indFea = selectedinputvalues.split(",");
                        String cond = "";
                        if (dropdwncondfield.equals("na")) {

                        } else {
                            cond = " where " + dropdwncondcol + " = '" + condvalue + "' ";
                        }

                        try {
                            String Sql = "select " + displayval + "," + saveval + " from " + tblname + " " + cond;
                            // System.out.print(Sql);
                            PreparedStatement ps = con.prepareStatement(Sql);
                            ResultSet rs = ps.executeQuery();

                            while (rs.next()) {
                                boolean selected = false;
                                int k = indFea.length;
                                int select = 0;
                                for (int i = 0; i < k; i++) {

                                    selected = indFea[i].equals(rs.getString("" + saveval + ""));
                                    if (selected) {
                                        select++;
                                    }

                                }
                                if (select != 0) {
                                    out1.print(" <option  selected value=\"" + rs.getString("" + saveval + "") + "\" >" + rs.getString("" + displayval + "") + "</option>");
                                } else {
                                    out1.print(" <option value=\"" + rs.getString("" + saveval + "") + "\">" + rs.getString("" + displayval + "") + "</option>");
                                }

                            }

                            rs.close();
                            ps.close();
                        } catch (Exception e) {
                            out1.print(e);
                        }
                    }
                    break;
                }
            }
            connectionPool.free(con);
        } catch (Exception ex) {
            out1.print(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        java.util.Date today = new java.util.Date();
        String action = request.getParameter("Action");
        try {
            ConnectionPool connectionPool = new ConnectionPool(
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://" + dblocation + "/" + dbName + "", "" + dbUser + "", "" + dbPass + "",
                    1, 50, true);
            con = connectionPool.getConnection();
            if (null != action) {
                switch (action) {
                    case "autoSaveform": {
                        String docid = request.getParameter("docid");
                        String user = request.getParameter("user");
                        String ID = request.getParameter("ID");
                        String valuesfrsave = request.getParameter("valuesfrsave");
                        String tblname = request.getParameter("tblname");
                        //String generatedid = request.getParameter("generatedid");

                        String Sql = "";

                        try {
                            if (ID.equals("0")) {
                                Sql = getinsertqry(docid, ID, valuesfrsave, user, tblname);
                                System.out.print("insert -" + Sql);
                            } else {
                                Sql = getupdateqry(docid, ID, valuesfrsave, user, tblname);
                                System.out.print("Update -" + Sql);
                            }

                            PreparedStatement ps = con.prepareStatement(Sql);
                            int success = ps.executeUpdate();
                            if (success > 0) {
                                out.print("Data Saved successfully");
                            }

                            //out.print(sql);
                        } catch (SQLException e) {
                            out.print(e);
                        }

                    }
                    break;
                    case "deletedata": {

                        String table = request.getParameter("tblname");

                        String ID = request.getParameter("Id");
                        String deletetbl=request.getParameter("deletetbl");
                           String deletecol=request.getParameter("deletecol");
                           String deletemaincol=request.getParameter("deletemaincol");
                           
                         String sql = "";
                        if("".equals(deletetbl)){
                             sql = "Delete from " + table + " where id=" + ID + "";
                        try {
                            PreparedStatement ps = con.prepareStatement(sql);
                            //out.print(sql);
                            int success = ps.executeUpdate();
                            if (success > 0) {
                                out.print("Data deleted Successfully");
                            }
                            ps.close();
                        } catch (SQLException e) {
                            out.print(e);
                        }
                        }else{
                          
                            
                            String[] deletetbls=deletetbl.split(" -- ");
                              System.out.print("\n deletetbls.length-"+deletetbls.length);
                             String[] deletecols=deletecol.split(" -- ");
                             int deleteno=0;
                            
                            for(int i=0;i<deletetbls.length;i++){
                                 try {
                                String selectdeletechecksql="Select id from "+deletetbls[i]+" where "+deletecols[i]+" in (select "+deletemaincol+" from " + table + " where id=" + ID + ") limit 1";
                                System.out.print("\n deletechecksql-"+selectdeletechecksql);
                                 PreparedStatement ps = con.prepareStatement(selectdeletechecksql);
                            ResultSet rs = ps.executeQuery();

                            if (rs.next()) {
                                deleteno++;
                            }
                                 } catch (SQLException e) {
                            out.print(e);
                        }
                            }
                            if(deleteno==0){
                                 sql = "Delete from " + table + " where id=" + ID + "";
                        try {
                            PreparedStatement ps = con.prepareStatement(sql);
                            //out.print(sql);
                            int success = ps.executeUpdate();
                            if (success > 0) {
                                out.print("Data deleted Successfully");
                            }
                            ps.close();
                        } catch (SQLException e) {
                            out.print(e);
                        }
                            }else{
                                out.print("This data was already used");
                            }
                        }


                       

                       
                    }
                    break;
                
                }
            }
            connectionPool.free(con);
        } catch (Exception ex) {
            out.print(ex);
        }
    }

    private String getinputstr(String docid, String inputtype, String inputid, String inputclass, String inputplaceholder, String setvalue) {
        String strthtml = "";
        if (inputplaceholder.equals("na")) {
            inputplaceholder = "";
        }
        switch (inputtype) {
            case "Idval":
                break;
            case "generatedid":
                strthtml = "   <input type=\"text\" class=\"form-control " + inputclass + "\" id=\"" + inputid + "\" placeholder=\"" + inputplaceholder + "\"  value='" + setvalue + "' readonly/>";
                break;
            case "checkbox":
                strthtml = "   <input type=\"checkbox\" class=\"form-control " + inputclass + "\" id=\"" + inputid + "\" checked/>";
                break;
            case "inputtext":
                strthtml = "   <input type=\"text\" class=\"form-control " + inputclass + "\" id=\"" + inputid + "\" placeholder=\"" + inputplaceholder + "\"  />";
                break;
            case "inputdate":
                strthtml = "   <input type=\"date\" class=\"form-control " + inputclass + "\" id=\"" + inputid + "\" placeholder=\"" + inputplaceholder + "\"   />";
                break;
                 case "inputtime":
                strthtml = "   <input type=\"time\" class=\"form-control " + inputclass + "\" id=\"" + inputid + "\" placeholder=\"" + inputplaceholder + "\"   />";
                break;
                  case "inputdatetime":
                strthtml = "   <input type=\"local\" class=\"form-control " + inputclass + "\" id=\"" + inputid + "\" placeholder=\"" + inputplaceholder + "\"   />";
                break;
                  case "datetime":
                strthtml = "   <input type=\"datetime-local\" class=\"form-control " + inputclass + "\" id=\"" + inputid + "\" placeholder=\"" + inputplaceholder + "\"   />";
                break;
            case "inputpassword":
                strthtml = "   <input type=\"password\" class=\"form-control " + inputclass + "\" id=\"" + inputid + "\" placeholder=\"" + inputplaceholder + "\"  />";
                break;
            case "inputtextwithgetdata":
                strthtml = "   <input type=\"text\" class=\"form-control " + inputclass + "\" id=\"" + inputid + "\" placeholder=\"" + inputplaceholder + "\"  />";
                break;
            case "inputnumberonly":
                strthtml = "   <input type=\"text\" class=\"form-control " + inputclass + "\" id=\"" + inputid + "\" placeholder=\"" + inputplaceholder + "\" onkeypress=\"return (event.charCode > 47 && event.charCode < 58)\" />";
                break;
            case "inputdecimalonly":
                strthtml = "   <input type=\"text\" class=\"form-control " + inputclass + "\" id=\"" + inputid + "\" placeholder=\"" + inputplaceholder + "\" onkeypress=\"return (event.charCode > 47 && event.charCode < 58) || (event.charCode == 46)\" />";
                break;
            case "normaldropdown":
                strthtml = "   <Select type=\"text\" class=\"form-control " + inputclass + "\" id=\"" + inputid + "\" placeholder=\"" + inputplaceholder + "\"  ><select>";
                break;
            case "normaldropdownwithcondition":
                strthtml = "   <Select type=\"text\" class=\"form-control " + inputclass + "\" id=\"" + inputid + "\" placeholder=\"" + inputplaceholder + "\"  ><select>";
                break;
            case "dropdowndatalist":
                strthtml = "   <input type=\"text\" autocomplete=\"off\" class=\"form-control " + inputclass + "\" list=\"" + inputid + "list\" id=\"" + inputid + "\" placeholder=\"" + inputplaceholder + "\"  />"
                        + " <datalist id=\"" + inputid + "list\"></datalist>";

                break;

            case "dropdowndatalistwithgetdata":
                strthtml = "   <input type=\"text\" autocomplete=\"off\" class=\"form-control " + inputclass + "\" list=\"" + inputid + "list\" id=\"" + inputid + "\" placeholder=\"" + inputplaceholder + "\"  />"
                        + " <datalist id=\"" + inputid + "list\"></datalist>";

                break;

            case "multiselectdropdown":
                strthtml = "   <div id='div" + inputid + "'><Select type=\"text\" class=\"form-control " + inputclass + "\" id=\"" + inputid + "\" placeholder=\"" + inputplaceholder + "\"  multiple><select></div>";
                break;
            case "textarea":
                strthtml = "   <textarea type=\"text\" class=\"form-control " + inputclass + "\" id=\"" + inputid + "\" placeholder=\"" + inputplaceholder + "\"  ></textarea>";
                break;
            default:
                break;
        }
        return strthtml;
    }

    private String getinsertqry(String docid, String ID, String valuesfrsave, String user, String tblname) {
        String[] valuesfrsavearray = valuesfrsave.split(" -- ");
        System.out.print("valus-" + valuesfrsave);
        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
        String message = "";

        StringBuilder qrybuilder = new StringBuilder();

        qrybuilder.append("Insert into " + tblname + " ");

        String allcolnames = "";
        String allcolvalues = "";
        try {
            String Sql = "Select docid,tablename,colname,coltype,label,labelclass,inputtype,inputplaceholder,inputid,inputclass,mandatory,validationmessage,dropdowntbl,displayvalue,"
                    + "savevalue,dropdwncondfield,dropdwncondcol,gridlarge,gridmedium,gridsmall,cast(inputorder AS UNSIGNED) inputorder from formcreation "
                    + "where docid='" + docid + "' order by cast(inputorder AS UNSIGNED) asc";
            PreparedStatement ps = con.prepareStatement(Sql);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                if (rs.getString("inputtype").equals("idval") || rs.getString("inputtype").equals("default")) {

                } else {
                    String colname = rs.getString("colname");
                    String colval = "";
                    
                    if (rs.getInt("inputorder") == 0) {
                        if (rs.getString("inputtype").equals("currenttime")) {
                            colval = DbEnteredDate.toString();
                        } else if (rs.getString("inputtype").equals("currentuser")) {
                            colval = user;
                        }
                        allcolnames += colname + ",";
                        allcolvalues += "'" + colval + "',";
                    } else {
 System.out.print("\n"+colname);
                        colval = valuesfrsavearray[rs.getInt("inputorder") - 1];
                        allcolnames += colname + ",";
                        allcolvalues += "'" + colval + "',";
                        
                    }

                }
            }
            allcolnames = allcolnames.substring(0, allcolnames.length() - 1);
            allcolnames = "(" + allcolnames + ")";
            allcolvalues = allcolvalues.substring(0, allcolvalues.length() - 1);
            allcolvalues = "(" + allcolvalues + ")";
            qrybuilder.append(allcolnames + " values " + allcolvalues);

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print(e);
        }
        return qrybuilder.toString();
    }

    private String getupdateqry(String docid, String ID, String valuesfrsave, String user, String tblname) {
        String[] valuesfrsavearray = valuesfrsave.split(" -- ");
        System.out.print("valus-" + valuesfrsave);
        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
        String message = "";

        StringBuilder qrybuilder = new StringBuilder();

        qrybuilder.append("update " + tblname + " set ");

        String allcolvalues = "";
        try {
            String Sql = "Select docid,tablename,colname,coltype,label,labelclass,inputtype,inputplaceholder,inputid,inputclass,mandatory,validationmessage,dropdowntbl,displayvalue,"
                    + "savevalue,dropdwncondfield,dropdwncondcol,gridlarge,gridmedium,gridsmall,cast(inputorder AS UNSIGNED) inputorder from formcreation where docid='" + docid + "' order by cast(inputorder AS UNSIGNED) asc";
            PreparedStatement ps = con.prepareStatement(Sql);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            int enterbyskip = 0;
            int enterdateskip = 0;
            while (rs.next()) {
                if (rs.getString("inputtype").equals("idval") || rs.getString("inputtype").equals("default")) {

                } else {
                    String colname = rs.getString("colname");
                    String colval = "";
                    if (rs.getInt("inputorder") == 0) {

                        if (rs.getString("inputtype").equals("currenttime")) {
                            enterdateskip++;

                            colval = DbEnteredDate.toString();
                            if (enterdateskip != 1) {

                                allcolvalues += colname + "=" + "'" + colval + "',";
                            }
                        } else if (rs.getString("inputtype").equals("currentuser")) {
                            enterbyskip++;
                            colval = user;
                            if (enterbyskip != 1) {

                                allcolvalues += colname + "=" + "'" + colval + "',";
                            }
                        }

                    } else {

                        colval = valuesfrsavearray[rs.getInt("inputorder") - 1];
                        allcolvalues += colname + "=" + "'" + colval + "',";
                    }

                }
            }

            allcolvalues = allcolvalues.substring(0, allcolvalues.length() - 1);

            qrybuilder.append(allcolvalues + " where Id='" + ID + "'");

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print(e);
        }
        return qrybuilder.toString();
    }

    private String getchangefunction(String docid, String colname) {

        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
        String message = "";

        StringBuilder qrybuilder = new StringBuilder();
        String dropdowntype = "";
        String dropdownids = "";
        String dropdowntblname = "";
        String dropdowndisplayvalue = "";
        String dropdownsavevalue = "";
        String dropdwncondfield = "";
        String dropdwncondcol = "";
        String drpcolname = "";
        String drpdwnstring = "";
        try {
            String Sql = "Select docid,tablename,colname,coltype,label,labelclass,inputtype,inputplaceholder,inputid,inputclass,mandatory,validationmessage,dropdowntbl,displayvalue,"
                    + "savevalue,dropdwncondfield,dropdwncondcol,gridlarge,gridmedium,gridsmall,cast(inputorder AS UNSIGNED) inputorder from "
                    + "formcreation where docid='" + docid + "' and dropdwncondfield='" + colname + "' order by cast(inputorder AS UNSIGNED) asc";
            //  System.out.print(Sql);
            PreparedStatement ps = con.prepareStatement(Sql);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            int enterbyskip = 0;
            int enterdateskip = 0;
            while (rs.next()) {
                //  if ((rs.getString("inputtype").equals("dropdowndatalist") || rs.getString("inputtype").equals("normaldropdown") || rs.getString("inputtype").equals("normaldropdownwithcondition")) && (!"na".equals(rs.getString("dropdwncondcol")))) {
                dropdowntype += " -- " + rs.getString("inputtype");
                dropdownids += " -- " + rs.getString("inputid");
                dropdowntblname += " -- " + rs.getString("dropdowntbl");
                dropdowndisplayvalue += " -- " + rs.getString("displayvalue");
                dropdownsavevalue += " -- " + rs.getString("savevalue");
                dropdwncondfield += " -- " + rs.getString("dropdwncondfield");
                dropdwncondcol += " -- " + rs.getString("dropdwncondcol");
                drpcolname += " -- " + rs.getString("colname");
                // }
            }
            drpdwnstring = " `` " + dropdowntype + " `` " + dropdownids + " `` " + dropdowntblname + " `` " + dropdowndisplayvalue + " `` " + dropdownsavevalue + " `` " + dropdwncondfield + " `` " + dropdwncondcol + " `` " + drpcolname;

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print(e);
        }
        return drpdwnstring;
    }

    private String getchangefunctiontext(String docid, String colname) {

        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
        String message = "";

        StringBuilder qrybuilder = new StringBuilder();
        String dropdowntype = "";
        String dropdownids = "";
        String dropdowntblname = "";
        String dropdowndisplayvalue = "";
        String dropdownsavevalue = "";
        String dropdwncondfield = "";
        String dropdwncondcol = "";
        String drpcolname = "";
        String drpdwnstring = "";
        try {
            String Sql = "Select docid,tablename,colname,coltype,label,labelclass,inputtype,inputplaceholder,inputid,inputclass,mandatory,validationmessage,dropdowntbl,displayvalue,"
                    + "savevalue,dropdwncondfield,dropdwncondcol,gridlarge,gridmedium,gridsmall,cast(inputorder AS UNSIGNED) inputorder from "
                    + "formcreation where docid='" + docid + "' and dropdwncondfield='" + colname + "' order by cast(inputorder AS UNSIGNED) asc";
            //  System.out.print(Sql);
            PreparedStatement ps = con.prepareStatement(Sql);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            int enterbyskip = 0;
            int enterdateskip = 0;
            while (rs.next()) {
                /// if ((rs.getString("inputtype").equals("dropdowndatalist") || rs.getString("inputtype").equals("normaldropdown") || rs.getString("inputtype").equals("normaldropdownwithcondition")) && (!"na".equals(rs.getString("dropdwncondcol")))) {
                dropdowntype += " -- " + rs.getString("inputtype");
                dropdownids += " -- " + rs.getString("inputid");
                dropdowntblname += " -- " + rs.getString("dropdowntbl");
                dropdowndisplayvalue += " -- " + rs.getString("displayvalue");
                dropdownsavevalue += " -- " + rs.getString("savevalue");
                dropdwncondfield += " -- " + rs.getString("dropdwncondfield");
                dropdwncondcol += " -- " + rs.getString("dropdwncondcol");
                drpcolname += " -- " + rs.getString("colname");
                // }
            }
            drpdwnstring = " `` " + dropdowntype + " `` " + dropdownids + " `` " + dropdowntblname + " `` " + dropdowndisplayvalue + " `` " + dropdownsavevalue + " `` " + dropdwncondfield + " `` " + dropdwncondcol + " `` " + drpcolname;

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print(e);
        }
        return drpdwnstring;
    }

    private String getrequireddatafordropdown(String docid, String inputid) {

        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
        String message = "";

        String drpdwnstring = "";
        try {
            String Sql = "Select docid,tablename,colname,coltype,label,labelclass,inputtype,inputplaceholder,inputid,inputclass,mandatory,validationmessage,dropdowntbl,displayvalue,"
                    + "savevalue,dropdwncondfield,dropdwncondcol,gridlarge,gridmedium,gridsmall,cast(inputorder AS UNSIGNED) inputorder from "
                    + "formcreation where docid='" + docid + "' and inputid='" + inputid + "' order by cast(inputorder AS UNSIGNED) asc";
            //  System.out.print(Sql);
            PreparedStatement ps = con.prepareStatement(Sql);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            int enterbyskip = 0;
            int enterdateskip = 0;
            while (rs.next()) {
                /// if ((rs.getString("inputtype").equals("dropdowndatalist") || rs.getString("inputtype").equals("normaldropdown") || rs.getString("inputtype").equals("normaldropdownwithcondition")) && (!"na".equals(rs.getString("dropdwncondcol")))) {
                drpdwnstring += " -- " + rs.getString("dropdowntbl") + " -- " + rs.getString("displayvalue") + " -- " + rs.getString("savevalue") + " -- " + rs.getString("dropdwncondfield") + " -- " + rs.getString("dropdwncondcol") + " -- " + rs.getString("colname");

                // }
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print(e);
        }
        return drpdwnstring;
    }

    private String gettblqry(String docid, String tblname, String filterdata, String generatedid) {

        java.sql.Timestamp DbEnteredDate = new java.sql.Timestamp(today.getTime());
        String message = "";
        String[] filterdataarray = filterdata.split(" -- ");
        StringBuilder qrybuilder = new StringBuilder();
        StringBuilder joinqrybuilder = new StringBuilder();
        StringBuilder qryconditionbuilder = new StringBuilder();
        qrybuilder.append("Select ");
        String sendcolnames = "";
        String sendtblheadernames = "";
        String sendcolnameforview = "";
        String sendinputidforview = "";
        String sendinputtypeforview = "";
        String samevalue = "";
        String allcolnames = "";
        String concatcol = "concat(";
        try {
            String Sql = "Select docid,tablename,colname,coltype,label,labelclass,inputtype,inputplaceholder,inputid,inputclass,mandatory,validationmessage,dropdowntbl,displayvalue,"
                    + "savevalue,dropdwncondfield,dropdwncondcol,gridlarge,gridmedium,gridsmall,inputorder,tblorder,tblheader,filter from formcreation where docid='" + docid + "' order by CAST(tblorder AS UNSIGNED) asc";
            
           
            PreparedStatement ps = con.prepareStatement(Sql);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            int nooffilter = 0;
            int noofcondition = 0;
            int enterbyskip = 0;
            int enterdateskip = 0;
            while (rs.next()) {
                if (rs.getString("inputtype").equals("generatedid")) {
                    if (noofcondition == 0) {
                        qryconditionbuilder.append(" where tbl." + rs.getString("colname") + " = '" + generatedid + "'");
                    } else {
                        qryconditionbuilder.append(" and tbl." + rs.getString("colname") + " = '" + generatedid + "'");
                    }

                    noofcondition++;
                }
                if (rs.getString("filter").equals("yes")) {
                    String filter = filterdataarray[nooffilter];
                    if (filter.equals("0")) {
                        filter = "";
                    }
                    if (noofcondition == 0) {
                        qryconditionbuilder.append(" where tbl." + rs.getString("colname") + " like '%" + filter + "%'");
                    } else {
                        qryconditionbuilder.append(" and tbl." + rs.getString("colname") + " like '%" + filter + "%'");
                    }
                    noofcondition++;
                    nooffilter++;
                }
                if (rs.getString("inputtype").equals("idval")) {
                    String colname = "tbl." + rs.getString("colname") + " as " + rs.getString("colname");
                    allcolnames += colname + ",";
                    sendcolnames += rs.getString("colname") + " -- ";
                    sendtblheadernames += rs.getString("tblheader") + " -- ";
                    if (rs.getString("inputid").equals("na")) {

                    } else {
                        //   sendcolnameforview += rs.getString("colname") + " -- ";
                        //  sendinputidforview += rs.getString("inputid") + " -- ";
                    }
                } else {
                    if (rs.getInt("tblorder") == 0) {
                        if (rs.getString("inputid").equals("na")) {

                        } else {
                            String colname = "tbl." + rs.getString("colname") + " as " + rs.getString("colname");
                            allcolnames += colname + ",";
                            sendcolnameforview += rs.getString("colname") + " -- ";
                            sendinputidforview += " -- " + rs.getString("inputid");
                            sendinputtypeforview += " -- " + rs.getString("inputtype");
                            if (rs.getString("displayvalue").equals(rs.getString("savevalue"))) {
                                samevalue += " -- " + "yes";

                            } else {
                                samevalue += " -- " + "no";
                            }
                        }
                    } else {

                        if (rs.getString("inputtype").equals("currenttime")) {
                            //  System.out.print("yes");
                            String colname = "DATE_FORMAT(tbl." + rs.getString("colname") + ",'%d %b,%y %T')";
                            concatcol += colname + ") as " + rs.getString("colname");
                            allcolnames += concatcol + ",";
                            sendcolnames += rs.getString("colname") + " -- ";
                            sendtblheadernames += rs.getString("tblheader") + " -- ";
                            sendcolnameforview += rs.getString("colname") + " -- ";
                            sendinputidforview += " -- " + "formlastupdatedby";
                            sendinputtypeforview += " -- " + "label";

                        } else if (rs.getString("inputtype").equals("currentuser")) {
                            String colname = rs.getString("colname") + "tbl." + rs.getString("displayvalue");
                            joinqrybuilder.append(" left join (select " + rs.getString("displayvalue") + "," + rs.getString("savevalue") + "  from " + rs.getString("dropdowntbl") + ") as " + rs.getString("colname") + "tbl "
                                    + "on (" + rs.getString("colname") + "tbl." + rs.getString("savevalue") + "=tbl." + rs.getString("colname") + ")");
                            concatcol += colname + ",' ',";

                        } else if (rs.getString("dropdowntbl").equals("na") || !rs.getString("inputtype").contains("dropdown")) {
                            String colname = "tbl." + rs.getString("colname") + " as " + rs.getString("colname");
                            allcolnames += colname + ",";
                            sendcolnames += rs.getString("colname") + " -- ";
                            sendtblheadernames += rs.getString("tblheader") + " -- ";
                            if (rs.getString("inputid").equals("na")) {

                            } else {
                                sendcolnameforview += rs.getString("colname") + " -- ";
                                sendinputidforview += " -- " + rs.getString("inputid");
                                sendinputtypeforview += " -- " + rs.getString("inputtype");
                                if (rs.getString("displayvalue").equals(rs.getString("savevalue"))) {
                                    samevalue += " -- " + "yes";

                                } else {
                                    samevalue += " -- " + "no";
                                }
                            }
                        } else {
                            String colname = rs.getString("colname") + "tbl.displayval as " + rs.getString("colname") + "," + rs.getString("colname") + " as act" + rs.getString("colname");
                            joinqrybuilder.append(" left join (select " + rs.getString("displayvalue") + " as displayval," + rs.getString("savevalue") + " as savevalue  from " + rs.getString("dropdowntbl") + ") as " + rs.getString("colname") + "tbl "
                                    + "on (" + rs.getString("colname") + "tbl.savevalue=tbl." + rs.getString("colname") + ")");

                            allcolnames += colname + ",";
                            sendcolnames += rs.getString("colname") + " -- ";
                            sendtblheadernames += rs.getString("tblheader") + " -- ";

                            if (rs.getString("inputid").equals("na")) {

                            } else {
                                sendcolnameforview += "act" + rs.getString("colname") + " -- ";
                                sendinputidforview += " -- " + rs.getString("inputid");
                                sendinputtypeforview += " -- " + rs.getString("inputtype");
                                if (rs.getString("displayvalue").equals(rs.getString("savevalue"))) {
                                    samevalue += " -- " + "yes";

                                } else {
                                    samevalue += " -- " + "no";
                                }
                            }
                        }
                    }

                }
            }
            allcolnames = allcolnames.substring(0, allcolnames.length() - 1);
            qrybuilder.append(allcolnames + " from " + tblname + " as tbl " + joinqrybuilder + " " + qryconditionbuilder + " order by tbl.id desc" + " `` " + sendcolnames + " `` " + sendtblheadernames + " `` " + sendcolnameforview + " `` " + sendinputidforview
                    + " `` " + sendinputtypeforview + " `` " + samevalue);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print("tbl qry error -" + e);
        }
        return qrybuilder.toString();
    }
}
