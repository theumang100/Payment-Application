<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add Money</title>
<script src="js\jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function myFun() {
		var debitCardNo = $("#debitcard_no").val();
		var debitCardpin = $("#debitcard_pin").val();
		var addMoneyAmount = $("#addmoney_input").val();
		if (debitCardNo == "") {
			alert("Please Enter Debit Card NO");
		} else if (debitCardpin == "") {
			alert("Please Enter Debit Card Pin");
		} else if (addMoneyAmount == "") {
			alert("Please Enter Amount");
		} else {
			document.getElementById("addmoney_button").type = "submit";
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

div.addmoneyblock {
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

#addmoney_button:hover {
	background-color: #f1f1f1;
	border-radius: 7px;
}
</style>
</head>
<body>
	<div class=" top">
		<div class="top-content">
			<p>PayYou</p>
		</div>
	</div>
	<form method="post"
		action="http://localhost:8080/PaymentApplication/Debitcardaddmoney">
		<div class="addmoneyblock">
			<div class="addmoney">
				<input type="text" id="debitcard_no" name="debitcardno"
					placeholder="Enter Debit Card No" /> <input type="text"
					id="debitcard_pin" name="debitcardpin"
					placeholder="Enter Four Digit Pin" /> <input type="number"
					id="addmoney_input" step="1" min="0" name="addmoneyinput"
					placeholder="Enter Amount in Rupees" />
			</div>
			<div class="addmoneybutton">
				<input type="button" id="addmoney_button" name="Addmoneybutton"
					value="Add Money" onclick="myFun()" />
			</div>
		</div>
	</form>
</body>
</html>