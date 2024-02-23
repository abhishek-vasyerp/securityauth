<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.IOException" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <title>Success</title>
    <style>
        .button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
    </style>
    <script>
        function handleClickAdmin() {
            $.ajax({
                type: "GET",
                beforeSend: function(request) {
                    request.setRequestHeader("Authorization", "Bearer " + "${data}");
                },
                url: "http://localhost:8080/admin-page",
                success: function (html){
                    $("html").html(html);
                }
            });
        }
        function handleClickUser() {
            $.ajax({
                type: "GET",
                beforeSend: function(request) {
                    request.setRequestHeader("Authorization", "Bearer " + "${data}");
                },
                url: "http://localhost:8080/user-page",
                success: function (html){
                    $("html").html(html);
                }
            });
        }
        $(document).ready(function() {
            var userRole = "${Role}"; // Assuming userRole is available
            console.log(userRole)
            if (userRole.includes("ADMIN")) {
                    handleClickAdmin();
            } else {
                    handleClickUser();
            }
        });
    </script>
</head>
<body>
</body>
</html>