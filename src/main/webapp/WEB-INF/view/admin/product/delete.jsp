<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Dashboard Products</title>

    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
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
                        <li class="breadcrumb-item"><a href="/admin/product">Products</a></li>
                        <li class="breadcrumb-item active">Delete Product</li>
                    </ol>
                    <div class="mt-5">
                        <div class="row">
                            <!-- If you use computer, Your screen will be 6 spacing and use responsive-->
                            <div class="col-md-6 col-12 mx-auto">
                                <h3>Delete a product ${id}</h3>
                                <hr />
                                <form:form method="post" action="/admin/product/delete" modelAttribute="product">
                                    <div class="alert alert-danger" role="alert">
                                        Are you sure to delete this product ?
                                    </div>
                                    <div class="mb-3" style="display: none;">
                                        <form:input value="${id}" type="text" class="form-control" path="id"/>
                                    </div>
                                    <a href="/admin/product" class="btn btn-success">Back</a>
                                    <button type="submit" class="btn btn-danger">Confim</button>
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