<%-- 
    Document   : cp_customer_transaction_home
    Created on : Aug 21, 2014, 9:57:58 AM
    Author     : ducfpt
--%>

<%@page import="Entity.Transaction"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <div class="my_message_right" id="mytransactions_page">
            <div class="message_common_border">
                <h1 title="MY TRANSACTIONS" style="width:131px;">MY TRANSACTIONS</h1>
                <p style="width:588px;">&nbsp;</p>
            </div>
            <div class="message_common">
                <div class="forms_common">
                    <%
                        ArrayList<Transaction> trans = (ArrayList<Transaction>) request.getAttribute("transaction");
                        if (trans.size() <= 0) {
                    %>
                    No items
                    <%} else { %>    
                    <div class="title_cont_watchilist">
                        <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0" class="table-top">
                            <thead>
                                <tr>

                                    <th width="30" align="center">
                                        <b>ID</b>
                                    </th>
                                    <th  align="center">
                                        <b>Transaction Date</b>
                                    </th>
                                    <th width="350" align="left">
                                        <b>Description</b>
                                    </th>
                                    <th align="left">
                                        <b>Total Amount</b>
                                    </th>
                                </tr>
                            </thead>

                            <tbody> 
                                <% for (int i = 0; i < trans.size(); i++) {
                                %> 
                                <tr>
                                    <td align="center">
                                        <h2><%=trans.get(i).getId()%></h2>
                                    </td>
                                    <td align="center">

                                        <h2><%=trans.get(i).getDateString()%></h2>
                                    </td>

                                    <td  align="left">
                                        <h2><%=trans.get(i).getDesc()%></h2>
                                    </td>
                                    <td align="left">
                                        <h2><font class=""></font><%=trans.get(i).getAmountString()%></h2>
                                    </td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                    <%

                        }
                    %>

                </div>
                <div class="user" style="display:none;">344</div>
            </div>		
            <div class="nauction_paginations">
                <p></p>
            </div>

        </div>
    </body>
</html>
