<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap" rel="stylesheet">

    <!-- reset -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

    <!-- fontawesome css: https://fontawesome.com -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">

    <link rel="stylesheet" href="/assets/css/main.css">
    <link rel="stylesheet" href="/assets/css/list.css">
<title>Insert title here</title>
<style>

.card-wrap {
 border: 1px solid #000;
 width: 60%;
 height: 500px;
 margin: 100px auto;
 
}

.color-header {
    background: dodgerblue;
    height: 25%;
}

h1 {
    font-size: 30px;
    width: fit-content;
    margin: 0 auto; 
    text-align: center;
    padding: 20px 0px 20px;
    
}
span {
    background: #fff;
    border-radius: 30px;
    height: 25px;
    line-height: 25px;
}
.color-header span:nth-child(2) {
    margin-left: 50px;
    float: left;
}
.color-header span:last-child {
    margin-right: 50px;
    float: right;
}

.content {
    padding: 30px;
}

</style>
</head>
<body>

    <div class="card-wrap">
        <div class="color-header">
            <h1>[게시글 타이틀 들어갈 부분]</h1>
            <span><i class="far fa-clock"></i>[날짜 들어갈 부분]</span>
            <span><i class="fas fa-eye"></i>[조회수 들어갈 부분]</span>
        </div>
        <div class="content">
            [내용 들어갈 부분]
        </div>
    </div>

</body>
</html>
