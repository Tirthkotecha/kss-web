<%--
    Document   : index
    Created on : 13 Mar, 2024, 12:36:21 AM
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
        <!-- End plugin css for this page -->
        <!-- inject:css -->
        <!-- endinject -->
        <!-- Layout styles -->
        <link rel="stylesheet" href="assets/css/style.css">
        <!-- End layout styles -->
        <link rel="shortcut icon" href="assets/images/favicon.png" />
    </head>
    <body>
        <div class="container-scroller">
            <div class="container-fluid page-body-wrapper full-page-wrapper">
                <div class="row w-100 m-0">
                    <div class="content-wrapper full-page-wrapper d-flex align-items-center auth " style="background: url(assets/images/6505910.jpg); background-repeat: no-repeat;
                         background-size: cover;">
                        <div class="card col-lg-4 mx-auto">
                            <div class="card-body px-5 py-5">
                                <h3 class="card-title text-left mb-3">Login</h3>
                                <form>
                                    <div class="form-group">
                                        <label>Username or email *</label>
                                        <input type="text" class="form-control p_input" id="email1" style=" color: white">
                                    </div>
                                    <div class="form-group">
                                        <label>Password *</label>
                                        <input type="password" class="form-control p_input" id="password1" style=" color: white">
                                    </div>

                                    <div class="text-center">
                                        <button type="button" class="btn btn-primary btn-block enter-btn" style=" width: 100%" onclick="checklogin()">Login</button>
                                    </div>


                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- content-wrapper ends -->
                </div>
                <!-- row ends -->
            </div>
            <!-- page-body-wrapper ends -->
        </div>
        <!-- container-scroller -->
        <!-- plugins:js -->
        <script src="assets/vendors/js/vendor.bundle.base.js"></script>
        <!-- endinject -->
        <!-- Plugin js for this page -->
        <!-- End plugin js for this page -->
        <!-- inject:js -->
        <script src="assets/js/off-canvas.js"></script>
        <script src="assets/js/hoverable-collapse.js"></script>
        <script src="assets/js/misc.js"></script>
        <script src="assets/js/settings.js"></script>
        <script src="assets/js/todolist.js"></script>
        <script src="assets/js/jquery.blockUI.js"></script>
        <!-- endinject -->
    </body>
    <script type="text/javascript">
                                            // var apiurl="http://103.145.51.176:8080/KSS/";

                                            var apiurl = "";

                                            function checklogin()
                                            {

                                                var email = document.getElementById('email1').value;
                                                var password = document.getElementById('password1').value;
                                                if (email == "")
                                                {
                                                    toastr.warning("please enter email");
                                                } else if (password == "")
                                                {
                                                    toastr.warning("please enter password");
                                                } else {
                                                    //  document.getElementById('loginbtn').disabled = true;
                                                    $.blockUI({message: 'Logging In...'});
                                                    $.ajax({
                                                        type: "GET",
                                                        url: 'LoginServlet',
                                                        data: {Action: 'checklogin', Email: email, Password: password},
                                                        success: function (data) {
                                                            // alert(data);
                                                            $.unblockUI();
                                                            var data1 = data.split(" -- ");
                                                            if (data1[0].includes("Logged"))
                                                            {
                                                                // toastr.success("Logged in successfully");
                                                                localStorage.setItem("petrol_userid", data1[1]);
                                                                localStorage.setItem("petrol_username", data1[2]);

                                                                localStorage.setItem("petrol_role", data1[3]);

                                                                //  alert("issue in java enviroment error code-401");

                                                                window.location = "Dashboard.jsp";

                                                            } else
                                                            {
                                                                //  alert(data1[0]);
                                                                alert("Please check username or password!");
                                                                //   document.getElementById('loginbtn').disabled = false;
                                                            }

                                                        },
                                                        error: function (xhr, ajaxOptions, thrownError)
                                                        {
                                                            $.unblockUI();
                                                            if (xhr.status == "500" || xhr.status == "404")
                                                            {
                                                                alert("Check Internet Connection");
                                                            }
                                                            //alert("errorstatus: " + xhr.status + " ajaxoptions: " + ajaxOptions + " throwError: " + thrownError);
                                                        }
                                                    });
                                                }
                                            }
    </script>

</html>