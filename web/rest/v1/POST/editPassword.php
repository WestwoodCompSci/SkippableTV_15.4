<?php
require("../../../config/config.php");
$requirements = array(
	"ispost",
	"id",
	"password",
	"username"
);
foreach($requirements as $requirement)
{
	if(!isset($_POST[$requirement]))
		die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Bad data $requirement"))));
}

$connection = mysqli_connect(config::$database["host"],config::$database["username"],config::$database["password"],config::$database["database"]) 
	or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not connect to databsase"))));

$info = array(
	"username" => $_POST['username'],
	"hash"     => $_POST['hash'],
	"id"       = $_POST['id']
);
settype($info["id"],"integer");

if(config::checkLogin($info,$connection))
{
	$query = "SELECT * FROM `Passwords` WHERE `id`={$_POST['id']}";
	$res   = mysqli_query($query,$connection) or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Couldn't get user details"))));
	$data  = mysqli_fetch_array($res);
	
	$salt  = $data["salt"];
	$password = $_POST['password'];
	$password = crypt($password,$salt);
	
	$query = "UPDATE `Passwords` SET `password`={$password} WHERE `id`={$_POST['id']}";
	mysqli_query($query,$connection) or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Couldn't update database"))));
	die(json_encode(array("error" => 0, "status" => 200,"errors" => array())));
}
die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Login fail"))));