<?php

require("../../config/config.php");
if(!(((isset($_GET['parent_show'])) && (isset($_GET['parent_season'])) && (isset($_GET['episode_number']))) || (isset($_GET['episode_id']))))
{
	die(json_encode(array("get"=> $_GET,"error" => 1,"status" => 403, "errors" => array("Not enough data not specified"))));
}
$connection = mysqli_connect(config::$database["host"],config::$database["username"],config::$database["password"],config::$database["database"]) 
	or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not connect to databsase"))));
$db_prefix = config::$database['prefix'];
if(isset($_GET['parent_show']) && isset($_GET['parent_season'])):
	$parent_show = $_GET['parent_show'];
	$parent_season = $_GET['parent_season'];
	$episode = $_GET['episode_number'];
	$query = "SELECT * FROM Episodes WHERE $parent_season=`Season Parent` AND $parent_show=`Show Parent` AND $episode=`number`";
else:
	$episode_id = $_GET['episode_id'];
	$query = "SELECT * FROM Episodes where $episode_id=`id`";
endif;
$result = mysqli_query($connection,$query) or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Query error"))));
$myArray = array();
if(mysqli_num_rows($result) > 1)
	die(json_encode(array("error" => 1, "status" => 500,"errors" => array("More than one episode returned"))));
while($row = $result->fetch_array(MYSQL_ASSOC))
	 $myArray[] = $row;
$return = array("error" => 0, "status" => 200, "errors" => null, "episode" => $myArray);
mysqli_close($connection);
die(json_encode($return));
?>