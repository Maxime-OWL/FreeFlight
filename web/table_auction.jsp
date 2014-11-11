<%-- 
    Document   : table_auction
    Created on : Jul 31, 2014, 10:24:46 PM
    Author     : MrTu
--%>
<%@page import="Entity.Auction"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/table.css"/>

        <script language="JavaScript" type="text/javascript" src="JavaScript/jquery.js"></script>
        <script language="JavaScript" type="text/javascript" src="JavaScript/jts.js"></script>

        <script>
            $(document).ready(function() {

                $('#demoTable').jTPS({perPages: [5, 12, 15, 50, 'ALL'], scrollStep: 1, scrollDelay: 30,
                    clickCallback: function() {
                        // target table selector
                        var table = '#demoTable';
                        // store pagination + sort in cookie 
                        document.cookie = 'jTPS=sortasc:' + $(table + ' .sortableHeader').index($(table + ' .sortAsc')) + ',' +
                                'sortdesc:' + $(table + ' .sortableHeader').index($(table + ' .sortDesc')) + ',' +
                                'page:' + $(table + ' .pageSelector').index($(table + ' .hilightPageSelector')) + ';';
                    }
                });

                // reinstate sort and pagination if cookie exists
                var cookies = document.cookie.split(';');
                for (var ci = 0, cie = cookies.length; ci < cie; ci++) {
                    var cookie = cookies[ci].split('=');
                    if (cookie[0] == 'jTPS') {
                        var commands = cookie[1].split(',');
                        for (var cm = 0, cme = commands.length; cm < cme; cm++) {
                            var command = commands[cm].split(':');
                            if (command[0] == 'sortasc' && parseInt(command[1]) >= 0) {
                                $('#demoTable .sortableHeader:eq(' + parseInt(command[1]) + ')').click();
                            } else if (command[0] == 'sortdesc' && parseInt(command[1]) >= 0) {
                                $('#demoTable .sortableHeader:eq(' + parseInt(command[1]) + ')').click().click();
                            } else if (command[0] == 'page' && parseInt(command[1]) >= 0) {
                                $('#demoTable .pageSelector:eq(' + parseInt(command[1]) + ')').click();
                            }
                        }
                    }
                }

                // bind mouseover for each tbody row and change cell (td) hover style
                $('#demoTable tbody tr:not(.stubCell)').bind('mouseover mouseout',
                        function(e) {
                            // hilight the row
                            e.type == 'mouseover' ? $(this).children('td').addClass('hilightRow') : $(this).children('td').removeClass('hilightRow');
                        }
                );

            });


        </script>
    </head>
    <body>
        <%
            ArrayList<Auction> auctionsCheck = (ArrayList<Auction>) request.getAttribute("auctions");
            if (auctionsCheck == null) {

            } else {
        %>
        <form name="form1" method="post" action="">
            <table id="demoTable" style="border: 1px solid #ccc;" cellspacing="0" width=99.5%>
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Category</th>
                        <th>Seller</th>
                        <th>Status</th>
                        <th>Current bid</th>
                        <th>Highest bidder</th>
                    </tr>  
                </thead>
                <tbody>
                    <jsp:useBean id="auctions" class="java.util.ArrayList" scope="request">
                    </jsp:useBean>
                    <c:forEach var="auction" items="${auctions}" varStatus="status"> 
                        <tr> 
                            <td>${status.count}</td> 
                            <td><a href="AuctionController?service=view_details&auctionId=${auction.id}">${auction.title}</a></td>
                            <td>${auction.categoryName}</td>
                            <td>${auction.sellerName}</td>
                            <td>${auction.getStatus()}</td>
                            <td>${auction.getCurrentBidString()}</td>
                            <td>${auction.getTopBidderName()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot class="nav">
                    <tr>
                        <td colspan=7>
                            <div class="pagination"></div>
                            <div class="paginationTitle">Page</div>
                            <div class="selectPerPage"></div>
                            <div class="status"></div>
                        </td>
                    </tr>
                </tfoot>
            </table>
        </form> 
        <% }
         %>
    </body>
</html>
