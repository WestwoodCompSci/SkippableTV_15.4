<?php
require("../../../config/config.php");
$requirements = array(
	"ispost",
	"id",
	"hash",
	"season",
	"show",
	"episode",
	"rating",
	"comment"
	
);
foreach($requirements as $requirement)
{
	if(!isset($_POST[$requirement]))
		die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Bad data"))));
	if(preg_match('/\s/',$requirement))
		die(json_encode(array("error" => 1, "status" => 403,"errors" => array("$requirement contains spaces"))));
}

$id = $_POST["id"];
$hash = $_POST["hash"];
$season = $_POST["season"];
$show = $_POST["show"];
$episode = $_POST["episode"];
$rating = $_POST["rating"];
$comment = $_POST["comment"];

settype($id,"integer") or die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Could not convert E1 to an int"))));
settype($season,"integer") or die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Could not convert E2 to an int"))));
settype($episode,"integer") or die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Could not convert E3 to an int"))));
settype($show,"integer") or die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Could not convert E4 to an int"))));
settype($rating,"integer") or die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Could not convert E5 to an int"))));

$connection = mysqli_connect(config::$database["host"],config::$database["username"],config::$database["password"],config::$database["database"]) 
	or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not connect to databsase"))));
$query = "SELECT * FROM `Hashes` WHERE `hash`='{$hash}' AND `owner`=$id'";
$result = mysql_query($connection,$query)
	or die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Query error E6"))));
if(mysqli_num_rows($result) == 1)
{
	$query = "SELECT  * FROM `Ratings` WHERE `season`=$season AND `show`=$show AND `episode`=$episode AND `owner_id`=$id";
	$result = mysql_query($connection,$query)
		or die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Query error E7"))));
	if(mysqli_num_rows($result) == 1)
	{
		$query = "INSERT INTO `Ratings` (`season`,`show`,`episode`,`owner_id`,`rating`,`comment`) VALUES ({$season},{$show},{$episode},{$owner_id},{$rating},\"{$comment}\")";
		$result = mysql_query($connection,$query)
			or die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Error E8 inserting comment. "))));
		die(json_encode(array("error" => 0, "status" => 200,"errors" =>null)));
	}
	die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Already rated episode."))));
}
else die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Error E7. Contact administrator."))));
?>