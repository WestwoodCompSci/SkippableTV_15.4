<?php
$title = "Logout | SKiPpaBLe";

require("header.php");

?>

<h1 class="center">
	Logging you out...
</h1>
<p class="center">
	 Make sure you have javascript enabled.
</p>
<div class="logout-status-parent blue-grey-text center">
	<h4>Here's the info we have so far:<br/></h4>
	<h2 class="logout-status red-text darken-3 center">Waiting for server response</h2>
</div>
<script type="text/javascript">
$(document).ready(function()
{
	var hash = window.localStorage.getItem("hash"), info = JSON.parse(window.localStorage.getItem("info"));
	if(info.id && info.username && hash)
	{
		$.post("/web/rest/v1/logout.php<?php if(isset($_GET['all']) && $_GET['all'] == "1") print "?all=1";?>",
			{
				ispost:1,
				username: info.username,
				id:info.id,
				hash:hash
			}
		).success(function(data)
		{
			if(data.error)
			{
				$(".logout-status").html("Error logging out... Removing data.");
				window.localStorage.setItem("hash",null);
				window.localStorage.setItem("info",null);
				$(".logout-status").delay(1000).html("Deleted traces of you being logged in... redirecting...");
				window.location = "login.php";
			}
			else
			{
				$(".logout-status").html("Success! Redirecting...");
				window.location = "./";
			}
		}).error(function(d)
		{
			console.log(d);
			$(".logout-status").html("Couldn't connect to server; If problem persists, try to <a class=' pink-text accent-4' href='logout.php?all=1'>logout of all sessions</a> or contact administrator");
		});
	}
	else
	{
		$(".logout-status").html("You are not properly logged in. Suggest you <a class='pink-text accent-4' href='logout.php?all=1'>log out of all sessions</a>");
		window.localStorage.setItem("hash",null);
		window.localStorage.setItem("info",null);
		$(".logout-status").delay(1000).html("Deleted traces of you being logged in... redirecting...");
		window.location = "login.php";
	}
});
</script>