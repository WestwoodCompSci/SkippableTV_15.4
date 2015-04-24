<?php

require("../../config/config.php");
if((!(isset($_GET['episode'])) || !isset($_GET['show']) || !isset($_GET['season'])) && !isset($_GET['rating_id']))
	die(json_encode(array("error" => 1,"status" => 403, "errors" => array("Not enough data specified"))));
$connection = mysqli_connect(config::$database["host"],config::$database["username"],config::$database["password"],config::$database["database"]) 
	or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not connect to databsase"))));
$db_prefix = config::$database['prefix'];
if(isset($_GET['episode']) && isset($_GET['show']) && isset($_GET['season'])):
	$episode = $_GET['episode'];
	$show = $_GET['show'];
	$season = $_GET['season'];
	$query = "SELECT * FROM Ratings WHERE $episode=`episode` AND $show=`show` AND $season=`season`";
else:
	$id = $_GET['rating_id']
	$query = "SELECT * FROM Ratings where $id=`id`";
endif;
$result = mysqli_query($connection,$query) or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Query error"))));
$myArray = array();
while($row = $result->fetch_array(MYSQL_ASSOC))
	 $myArray[] = $row;
$return = array("error" => 0, "status" => 200, "errors" => null, "rating" => $myArray, "number_of_ratings"=>mysqli_num_rows($rating));
mysqli_close($connection);
die(json_encode($return));
?>