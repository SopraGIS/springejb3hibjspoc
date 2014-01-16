
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" type="text/css" media="screen, projection">
    <style>
        body {
            padding-top: 60px;
        }
    </style>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.css" />" type="text/css" media="screen, projection">
</head>

<body>

<c:url var="newUrl" value="/blogposts/new"/>
<c:url var="homeUrl" value="/"/>

<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">blogboard</a>

            <div class="nav-collapse">
                <ul class="nav">
                    <li class="active"><a href="${homeUrl}"><fmt:message key="nav.home"/></a></li>
                    <li><a href="${newUrl}"><fmt:message key="nav.new"/></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>