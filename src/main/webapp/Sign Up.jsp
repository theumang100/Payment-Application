<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<script src="js\jquery-3.4.1.min.js"></script>


<script type="text/javascript">
	function myFunction() {
		var firstName = $("#first_name").val();
		var lastname = $("#last-name").val();
		var phoneNo = $("#ph_no").val();
		var Email = $("#email").val();
		var Date = $("#datepicker").val();
		var pass = $("#password").val();
		var Gender = $('input[name=gender]').val();
		var emailidvalid = IsEmailvalid(Email);
		var phoneNumberValid = IsPhoneNumberValid(phoneNo);
		var passwordValid = IsPasswordValid(pass);
		if (firstName == "") {
			alert("First Name Should Not be Empty");
		} else if (!phoneNumberValid) {
			alert("Enter Phone Number Correctly");
		} else if (!passwordValid) {
			alert("Enter Password Correctly");
		} else if (!emailidvalid) {
			alert("Enter E-mail Correctly");
		} else {
			document.getElementById("submit").type = "submit";

		}
	}

	function IsPhoneNumberValid(phoneNo) {
		var numericReg = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
		if (!numericReg.test(phoneNo)) {
			return false;
		}
		if (phoneNo == '' || phoneNo.length != 10) {
			return false;
		}
		return true;

	}
	function IsEmailvalid(Email) {
		var emailreg = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
		if (!emailreg.test(Email)) {
			return false;
		}

		if (Email == '') {
			return false;
		}

		return true;
	}
	function IsPasswordValid(pass) {
		if (pass == "" || pass.length < 8) {
			return false;
		}
		return true;
	}
</script>




<title>SignUp</title>
<style>
input[type=text], [type=date], [type=password] {
	width: 100%;
	padding: 15px;
	margin-top: 5px;
	margin-bottom: 22px;
	border: none;
	background: #f1f1f1;
	border-radius: 7px;
	display: inline-block;
}

input[type=button], [type=submit] {
	width: 50%;
	padding: 15px;
	margin-top: 5px;
	margin-bottom: 22px;
	border: none;
	background: #5b4159;
	border-radius: 5px;
	display: inline-block;
	color: white;
}

#email:hover, #password:hover, #first_name:hover, #last-name:hover,
	#ph_no:hover, #datepicker:hover, #submit:hover {
	background-color: #afbab1;
	border-radius: 7px;
}

div.form {
	color: black;
	padding: 8px;
	width: 50%;
	margin-left: 25%;
}

div.head {
	color: #232823;
	text-align: center;
}

div.button {
	display: block;
}

div.temp {
	font-family: monospace;
}

div.label {
	color: #586758;
}

div.inp {
	
}

div.submitbutton {
	text-align: center;
}

div.top {
	background-color: #5b4159;
	padding: 5px;
	font-family: fontawesome;
	color: white;
	font-size: 18px;
	border-radius: 5px;
	letter-spacing: 1px;
}

div.top-content {
	margin-left: 2%;
}
</style>
</head>

<body>
	<div class=" top">
		<div class="top-content">
			<p>PayYou</p>
		</div>
	</div>
	<div class="form">

		<div>
			<form
				action="http://localhost:8080/PaymentApplication/InsertUserdata"
				class="form_class" method="post" id="submit_form" id="signupform">

				<div>

					<div class="temp">
						<div class="label">
							<b> FIRST NAME:</b>
						</div>
						<div class="inp">
							<input title="Enter First Name" id="first_name" type="text"
								name="first_name" placeholder="Enter First Name" required
								autofocus>
						</div>
					</div>
					<div class="temp">
						<div class="label">
							<b> LAST NAME:</b>
						</div>
						<div class="inp">
							<input id="last-name" type="text" name="last_name"
								placeholder="Enter Last Name">
						</div>
					</div>
					<div class="temp">
						<div class="label">
							<b>MOBILE:</b>
						</div>
						<div class="inp">
							<input title="Enter 10-digit Mobile Number" id="ph_no"
								type="text" name="phone_number"
								placeholder="Enter 10-digit Mobile Number" required>
						</div>

					</div>
					<div class="temp">
						<div class="label">
							<b>PASSWORD:</b>
						</div>
						<div class="inp">
							<input title="Enter Password" id="password" type="password"
								name="password"
								placeholder="Enter Password min 8 character require" required>
						</div>

					</div>
					<div class="temp">
						<div class="label">
							<b> E-mail Address:</b>
						</div>


						<div class="inp">
							<input id="email" type="text" name="email_address"
								placeholder="Enter E-mail id" />
						</div>
					</div>
					<div class="temp">
						<div class="label">
							<b>Birth Date:</b>
						</div>
						<div class="inp">
							<input type="date" name="birth_date" id="datepicker" />
						</div>
					</div>
					<div class="temp">
						<div class="label">
							<br /> <b>GENDER:</b>
						</div>
						<div class="inp">
							<input class="circle" type="radio" name="gender" value="male"
								checked />Male<input class="circle" type="radio" name="gender"
								value="female" />Female<br />
						</div>
					</div>
					<div class="temp">
						<div class="inp">
							<div class="submitbutton">
								<input type="button" id="submit" value="SignUp"
									onclick="myFunction()" />
							</div>
						</div>
					</div>

				</div>



			</form>

		</div>
	</div>
</body>
</html>