<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org" xmlns="http://www.w3.org/1999/html">
<head>
    <title>calculator</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

</head>
<body>
<#if error??>
<div class="alert">
    <strong>${error}</strong>
</div>
</#if>
        <center>
            <form method="GET">
                <div class="input-group">
                    <input type="text" class="form-control" name="a" <#if a??>value="${a}"</#if>
                           placeholder="First num"/>
                </div>
                <div class="input-group">
                    <input type="text" class="form-control" name="b" <#if a??>value="${b}"</#if>
                           placeholder="Second num"/>
                </div>
                <select id="categorySelector1" class="form-control"
                        name="oper">
                    <option value="plus">+</option>
                    <option value="minus">-</option>
                    <option value="mult">*</option>
                    <option value="div">/</option>
                </select>
                <input type="submit">Calc</input>
            </form>
        </center>
<#if answer??>
<div class="alert">
    <strong>${answer}</strong>
</div>
</#if>

</body>
<script type="text/javascript">
    <#if oper??>
var elem = document.getElementById("categorySelector1");
if("plus" === "${oper}"){

    elem.selectedIndex = 0;
}
    if ("minus" === "${oper}") {
        elem.selectedIndex = 1;
    }
    if ("mult" === "${oper}") {
        elem.selectedIndex = 2;
    }
    if ("div" === "${oper}") {
        elem.selectedIndex = 3;
    }
    </#if>
</script>
</html>