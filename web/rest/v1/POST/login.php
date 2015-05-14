<?php

$requirements = array(
	"ispost" => 1,
	"username",
	"password"
);
foreach($requirements as $requirement)
{
	if(!isset($_POST[$requirement]))
		die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Bad data"))));
}

$username = $_POST['username'];
$password = $_POST['password'];

if(preg_match('/\s/',$username))
	die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Username contains spaces"))));

$connection = mysqli_connect(config::$database["host"],config::$database["username"],config::$database["password"],config::$database["database"]) 
	or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not connect to databsase"))));
$query = "SELECT `id` FROM `Users` WHERE `username` = $username";

$result = mysqli_query($connection,$query)
	or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Query error checking username"))));
switch(mysqli_num_rows($result)):
	case 0:
		die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Username does not exist"))));
	case 1:
		cont();
	default:
		die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Multiple usernames exist. Contact administrator"))));

function cont()
{
	$password = crypt($password);
	$id = mysqli_fetch_array($result);
	$id = $id["id"];
	settype($id,"integer");
	$query = "SELECT * FROM `Passwords` WHERE `id` = $id";
	$result mysqli_query($connection,$query) or 
		die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not get username from password database"))));
	if(mysqli_num_rows($result) == 1)
	
}
?>