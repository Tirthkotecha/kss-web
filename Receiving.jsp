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
                            <h3 class="page-title"> Receiving Entry </h3>
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">KSS</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Receiving Entry </li>
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

                                                    <div class="col-3 mt-3" >
                                                        <label>Tank</label>
                                                        <select style="width: 100%;" class="form-control" id="tanklist">
                                                            <option value="0">-Select Tank-</option>
                                                        </select>
                                                    </div>



                                                    <div class="col-2 mt-3" >
                                                        <label>Date</label>
                                                        <input class="form-control" type="date" id="entrydate">
                                                    </div>

                                                    <div class="col-3 mt-3">
                                                        <label>Status</label>
                                                        <select class="form-control" id="status">
                                                            <option value="0">Select Status</option>
                                                            <option value="Matched">Matched</option>
                                                            <option value="Not Matched">Not Matched</option>

                                                        </select>
                                                    </div>


                                                    <div class="col-2 mt-3" >


                                                        <button class="btn btn-success mt-4" onclick=" MaterialSummaryTbl()" >Search</button>




                                                    </div>
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

            <div id="subdatamodal" class="modal custom-modal fade" role="dialog">
                <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="submodaltitle">RECEIVING DATA</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="closemodal()">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body" >
                            <div class="row" >
                                <div class="col-4 form-inline">


                                    <label class="bluelbl">Tank -</label>
                                    <label class="blacklbl pl-2" id="etankname">-</label>
                                </div>
                                <div class="col-4 form-inline">
                                    <div class="form-group pl-2">
                                        <label class="bluelbl">Product -</label>
                                        <label class="blacklbl pl-2" id="eproduct">-</label>
                                    </div>
                                </div>

                                <div class="col-4 form-inline">
                                    <div class="form-group pl-2">
                                        <label class="bluelbl">Entry Time -</label>
                                        <label class="blacklbl pl-2" id="eentrytime">-</label>
                                    </div>
                                </div>



                            </div>
                            <div class="row" >
                                <div class="col-4" >
                                    <label >Hydrometer Reading</label><label style="color: red;">*</label>
                                    <input type="text" class="form-control inputt1"   id="hydrometerreading" >
                                </div>
                                <div class="col-4" >
                                    <label >Temperature</label><label style="color: red;">*</label>
                                    <input type="text" class="form-control inputt1"   id="temperature"  onblur="getdensity()">
                                </div>
                                <div class="col-4" >
                                    <label >Receiving Density @15C</label><label style="color: red;">*</label>
                                    <input type="text" class="form-control inputt1"   id="receivingdensityat15" >
                                </div>

                                <div class="col-4" >
                                    <label >Quantity</label><label style="color: red;">*</label>
                                    <input type="text" class="form-control inputt1"   id="qty" >
                                </div>
                                <div class="col-4" >
                                    <label >Bill No</label><label style="color: red;">*</label>
                                    <input type="text" class="form-control inputt1"   id="billno"  >
                                </div>
                                <div class="col-4" >
                                    <label >Bill Density</label><label style="color: red;">*</label>
                                    <input type="text" class="form-control inputt1"   id="billdensityat15" >
                                </div>
                                <div class="col-4" >
                                    <label >Base</label><label style="color: red;">*</label>
                                    <input type="text" class="form-control inputt1"   id="base" >
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="form-group col-lg-3 col-md-3 col-xs-12 col-sm-6">
                                    <small><span  style="color: black;">Entered Details : </span ><span id="entereddetailspart"  style="color: black;"></span></small>
                                </div>
                                <div class="form-group col-lg-3 col-md-3 col-xs-12 col-sm-6"
                                     <small hidden="">><span  style="color: black;">Updated Details : </span><span id="updateddetailspart"  style="color: black;"></span></small>
                                </div>
                                <div class="col-lg-6 col-md-6 col-xs-6 col-sm-3">
                                    <div class="row">
                                        <div class="col-lg-3 col-md-3 col-xs-6 col-sm-3">
                                            <div class ="form-group">
                                                <button  id="savebtnpart" class="btn btn-success inputt1" onclick="savepartdetails();"   style="width: 95%">Save</button>
                                            </div>
                                        </div>

                                        <div class="col-lg-3 col-md-3 col-xs-6 col-sm-3">
                                            <div class ="form-group">
                                                <button  id="editbtnpart" class="btn btn-primary" onclick="Editpartwise();"   style="width: 95%">Edit</button>
                                            </div>
                                        </div>

                                        <div class="col-lg-3 col-md-3 col-xs-6 col-sm-3">
                                            <div class ="form-group">
                                                <button id="deletebtnpart" class="btn btn-danger" onclick="Deletedynamic('tankreceivingentry');"  style="width: 95%">Delete</button>
                                            </div>
                                        </div>
                                        <div class="col-lg-3 col-md-3 col-xs-6 col-sm-3">
                                            <div class ="form-group">
                                                <button id="cancelbtnpart" class="btn btn-outline-info" type="reset" onclick="closemodal();" style="width: 95%">Cancel</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>


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
                                                    gettankmaster();
                                                    function gettankmaster() {







                                                        $.blockUI({message: 'Loading Data...'});
                                                        $.ajax({
                                                            type: "GET",
                                                            url: "dataapi",
                                                            data: {Action: "gettankmaster"},
                                                            success: function (resp) {
                                                                $.unblockUI();
                                                                //     alert(resp);

                                                                $("#tanklist").html(resp);


                                                            },
                                                            error: function (xhr, ajaxOptions, thrownError)
                                                            {
                                                                $.unblockUI();
                                                                alert("errorstatus: " + xhr.status + " ajaxoptions: " + ajaxOptions + " throwError: " + thrownError);
                                                            }
                                                        });
                                                    }

                                                    MaterialSummaryTbl();
                                                    function MaterialSummaryTbl()
                                                    {

                                                        var action = "gettankreceivingentry";
                                                        var entrydate = "";
                                                        var tanklist = document.getElementById("tanklist").value;
                                                        var splitdata = tanklist.split(" -- ");
                                                        var tankid = "";
                                                        $.blockUI({message: "loading..."});
                                                        $.ajax({
                                                            type: 'GET',
                                                            url: "dataapi",
                                                            data: {Action: action, entrydate: entrydate, tank: tankid},

                                                            success: function (data) {

                                                                $("#tblcontainer").html(data);

                                                                $('.dataTables-example').DataTable({
                                                                    pageLength: 25,
                                                                    responsive: true,
                                                                    dom: '<"top"<"left-col"B><"center-col"l><"right-col"f>>rtip',
                                                                    buttons: [
                                                                        {extend: 'copy'},
                                                                        {extend: 'csv', title: 'Hisab'},
                                                                        {extend: 'excel', title: 'Hisab'},
                                                                        {extend: 'pdf', title: 'Hisab'},

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
                                                                $.unblockUI();
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


                                                    function openexistingdetails(id, tankname, Productname, tstatus, entrytime,
                                                            hydrometerreading, temperature, receivingdensityat15, qty, billno, billdensityat15, base) {
                                                        localStorage.setItem("entryid", id);
                                                        localStorage.setItem("entryid", id);
                                                        document.getElementById("etankname").innerHTML = tankname;
                                                        document.getElementById("eproduct").innerHTML = Productname;
                                                        document.getElementById("eentrytime").innerHTML = entrytime;

                                                        document.getElementById("hydrometerreading").value = hydrometerreading;
                                                        document.getElementById("temperature").value = temperature;
                                                        document.getElementById("receivingdensityat15").value = receivingdensityat15;
                                                        document.getElementById("qty").value = qty;
                                                        document.getElementById("billno").value = billno;
                                                        document.getElementById("billdensityat15").value = billdensityat15;
                                                        document.getElementById("base").value = base;

                                                        var cells = document.getElementsByClassName("inputt1");
                                                        for (var i = 0; i < cells.length; i++) {
                                                            cells[i].disabled = true;
                                                        }

                                                        document.getElementById("editbtnpart").disabled = false;
                                                        $('#subdatamodal').modal('toggle');
                                                        // window.location = "Hisabdetails.jsp";
                                                    }


                                                    function closemodal() {
                                                        $('#subdatamodal').modal('toggle');
                                                    }
                                                    function Deletedynamic(tbl) {

                                                        var result = confirm("Are you sure to delete?");
                                                        if (result == true)
                                                        {
                                                            var entryid = localStorage.getItem("entryid");
                                                            $.ajax({
                                                                type: "POST",
                                                                url: "../generalservlet",
                                                                data: {Action: "deletetable", Id: entryid, tbl: tbl}, success: function (data) {
                                                                    alert(data);

                                                                    MaterialSummaryTbl();

                                                                    //  $.unblockUI();
                                                                },
                                                                error: function (xhr, ajaxOptions, thrownError)
                                                                {
                                                                    $.unblockUI();
                                                                    alert("errorstatus: " + xhr.status + " ajaxoptions: " + ajaxOptions + " throwError: " + thrownError);
                                                                }
                                                            });
                                                        }

                                                    }

                                                    function Editpartwise() {
                                                        var cells = document.getElementsByClassName("inputt1");
                                                        for (var i = 0; i < cells.length; i++) {
                                                            cells[i].disabled = false;
                                                        }
                                                        document.getElementById("editbtnpart").disabled = true;
                                                    }
                                                    function savepartdetails() {

                                                        var hydrometerreading = document.getElementById("hydrometerreading").value;
                                                        var temperature = document.getElementById("temperature").value;
                                                        var receivingdensityat15 = document.getElementById("receivingdensityat15").value;
                                                        var billno = document.getElementById("billno").value;
                                                        var billdensityat15 = document.getElementById("billdensityat15").value;
                                                        var qty = document.getElementById("qty").value;
                                                        var base = document.getElementById("base").value;
                                                        var user = localStorage.getItem("petrol_username");
                                                        var entryid = localStorage.getItem("entryid");
                                                        document.getElementById('savebtnpart').disabled = true;
                                                        $.ajax({
                                                            type: "POST",
                                                            url: "dataapi",
                                                            data: {Action: "Savereceiving", hydrometerreading: hydrometerreading,
                                                                temperature: temperature, receivingdensityat15: receivingdensityat15, billno: billno,
                                                                billdensityat15: billdensityat15, base: base, user: user, entryid: entryid, quantity: qty},
                                                            success: function (data) {
                                                                document.getElementById('savebtnpart').disabled = false;
                                                                if (data == "Saved Successfully") {
                                                                    $('#subdatamodal').modal('toggle');
                                                                    alert(data);
                                                                    // cleardata();
                                                                    // openpage();


                                                                } else {
                                                                    alert(data);
                                                                }


                                                            },
                                                            error: function (xhr, ajaxOptions, thrownError)
                                                            {
                                                                document.getElementById('savebtnmain').disabled = false;
                                                                if (xhr.status == "500" || xhr.status == "404")
                                                                {
                                                                    alert("Check Internet Connection");
                                                                }
                                                                alert("errorstatus: " + xhr.status + " ajaxoptions: " + ajaxOptions + " throwError: " + thrownError);
                                                            }
                                                        });
                                                    }


    </script>
</html>