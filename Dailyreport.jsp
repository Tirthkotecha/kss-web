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
        <title>Corona Admin</title>
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
                            <h3 class="page-title"> Daily Repot </h3>
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">KSS</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Daily Report</li>
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
                                            <div class="col-lg-12" id="addnewcontainer">
                                                <div class="row">




                                                    <div class="col-2 mt-3" >
                                                        <label>Date</label>
                                                        <input class="form-control" type="date" id="entrydate">
                                                    </div>



                                                    <div class="col-2 mt-3" >


                                                        <button class="btn btn-success mt-4" onclick=" checkreport()" >Load</button>




                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="table-responsive" id="tblcontainer">

                                        </div>
                                        <div class="row "  >
                                            <br>


                                            <div style="text-align: center; ">

                                                <button type="button" class="w-90 btn btn-info" id="closedaybtn" onclick="Savereports()" style=" visibility:  hidden">Save & Close</button>
                                                <button class='btn btn-secondary' onclick='printDiv()'>Print</button>
                                            </div>





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
                                                    var reportsgeneratd = "0";
                                                    function checkreport() {
                                                        var entrydate = document.getElementById("entrydate").value;
                                                        reportsgeneratd = "0";
                                                        $.ajax({
                                                            type: 'GET',
                                                            url: "dataapi",
                                                            data: {Action: "getdailyreportcheck", entrydate: entrydate},

                                                            success: function (data) {
                                                                $.unblockUI();
                                                                //   alert(data);
                                                                reportsgeneratd = data;
                                                                if (reportsgeneratd == "0") {
                                                                    MaterialSummaryTbl();
                                                                } else {
                                                                    getreport();
                                                                }

                                                            },
                                                            error: function (xhr, ajaxOptions, thrownError)
                                                            {
                                                                $.unblockUI();
                                                                if (xhr.status == "500" || xhr.status == "404")
                                                                {
                                                                    alert("Check Internet Connection " + xhr.status);
                                                                }
                                                            }
                                                        });
                                                    }
                                                    function MaterialSummaryTbl()
                                                    {
                                                        document.getElementById("closedaybtn").style.visiblity = "hidden";
                                                        var entrydate = document.getElementById("entrydate").value;
                                                        $.blockUI({message: 'Calculating Data...'});

                                                        $.ajax({
                                                            type: 'POST',
                                                            url: "dataapi",
                                                            data: {Action: 'Createmonthlyreport', entrydate: entrydate},

                                                            success: function (data) {

                                                                $.unblockUI();

                                                                //     $("#transactionlist").html(data);

                                                                getreport();
                                                            },
                                                            error: function (xhr, ajaxOptions, thrownError)
                                                            {
                                                                $.unblockUI();
                                                                if (xhr.status == "500" || xhr.status == "404")
                                                                {
                                                                    alert("Check Internet Connection " + xhr.status);
                                                                }
                                                            }
                                                        });
                                                    }

                                                    function getreport()
                                                    {
                                                        document.getElementById("closedaybtn").style.visiblity = "hidden";

                                                        $.blockUI({message: 'Loading report please wait...'});
                                                        var entrydate = document.getElementById("entrydate").value;

                                                        $.ajax({
                                                            type: 'GET',
                                                            url: "dataapi",
                                                            data: {Action: "Dailyreport", entrydate: entrydate, reportsgeneratd: reportsgeneratd},

                                                            success: function (data) {
                                                                $.unblockUI();
                                                                $("#tblcontainer").html(data);
                                                                if (reportsgeneratd == "0") {
                                                                    document.getElementById("closedaybtn").style.visibility = "Visible";
                                                                }


                                                            },
                                                            error: function (xhr, ajaxOptions, thrownError)
                                                            {
                                                                $.unblockUI();
                                                                if (xhr.status == "500" || xhr.status == "404")
                                                                {
                                                                    alert("Check Internet Connection " + xhr.status);
                                                                }
                                                            }
                                                        });
                                                    }

                                                    function closemodal() {
                                                        $('#subdatamodal').modal('toggle');
                                                    }

                                                    function Savereports() {
                                                        var entrydate = document.getElementById("entrydate").value;
                                                        var bankdeposit = document.getElementById("bankdeposit").value;
                                                        $.blockUI({message: 'Generating Density report for a day...'});

                                                        $.ajax({
                                                            type: 'POST',
                                                            url: "dataapi",
                                                            data: {Action: 'closingreports', entrydate: entrydate, bankdeposit: bankdeposit},

                                                            success: function (data) {
                                                                alert(data);
                                                                $.unblockUI();
                                                                SavereportsDSR();
                                                                //     $("#transactionlist").html(data);


                                                            },
                                                            error: function (xhr, ajaxOptions, thrownError)
                                                            {
                                                                $.unblockUI();
                                                                if (xhr.status == "500" || xhr.status == "404")
                                                                {
                                                                    alert("Check Internet Connection " + xhr.status);
                                                                }
                                                            }
                                                        });
                                                    }

                                                    function SavereportsDSR() {
                                                        var entrydate = document.getElementById("entrydate").value;
                                                        $.blockUI({message: 'Generating DSR report and Patrak report for a day...'});

                                                        $.ajax({
                                                            type: 'POST',
                                                            url: "dataapi",
                                                            data: {Action: 'closingreportsDSR', entrydate: entrydate},

                                                            success: function (data) {
                                                                alert(data);
                                                                $.unblockUI();

                                                                //     $("#transactionlist").html(data);
                                                                getreport();

                                                            },
                                                            error: function (xhr, ajaxOptions, thrownError)
                                                            {
                                                                $.unblockUI();
                                                                if (xhr.status == "500" || xhr.status == "404")
                                                                {
                                                                    alert("Check Internet Connection " + xhr.status);
                                                                }
                                                            }
                                                        });
                                                    }
                                                    function  printDiv() {
                                                        var mywindow = window.open('', 'new div', 'height=400,width=600');
                                                        var printContents = document.getElementById("printcontent").innerHTML;
                                                        mywindow.document.write('<html><head><title></title>');
                                                        mywindow.document.write('<link rel="stylesheet" href="assets/css/style.css" type="text/css" />');
                                                        mywindow.document.write('</head><body >');
                                                        mywindow.document.write(printContents);
                                                        mywindow.document.write('</body></html>');
                                                        mywindow.document.close();
                                                        mywindow.focus();
                                                        setTimeout(function () {
                                                            mywindow.print();
                                                            mywindow.close();
                                                        }, 1000);

                                                        return true;

                                                    }
                                                    function calculatedcashleft() {
                                                        var cashinhand = document.getElementById("cashinhand").innerHTML;
                                                        var bankdeposit = document.getElementById("bankdeposit").value;
                                                        var cashleft = parseFloat(cashinhand) - parseFloat(bankdeposit);
                                                        document.getElementById("cashleft").innerHTML = cashleft;

                                                    }
    </script>
</html>