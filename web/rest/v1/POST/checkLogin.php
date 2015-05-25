<?php
require("../../../config/config.php");
$requirements = array(
	"ispost",
	"username",
	"hash",
	"id"
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
	"id"       => $_POST['id']
);

die(json_encode(array("loggedIn"=>config::checkLogin($info))));