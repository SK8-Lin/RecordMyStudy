<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
    <script type="text/javascript" src="js/easying.js"></script>
    <script type="text/javascript" src="js/jquery.beattext.js"></script>
    <title>index</title>
</head>
<script>
    $(function () {
        /*展示全部信息功能*/
        $.ajax({
            url : "showAll.do",
            type : "get",
            dataType : "json",
            success : function (data) {
                $.each (data, function (i, n) {
                    $("#showAll").append("<tr><td>"+n.date+"</td><td>"+n.time+"<span>&nbsp;小时" +
                        "</span></td><td>"+n.timeAll+"<span>&nbsp;小时</span></td></tr>");
                })
            }
        })

        /*更新记录*/
        $("#add").click(function () {
            var time = $.trim($("#time").val());
            $.ajax({
                url : "add.do",
                type : "get",
                dataType : "json",
                data : {
                    "time" : time
                },
                success : function (data) {
                    if (data.succeed) {
                        alert("更新成功");
                    } else {
                        alert("更新失败");
                    }
                    window.location.href = "index.jsp";
                }
            })
        })
    })
</script>
<body>
    <div class="layui-container bg">
        <!-- 标题部分 -->
        <div class="layui-row box1">
            <div class="layui-col-md12">
                <p id="autoRotateText">今天你学习了吗？</p>
                <!-- <h1>300+挑战计划</h1> -->
            </div>
        </div>
        <!-- 输入日期部分 -->
        <div class="layui-row box2">
            <div class="layui-col-md4">
                <label class="layui-form-label label">请输入您今天的学习时长</label>
                <div class="layui-input-block input">
                    <input type="text" id="time" autocomplete="off" placeholder="单位：小时" class="layui-input" style="width: 100px;">
                </div>
            </div>
            <div class="layui-col-md4">
                <button id="add" type="button" class="layui-btn  layui-btn-radius layui-btn-warm update">
                    <i class="layui-icon layui-icon-upload-drag"></i>
                    &nbsp;更新
                </button>
            </div>
        </div>
        <!-- 展示部分 -->
        <div class="layui-row box3">
            <div class="layui-col-md10 layui-col-md-offset1">
                <table class="layui-table show" lay-size="lg">
                    <tbody id="showAll">
                    <%--<tr>
                        <td>2020-1-7</td>
                        <td>4.5<span>&nbsp;小时</span></td>
                        <td>4.5<span>&nbsp;小时</span></td>
                    </tr>--%>
                    </tbody>
                    <thead>
                        <tr>
                            <th>日期</th>
                            <th>当天时长</th>
                            <th>总计时长</th>
                        </tr> 
                    </thead>
                </table>
            </div>
        </div>
    </div>
</body>
</html>