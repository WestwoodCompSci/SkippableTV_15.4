<?php

require("../../config/config.php");
if(!isset($_GET['user_id'])) die(json_encode(array("error" => 1,"status" => 403, "errors" => array("User ID not specified"))));
$connection = mysqli_connect(config::$database["host"],config::$database["username"],config::$database["password"],config::$database["database"]) or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not connect to databsase"))));
$db_prefix = config::$database['prefix'];
$username = $_GET['user_id'];
$query = "SELECT * FROM users where  $username=`id`";
print $query;
$return = mysqli_query($connection,$query) or die();
while($row = $result->fetch_array(MYSQL_ASSOC))
{
	 $myArray[] = $row;
}
echo json_encode($myArray);
$result->close();
$mysqli->close();
?>