<?php

require("../../../config/config.php");
$post_requirements = array(
	"is_post" => 1,
	"security_token" => "xB9wFUSNz3H3j69sUxK3rjz8sSderupCvekhPY5BkWKGAnYj3Uasd7xPtwD9m82d",
	"token" => null,
	"security" => isset($_POST['token'])? base64_decode(base64_decode($_POST["token"])) : null,
	"name" => null,
	"num_seasons" => null,
	"num_episodes" => null,
	"total_length" => null,
	"genre" => null,
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

$show_name = $_POST["name"];
$num_seasons = $_POST["num_seasons"];
$num_episodes = $_POST["num_episodes"];
$length = $_POST["total_length"];

if(!settype($num_seasons,"int")) die(json_encode(array("error"=>1,"status"=>400,"errors"=>array("Given POST data has malformed syntax. Please recheck your syntax and try again later")))); //All of these will fail if it doesn't convert properly.
if(!settype($num_episodes,"int")) die(json_encode(array("error"=>1,"status"=>400,"errors"=>array("Given POST data has malformed syntax. Please recheck your syntax and try again later")))); //All of these will fail if it doesn't convert properly.

$connection = mysqli_connect(config::$database["host"],config::$database["username"],config::$database["password"],config::$database["database"]) 
	or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not connect to databsase"))));
$query = "SELECT * FROM `Shows` WHERE \"$show_name\"=`name`";
$result = mysqli_query($connection, $query) or die(json_encode(array("error"=>1,"status"=>500,"errors"=>array("Query erroer!"))));

if(mysqli_num_rows($result) > 0)
	die(json_encode(array("error"=>1,"status"=>500,"errors"=>array("Show Exists!"))));

$query = "INSERT INTO `Shows` (`name`,`seasons`,`episodes`,`total length`,`genre`) VALUES (\"$show_name\",$num_seasons,$num_episodes,'{$length}','{$genre}'')";
$result = mysqli_query($connection, $query) or die(json_encode(array("error"=>1,"status"=>500,"errors"=>array("Query error!"))));

die(json_encode(array("error"=>0,"status"=>200,"errors"=>null,"result"=>mysqli_insert_id($connection))));
?>