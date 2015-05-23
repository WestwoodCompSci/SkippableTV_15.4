<?php
header("Content-Type: application/json"); //This header is placed because most API calls are for JSON. In the event that it's not we'll just it there. 
class config
{
	public static $database = array
	(
		"host" => "localhost",
		"username" => "root",
		"password" => "",
		"database" => "skippable",
		"prefix" => "stv_"
	);
	
	public static function checkLogin($info,$conn)
	{
		if(!(isset($info['username']) && isset($info['hash']) && isset($info['id'])))
			return false;
		
		$query = "SELECT * FROM `Hashes` WHERE `id` = {$info['id']} AND `hash` = \"{$info['hash']}\"";
		
		$res = mysqli_query($conn,$query) or return false;
		
		if(mysqli_num_rows($res) == 1)
		{
			$query = "SELECT * FROM `Users` WHERE `id`={$info['id']} AND `username` = \"{$imfo['username']}\"";
			$res   = mysqli_query($conn,$query) or return false;
			$data  = mysqli_fetch_array($res);
			$hashes = unserialize($data['hashes']);
			foreach($hashes as $hash)
			{
				if($hash == $info['hash'])
					return true;
			}
			return false;
		}
		return false; 
	}
}

?>