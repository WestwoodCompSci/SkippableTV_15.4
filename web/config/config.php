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
}

?>