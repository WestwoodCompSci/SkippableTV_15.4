<?php
require("../../../config/config.php");
$requirements = array(
	"ispost",
	"ep_id",
	"season_parent",
	"show_parent",
	"name",
	"length",
	"episode_number",
	"username",
	"id",
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
	"id"       => $_POST['id']
);
settype($info["id"],"integer");

if(config::checkLogin($info,$connection))
{
	$query = "SELECT * FROM `Users` WHERE `id`={$info['id']}";
	$res   = mysqli_query($connection,$query) or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Couldn't get user"))));
	$info  = mysqli_fetch_array($res);
	if($info["privileges"] == "2" || $info["privileges"] == 2 || $info["privileges"] == "1" || $info["privileges"] == 1):
		$query = "UPDATE `Episodes` SET `Season Parent` = {$_POST["season_parent",]} AND `Show Parent` = {$_POST["show_parent",]} AND `name` = \"{$_POST["name",]}\" AND `length` = \"{$_POST["length",]}\" AND `number` = {$_POST["episode_number",]} AND  WHERE `id`={$_POST["ep_id",]}";
		mysqli_query($connction,$query) or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Couldn't update database"))));
		die(json_encode(array("error" => 0, "status" => 200,"errors" =>array())));
	else:
		die(json_encode(array("error" => 1, "status" => 500,"errors" => array("User needs to be administrator or moderator"))));
	endif;
}
die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Login fail"))));