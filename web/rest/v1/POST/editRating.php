<?php
require("../../../config/config.php");
$requirements = array(
	"ispost",
	"season",
	"show",
	"episode",
	"rating",
	"comment",
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
	"id"       = $_POST['id']
);
settype($info["id"],"integer");

if(config::checkLogin($info,$connection))
{
	$query = "UPDATE `Ratings` SET `rating` = {$_POST['rating']} AND `comment` = \"{$_POST['comment']}\" WHERE `season`={$_POST['season']} AND `show`={$_POST['show']} AND `episode`={$_POST['episode']} AND `owner_id` = {$_POST['id']}";;
	mysqli_query($query,$connection) or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Couldn't update table"))));
	die(json_encode(array("error" => 0, "status" => 200,"errors" => array())));
}
die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Login fail"))));