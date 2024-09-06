<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <title>Dashboard Orders</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Manager Orders</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item"><a href="/admin/order">Orders</a></li>
                                    <li class="breadcrumb-item active">Order Delete</li>
                                </ol>
                                <div class="mt-5">
                                    <div class="row">
                                        <div class="col-md-6 col-12 mx-auto">
                                            <h3>Update a Delete</h3>
                                            <hr />
                                            <form:form method="post" action="/admin/order/update"
                                                modelAttribute="newOrder" class="row">
                                                <div class="mb-3 col-12 col-md-6" style="display: block;">
                                                    <label class="form-label">Id:
                                                        <form:input type="text" class="form-control" path="id" />
                                                    </label>
                                                </div>
                                                <div class="mb-2 col-12 col-md-6">
                                                    <label class="form-label">Status:
                                                        <form:select class="form-select" path="status">
                                                            <form:option value="PENDING">PENDING</form:option>
                                                            <form:option value="SHIPPING">SHIPPING</form:option>
                                                            <form:option value="COMPLETE">COMPLETE</form:option>
                                                            <form:option value="CANCEL">CANCEL</form:option>
                                                            
                                                        </form:select>
                                                    </label>
                                                </div>
                                                <div class="col-12 mb-5">
                                                    <a href="/admin/order" class="btn btn-success">Back</a>
                                                    <button type="submit" class="btn btn-warning">Update</button>
                                                </div>
                                            </form:form>

                                        </div>

                                    </div>
                                </div>
                            </div>
                        </main>
                        <jsp:include page="../layout/footer.jsp" />
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/scripts.js"></script>

                <script src="/js/datatables-simple-demo.js"></script>
            </body>

            </html>