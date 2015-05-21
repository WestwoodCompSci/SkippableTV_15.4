<?php
require("../../config/config.php");
$requirements = array(
	"ispost",
	"username",
	"id",
	"hash"
);
foreach($requirements as $requirement)
{
	if(!isset($_POST[$requirement]))
		die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Bad data"))));
	if(preg_match('/\s/',$requirement))
		die(json_encode(array("error" => 1, "status" => 403,"errors" => array("$requirement contains spaces"))));

}

$username = $_POST['username'];
$id = $_POST['id'];
$id = settype($id,"integer");
$hash = $_POST['hash'];

$connection = mysqli_connect(config::$database["host"],config::$database["username"],config::$database["password"],config::$database["database"]) 
	or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not connect to databsase"))));

if(isset($_GET['all']) && $_GET['all'] == "1")
{
	$query = "SELECT * FROM `Hashes` WHERE `owner`=$id AND `hash`=\"$hash\"";
	$result = mysqli_query($connection,$query)
		or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Wrong user"))));
	$query = "DELETE FROM  `Hashes` WHERE  `owner`=$id";
	$result = mysqli_query($connection,$query)
		or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not delete Hashes E-1"))));
	$query = "UPDATE `Passwords` SET `allowed_hashes`=\"\" WHERE `id`=$id";
	$result = mysqli_query($connection,$query)
		or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not delete Hashes E-2"))));
	die(json_encode(array("error" => 0, "status" => 200,"errors" => null,"success"=>true)));
}
else
{
	$query = "DELETE FROM  `Hashes` WHERE  `owner`=$id AND `hash`=\"{$hash}\"";
	$result = mysqli_query($connection,$query)
		or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not delete Hash E1"))));
	$query = "SELECT * FROM `Passwords` WHERE `id`=$id";
	$result = mysqli_query($connection,$query)
		or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not delete Hash E2"))));
	$data = mysqli_fetch_array($result);
	$hashes = (unserialize($data['allowed_hashes']));
	for($i = 0; $i<count($hashes); $i++)
	{
		$key = $hashes[$i];
		if($hash == $key)
		{
			unset($hashes[$i]);
			print_r($hashes);
			$hashes = serialize($hashes);
			$query = "UPDATE `Passwords` SET `allowed_hashes`='{$hashes}' WHERE `id`=$id";
			$result = mysqli_query($connection,$query)
				or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not delete Hash E3"))));
			die(json_encode(array("error" => 0, "status" => 500,"errors" => null,"success"=>true)));
		}
	}
	die(json_encode(array("error" => 1, "status" => 401,"errors" => array("Block user. No hash found in allowed_hashes"))));
}

?>