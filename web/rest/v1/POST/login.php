<?php
require("../../../config/config.php");
$requirements = array(
	"ispost",
	"username",
	"password"
);
foreach($requirements as $requirement)
{
	if(!isset($_POST[$requirement]))
		die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Bad data"))));
}

$username = $_POST['username'];
$password = $_POST['password'];

if(preg_match('/\s/',$username))
	die(json_encode(array("error" => 1, "status" => 403,"errors" => array("Username contains spaces"))));

$connection = mysqli_connect(config::$database["host"],config::$database["username"],config::$database["password"],config::$database["database"]) 
	or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not connect to databsase"))));
$query = "SELECT `id` FROM `Users` WHERE `username` = \"$username\"";

$result = mysqli_query($connection,$query)
	or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Query error checking username"))));
switch(mysqli_num_rows($result))
{
	case 0:
		die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Username does not exist"))));
		break;
	case 1:
		cont($result);
		break;
	default:
		die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Multiple usernames exist. Contact administrator"))));
		break;
	
}

function cont($result)
{
	global $connection;
	global $password;
	$id = mysqli_fetch_array($result);
	$id = $id["id"];
	settype($id,"integer");
	$query = "SELECT * FROM `Passwords` WHERE `id` = $id";
	$result=mysqli_query($connection,$query) or 
		die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not get username from password database"))));
	if(mysqli_num_rows($result) == 1)
	{
		$data = mysqli_fetch_array($result);
		$password = crypt($password,$data['salt']);
		$query = "SELECT * FROM `Passwords` WHERE `id`=$id AND `password` = \"$password\"";
		$success = mysqli_query($connection,$query) or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not check password"))));
		if(mysqli_num_rows($success) == 1)
		{
			$data = mysqli_fetch_array($success);
			if($data['allowed_hashes'] == "")
			{
				$hash = crypt(rand(0,198785).$_SERVER['REQUEST_TIME'].'skippable',crypt(rand(0,1983)));
				$hashes = array($hash);
				$hashes = serialize($hashes);
				$query = "UPDATE `Passwords` SET `allowed_hashes`='{$hashes}' WHERE `id`=$id";
				$success = mysqli_query($connection,$query) or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not create session. Contact Administrator"))));
			}
			else
			{
				$hash = crypt(rand(0,198785).$_SERVER['REQUEST_TIME'].'skippable',crypt(rand(0,1983)));
				$hashes = unserialize($data['allowed_hashes']);
				array_push($hashes,$hash);
				$hashes = serialize($hashes);
				$query = "UPDATE `Passwords` SET `allowed_hashes`='{$hashes}' WHERE `id`=$id";
				$success = mysqli_query($connection,$query) or die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Could not create session. Contact Administrator"))));
			}
			die(json_encode(array("error" => 0, "status" => 200,"errors" => null,"user_hash"=>$hash)));
		}
		else die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Something was wrong in checking the password."))));
	}
	else die(json_encode(array("error" => 1, "status" => 500,"errors" => array("Multiple usernames in password database. Contact administrator"))));
}
?>