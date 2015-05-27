<?php

$title = "Manage Account | SKiPpaBLe";

require("header.php");

?>

<div class="valign-wrapper">
	<div class="valign full-width center">
		<h1 class="center white-text darken-3 flow-text font-600">Manage your SKiPpaBLe account</h1>
		<form class="col s12 valign" id="form">
			<p id="login-error" class="hide red-text center accent-4 font-600">
				You shouldn't see this
			</p>
			<div class="row">
				<div class="input-field col s12">
					<a class="flow-text white-text username-container"><i class="mdi-social-person white-text darken-3"></i> Your username is <span class='username'>(this shouldn't be here)</span>. You cannot modify it.</a>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<a class="flow-text white-text"><i class="mdi-communication-vpn-key white-text darken-3"></i> We don't know your password. It was encrypted with a 1-way encryption metod.</a>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<a class="flow-text white-text profile-picture-description"><i class="mdi-action-face-unlock white-text darken-3"></i> Profile Picture</a><br/>
					<small class="flow-text white-text">We're working on an upload tool for images. Please upload your picture to an image hosting site and paste the url to change it.</small><br/>
					<img src="http://www.raswa.org.au/images/profile-default.jpg" alt="Profile picture" class="media profile-image" />
					<input type="url" class="center input-text" name="profile-uri" value="http://www.raswa.org.au/images/profile-default.jpg" />
				</div>
			</div>
		</form>
	</div>
</div>

<?php

require("footer.php");

?>