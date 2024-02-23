<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
<!-- Bootstrap CSS link -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom CSS -->
<style>
    body {
        background-color: #f8f9fa;
    }
    .container {
        padding-top: 50px;
    }
    .card {
        width: 400px;
        margin: 0 auto;
        margin-bottom: 20px;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    }
    .card-title {
        margin-bottom: 0;
    }
    .card-body {
        padding: 20px;
    }
    h1 {
        text-align: center;
        margin-bottom: 20px;
    }
</style>
</head>
<body>
<div class="container">
    <h1>Homepage</h1>
    <div class="card">
        <div class="card-body">
            <h2 class="card-title text-center">Token</h2>
            <h3 class="text-center">${Token}</h3>
        </div>
    </div>
    <div class="card">
        <div class="card-body">
            <h2 class="card-title text-center">Role</h2>
            <h3 class="text-center">${Role}</h3>
        </div>
    </div>
</div>

<!-- Bootstrap JS and Popper.js scripts (place at the end of the body to improve page load time) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
