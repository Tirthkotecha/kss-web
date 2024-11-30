<%--
    Document   : navbar
    Created on : 28 Feb, 2020, 12:41:05 PM
    Author     : lenovo
--%>












<nav class="navbar p-0 fixed-top d-flex flex-row" id="dynamicnav">
    <div class="navbar-brand-wrapper d-flex d-lg-none align-items-start justify-content-start">
        <a class="navbar-brand brand-logo-mini" href="index.html">KSS</a>
    </div>
    <div class="navbar-menu-wrapper flex-grow d-flex align-items-stretch">


        <ul class="navbar-nav navbar-nav-right">
            <li class="nav-item dropdown d-none d-lg-block">
                <a class="nav-link btn btn-outline-success create-new-button"  href="Dashboard.jsp">Dashboard</a>

            </li>
            <li class="nav-item dropdown d-none d-lg-block">
                <a class="nav-link btn btn-outline-success create-new-button" id="createbuttonDropdown" data-bs-toggle="dropdown" aria-expanded="false"   href="#">Daily Entries</a>
                <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="createbuttonDropdown">

                    <a class="dropdown-item preview-item" href="Hisab.jsp">
                        <div class="preview-thumbnail">
                            <div class="preview-icon bg-dark rounded-circle">
                                <img src="assets/images/hisab.png" alt="">
                            </div>
                        </div>
                        <div class="preview-item-content">
                            <p class="preview-subject ellipsis mb-1">HISAB</p>
                        </div>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item preview-item">
                        <div class="preview-thumbnail">
                            <div class="preview-icon bg-dark rounded-circle">
                                <img src="assets/images/debit.jpg" alt="">
                            </div>
                        </div>
                        <div class="preview-item-content">
                            <p class="preview-subject ellipsis mb-1">Debit</p>
                        </div>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item preview-item">
                        <div class="preview-thumbnail">
                            <div class="preview-icon bg-dark rounded-circle">
                                <img src="assets/images/oil.png" alt="">
                            </div>
                        </div>
                        <div class="preview-item-content">
                            <p class="preview-subject ellipsis mb-1">Oil</p>
                        </div>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item preview-item">
                        <div class="preview-thumbnail">
                            <div class="preview-icon bg-dark rounded-circle">
                                <img src="assets/images/jumma.png" alt="">
                            </div>
                        </div>
                        <div class="preview-item-content">
                            <p class="preview-subject ellipsis mb-1">Jamma</p>
                        </div>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item preview-item" href="Density.jsp">
                        <div class="preview-thumbnail">
                            <div class="preview-icon bg-dark rounded-circle">
                                <img src="assets/images/density.png" alt="">
                            </div>
                        </div>
                        <div class="preview-item-content">
                            <p class="preview-subject ellipsis mb-1">Density</p>
                        </div>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item preview-item" href="Dip.jsp">
                        <div class="preview-thumbnail">
                            <div class="preview-icon bg-dark rounded-circle">
                                <img src="assets/images/dip.png" alt="">
                            </div>
                        </div>
                        <div class="preview-item-content">
                            <p class="preview-subject ellipsis mb-1">Dip</p>
                        </div>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item preview-item" href="Receiving.jsp">
                        <div class="preview-thumbnail">
                            <div class="preview-icon bg-dark rounded-circle">
                                <img src="assets/images/receiving.png" alt="">
                            </div>
                        </div>
                        <div class="preview-item-content">
                            <p class="preview-subject ellipsis mb-1">Receiving Entry</p>
                        </div>
                    </a>
                </div>
            </li>

            <li class="nav-item dropdown d-none d-lg-block">
                <a class="nav-link btn btn-outline-success create-new-button"  id="createbuttonDropdown" data-bs-toggle="dropdown" aria-expanded="false" >Reports</a>
                <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="createbuttonDropdown">

                    <a class="dropdown-item preview-item" href="Dailyreport.jsp">

                        <div class="preview-item-content">
                            <p class="preview-subject ellipsis mb-1">Daily Report</p>
                        </div>
                    </a>

                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item preview-item" href="densityregister.jsp">

                        <div class="preview-item-content">
                            <p class="preview-subject ellipsis mb-1">Density Report</p>
                        </div>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item preview-item" href="dsreport.jsp">

                        <div class="preview-item-content">
                            <p class="preview-subject ellipsis mb-1">DSR Report</p>
                        </div>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item preview-item" href="patrakreport.jsp">

                        <div class="preview-item-content">
                            <p class="preview-subject ellipsis mb-1">Patrak Report</p>
                        </div>
                    </a>
                </div>
            </li>

            <li class="nav-item dropdown d-none d-lg-block">
                <a class="nav-link btn btn-outline-success create-new-button" id="createbuttonDropdown" data-bs-toggle="dropdown" aria-expanded="false" href="#">Master</a>
                <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="createbuttonDropdown">

                    <a class="dropdown-item preview-item" onclick="openmaster('D0001', 'usermaster', 'User Master', 'modal form', 'aforms', 'mastersnav')">

                        <div class="preview-item-content">
                            <p class="preview-subject ellipsis mb-1">User Master</p>
                        </div>
                    </a>

                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item preview-item">

                        <div class="preview-item-content">
                            <p class="preview-subject ellipsis mb-1">Opening Value</p>
                        </div>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item preview-item">

                        <div class="preview-item-content">
                            <p class="preview-subject ellipsis mb-1">Rate Master</p>
                        </div>
                    </a>
                </div>
            </li>




            <li class="nav-item dropdown">
                <a class="nav-link" id="profileDropdown" href="#" data-bs-toggle="dropdown">
                    <div class="navbar-profile">
                        <img class="img-xs rounded-circle" src="assets/images/faces/face15.jpg" alt="">
                        <p class="mb-0 d-none d-sm-block navbar-profile-name">Henry Klein</p>
                        <i class="mdi mdi-menu-down d-none d-sm-block"></i>
                    </div>
                </a>
                <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="profileDropdown">
                    <h6 class="p-3 mb-0">Profile</h6>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item preview-item">
                        <div class="preview-thumbnail">
                            <div class="preview-icon bg-dark rounded-circle">
                                <i class="mdi mdi-settings text-success"></i>
                            </div>
                        </div>
                        <div class="preview-item-content">
                            <p class="preview-subject mb-1">Settings</p>
                        </div>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item preview-item">
                        <div class="preview-thumbnail">
                            <div class="preview-icon bg-dark rounded-circle">
                                <i class="mdi mdi-logout text-danger"></i>
                            </div>
                        </div>
                        <div class="preview-item-content">
                            <p class="preview-subject mb-1">Log out</p>
                        </div>
                    </a>
                    <div class="dropdown-divider"></div>
                    <p class="p-3 mb-0 text-center">Advanced settings</p>
                </div>
            </li>
        </ul>
        <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
            <span class="mdi mdi-format-line-spacing"></span>
        </button>
    </div>
</nav>

<script>
    function openmaster(docid, tblname, formname, formtype, pagename, pagenamebar) {
        localStorage.setItem("docid", docid);
        localStorage.setItem("tblname", tblname);
        localStorage.setItem("formname", formname);
        localStorage.setItem("Activebar", pagenamebar);
        localStorage.setItem("formtype", formtype);
        window.location = pagename + ".jsp";
    }
    var Role = localStorage.getItem("petrol_role");
    // alert(Role);
    details();
    function details()
    {
        var dyhtml = "";

        if (Role == "1") {
            dyhtml = '<div class="navbar-brand-wrapper d-flex d-lg-none align-items-start justify-content-start">'
                    + '<a class="navbar-brand brand-logo-mini" href="index.html">KSS</a></div>'
                    + '<div class="navbar-menu-wrapper flex-grow d-flex align-items-stretch">'


                    + '<ul class="navbar-nav navbar-nav-right">'
                    + '<li class="nav-item dropdown d-none d-lg-block">'
                    + '<a class="nav-link btn btn-outline-success create-new-button"  href="Dashboard.jsp">Dashboard</a>'

                    + '</li>'
                    + '<li class="nav-item dropdown d-none d-lg-block">'
                    + '<a class="nav-link btn btn-outline-success create-new-button" id="createbuttonDropdown" data-bs-toggle="dropdown" aria-expanded="false"   href="#">Daily Entries</a>'
                    + '<div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="createbuttonDropdown">'

                    + '<a class="dropdown-item preview-item" href="Hisab.jsp">'
                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/hisab.png" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">HISAB</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0007\', \'debitdata\', \'Debit Entry\', \'modal form\', \'aforms\', \'mastersnav\')">'

                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/debit.jpg" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Debit</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0006\', \'oildata\', \'Oil Entry\', \'modal form\', \'aforms\', \'mastersnav\')">'

                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/oil.png" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Oil</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0005\', \'jummadata\', \'Jamma Entry\', \'modal form\', \'aforms\', \'mastersnav\')">'
                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/jumma.png" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Jamma</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" href="Density.jsp">'
                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/density.png" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Density</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" href="Dip.jsp">'
                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/dip.png" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Dip</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" href="Receiving.jsp">'
                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/receiving.png" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Receiving Entry</p>'
                    + '</div>'
                    + '</a>'
                    + '</div>'
                    + '</li>'

                    + '<li class="nav-item dropdown d-none d-lg-block">'
                    + '<a class="nav-link btn btn-outline-success create-new-button"  id="createbuttonDropdown" data-bs-toggle="dropdown" aria-expanded="false" >Reports</a>'
                    + '<div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="createbuttonDropdown">'

                    + '<a class="dropdown-item preview-item" href="Dailyreport.jsp">'

                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Daily Report</p>'
                    + '</div>'
                    + '</a>'

                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item"  href="#"onclick="openmaster(\'D0009\', \'densityregister\', \'Density Report\', \'modal form\', \'aforms\', \'mastersnav\')">'

                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Density Report</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item"  href="#"onclick="openmaster(\'D0010\', \'dsrreport\', \'DSR Report\', \'modal form\', \'aforms\', \'mastersnav\')">'

                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">DSR Report</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item"  href="#"onclick="openmaster(\'D0008\', \'patrakreport\', \'Patrak Report\', \'modal form\', \'aforms\', \'mastersnav\')">'

                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Patrak Report</p>'
                    + '</div>'
                    + '</a>'
                    + '</div>'
                    + '</li>'

                    + '<li class="nav-item dropdown d-none d-lg-block">'
                    + '<a class="nav-link btn btn-outline-success create-new-button" id="createbuttonDropdown" data-bs-toggle="dropdown" aria-expanded="false" href="#">Master</a>'
                    + '<div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="createbuttonDropdown">'

                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0001\', \'usermaster\', \'User Master\', \'modal form\', \'aforms\', \'mastersnav\')">'

                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">User Master</p>'
                    + '</div>'
                    + '</a>'

                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0003\', \'openingvaluemaster\', \'Opening Value Master\', \'modal form\', \'aforms\', \'mastersnav\')">'


                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Opening Value</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0011\', \'owneropeningvaluemaster\', \'Owner Opening Value Master\', \'modal form\', \'aforms\', \'mastersnav\')">'


                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Owner Opening Value</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0012\', \'testingmaster\', \'Testing Master\', \'modal form\', \'aforms\', \'mastersnav\')">'


                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Testing Master</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0004\', \'todaysrate\', \'Rate Master\', \'modal form\', \'aforms\', \'mastersnav\')">'


                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Rate Master</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0014\', \'lubricantmaster\', \'Lubricant Master\', \'modal form\', \'aforms\', \'mastersnav\')">'


                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Lubricant Master</p>'
                    + '</div>'
                    + '</a>'
                    + '</div>'
                    + '</li>'
                    + '<li class="nav-item dropdown">'
                    + '<a class="nav-link" id="profileDropdown" href="#" data-bs-toggle="dropdown">'
                    + '<div class="navbar-profile">'
                    + '<img class="img-xs rounded-circle" src="assets/images/faces/face15.jpg" alt="">'
                    + '<p class="mb-0 d-none d-sm-block navbar-profile-name" id="username">Henry Klein</p>'
                    + '<i class="mdi mdi-menu-down d-none d-sm-block"></i>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="profileDropdown">'
                    + '<h6 class="p-3 mb-0">Profile</h6>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item">'
                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<i class="mdi mdi-settings text-success"></i>'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject mb-1">Settings</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="LogoutFunction()">'
                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<i class="mdi mdi-logout text-danger"></i>'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject mb-1" >Log out</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<p class="p-3 mb-0 text-center">Advanced settings</p>'
                    + '</div>'
                    + '</li>'
                    + '</ul>'
                    + '<button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">'
                    + '<span class="mdi mdi-format-line-spacing"></span>'
                    + '</button>'
                    + '</div>';
        } else if (Role == "2" || Role == "3") {
            dyhtml = '<div class="navbar-brand-wrapper d-flex d-lg-none align-items-start justify-content-start">'
                    + '<a class="navbar-brand brand-logo-mini" href="index.html">KSS</a></div>'
                    + '<div class="navbar-menu-wrapper flex-grow d-flex align-items-stretch">'


                    + '<ul class="navbar-nav navbar-nav-right">'
                    + '<li class="nav-item dropdown d-none d-lg-block">'
                    + '<a class="nav-link btn btn-outline-success create-new-button"  href="Dashboard.jsp">Dashboard</a>'

                    + '</li>'
                    + '<li class="nav-item dropdown d-none d-lg-block">'
                    + '<a class="nav-link btn btn-outline-success create-new-button" id="createbuttonDropdown" data-bs-toggle="dropdown" aria-expanded="false"   href="#">Daily Entries</a>'
                    + '<div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="createbuttonDropdown">'

                    + '<a class="dropdown-item preview-item" href="Hisab.jsp">'
                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/hisab.png" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">HISAB</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0007\', \'debitdata\', \'Debit Entry\', \'modal form\', \'aforms\', \'mastersnav\')">'

                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/debit.jpg" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Debit</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0006\', \'oildata\', \'Oil Entry\', \'modal form\', \'aforms\', \'mastersnav\')">'

                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/oil.png" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Oil</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0005\', \'jummadata\', \'Jamma Entry\', \'modal form\', \'aforms\', \'mastersnav\')">'

                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/jumma.png" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Jamma</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" href="Density.jsp">'
                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/density.png" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Density</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" href="Dip.jsp">'
                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/dip.png" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Dip</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" href="Receiving.jsp">'
                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/receiving.png" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Receiving Entry</p>'
                    + '</div>'
                    + '</a>'
                    + '</div>'
                    + '</li>'

                    + '<li class="nav-item dropdown d-none d-lg-block">'
                    + '<a class="nav-link btn btn-outline-success create-new-button"  id="createbuttonDropdown" data-bs-toggle="dropdown" aria-expanded="false" >Reports</a>'
                    + '<div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="createbuttonDropdown">'

                    + '<a class="dropdown-item preview-item" href="Dailyreport.jsp">'

                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Daily Report</p>'
                    + '</div>'
                    + '</a>'

                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item"  href="#"onclick="openmaster(\'D0009\', \'densityregister\', \'Density Report\', \'modal form\', \'aforms\', \'mastersnav\')">'

                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Density Report</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item"  href="#"onclick="openmaster(\'D0010\', \'dsrreport\', \'DSR Report\', \'modal form\', \'aforms\', \'mastersnav\')">'


                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">DSR Report</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item"  href="#"onclick="openmaster(\'D0008\', \'patrakreport\', \'Patrak Report\', \'modal form\', \'aforms\', \'mastersnav\')">'


                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Patrak Report</p>'
                    + '</div>'
                    + '</a>'
                    + '</div>'
                    + '</li>'

                    + '<li class="nav-item dropdown d-none d-lg-block">'
                    + '<a class="nav-link btn btn-outline-success create-new-button" id="createbuttonDropdown" data-bs-toggle="dropdown" aria-expanded="false" href="#">Master</a>'
                    + '<div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="createbuttonDropdown">'

                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0001\', \'usermaster\', \'User Master\', \'modal form\', \'aforms\', \'mastersnav\')">'

                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">User Master</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0003\', \'openingvaluemaster\', \'Opening Value Master\', \'modal form\', \'aforms\', \'mastersnav\')">'


                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Opening Value</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0011\', \'owneropeningvaluemaster\', \'Owner Opening Value Master\', \'modal form\', \'aforms\', \'mastersnav\')">'


                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Owner Opening Value</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0012\', \'testingmaster\', \'Testing Master\', \'modal form\', \'aforms\', \'mastersnav\')">'


                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Testing Master</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0004\', \'todaysrate\', \'Rate Master\', \'modal form\', \'aforms\', \'mastersnav\')">'


                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Rate Master</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0014\', \'lubricantmaster\', \'Lubricant Master\', \'modal form\', \'aforms\', \'mastersnav\')">'


                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Lubricant Master</p>'
                    + '</div>'
                    + '</a>'
                    + '</div>'
                    + '</li>'
                    + '<li class="nav-item dropdown">'
                    + '<a class="nav-link" id="profileDropdown" href="#" data-bs-toggle="dropdown">'
                    + '<div class="navbar-profile">'
                    + '<img class="img-xs rounded-circle" src="assets/images/faces/face15.jpg" alt="">'
                    + '<p class="mb-0 d-none d-sm-block navbar-profile-name" id="username">Henry Klein</p>'
                    + '<i class="mdi mdi-menu-down d-none d-sm-block"></i>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="profileDropdown">'
                    + '<h6 class="p-3 mb-0">Profile</h6>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item">'
                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<i class="mdi mdi-settings text-success"></i>'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject mb-1">Settings</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="LogoutFunction()">'
                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<i class="mdi mdi-logout text-danger"></i>'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject mb-1">Log out</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<p class="p-3 mb-0 text-center">Advanced settings</p>'
                    + '</div>'
                    + '</li>'
                    + '</ul>'
                    + '<button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">'
                    + '<span class="mdi mdi-format-line-spacing"></span>'
                    + '</button>'
                    + '</div>';
        } else if (Role == "4") {
            dyhtml = '<div class="navbar-brand-wrapper d-flex d-lg-none align-items-start justify-content-start">'
                    + '<a class="navbar-brand brand-logo-mini" href="index.html">KSS</a></div>'
                    + '<div class="navbar-menu-wrapper flex-grow d-flex align-items-stretch">'


                    + '<ul class="navbar-nav navbar-nav-right">'
                    + '<li class="nav-item dropdown d-none d-lg-block">'
                    + '<a class="nav-link btn btn-outline-success create-new-button"  href="Dashboard.jsp">Dashboard</a>'

                    + '</li>'
                    + '<li class="nav-item dropdown d-none d-lg-block">'
                    + '<a class="nav-link btn btn-outline-success create-new-button" id="createbuttonDropdown" data-bs-toggle="dropdown" aria-expanded="false"   href="#">Daily Entries</a>'
                    + '<div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="createbuttonDropdown">'

                    + '<a class="dropdown-item preview-item" href="Hisab.jsp">'
                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/hisab.png" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">HISAB</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0007\', \'debitdata\', \'Debit Entry\', \'modal form\', \'aforms\', \'mastersnav\')">'

                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/debit.jpg" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Debit</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0006\', \'oildata\', \'Oil Entry\', \'modal form\', \'aforms\', \'mastersnav\')">'

                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/oil.png" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Oil</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="openmaster(\'D0005\', \'jummadata\', \'Jamma Entry\', \'modal form\', \'aforms\', \'mastersnav\')">'
                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<img src="assets/images/jumma.png" alt="">'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject ellipsis mb-1">Jamma</p>'
                    + '</div>'
                    + '</a>'

                    + '</li>'



                    + '<li class="nav-item dropdown">'
                    + '<a class="nav-link" id="profileDropdown" href="#" data-bs-toggle="dropdown">'
                    + '<div class="navbar-profile">'
                    + '<img class="img-xs rounded-circle" src="assets/images/faces/face15.jpg" alt="">'
                    + '<p class="mb-0 d-none d-sm-block navbar-profile-name" id="username">Henry Klein</p>'
                    + '<i class="mdi mdi-menu-down d-none d-sm-block"></i>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="profileDropdown">'
                    + '<h6 class="p-3 mb-0">Profile</h6>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item">'
                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<i class="mdi mdi-settings text-success"></i>'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject mb-1">Settings</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<a class="dropdown-item preview-item" onclick="LogoutFunction()">'
                    + '<div class="preview-thumbnail">'
                    + '<div class="preview-icon bg-dark rounded-circle">'
                    + '<i class="mdi mdi-logout text-danger"></i>'
                    + '</div>'
                    + '</div>'
                    + '<div class="preview-item-content">'
                    + '<p class="preview-subject mb-1">Log out</p>'
                    + '</div>'
                    + '</a>'
                    + '<div class="dropdown-divider"></div>'
                    + '<p class="p-3 mb-0 text-center">Advanced settings</p>'
                    + '</div>'
                    + '</li>'
                    + '</ul>'
                    + '<button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">'
                    + '<span class="mdi mdi-format-line-spacing"></span>'
                    + '</button>'
                    + '</div>';
        }

        document.getElementById("dynamicnav").innerHTML = dyhtml;

        var userName = localStorage.getItem("petrol_username");

        if (userName === "" || userName === "null" || userName === null) {
            //alert("--" + userName + "--");
            document.getElementById("username").innerHTML = "Admin";
            window.location = "Logout.jsp";
        } else {
            document.getElementById("username").innerHTML = userName;
        }
    }

    function LogoutFunction() {

        var r = confirm("Are you sure you want to logout?");
        if (r == true) {
            window.location = "Logout.jsp";
        } else {

        }
    }

</script>
