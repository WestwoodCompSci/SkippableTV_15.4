<?php
require("../../../config/config.php");
$requirements = array(
	"id",
	"username",
	"privileges",
	"Profile URI",
	"email",
	"fname",
	"lname",
	"birthday",
	"playlists",
	"ratings",
	"history",
	"progress",
	"hash"
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
	$query = "UPDATE `Users` SET `username` = {$_POST["username"]} AND `privileges` = {$_POST["privileges"]} AND `Profile URI` = {$_POST["Profile URI"]} AND `email` = {$_POST["email"]} AND `fname` = {$_POST["fname"]} AND `lname` = {$_POST["lname"]} AND `birthday` = {$_POST["birthday"]} AND `playlists` = {$_POST["playlists"]} AND `ratings` = {$_POST["ratings"]} AND `history` = {$_POST["history"]} AND `progress` = {$_POST["progress"]} WHERE `id`={$_POST['id']}";
	mysqli_query($query,$connection) or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Failed to update database"))));
	die(json_encode(array("error" => 0, "status" => 200,"errors" => array())));
}
die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Login fail"))));