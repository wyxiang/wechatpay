<%@ page contentType="text/html;charset=UTF-8" %>
<title>${webTitle}</title>
<LINK REL="SHORTCUT ICON" HREF="/demo/res/img/logo/icon.ico">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>


<script src="<%=basePath%>res/js/jquery.min.js"></script>
<script src="<%=basePath%>res/js/jquery.qrcode.min.js"></script>