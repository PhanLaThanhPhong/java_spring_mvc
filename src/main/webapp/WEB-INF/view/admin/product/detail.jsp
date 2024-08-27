<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="utf-8" />
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                    <title>Dashboard Products</title>

                    <link href="/css/styles.css" rel="stylesheet" />
                    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
                        crossorigin="anonymous"></script>
                </head>

                <body class="sb-nav-fixed">
                    <jsp:include page="../layout/header.jsp" />
                    <div id="layoutSidenav">
                        <jsp:include page="../layout/sidebar.jsp" />
                        <div id="layoutSidenav_content">
                            <main>
                                <div class="container-fluid px-4">
                                    <h1 class="mt-4">Manager Products</h1>
                                    <ol class="breadcrumb mb-4">
                                        <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                        <li class="breadcrumb-item active">Products</li>
                                    </ol>
                                    <div class="mt-5">
                                        <div class="row">
                                            <div class="col-12 mx-auto">
                                                <div class="d-flex justify-content-between">
                                                    <h3>Product Detail ${product.id}</h3>
                                                </div>
                                                <hr />
                                                <div class="container col-12" style="display: inline-flex;">
                                                    <div class="card" style="width: 70%;">
                                                        <div class="card-header">

                                                            Product Information
                                                        </div>
                                                        <ul class="list-group list-group-flush">

                                                            <li class="list-group-item">ID: ${product.id}</li>
                                                            <li class="list-group-item">Name: ${product.name}</li>
                                                            <li class="list-group-item">Price:
                                                                <fmt:formatNumber type="number"
                                                                    value="${product.price}" /> đ</td>
                                                            </li>
                                                            <li class="list-group-item">Factory: ${product.factory}</li>
                                                            <li class="list-group-item">Target: ${product.target}</li>

                                                        </ul>
                                                    </div>
                                                    <div class="mx-2">
                                                        <img src="/images/product/${product.image}" alt="Product Avatar"
                                                            class="img-thumbnail" style="width: 250px; height: 250px;">
                                                    </div>
                                                </div>
                                                <a href="/admin/product" class="btn btn-success mt-2">Back</a>

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