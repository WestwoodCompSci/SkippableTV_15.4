<?php

require("../../../config/config.php");
$post_requirements = array(
	"is_post" => 1,
	"security_token" => "fUheHuhaSaH82haswU8ReSAcreD6wre5gevanEPaWrerEca6HacHAqechEnazEq2",
	"token" => null,
	"security" => isset($_POST['token'])? base64_decode(base64_decode($_POST["token"])) : null,
	"season_parent" => null,
	"season_number" => null,
	"num_episodes" => null,
	"season_length" => null,
);
if(!isset($_POST['is_post']) || $_POST['is_post'] !== "1")
	die(json_encode(array("error"=>1,"status"=>400,"errors"=>array("POST methods only"))));
foreach($post_requirements as $key=>$value):
if(!isset($_POST[$key]))
		die(json_encode(array("error"=>1,"status"=>412,"errors"=>array("Missing POST data"))));
	if(!is_null($value)):
		if(!$_POST[$key] == $value)
			die(json_encode(array("error"=>1,"status"=>412,"errors"=>array("POST data failed $key,$value"))));
	endif;
endforeach;

$not_allowed = array("HTTP_REFERER","HTTP_CACHE_CONTROL");
foreach($not_allowed as $key) {if(isset($_SERVER[$key])) die(json_encode(array("error"=>1,"status"=>403,"errors"=>array("Security exception"))));}

$required = array(); // This is temporary until we have the user agent set up from network; right now the requests are sent via a REST app
foreach($required as $key => $value):
	if(!isset($_SERVER[$key]))
		die(json_encode(array("error"=>1,"status"=>403,"errors"=>array("Security Exception"))));
	if(!is_null($value)):
		if(!($_SERVER[$key] === $value))
			die(json_encode(array("error"=>1,"status"=>403,"errors"=>array("Security Exception"))));
	endif;
endforeach;

$season_parent = $_POST['season_parent'];
$season_number = $_POST['season_number'];
$season_episodes = $_POST['num_episodes'];
$season_length = $_POST['season_length'];

if(!settype($season_parent,"int")) die(json_encode(array("error"=>1,"status"=>400,"errors"=>array("Given POST data has malformed syntax. Please recheck your syntax and try again later")))); //All of these will fail if it doesn't convert properly.
if(!settype($season_number,"int")) die(json_encode(array("error"=>1,"status"=>400,"errors"=>array("Given POST data has malformed syntax. Please recheck your syntax and try again later")))); //All of these will fail if it doesn't convert properly.
if(!settype($season_episodes,"int")) die(json_encode(array("error"=>1,"status"=>400,"errors"=>array("Given POST data has malformed syntax. Please recheck your syntax and try again later")))); //All of these will fail if it doesn't convert properly.

$connection = mysqli_connect(config::$database["host"],config::$database["username"],config::$database["password"],config::$database["database"]) 
	or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not connect to databsase"))));
$query = "SELECT * FROM `Seasons` WHERE $season_parent=`parent` AND $season_number = `number`";
$result = mysqli_query($connection, $query) or die(json_encode(array("error"=>1,"status"=>500,"errors"=>array("Query error!"))));

if(mysqli_num_rows($result) > 0)
	die(json_encode(array("error"=>1,"status"=>500,"errors"=>array("Season Exists!"))));

$query = "INSERT INTO `Seasons` (`parent`,`number`,`episodes`,`total length`) VALUES ({$season_parent},{$season_number},{$season_episodes},'{$season_length}')";
$result = mysqli_query($connection, $query) or die(json_encode(array("error"=>1,"status"=>500,"errors"=>array("Query error!"))));

die(json_encode(array("error"=>0,"status"=>200,"errors"=>null)));
?>