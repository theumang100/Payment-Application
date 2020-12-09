<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Send Money</title>
    <script src="js\jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        function myFun() {
            var sendMoneyUser = $("#sendmoney_user").val();
            var sendMoneyAmount = $("#sendmoney_input").val();
            if (sendMoneyUser == "") {
                alert("Please Enter Receiver User Mobile NO");
            }
            else if (sendMoneyAmount == "") {
                alert("Please Enter Amount To be Send");
            }
            else {
                document.getElementById("sendmoney_button").type = "submit";
            }
        }
    </script>
    <style>
        div.top {
            background-color: #5b4159;
            padding: 5px;
            font-family: fontawesome;
            color: white;
            font-size: 18px;
            border-radius: 5px;
            letter-spacing: 1px;
        }

        div.sendmoneyblock {
            border: 1px solid;
            box-shadow: 5px 5px 5px #5b4159;
            border-radius: 7px;
            margin-top: 12%;
            margin-left: 26%;
            margin-right: 20%;
            width: 40%;
        }

        input[type=number], [type=text] {
            width: 60%;
            padding: 15px;
            margin-top: 1%;
            margin-bottom: 15px;
            margin-left: 18%;
            border: none;
            background: #f1f1f1;
            border-radius: 5px;
            display: block;
            color: #735c5c;
        }

        input[type=button], [type=submit] {
            width: 44%;
            padding: 15px;
            margin-bottom: 15px;
            margin-left: 28%;
            border: none;
            background: #5b4159;
            border-radius: 5px;
            display: block;
            color: #735c5c;
        }

        #sendmoney_button:hover {
            background-color: #f1f1f1;
            border-radius: 7px;
        }
    </style>
</head>
<body>
    <div class=" top">
        <div class="top-content"> <p>PayYou</p></div>
    </div>
    <form action="http://localhost:8080/PaymentApplication/Sendmoneytouser" method="post">
        <div class="sendmoneyblock">
            <div class="sendmoney">
                <input type="text" id="sendmoney_user" name="sendmoneyuser" placeholder="Enter user mobile no link to wallet" />
                <input type="number" id="sendmoney_input" step="1" min="0" name="sendmoneyinput" placeholder="Enter Amount in Rupees" />
            </div>
            <div class="sendmoneybutton">
                <input type="button" id="sendmoney_button" name="Sendmoneybutton" value="Send Money" onclick="myFun()" />
            </div>
        </div>
    </form>
</body>
</html>