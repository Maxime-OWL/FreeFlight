<%-- 
    Document   : ajax_top_message
    Created on : Aug 28, 2014, 9:32:36 AM
    Author     : Maxime
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            String errorCodeString = request.getParameter("errorCode");
            int errorCode = 0;
            if (errorCodeString != null) {
                errorCode = Integer.parseInt(errorCodeString);
            }
        %>
    </head>
    <body>
        <% if (errorCode == 1) { %>
        <div class="sliders">
            <label style="display:block;color:#666;padding-top: 3px;;padding-left: 5px ">
                <span>Added to <a href="WatchlistController?service=viewwatchlist">watchlist</a></span>
            </label>
        </div>

        <% } else if (errorCode == 2) { %>
        <div class="sliders">
            <label style="display:block;color:#666;padding-top: 3px;;padding-left: 2px ">
                <span>Already in <a href="WatchlistController?service=viewwatchlist">watchlist</a></span>
            </label>
        </div>
        <% } else if (errorCode == 3) { %>
        <div class="sliders">
            <label style="display:block;color:#666;padding-top: 3px;;padding-left: 2px ">
                <span>Restricted</span>
            </label>
        </div>
        <% } else { %>
        <div class="sliders">
            <label style="display:block;color:#666;padding-top: 3px;;padding-left: 2px ">
                <span>Watchlist add failed</span>
            </label>
        </div>
        <% } %>
    </body>
</html>
