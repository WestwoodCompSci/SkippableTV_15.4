<?php
require("../../../config/config.php");
$requirements = array(
	"ispost",
	"username",
	"password",
	"profile_uri",
	"email_address",
	"fname",
	"lname",
	"birthday"
);
foreach($requirements as $requirement)
{
	if(!isset($_POST[$requirement]))
		die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Bad data $requirement"))));
	if(preg_match('/\s/',$requirement))
		die(json_encode(array("error" => 1, "status" => 403,"errors" => array("$requirement contains spaces"))));
}

$username = $_POST['username'];
$password = $_POST['password'];
$picture  = $_POST['profile_uri'];
$email   = $_POST["email_address"];
$fname   = $_POST["fname"];
$lname   = $_POST["lname"];
$bday   = $_POST["birthday"];

$connection = mysqli_connect(config::$database["host"],config::$database["username"],config::$database["password"],config::$database["database"]) 
	or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not connect to databsase"))));
$query = "SELECT `id` FROM `Users` WHERE `username` = \"$username\"";
$result = mysqli_query($connection,$query)
	or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Query error checking if username exists"))));
if(mysqli_num_rows($result) > 0)
	die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Username exists"))));
$query = "INSERT INTO `Users` (`username`, `privileges`, `Profile URI`, `email`, `fname`, `lname`, `birthday`) VALUES (\"{$username}\",0,\"{$picture}\",\"{$email}\",\"{$fname}\",\"{$lname}\",{$bday})";
//print $query;
$res = mysqli_query($connection,$query)
	or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Query error E-3"))));
if(mysqli_affected_rows($connection) > 0)
{
	$query = "SELECT * FROM `Users` WHERE `Username`=\"{$username}\" AND `fname`=\"{$fname}\"";
	$info = mysqli_query($connection,$query)
		or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not retreive information"))));
	$data = mysqli_fetch_array($info);
	$salt = crypt(rand(0,1887).$username.$password);
	$password = crypt($password,$salt);
	$id = $data['id'];
	$query = "INSERT INTO `Passwords` (`id`,`password`,`salt`) VALUES ($id,\"$password\",\"$salt\")" or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Error saving password"))));;
	die(json_encode(array("error" => 1, "status" => 200,"errors" => null,"data"=>$data)));
}
else die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Error inserting"))));
?>