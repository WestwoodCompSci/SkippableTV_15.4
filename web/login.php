<?php $title="Login | SKiPpaBLe" ; $mainClass = ""; require( "header.php"); ?>
<style>
</style>
<div class="page-height login-background valign-wrapper">
	<div class="trans-overlay"></div>
	<div class="no-trans teal valign center login z-depth-5">
		<h1 class="center white-text darken-3 flow-text font-600">SKiPpaBLe login</h1>
		<form class="col s12 valign" id="form">
			<p id="login-error" class="hide red-text center accent-4 font-600">
				You shouldn't see this
			</p>
			<div class="row">
				<div class="input-field col s12">
					<i class="mdi-social-person white-text darken-3 prefix"></i>
					<input id="username" name="username" type="text" class="validate" required>
					<label for="username">Username</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<i class="mdi-communication-vpn-key prefix white-text darken-3"></i>
					<input id="password" name="password" type="password" class="validate" required>
					<label for="password">Password</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<button class="btn waves-effect waves-light white-text darken-3" type="text" name="action" id="btn-login">Login
						<i class="mdi-content-send right white-text darken-3"></i>
					</button>
				</div>
			</div>
		</form>
		<div class="row">
			<div class="input-field col s12">
				<button class="btn waves-effect white-text darken-3" type="text">
					<a href="signup.php" class="white-text darken-3">SignUp?</a>
				</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$("#form").submit(function(e)
{
	e.preventDefault();
	if(JSON.parse(window.localStorage.getItem("info")) && window.localStorage.getItem("hash"))
	{
		$("#login-error").html("You are already logged in! <a href='logout.php'>Logout?</a>");
		$("#login-error").removeClass("hide");
		return false;
	}
	$("#btn-login").html("working...");
	var username = $("input[name=username]").val();
	var password = $("input[name=password]").val();
	$.post("/web/rest/v1/POST/login.php",{
			"ispost" : 1,
			"username" : username,
			"password" : password
		}
	).success(function(d)
	{
		console.log(d);
		if(d.error)
		{
			$("#login-error").html(d.errors);
			$("#login-error").removeClass("hide");
		}
		else
		{
			window.localStorage.setItem("info",JSON.stringify(d.info[0]));
			window.localStorage.setItem("hash",d.user_hash);
			$("#login-error").addClass("green-text");
			$("#login-error").html("Success");
			$("#login-error").removeClass("hide");
		}
	}).error(function()
	{
		$("#login-error").html("Couldn't connect to server");
		$("#login-error").removeClass("hide");
	}).always(function(){$("#btn-login").html("Login <i class=\"mdi-content-send right grey-text darken-2\"></i>")});
	return false;
});
</script>
<?php require( "footer.php"); ?>