<?php

require("../../config/config.php");
if(!isset($_GET['show_id'])) 
	die(json_encode(array("error" => 1,"status" => 403, "errors" => array("Show id not specified"))));
$connection = mysqli_connect(config::$database["host"],config::$database["username"],config::$database["password"],config::$database["database"]) 
	or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not connect to databsase"))));
$db_prefix = config::$database['prefix'];
$showid = $_GET['show_id'];
$query = "SELECT * FROM Shows where $showid=`id`";
$result = mysqli_query($connection,$query) or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Query error"))));;
$myArray = array();
if(mysqli_num_rows($result) > 1)
	die(json_encode(array("error" => 1, "status" => 500,"errors" => array("More than one show returned"))));
while($row = $result->fetch_array(MYSQL_ASSOC))
	 $myArray[] = $row;
$return = array("error" => 0, "status" => 200, "errors" => null, "show" => $myArray);
mysqli_close($connection);
die(json_encode($return));
?>