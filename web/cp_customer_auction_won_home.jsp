<%-- 
    Document   : cp_customer_auction_won_home
    Created on : Aug 21, 2014, 10:04:49 AM
    Author     : ducfpt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="my_message_right" id="won_auctions_tpage">
            <div class="message_common_border">
                <h1 title="WON AUCTIONS">WON AUCTIONS</h1>
                <p>&nbsp;</p>
            </div>
            <div class="message_common">
                <div class="forms_common">
                    <div class="title_cont_watchilist">
                        <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0" class="table-top">

                            <thead>
                                <tr>
                                    <th width="100" align="center"><b>Image</b></th>
                                    <th width="100" align="center"><b>Title</b></th>
                                    <th width="100" align="center"><b>End Time</b></th>
                                    <th width="100" align="center"><b>Price</b></th>
                                    <th width="100" align="center"><b>status</b></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td width="100" align="center">
                                        <h3>
                                            <a href=""><img src="http://www.unieauction.com/buy-sell-demo/public/uploaded_files/products/thumb/52a2c96dd6a0bBird-1.jpg" width="100" height="100" title="Handmade Gifts"></a>
                                        </h3>
                                    </td>
                                    <td width="100" align="center">
                                        <a title="Handmade Gifts" href="">Handmade Gifts</a>
                                    </td>

                                    <td width="100" align="center">
                                        <h2>2013-12-14 01:50:27</h2>
                                    </td>
                                    <td width="100" align="center">
                                        <h2><font class="">$</font> 51.00</h2>
                                    </td>
                                    <td width="100" align="center">
                                        <h2>
                                            <form action="" name="paymentsubmit9" id="paymentsubmit9" method="post">
                                                <input type="hidden" name="form[id][]" value="247">
                                                <input type="hidden" name="form[image][]" value="53e325f83b5ea38265-640x360-barclays-bike_tower-bridge_640.jpg">
                                                <input type="hidden" name="form[product_url][]" value="bicycleY9KJ2">
                                                <input type="hidden" name="form[unitprice][]" value="32">
                                                <input type="hidden" name="form[shipping_cost]" value="30">
                                                <input type="hidden" name="form[quantity][]" value="1">
                                                <input type="hidden" name="form[name][]" value="Bicycle">
                                                <input type="hidden" name="form[type]" value="wonauction">
                                                <input type="hidden" name="form[nauction_type]" value="product">
                                                <a href="javascript:;" onclick="$( & quot; #paymentsubmit9 & quot; ).submit();">Buy Now</a>
                                            </form>
                                        </h2>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
