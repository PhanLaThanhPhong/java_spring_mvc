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
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#avatarFile");
                        const orgImage = "${newProduct.image}";
                        if (orgImage) {
                            const urlImage = "/images/product/" + orgImage;
                            $("#avatarPreview").attr("src", urlImage);
                            $("#avatarPreview").css({ "display": "block" });
                        }

                        avatarFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#avatarPreview").attr("src", imgURL);
                            $("#avatarPreview").css({ "display": "block" });
                        });
                    });
                </script>
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
                                    <li class="breadcrumb-item active">Update Product</li>
                                </ol>
                                <div class="mt-5">
                                    <div class="row">
                                        <div class="col-md-6 col-12 mx-auto">
                                            <h3>Update a product</h3>
                                            <hr />
                                            <form:form method="post" action="/admin/product/update"
                                                modelAttribute="newProduct" class="row" enctype="multipart/form-data">
                                                <div class="mb-3 col-12 col-md-6" style="display: none;">
                                                    <label class="form-label">Id:</label>
                                                    <form:input type="text"
                                                        class="form-control ${not empty nameHasBindError?'is-invalid':''}"
                                                        path="id" />
                                                    <form:errors path="name" cssClass="invalid-feedback" />
                                                </div>
                                                <div class="mb-3 col-12 col-md-6">
                                                    <c:set var="nameHasBindError">
                                                        <form:errors path="name" />
                                                    </c:set>
                                                    <label class="form-label">Name:</label>
                                                    <form:input type="text"
                                                        class="form-control ${not empty nameHasBindError?'is-invalid':''}"
                                                        path="name" />
                                                    <form:errors path="name" cssClass="invalid-feedback" />
                                                </div>
                                                <div class="mb-3 col-12 col-md-6">
                                                    <c:set var="nameHasBindError">
                                                        <form:errors path="price" />
                                                    </c:set>
                                                    <label class="form-label">Price:</label>
                                                    <form:input type="price"
                                                        class="form-control ${not empty nameHasBindError?'is-invalid':''}"
                                                        path="price" />
                                                    <form:errors path="price" cssClass="invalid-feedback" />
                                                </div>
                                                <div class="mb-3 col-12">
                                                    <c:set var="nameHasBindError">
                                                        <form:errors path="detailDesc" />
                                                    </c:set>
                                                    <label class="form-label">Detail Description:</label>
                                                    <form:input type="text"
                                                        class="form-control ${not empty nameHasBindError?'is-invalid':''}"
                                                        path="detailDesc" />
                                                    <form:errors path="detailDesc" cssClass="invalid-feedback" />
                                                </div>
                                                <div class="mb-3 col-12 col-md-6">
                                                    <c:set var="nameHasBindError">
                                                        <form:errors path="shortDesc" />
                                                    </c:set>
                                                    <label class="form-label">Short Description:</label>
                                                    <form:input type="text"
                                                        class="form-control ${not empty nameHasBindError?'is-invalid':''}"
                                                        path="shortDesc" />
                                                    <form:errors path="shortDesc" cssClass="invalid-feedback" />
                                                </div>
                                                <div class="mb-3 col-12 col-md-6">
                                                    <c:set var="nameHasBindError">
                                                        <form:errors path="quantity" />
                                                    </c:set>
                                                    <label class="form-label">Quantity:</label>
                                                    <form:input type="number"
                                                        class="form-control ${not empty nameHasBindError?'is-invalid':''}"
                                                        path="quantity" />
                                                    <form:errors path="quantity" cssClass="invalid-feedback" />
                                                </div>

                                                <div class="mb-3 col-12 col-md-6">
                                                    <label class="form-label">Factory:</label>
                                                    <form:select class="form-select" path="factory">
                                                        <form:option value="APPLE">Apple (MacBook)</form:option>
                                                        <form:option value="ASUS">ASUS</form:option>
                                                        <form:option value="LENOVO">Lenovo</form:option>
                                                        <form:option value="DELL">Dell</form:option>
                                                        <form:option value="LG">LG</form:option>
                                                        <form:option value="ACER">ACER</form:option>
                                                    </form:select>
                                                </div>

                                                <div class="mb-3 col-12 col-md-6">
                                                    <label class="form-label">Target:</label>
                                                    <form:select class="form-select" path="target">
                                                        <form:option value="GAMING">Gaming</form:option>
                                                        <form:option value="VANPHONG">Văn phòng</form:option>
                                                        <form:option value="THIET_KE_DO_HOA">Thiết kế đồ họa
                                                        </form:option>
                                                        <form:option value="MONG_NHE">Mỏng nhẹ</form:option>
                                                        <form:option value="DOANH_NHAN">Doanh nhân</form:option>
                                                    </form:select>
                                                </div>

                                                <div class="mb-3 col-12 col-md-6">
                                                    <label for="avatarFile" class="form-label">File Image:</label>
                                                    <input class="form-control" type="file" id="avatarFile" name="file"
                                                        accept=".png, .jpg, .jpeg">
                                                </div>
                                                <div class="col-12 mb-3">
                                                    <img style="max-height: 250px; display: none;" alt="avatar preview"
                                                        id="avatarPreview" />
                                                </div>
                                                <div class="col-12 mb-5">
                                                    <a href="/admin/product" class="btn btn-success">Back</a>
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