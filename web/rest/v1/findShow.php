<?php

require("../../config/config.php");
if(!isset($_GET['show_name'])) 
	die(json_encode(array("error" => 1,"status" => 403, "errors" => array("Show name not specified"))));
$connection = mysqli_connect(config::$database["host"],config::$database["username"],config::$database["password"],config::$database["database"]) 
	or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not connect to databsase"))));
$db_prefix = config::$database['prefix'];
$showname = $_GET['show_name'];
$query = "SELECT * FROM Shows where `name` LIKE \"%$showname%\"";
$result = mysqli_query($connection,$query) or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Query error"))));;
$myArray = array();
while($row = $result->fetch_array(MYSQL_ASSOC))
	 $myArray[] = $row;
$return = array("error" => 0, "status" => 200, "errors" => null, "shows" => $myArray, "num_shows" => mysqli_num_rows($result));
mysqli_close($connection);
die(json_encode($return));
?>