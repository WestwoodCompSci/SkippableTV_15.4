<?php

require("../../config/config.php");
if((!isset($_GET['season_number']) || !isset($_GET['show_id'])) && !(isset($_GET['season_id'])))
	die(json_encode(array("error" => 1,"status" => 403, "errors" => array("Correct parameters not specified"))));
$connection = mysqli_connect(config::$database["host"],config::$database["username"],config::$database["password"],config::$database["database"]) 
	or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not connect to databsase"))));
$db_prefix = config::$database['prefix'];
if(isset($_GET['season_number']) && isset($_GET['show_id'])):
	$number = $_GET['season_number'];
	$parent = $_GET['show_id'];
	$query = "SELECT * FROM Seasons WHERE $number=`number` AND $parent=`parent`";
else:
	$season_id = $_GET['season_id'];
	$query = "SELECT * FROM Seasons WHERE $season_id=`id`";
endif;
$result = mysqli_query($connection,$query) or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Query error"))));
$myArray = array();
if(mysqli_num_rows($result) > 1)
	die(json_encode(array("error" => 1, "status" => 500,"errors" => array("More than one season specified"))));
while($row = $result->fetch_array(MYSQL_ASSOC))
	 $myArray[] = $row;
$return = array("error" => 0, "status" => 200, "errors" => null, "season" => $myArray);
mysqli_close($connection);
die(json_encode($return));
?>