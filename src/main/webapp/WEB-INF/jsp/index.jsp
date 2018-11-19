<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="inc/pagebegin.jsp" %>

<script>
    $(function () {
        $("#btn").on("click", function () {

            $.ajax({
                url: "wechatpay.action",
                type: "post",
                dataType: "json",
                async: false,
                data: "",
                success: function (data) {
                    $("#qrcodeDiv").empty();
                if(data.result=="SUCCESS"){
                    $("#qrcodeDiv").qrcode({
                        render: "table",
                        width: 300,
                        height: 300,
                        text:data.qrCode
                    });
                }
                }
            })
        })

        $("#btnUni").on("click", function () {

            $.ajax({
                url: "pay/unionpay.action",
                type: "post",
                dataType: "json",
                async: false,
                data: "",
                success: function (data) {
                    if(data.result=="SUCCESS"){
                        alert(data.testUnitorderVO.retcode);
                    }
                }
            })
        })
    })


</script>

<body>
<div align="center" style="margin-top: 30px">
    <input type="button" id="btn" value="支付测试" >
</div>


<div id="qrcodeDiv" align="center" style="margin-top: 30px"></div>

<div align="center" style="margin-top: 30px">
    <input type="button" id="btnUni" value="通联接口支付测试" >
</div>
</body>
</html>