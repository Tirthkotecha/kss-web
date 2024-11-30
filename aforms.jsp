<%--
    Document   : index
    Created on : 13 Feb, 2024, 5:03:21 PM
    Author     : praveen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title id="autotitle"></title>
        <!-- plugins:css -->
        <link rel="stylesheet" href="assets/vendors/mdi/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="assets/vendors/css/vendor.bundle.base.css">
        <!-- endinject -->
        <!-- Plugin css for this page -->
        <link rel="stylesheet" href="assets/vendors/jvectormap/jquery-jvectormap.css">
        <link rel="stylesheet" href="assets/vendors/flag-icon-css/css/flag-icon.min.css">
        <link rel="stylesheet" href="assets/vendors/owl-carousel-2/owl.carousel.min.css">
        <link rel="stylesheet" href="assets/vendors/owl-carousel-2/owl.theme.default.min.css">
        <link href="assets/css/dataTables/datatables.min.css" rel="stylesheet">
        <!-- End plugin css for this page -->
        <!-- inject:css -->
        <!-- endinject -->
        <!-- Layout styles -->
        <link rel="stylesheet" href="assets/css/style.css">
        <!-- End layout styles -->
        <link rel="shortcut icon" href="assets/images/favicon.png" />
    </head>
    <style>


        input[type="checkbox"]{
            appearance: none;
            width: 5rem;
            height: 5rem;
            position: relative;

            &::after {
                content: "";
                position: absolute;
                top: 0;
                right: 0;
                bottom: 0;
                left: 0;
                color: rgba(white, 0);
                background-color: white;
                border-radius: 3px;
                box-shadow: inset 0 0 0 2px mediumvioletred;
                font: {
                    size: 0rem;
                    weight: bolder;
                }
                line-height: 5rem;
                text-align: center;
                transition:
                    background-color 350ms 0ms ease,
                    color 350ms 250ms ease,
                    font-size 350ms 250ms ease;
            }

            &:checked::after {
                content: "\02714";
                color: rgba(white, 1);
                background-color: mediumvioletred;
                font-size: 4rem;
            }
        }
        .left-col {
            float: left;
            width: 50%;
        }

        .center-col {
            float: left;
            width: 25%;
        }

        .right-col {
            float: left;
            width: 25%;
        }
        .form-control {
            color: white !important;
        }
        .form-control {
            background-color: #2A3038 !important;
            color: white !important;
        }
    </style>
    <body>
        <div class="container-scroller">
            <!--  <div class="row p-0 m-0 proBanner" id="proBanner">
                <div class="col-md-12 p-0 m-0">
                  <div class="card-body card-body-padding d-flex align-items-center justify-content-between">
                    <div class="ps-lg-1">
                      <div class="d-flex align-items-center justify-content-between">
                        <p class="mb-0 font-weight-medium me-3 buy-now-text">Free 24/7 customer support, updates, and more with this template!</p>
                        <a href="https://www.bootstrapdash.com/product/corona-free/?utm_source=organic&utm_medium=banner&utm_campaign=buynow_demo" target="_blank" class="btn me-2 buy-now-btn border-0">Get Pro</a>
                      </div>
                    </div>
                    <div class="d-flex align-items-center justify-content-between">
                      <a href="https://www.bootstrapdash.com/product/corona-free/"><i class="mdi mdi-home me-3 text-white"></i></a>
                      <button id="bannerClose" class="btn border-0 p-0">
                        <i class="mdi mdi-close text-white me-0"></i>
                      </button>
                    </div>
                  </div>
                </div>
              </div>-->
            <!-- partial:partials/_sidebar.html -->

            <!-- partial -->
            <div class="container-fluid full-page-wrapper">
                <!-- partial:partials/_navbar.html -->
                <%@include file="NavBar.jsp" %>
                <!-- partial -->
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="page-header">
                            <h3 class="page-title" id="maintitle"> Receiving Entry </h3>
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">KSS</a></li>
                                    <li class="breadcrumb-item active" aria-current="page" id="breadmainpage">Receiving Entry </li>
                                </ol>
                            </nav>
                        </div>
                        <!--  <div class="row">
                            <div class="col-12 grid-margin stretch-card">
                              <div class="card corona-gradient-card">
                                <div class="card-body py-0 px-0 px-sm-3">
                                  <div class="row align-items-center">
                                    <div class="col-4 col-sm-3 col-xl-2">
                                      <img src="assets/images/dashboard/Group126@2x.png" class="gradient-corona-img img-fluid" alt="">
                                    </div>
                                    <div class="col-5 col-sm-7 col-xl-8 p-0">
                                      <h4 class="mb-1 mb-sm-0">Want even more features?</h4>
                                      <p class="mb-0 font-weight-normal d-none d-sm-block">Check out our Pro version with 5 unique layouts!</p>
                                    </div>
                                    <div class="col-3 col-sm-2 col-xl-2 ps-0 text-center">
                                      <span>
                                        <a href="https://www.bootstrapdash.com/product/corona-admin-template/" target="_blank" class="btn btn-outline-light btn-rounded get-started-btn">Upgrade to PRO</a>
                                      </span>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>-->


                        <div class="row ">
                            <div class="col-12 grid-margin">
                                <div class="card">

                                    <div class="card-body">
                                        <div class="card-title m-2">
                                            <div class="row">
                                                <div class="col-2">
                                                    <h5 id="tabletitle"></h5>

                                                </div>
                                                <div class="col-9" id="filtercontainer">

                                                </div>
                                                <div class="col-lg-1" id="addnewcontainer">
                                                    <button  class="btn btn-primary btn-sm mt-4 float-right" onclick="addNew();" >Add New</button>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="table-responsive" id="tblcontainer">

                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>


                    </div>
                    <!-- content-wrapper ends -->
                    <!-- partial:partials/_footer.html -->
                    <footer class="footer">
                        <div class="d-sm-flex justify-content-center justify-content-sm-between">
                            <span class="text-muted d-block text-center text-sm-left d-sm-inline-block">Copyright © bootstrapdash.com 2021</span>
                            <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center"> Free <a href="https://www.bootstrapdash.com/bootstrap-admin-template/" target="_blank">Bootstrap admin template</a> from Bootstrapdash.com</span>
                        </div>
                    </footer>
                    <!-- partial -->
                </div>
                <!-- main-panel ends -->
            </div>
            <!-- page-body-wrapper ends -->
        </div>
        <div class="modal fade mo" id="formmodal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="exampleModalLabel" style="display: none;" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document ">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="ModalLabel"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="closemodal()">
                            <span aria-hidden="true ">×</span>
                        </button>
                    </div>
                    <div class="modal-body"  >
                        <div class="row" style=" width: 100%" >
                            <div class="col-12" id="formcontainer">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- container-scroller -->
        <!-- plugins:js -->
        <script src="assets/vendors/js/vendor.bundle.base.js"></script>
        <!-- endinject -->
        <!-- Plugin js for this page -->
        <script src="assets/vendors/chart.js/Chart.min.js"></script>
        <script src="assets/vendors/progressbar.js/progressbar.min.js"></script>
        <script src="assets/vendors/jvectormap/jquery-jvectormap.min.js"></script>
        <script src="assets/vendors/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
        <script src="assets/vendors/owl-carousel-2/owl.carousel.min.js"></script>
        <script src="assets/js/jquery.cookie.js" type="text/javascript"></script>
        <!-- End plugin js for this page -->
        <!-- inject:js -->
        <script src="assets/js/off-canvas.js"></script>
        <script src="assets/js/hoverable-collapse.js"></script>
        <script src="assets/js/misc.js"></script>
        <script src="assets/js/settings.js"></script>
        <script src="assets/js/todolist.js"></script>

        <script src="assets/js/dataTables/datatables.min.js"></script>
        <script src="assets/js/dataTables/dataTables.bootstrap4.min.js"></script>
        <script src="assets/js/jquery.blockUI.js"></script>
        <!-- endinject -->
        <!-- Custom js for this page -->
        <script src="assets/js/dashboard.js"></script>
        <!-- End custom js for this page -->
    </body>
    <script>



                            var generatedid = localStorage.getItem("generatedid");
                            var activedocid = localStorage.getItem("docid");

                            var ID = "0";
                            var user = "1";
                            var tblname = localStorage.getItem("tblname");
                            var formname = localStorage.getItem("formname");
                            var pagename = localStorage.getItem("formname");
                            document.getElementById('autotitle').innerHTML = formname;
                            document.getElementById('ModalLabel').innerHTML = formname;
                            document.getElementById('tabletitle').innerHTML = formname;
                            document.getElementById('maintitle').innerHTML = formname;
                            document.getElementById('breadmainpage').innerHTML = formname;
                            var universalinputtype = "";
                            var universalinputids = "";
                            init();
                            function init() {
                                var formtype = localStorage.getItem("formtype");
                                //alert(formtype);

                                getfilter();

                                generateform(activedocid);
                            }
                            function getfilter() {
                                $.ajax({
                                    type: "GET",
                                    url: "Formservlet",
                                    data: {Action: "generateFilter", docid: activedocid, tblname: tblname},
                                    success: function (resp) {
                                        //  alert(resp);
                                        if (resp == "") {
                                            gettbldata("", "");
                                        } else {
                                            var splitdata = resp.split(" `` ");
                                            // alert(resp);
                                            $("#filtercontainer").html(splitdata[0]);

                                            var inputtype = splitdata[1].split(" -- ");
                                            var inputids = splitdata[2].split(" -- ");
                                            var dropdowntblname = splitdata[3].split(" -- ");
                                            var dropdowndisplayvalue = splitdata[4].split(" -- ");
                                            var dropdownsavevalue = splitdata[5].split(" -- ");
                                            var dropdwncondfield = splitdata[6].split(" -- ");
                                            var dropdwncondcol = splitdata[7].split(" -- ");
                                            var drpcolname = splitdata[8].split(" -- ");

                                            for (var i = 1; i < inputids.length; i++) {

                                                var selectedinputtype = inputtype[i];
                                                selectedinputtype = new String(selectedinputtype);
                                                selectedinputtype = selectedinputtype.toString();


                                                var selecteddropdownids = inputids[i];
                                                selecteddropdownids = new String(selecteddropdownids);
                                                selecteddropdownids = selecteddropdownids.toString();

                                                var selecteddropdowntblname = dropdowntblname[i];
                                                selecteddropdowntblname = new String(selecteddropdowntblname);
                                                selecteddropdowntblname = selecteddropdowntblname.toString();

                                                var selecteddropdowndisplayvalue = dropdowndisplayvalue[i];
                                                selecteddropdowndisplayvalue = new String(selecteddropdowndisplayvalue);
                                                selecteddropdowndisplayvalue = selecteddropdowndisplayvalue.toString();

                                                var selecteddropdownsavevalue = dropdownsavevalue[i];
                                                selecteddropdownsavevalue = new String(selecteddropdownsavevalue);
                                                selecteddropdownsavevalue = selecteddropdownsavevalue.toString();


                                                var selecteddropdwncondfield = dropdwncondfield[i];
                                                selecteddropdwncondfield = new String(selecteddropdwncondfield);
                                                selecteddropdwncondfield = selecteddropdwncondfield.toString();

                                                var selecteddropdwncondcol = dropdwncondcol[i];
                                                selecteddropdwncondcol = new String(selecteddropdwncondcol);
                                                selecteddropdwncondcol = selecteddropdwncondcol.toString();

                                                var selectedcolname = drpcolname[i];
                                                selectedcolname = new String(selectedcolname);
                                                selectedcolname = selectedcolname.toString();

                                                if (selectedinputtype == "normaldropdown" || selectedinputtype == "normaldropdownwithcondition" || selectedinputtype == "multiselectdropdown") {
                                                    generatenormaldropdwndatafilter(selectedinputtype, selecteddropdownids, selecteddropdowntblname, selecteddropdowndisplayvalue, selecteddropdownsavevalue, selecteddropdwncondfield, selecteddropdwncondcol, "na", "", selectedcolname);


                                                } else if (selectedinputtype == "dropdowndatalist") {
                                                    generatedropdwndatatlist(selecteddropdownids + "list", selecteddropdowntblname, selecteddropdowndisplayvalue, selecteddropdownsavevalue, selecteddropdwncondfield, selecteddropdwncondcol, "na", "", selectedcolname);
                                                }
                                                //   generatedropdwndatatlist(selecteddropdownids + "list", selecteddropdowntblname, selecteddropdowndisplayvalue, selecteddropdownsavevalue, "na");

                                            }
                                            setTimeout(function () {
                                                universalinputtype = splitdata[1];
                                                universalinputids = splitdata[2];
                                                gettbldata(universalinputtype, universalinputids);
                                            }, 1000);
                                        }
                                    },
                                    error: function (xhr, ajaxOptions, thrownError)
                                    {

                                        alert("errorstatus: " + xhr.status + " ajaxoptions: " + ajaxOptions + " throwError: " + thrownError);

                                    }
                                });
                            }

                            function generatenormaldropdwndatafilter(maindropdowntype, dropdownmainids, tblname, displayval, saveval, dropdwncondfield, dropdwncondcol, limit, condvalue, drpcolname) {
                                $.ajax({
                                    type: "GET",
                                    url: "Formservlet",
                                    data: {Action: "generatenormaldropdown", docid: activedocid, tblname: tblname, displayval: displayval,
                                        saveval: saveval, dropdwncondfield: dropdwncondfield, dropdwncondcol: dropdwncondcol, limit: limit, condvalue: condvalue, colname: drpcolname},
                                    success: function (resp) {
                                        //    alert(resp);

                                        var splitdata = resp.split(" `` ");
                                        $("#" + dropdownmainids).html(splitdata[0]);


                                        var dropdowntype = splitdata[1].split(" -- ");
                                        var dropdownids = splitdata[2].split(" -- ");
                                        var dropdowntblname = splitdata[3].split(" -- ");
                                        var dropdowndisplayvalue = splitdata[4].split(" -- ");
                                        var dropdownsavevalue = splitdata[5].split(" -- ");

                                        var dropdwncondfield = splitdata[6].split(" -- ");
                                        var dropdwncondcol = splitdata[7].split(" -- ");
                                        var drpcolname = splitdata[8].split(" -- ");
                                        //alert(dropdownids.length);
                                        for (var i = 1; i < dropdownids.length; i++) {

                                            var selecteddropdownids = dropdownids[i];
                                            selecteddropdownids = new String(selecteddropdownids);
                                            selecteddropdownids = selecteddropdownids.toString() + "filter";

                                            var selecteddropdowntblname = dropdowntblname[i];
                                            selecteddropdowntblname = new String(selecteddropdowntblname);
                                            selecteddropdowntblname = selecteddropdowntblname.toString();

                                            var selecteddropdowndisplayvalue = dropdowndisplayvalue[i];
                                            selecteddropdowndisplayvalue = new String(selecteddropdowndisplayvalue);
                                            selecteddropdowndisplayvalue = selecteddropdowndisplayvalue.toString();

                                            var selecteddropdownsavevalue = dropdownsavevalue[i];
                                            selecteddropdownsavevalue = new String(selecteddropdownsavevalue);
                                            selecteddropdownsavevalue = selecteddropdownsavevalue.toString();

                                            var selecteddropdowntype = dropdowntype[i];
                                            selecteddropdowntype = new String(selecteddropdowntype);
                                            selecteddropdowntype = selecteddropdowntype.toString();

                                            var selecteddropdwncondfield = dropdwncondfield[i];
                                            selecteddropdwncondfield = new String(selecteddropdwncondfield);
                                            selecteddropdwncondfield = selecteddropdwncondfield.toString();

                                            var selecteddropdwncondcol = dropdwncondcol[i];
                                            selecteddropdwncondcol = new String(selecteddropdwncondcol);
                                            selecteddropdwncondcol = selecteddropdwncondcol.toString();

                                            var selectedcolname = drpcolname[i];
                                            selectedcolname = new String(selectedcolname);
                                            selectedcolname = selectedcolname.toString();

                                            $("#" + dropdownmainids).on('change', function () {

                                                var conditionvalue = document.getElementById(dropdownmainids).value;

                                                if (selecteddropdowntype == "normaldropdown" || selecteddropdowntype == "normaldropdownwithcondition") {
                                                    generatenormaldropdwndata(selecteddropdowntype, selecteddropdownids, selecteddropdowntblname, selecteddropdowndisplayvalue, selecteddropdownsavevalue, selecteddropdwncondfield, selecteddropdwncondcol, "na", conditionvalue, selectedcolname);
                                                } else {
                                                    generatedropdwndatatlist(selecteddropdownids + "list", selecteddropdowntblname, selecteddropdowndisplayvalue, selecteddropdownsavevalue, selecteddropdwncondfield, selecteddropdwncondcol, "na", conditionvalue, selectedcolname);
                                                }
                                            });

                                        }


                                    },
                                    error: function (xhr, ajaxOptions, thrownError)
                                    {

                                        alert("errorstatus: " + xhr.status + " ajaxoptions: " + ajaxOptions + " throwError: " + thrownError);

                                    }
                                });

                            }

                            function gettbldata(inputtype, inputids) {

                                var inputtypearray = inputtype.split(" -- ");
                                var inputidsarray = inputids.split(" -- ");
                                var filterdata = "";
                                // alert(inputtypearray + inputidsarray);
                                for (var i = 1; i < inputtypearray.length; i++) {
                                    var selectedinputtype = inputtypearray[i];
                                    selectedinputtype = new String(selectedinputtype);
                                    selectedinputtype = selectedinputtype.toString();


                                    var selectedinputid = inputidsarray[i];
                                    selectedinputid = new String(selectedinputid);
                                    selectedinputid = selectedinputid.toString();
                                    if (selectedinputtype == "dropdowndatalist") {

                                        var getvalue = "";
                                        var inputdrpdwn = document.getElementById(selectedinputid).value;
                                        if (inputdrpdwn == "") {

                                        } else {
                                            var datalisid = selectedinputid + "list";


                                            getvalue = $("#" + datalisid + " option[value='" + inputdrpdwn + "']").attr('id');
                                        }

                                        if (getvalue == "") {
                                            getvalue = "0";
                                        }
                                        filterdata += getvalue + " -- ";

                                    } else {

                                        var getvalue = document.getElementById(selectedinputid).value;

                                        if (getvalue == "") {
                                            getvalue = "0";
                                        }
                                        filterdata += getvalue + " -- ";


                                    }
                                }
                                $.blockUI({message: 'Loading, please wait..'});
                                $.ajax({
                                    type: "GET",
                                    url: "Formservlet",
                                    data: {Action: "generateTbl", docid: activedocid, tblname: tblname, filterdata: filterdata, generatedid: generatedid},
                                    success: function (resp) {
                                        $.unblockUI();
                                        //alert(resp);
                                        $("#tblcontainer").html(resp);

                                        $('.dataTables-example').DataTable({
                                            pageLength: 25,
                                            responsive: true,
                                            dom: '<"html5buttons"B>lTfgitp',
                                            buttons: [
                                                {extend: 'copy'},
                                                {extend: 'csv'},
                                                {extend: 'excel', title: 'ExampleFile'},
                                                {extend: 'pdf', title: 'ExampleFile'},

                                                {extend: 'print',
                                                    customize: function (win) {
                                                        $(win.document.body).addClass('white-bg');
                                                        $(win.document.body).css('font-size', '10px');

                                                        $(win.document.body).find('table')
                                                                .addClass('compact')
                                                                .css('font-size', 'inherit');
                                                    }
                                                }
                                            ]

                                        });


                                    },
                                    error: function (xhr, ajaxOptions, thrownError)
                                    {
                                        $.unblockUI();
                                        alert("errorstatus: " + xhr.status + " ajaxoptions: " + ajaxOptions + " throwError: " + thrownError);

                                    }
                                });
                            }

                            function generateform(docid) {
                                $.ajax({
                                    type: "GET",
                                    url: "Formservlet",
                                    data: {Action: "generateForm", docid: docid, tblname: tblname, generatedid: generatedid},
                                    success: function (resp) {
                                        var splitdata = resp.split(" `` ");

                                        $("#formcontainer").html(splitdata[0]);

                                        var dropdowntype = splitdata[1].split(" -- ");
                                        var dropdownids = splitdata[2].split(" -- ");
                                        var dropdowntblname = splitdata[3].split(" -- ");
                                        var dropdowndisplayvalue = splitdata[4].split(" -- ");
                                        var dropdownsavevalue = splitdata[5].split(" -- ");

                                        var dropdwncondfield = splitdata[6].split(" -- ");
                                        var dropdwncondcol = splitdata[7].split(" -- ");
                                        var drpcolname = splitdata[8].split(" -- ");
                                        var addfunc = splitdata[9].split(" -- ");

                                        for (var i = 1; i < dropdownids.length; i++) {
                                            var selecteddropdownids = dropdownids[i];
                                            selecteddropdownids = new String(selecteddropdownids);
                                            selecteddropdownids = selecteddropdownids.toString();

                                            var selecteddropdowntblname = dropdowntblname[i];
                                            selecteddropdowntblname = new String(selecteddropdowntblname);
                                            selecteddropdowntblname = selecteddropdowntblname.toString();

                                            var selecteddropdowndisplayvalue = dropdowndisplayvalue[i];
                                            selecteddropdowndisplayvalue = new String(selecteddropdowndisplayvalue);
                                            selecteddropdowndisplayvalue = selecteddropdowndisplayvalue.toString();

                                            var selecteddropdownsavevalue = dropdownsavevalue[i];
                                            selecteddropdownsavevalue = new String(selecteddropdownsavevalue);
                                            selecteddropdownsavevalue = selecteddropdownsavevalue.toString();

                                            var selecteddropdowntype = dropdowntype[i];
                                            selecteddropdowntype = new String(selecteddropdowntype);
                                            selecteddropdowntype = selecteddropdowntype.toString();

                                            var selecteddropdwncondfield = dropdwncondfield[i];
                                            selecteddropdwncondfield = new String(selecteddropdwncondfield);
                                            selecteddropdwncondfield = selecteddropdwncondfield.toString();

                                            var selecteddropdwncondcol = dropdwncondcol[i];
                                            selecteddropdwncondcol = new String(selecteddropdwncondcol);
                                            selecteddropdwncondcol = selecteddropdwncondcol.toString();

                                            var selectedcolname = drpcolname[i];
                                            selectedcolname = new String(selectedcolname);
                                            selectedcolname = selectedcolname.toString();

                                            var selectedaddfunc = addfunc[i];
                                            selectedaddfunc = new String(selectedaddfunc);
                                            selectedaddfunc = selectedaddfunc.toString();

                                            if (selecteddropdowntype == "normaldropdown" || selecteddropdowntype == "normaldropdownwithcondition" || selecteddropdowntype == "multiselectdropdown") {

                                                generatenormaldropdwndata(selecteddropdowntype, selecteddropdownids, selecteddropdowntblname, selecteddropdowndisplayvalue, selecteddropdownsavevalue, selecteddropdwncondfield, selecteddropdwncondcol, "na", "", selectedcolname, selectedaddfunc);
                                                if (selectedaddfunc == "change") {



                                                    onchangefortext(selectedcolname, selecteddropdownids);


                                                }

                                            } else if (selecteddropdowntype == "dropdowndatalist") {
                                                generatedropdwndatatlist(selecteddropdownids + "list", selecteddropdowntblname, selecteddropdowndisplayvalue, selecteddropdownsavevalue, selecteddropdwncondfield, selecteddropdwncondcol, "na", "", selectedcolname, selectedaddfunc);
                                            } else {

                                                if (selectedaddfunc == "blur") {



                                                    onblurfortext(selectedcolname, selecteddropdownids);


                                                }

                                            }
                                        }

                                    },
                                    error: function (xhr, ajaxOptions, thrownError)
                                    {

                                        alert("errorstatus: " + xhr.status + " ajaxoptions: " + ajaxOptions + " throwError: " + thrownError);

                                    }
                                });
                            }
                            function onchangefortext(drpcolname, dropdownmainids) {

                                $.ajax({
                                    type: "GET",
                                    url: "Formservlet",
                                    data: {Action: "onblurfortext", docid: activedocid, colname: drpcolname},
                                    success: function (resp) {
                                        //  alert(resp);

                                        var splitdata = resp.split(" `` ");
                                        var dropdowntype = splitdata[1].split(" -- ");
                                        var dropdownids = splitdata[2].split(" -- ");
                                        var dropdowntblname = splitdata[3].split(" -- ");
                                        var dropdowndisplayvalue = splitdata[4].split(" -- ");
                                        var dropdownsavevalue = splitdata[5].split(" -- ");
                                        var dropdwncondfield = splitdata[6].split(" -- ");
                                        var dropdwncondcol = splitdata[7].split(" -- ");
                                        var drpcolname = splitdata[8].split(" -- ");

                                        $("#" + dropdownmainids).on('change', function () {

                                            for (var i = 1; i < dropdownids.length; i++) {

                                                var selecteddropdownids = dropdownids[i];
                                                selecteddropdownids = new String(selecteddropdownids);
                                                selecteddropdownids = selecteddropdownids.toString();
                                                var selecteddropdowntblname = dropdowntblname[i];
                                                selecteddropdowntblname = new String(selecteddropdowntblname);
                                                selecteddropdowntblname = selecteddropdowntblname.toString();
                                                var selecteddropdowndisplayvalue = dropdowndisplayvalue[i];
                                                selecteddropdowndisplayvalue = new String(selecteddropdowndisplayvalue);
                                                selecteddropdowndisplayvalue = selecteddropdowndisplayvalue.toString();
                                                var selecteddropdownsavevalue = dropdownsavevalue[i];
                                                selecteddropdownsavevalue = new String(selecteddropdownsavevalue);
                                                selecteddropdownsavevalue = selecteddropdownsavevalue.toString();
                                                var selecteddropdowntype = dropdowntype[i];
                                                selecteddropdowntype = new String(selecteddropdowntype);
                                                selecteddropdowntype = selecteddropdowntype.toString();
                                                var selecteddropdwncondfield = dropdwncondfield[i];
                                                selecteddropdwncondfield = new String(selecteddropdwncondfield);
                                                selecteddropdwncondfield = selecteddropdwncondfield.toString();
                                                var selecteddropdwncondcol = dropdwncondcol[i];
                                                selecteddropdwncondcol = new String(selecteddropdwncondcol);
                                                selecteddropdwncondcol = selecteddropdwncondcol.toString();
                                                var selectedcolname = drpcolname[i];
                                                selectedcolname = new String(selectedcolname);
                                                selectedcolname = selectedcolname.toString();




                                                var conditionvalue = document.getElementById(dropdownmainids).value;

                                                if (selecteddropdowntype == "normaldropdown" || selecteddropdowntype == "normaldropdownwithcondition" || selecteddropdowntype == "multiselectdropdown") {

                                                    generatenormaldropdwndata(selecteddropdowntype, selecteddropdownids, selecteddropdowntblname, selecteddropdowndisplayvalue, selecteddropdownsavevalue, selecteddropdwncondfield, selecteddropdwncondcol, "na", conditionvalue, selectedcolname);

                                                } else if (selecteddropdowntype == "dropdowndatalist" || selecteddropdowntype == "dropdowndatalistwithgetdata") {
                                                    //   generatedropdwndatatlist(selecteddropdownids + "list", selecteddropdowntblname, selecteddropdowndisplayvalue, selecteddropdownsavevalue, selecteddropdwncondfield, selecteddropdwncondcol, "na", conditionvalue, selectedcolname);

                                                    gettextvalue(selecteddropdownids, selecteddropdowntblname, selecteddropdowndisplayvalue, selecteddropdownsavevalue, selecteddropdwncondfield, selecteddropdwncondcol, "na", conditionvalue, selectedcolname);

                                                } else {
                                                    gettextvalue(selecteddropdownids, selecteddropdowntblname, selecteddropdowndisplayvalue, selecteddropdownsavevalue, selecteddropdwncondfield, selecteddropdwncondcol, "na", conditionvalue, selectedcolname);

                                                }




                                            }

                                        });
                                    }
                                    ,
                                    error: function (xhr, ajaxOptions, thrownError)
                                    {

                                        alert("errorstatus: " + xhr.status + " ajaxoptions: " + ajaxOptions + " throwError: " + thrownError);

                                    }
                                }
                                );

                            }
                            function onblurfortext(drpcolname, dropdownmainids) {

                                $.ajax({
                                    type: "GET",
                                    url: "Formservlet",
                                    data: {Action: "onblurfortext", docid: activedocid, colname: drpcolname},
                                    success: function (resp) {
                                        // alert(resp);
                                        var splitdata = resp.split(" `` ");
                                        var dropdowntype = splitdata[1].split(" -- ");
                                        var dropdownids = splitdata[2].split(" -- ");
                                        var dropdowntblname = splitdata[3].split(" -- ");
                                        var dropdowndisplayvalue = splitdata[4].split(" -- ");
                                        var dropdownsavevalue = splitdata[5].split(" -- ");
                                        var dropdwncondfield = splitdata[6].split(" -- ");
                                        var dropdwncondcol = splitdata[7].split(" -- ");
                                        var drpcolname = splitdata[8].split(" -- ");
                                        //alert(dropdownids.length);
                                        $("#" + dropdownmainids).on('blur', function () {
                                            for (var i = 1; i < dropdownids.length; i++) {

                                                var selecteddropdownids = dropdownids[i];
                                                selecteddropdownids = new String(selecteddropdownids);
                                                selecteddropdownids = selecteddropdownids.toString();
                                                var selecteddropdowntblname = dropdowntblname[i];
                                                selecteddropdowntblname = new String(selecteddropdowntblname);
                                                selecteddropdowntblname = selecteddropdowntblname.toString();
                                                var selecteddropdowndisplayvalue = dropdowndisplayvalue[i];
                                                selecteddropdowndisplayvalue = new String(selecteddropdowndisplayvalue);
                                                selecteddropdowndisplayvalue = selecteddropdowndisplayvalue.toString();
                                                var selecteddropdownsavevalue = dropdownsavevalue[i];
                                                selecteddropdownsavevalue = new String(selecteddropdownsavevalue);
                                                selecteddropdownsavevalue = selecteddropdownsavevalue.toString();
                                                var selecteddropdowntype = dropdowntype[i];
                                                selecteddropdowntype = new String(selecteddropdowntype);
                                                selecteddropdowntype = selecteddropdowntype.toString();
                                                var selecteddropdwncondfield = dropdwncondfield[i];
                                                selecteddropdwncondfield = new String(selecteddropdwncondfield);
                                                selecteddropdwncondfield = selecteddropdwncondfield.toString();
                                                var selecteddropdwncondcol = dropdwncondcol[i];
                                                selecteddropdwncondcol = new String(selecteddropdwncondcol);
                                                selecteddropdwncondcol = selecteddropdwncondcol.toString();
                                                var selectedcolname = drpcolname[i];
                                                selectedcolname = new String(selectedcolname);
                                                selectedcolname = selectedcolname.toString();




                                                var conditionvalue = document.getElementById(dropdownmainids).value;

                                                if (selecteddropdowntype == "normaldropdown" || selecteddropdowntype == "normaldropdownwithcondition" || selecteddropdowntype == "multiselectdropdown") {
                                                    generatenormaldropdwndata(selecteddropdowntype, selecteddropdownids, selecteddropdowntblname, selecteddropdowndisplayvalue, selecteddropdownsavevalue, selecteddropdwncondfield, selecteddropdwncondcol, "na", conditionvalue, selectedcolname);

                                                } else if (selecteddropdowntype == "dropdowndatalist") {
                                                    generatedropdwndatatlist(selecteddropdownids + "list", selecteddropdowntblname, selecteddropdowndisplayvalue, selecteddropdownsavevalue, selecteddropdwncondfield, selecteddropdwncondcol, "na", conditionvalue, selectedcolname);
                                                } else {
                                                    gettextvalue(selecteddropdownids, selecteddropdowntblname, selecteddropdowndisplayvalue, selecteddropdownsavevalue, selecteddropdwncondfield, selecteddropdwncondcol, "na", conditionvalue, selectedcolname);

                                                }




                                            }
                                        });
                                    }
                                    ,
                                    error: function (xhr, ajaxOptions, thrownError)
                                    {

                                        alert("errorstatus: " + xhr.status + " ajaxoptions: " + ajaxOptions + " throwError: " + thrownError);

                                    }
                                }
                                );

                            }
                            function gettextvalue(dropdownmainids, tblname, displayval, saveval, dropdwncondfield, dropdwncondcol, limit, condvalue, drpcolname, addifunc) {
                                $.ajax({
                                    type: "GET",
                                    url: "Formservlet",
                                    data: {Action: "gettextvalue", docid: activedocid, tblname: tblname, displayval: displayval,
                                        saveval: saveval, dropdwncondfield: dropdwncondfield, dropdwncondcol: dropdwncondcol, limit: limit, condvalue: condvalue, colname: drpcolname},
                                    success: function (resp) {
                                        //alert(resp);

                                        document.getElementById(dropdownmainids).value = resp;


                                    },
                                    error: function (xhr, ajaxOptions, thrownError)
                                    {

                                        alert("errorstatus: " + xhr.status + " ajaxoptions: " + ajaxOptions + " throwError: " + thrownError);

                                    }
                                });

                            }
                            function generatedropdwndatatlist(dropdownmainids, tblname, displayval, saveval, dropdwncondfield, dropdwncondcol, limit, condvalue, drpcolname, addifunc) {
                                $.ajax({
                                    type: "GET",
                                    url: "Formservlet",
                                    data: {Action: "generatedatatlist", docid: activedocid, tblname: tblname, displayval: displayval, saveval: saveval,
                                        dropdwncondfield: dropdwncondfield, dropdwncondcol: dropdwncondcol, limit: limit, condvalue: condvalue, colname: drpcolname},
                                    success: function (resp) {

                                        //alert(resp);

                                        $("#" + dropdownmainids).html(resp);

                                    },
                                    error: function (xhr, ajaxOptions, thrownError)
                                    {

                                        alert("errorstatus: " + xhr.status + " ajaxoptions: " + ajaxOptions + " throwError: " + thrownError);

                                    }
                                });

                            }

                            function generatenormaldropdwndata(maindropdowntype, dropdownmainids, tblname, displayval, saveval, dropdwncondfield, dropdwncondcol, limit, condvalue, drpcolname, addifunc) {
                                $.ajax({
                                    type: "GET",
                                    url: "Formservlet",
                                    data: {Action: "generatenormaldropdown", docid: activedocid, tblname: tblname, displayval: displayval,
                                        saveval: saveval, dropdwncondfield: dropdwncondfield, dropdwncondcol: dropdwncondcol, limit: limit, condvalue: condvalue, colname: drpcolname},
                                    success: function (resp) {
                                        //    alert(resp);

                                        var splitdata = resp.split(" `` ");
                                        $("#" + dropdownmainids).html(splitdata[0]);
                                        if (maindropdowntype == "multiselectdropdown") {

                                            $("#" + dropdownmainids).bsMultiSelect();
                                        }

                                        var dropdowntype = splitdata[1].split(" -- ");
                                        var dropdownids = splitdata[2].split(" -- ");
                                        var dropdowntblname = splitdata[3].split(" -- ");
                                        var dropdowndisplayvalue = splitdata[4].split(" -- ");
                                        var dropdownsavevalue = splitdata[5].split(" -- ");

                                        var dropdwncondfield = splitdata[6].split(" -- ");
                                        var dropdwncondcol = splitdata[7].split(" -- ");
                                        var drpcolname = splitdata[8].split(" -- ");
                                        //alert(dropdownids.length);
                                        $("#" + dropdownmainids).on('change', function () {
                                            for (var i = 1; i < dropdownids.length; i++) {

                                                var selecteddropdownids = dropdownids[i];
                                                selecteddropdownids = new String(selecteddropdownids);
                                                selecteddropdownids = selecteddropdownids.toString();

                                                var selecteddropdowntblname = dropdowntblname[i];
                                                selecteddropdowntblname = new String(selecteddropdowntblname);
                                                selecteddropdowntblname = selecteddropdowntblname.toString();

                                                var selecteddropdowndisplayvalue = dropdowndisplayvalue[i];
                                                selecteddropdowndisplayvalue = new String(selecteddropdowndisplayvalue);
                                                selecteddropdowndisplayvalue = selecteddropdowndisplayvalue.toString();

                                                var selecteddropdownsavevalue = dropdownsavevalue[i];
                                                selecteddropdownsavevalue = new String(selecteddropdownsavevalue);
                                                selecteddropdownsavevalue = selecteddropdownsavevalue.toString();

                                                var selecteddropdowntype = dropdowntype[i];
                                                selecteddropdowntype = new String(selecteddropdowntype);
                                                selecteddropdowntype = selecteddropdowntype.toString();

                                                var selecteddropdwncondfield = dropdwncondfield[i];
                                                selecteddropdwncondfield = new String(selecteddropdwncondfield);
                                                selecteddropdwncondfield = selecteddropdwncondfield.toString();

                                                var selecteddropdwncondcol = dropdwncondcol[i];
                                                selecteddropdwncondcol = new String(selecteddropdwncondcol);
                                                selecteddropdwncondcol = selecteddropdwncondcol.toString();

                                                var selectedcolname = drpcolname[i];
                                                selectedcolname = new String(selectedcolname);
                                                selectedcolname = selectedcolname.toString();



                                                var conditionvalue = document.getElementById(dropdownmainids).value;

                                                if (selecteddropdowntype == "normaldropdown" || selecteddropdowntype == "normaldropdownwithcondition" || selecteddropdowntype == "multiselectdropdown") {
                                                    generatenormaldropdwndata(selecteddropdowntype, selecteddropdownids, selecteddropdowntblname, selecteddropdowndisplayvalue, selecteddropdownsavevalue, selecteddropdwncondfield, selecteddropdwncondcol, "na", conditionvalue, selectedcolname);
                                                } else {
                                                    generatedropdwndatatlist(selecteddropdownids + "list", selecteddropdowntblname, selecteddropdowndisplayvalue, selecteddropdownsavevalue, selecteddropdwncondfield, selecteddropdwncondcol, "na", conditionvalue, selectedcolname);
                                                }


                                            }
                                        });

                                    },
                                    error: function (xhr, ajaxOptions, thrownError)
                                    {

                                        alert("errorstatus: " + xhr.status + " ajaxoptions: " + ajaxOptions + " throwError: " + thrownError);

                                    }
                                });

                            }



                            function addNew() {
                                $('#formmodal').modal('toggle');
                                var evt = new Event('click');
                                document.getElementById('formclearbtn').dispatchEvent(evt);

                            }

                            function Saveform(inputtype, inputid, mandatoryval, vaidationmsg, filterids, filtertype) {

                                var inputidarray = inputid.split(" -- ");
                                var inputtypearray = inputtype.split(" -- ");
                                var mandatoryvalarray = mandatoryval.split(" -- ");
                                var validationmsgarray = vaidationmsg.split(" -- ");

                                var allvalidationpassed = 1;

                                var valuesfrsave = "";
                                for (var i = 1; i < inputidarray.length; i++) {
                                    if (allvalidationpassed == 1) {
                                        var selectedinputtype = inputtypearray[i];
                                        selectedinputtype = new String(selectedinputtype);
                                        selectedinputtype = selectedinputtype.toString();

                                        var selectedmandatoryval = mandatoryvalarray[i];
                                        selectedmandatoryval = new String(selectedmandatoryval);
                                        selectedmandatoryval = selectedmandatoryval.toString();

                                        var selectedvaidationmsg = validationmsgarray[i];
                                        selectedvaidationmsg = new String(selectedvaidationmsg);
                                        selectedvaidationmsg = selectedvaidationmsg.toString();

                                        var selectedinputid = inputidarray[i];
                                        selectedinputid = new String(selectedinputid);
                                        selectedinputid = selectedinputid.toString();



                                        if (selectedinputtype == "checkbox") {
                                            var getvalue = $('#' + selectedinputid).is(':checked');
                                            if (getvalue == false) {
                                                getvalue = "0";
                                            } else {
                                                getvalue = "1";
                                            }
                                            valuesfrsave += getvalue + " -- ";
                                        } else if (selectedinputtype == "dropdowndatalist") {


                                            var inputdrpdwn = document.getElementById(selectedinputid).value;
                                            var datalisid = selectedinputid + "list";
                                            // var getvalue = document.querySelector("#" + datalisid + " option[value='" + inputdrpdwn + "']").dataset.value;
                                            var getvalue = $("#" + datalisid + " option[value='" + inputdrpdwn + "']").attr('id');
                                            //  var getvalue = document.querySelector('#' + datalisid + ' option[value='" + inputdrpdwn + "']').dataset.id;


                                            if (selectedmandatoryval == "yes") {
                                                if (getvalue == "" || getvalue == null) {
                                                    allvalidationpassed = 0;
                                                    alert(selectedvaidationmsg);
                                                } else {
                                                    allvalidationpassed = 1;
                                                    valuesfrsave += getvalue + " -- ";
                                                }
                                            } else {
                                                allvalidationpassed = 1;
                                                valuesfrsave += getvalue + " -- ";
                                            }
                                        } else if (selectedinputtype == "multiselectdropdown") {
                                            var items = [];
                                            $('#' + selectedinputid + ' option:selected').each(function () {
                                                // alert(items);
                                                if ($(this).val() == "") {

                                                } else {
                                                    items.push($(this).val());
                                                }
                                            });


                                            if (selectedmandatoryval == "yes") {

                                                if (items.length == 0) {
                                                    allvalidationpassed = 0;
                                                    alert(selectedvaidationmsg);
                                                } else {
                                                    var getvalue = items.join(',');
                                                    allvalidationpassed = 1;
                                                    valuesfrsave += getvalue + " -- ";
                                                }
                                            } else {
                                                if (items.length == 0) {
                                                    getvalue = "";
                                                } else {
                                                    var getvalue = items.join(',');
                                                }

                                                //  alert(getvalue);
                                                valuesfrsave += getvalue + " -- ";
                                            }

                                        } else {
                                            var getvalue = document.getElementById(selectedinputid).value;
                                            if (selectedmandatoryval == "yes") {
                                                if (getvalue == "" || getvalue == null) {
                                                    allvalidationpassed = 0;
                                                    alert(selectedvaidationmsg);
                                                } else {
                                                    allvalidationpassed = 1;
                                                    valuesfrsave += getvalue + " -- ";
                                                }
                                            } else {
                                                allvalidationpassed = 1;
                                                valuesfrsave += getvalue + " -- ";
                                            }

                                        }

                                    }
                                }

                                if (allvalidationpassed == 1) {


                                    $.ajax({
                                        type: "POST",
                                        url: "Formservlet",
                                        data: {Action: "autoSaveform", docid: activedocid, ID: ID, user: user, valuesfrsave: valuesfrsave, tblname: tblname},
                                        success: function (resp) {
                                            ID = "0";
                                            alert(resp);

                                            gettbldata(filterids, filtertype);
                                            $('#formmodal').modal('toggle');
                                        },
                                        error: function (xhr, ajaxOptions, thrownError)
                                        {

                                            alert("errorstatus: " + xhr.status + " ajaxoptions: " + ajaxOptions + " throwError: " + thrownError);

                                        }
                                    });
                                }

                            }

                            function viewform(id, inputvalues, inputid, inputtype) {
                                $.blockUI({message: 'loading data,please wait..'});
                                ID = id;
                                var inputidarray = inputid.split(" -- ");
                                var inputtypearray = inputtype.split(" -- ");
                                var inputvaluesarray = inputvalues.split(" -- ");
                                var delaycount = 0;
                                for (var i = 1; i < inputidarray.length; i++) {

                                    var selectedinputtype = inputtypearray[i];
                                    selectedinputtype = new String(selectedinputtype);
                                    selectedinputtype = selectedinputtype.toString();

                                    var selectedinputid = inputidarray[i];
                                    selectedinputid = new String(selectedinputid);
                                    selectedinputid = selectedinputid.toString();

                                    var selectedinputvalues = inputvaluesarray[i];
                                    selectedinputvalues = new String(selectedinputvalues);
                                    selectedinputvalues = selectedinputvalues.toString();

                                    if (selectedinputtype == "checkbox") {
                                        if (selectedinputvalues == "1") {

                                            $('#' + selectedinputid).prop("checked", true);
                                        } else {
                                            $('#' + selectedinputid).prop("checked", false);
                                        }
                                        document.getElementById(selectedinputid).disabled = true;
                                    } else if (selectedinputtype == "label") {
                                        document.getElementById(selectedinputid).innerHTML = selectedinputvalues;
                                    } else if (selectedinputtype == "dropdowndatalist") {
                                        var datavalue;
                                        var datalisid = selectedinputid + "list";


                                        var value = $('#' + datalisid + '  > #' + selectedinputvalues).attr("value");
                                        $("#" + selectedinputid).val(value);

                                        // document.getElementById(selectedinputid).disabled = true;


                                    } else if (selectedinputtype == "normaldropdown") {

                                        document.getElementById(selectedinputid).value = selectedinputvalues;
                                        //  document.getElementById(selectedinputid).disabled = true;
                                        var evt = new Event('change');
                                        document.getElementById(selectedinputid).dispatchEvent(evt);
                                        // alert(selectedinputid);

                                    } else if (selectedinputtype == "normaldropdownwithcondition") {

                                        delaycount++;
                                        delaysetvalue(selectedinputtype, selectedinputid, selectedinputvalues);



                                    } else if (selectedinputtype == "multiselectdropdown") {

                                        generatemultiselectdropdownwithexistingdata(selectedinputtype, selectedinputid, selectedinputvalues);



                                    } else {
                                        document.getElementById(selectedinputid).value = selectedinputvalues;
                                        //     document.getElementById(selectedinputid).disabled = true;
                                        //  alert(selectedinputid);

                                    }
                                }
                                document.getElementById('formsavebtn').disabled = false;
                                document.getElementById('formeditbtn').disabled = false;

                                var delaytime = delaycount * 2000;
                                setTimeout(function () {
                                    $.unblockUI();
                                    $('#formmodal').modal('toggle');
                                }, delaytime);

                            }

                            function Editform(inputtype, inputid) {
                                var inputidarray = inputid.split(" -- ");
                                var inputtypearray = inputtype.split(" -- ");
                                for (var i = 1; i < inputidarray.length; i++) {
                                    var selectedinputtype = inputtypearray[i];
                                    selectedinputtype = new String(selectedinputtype);
                                    selectedinputtype = selectedinputtype.toString();

                                    var selectedinputid = inputidarray[i];
                                    selectedinputid = new String(selectedinputid);
                                    selectedinputid = selectedinputid.toString();
                                    document.getElementById(selectedinputid).disabled = false;
                                }
                                document.getElementById('formsavebtn').disabled = false;
                                document.getElementById('formeditbtn').disabled = true;
                            }
                            function Clearform(inputtype, inputid) {

                                var inputidarray = inputid.split(" -- ");
                                var inputtypearray = inputtype.split(" -- ");
                                for (var i = 1; i < inputidarray.length; i++) {
                                    var selectedinputtype = inputtypearray[i];
                                    selectedinputtype = new String(selectedinputtype);
                                    selectedinputtype = selectedinputtype.toString();

                                    var selectedinputid = inputidarray[i];
                                    selectedinputid = new String(selectedinputid);
                                    selectedinputid = selectedinputid.toString();
                                    if (selectedinputtype == "multiselectdropdown") {
                                        $('#' + selectedinputid).bsMultiSelect('DeselectAll');
                                    } else if (selectedinputtype == "generatedid") {

                                    } else {
                                        document.getElementById(selectedinputid).value = "";
                                        document.getElementById(selectedinputid).disabled = false;
                                    }

                                }
                                document.getElementById('formsavebtn').disabled = false;
                                document.getElementById('formeditbtn').disabled = true;
                            }
                            function delaysetvalue(intype, inid, inval) {
                                if (intype == "multiselectdropdown") {

                                } else {
                                    setTimeout(function () {
                                        document.getElementById(inid).value = inval;
                                        document.getElementById(inid).disabled = true;
                                        var evt = new Event('change');
                                        document.getElementById(inid).dispatchEvent(evt);
                                    }, 2000);
                                }
                            }
                            function generatemultiselectdropdownwithexistingdata(maindropdowntype, selectedinputid, selectedinputvalues) {
                                var li = document.getElementById(selectedinputid);
                                var classname = li.className;
                                $("#div" + selectedinputid).html("");

                                $.ajax({
                                    type: "GET",
                                    url: "Formservlet",
                                    data: {Action: "generatemultiselectdropdownwithexistingdata", docid: activedocid, selectedinputid: selectedinputid, selectedinputvalues: selectedinputvalues},
                                    success: function (resp) {
                                        var html = '<select type=\"text\" class=\"form-control' + classname + '\" id=\"' + selectedinputid + '\"   multiple >';
                                        html += resp + "</select>";

                                        $("#div" + selectedinputid).html(html);
                                        $("#" + selectedinputid).bsMultiSelect();

//                                            $("#" + selectedinputid).bsMultiSelect();



                                        //alert(dropdownids.length);


                                    },
                                    error: function (xhr, ajaxOptions, thrownError)
                                    {

                                        alert("errorstatus: " + xhr.status + " ajaxoptions: " + ajaxOptions + " throwError: " + thrownError);

                                    }
                                });

                            }
                            function Deleteform() {

                                var r = confirm("Are you sure you want to Delete?");
                                if (r == true) {
                                    $.ajax({
                                        type: "POST",
                                        url: "Formservlet",
                                        data: {Action: "deletedata", Id: ID, tblname: tblname},
                                        success: function (data) {
                                            alert(data);

                                            $('#formmodal').modal('toggle');
                                            gettbldata(universalinputtype, universalinputids);
                                        },
                                        error: function (xhr, ajaxOptions, thrownError)
                                        {
                                            if (xhr.status == "500" || xhr.status == "404")
                                            {
                                                alert("Check Internet Connection");
                                                // dangertoast("Check Internet Connection");
                                            }
                                            //                                           alert('errorstatus: ' + xhr.status + ' ajaxoptions: ' + ajaxOptions + ' throwError: ' + thrownError);
                                        }
                                    });
                                }

                            }

                            function processcbm() {
                                var r = confirm("Are you sure you want to Process CBM?");
                                if (r == true) {
                                    $.blockUI({message: '<img src="assets/images/loading.gif"/>'});
                                    $.ajax({
                                        type: "POST",
                                        url: "functionalservlet",
                                        data: {Action: "processcbmbudget"},
                                        success: function (data) {
                                            $.unblockUI();
                                            alert(data);



                                        },
                                        error: function (xhr, ajaxOptions, thrownError)
                                        {
                                            $.unblockUI();
                                            if (xhr.status == "500" || xhr.status == "404")
                                            {
                                                alert("Check Internet Connection");
                                                // dangertoast("Check Internet Connection");
                                            }
                                            //                                           alert('errorstatus: ' + xhr.status + ' ajaxoptions: ' + ajaxOptions + ' throwError: ' + thrownError);
                                        }
                                    });
                                }

                            }

                            function processsalecbm() {
                                var r = confirm("Are you sure you want to Process CBM?");
                                if (r == true) {
                                    $.blockUI({message: '<img src="assets/images/loading.gif"/>'});
                                    $.ajax({
                                        type: "POST",
                                        url: "functionalservlet",
                                        data: {Action: "processcbmsale"},
                                        success: function (data) {
                                            $.unblockUI();
                                            alert(data);



                                        },
                                        error: function (xhr, ajaxOptions, thrownError)
                                        {
                                            $.unblockUI();
                                            if (xhr.status == "500" || xhr.status == "404")
                                            {
                                                alert("Check Internet Connection");
                                                // dangertoast("Check Internet Connection");
                                            }
                                            //                                           alert('errorstatus: ' + xhr.status + ' ajaxoptions: ' + ajaxOptions + ' throwError: ' + thrownError);
                                        }
                                    });
                                }

                            }

                            function closemodal() {
                                $('#formmodal').modal('toggle');
                            }

    </script>

</html>