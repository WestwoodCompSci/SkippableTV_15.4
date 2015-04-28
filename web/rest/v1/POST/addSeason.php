<?php

require("../../../config/config.php");
$post_requirements = array(
	"is_post" => 1,
	"security_token" => "fUheHuhaSaH82haswU8ReSAcreD6wre5gevanEPaWrerEca6HacHAqechEnazEq2",
	"token" => null,
	"security" => isset($_POST['token'])? base64_decode(base64_decode($_POST["token"])) : null,
	"season_parent" => null,
	"season_number" => null,
	"num_episodes" => null,
	"season_length" => null,
	"autocreate_episodes" => null,
	"override" => null
);
//First, check the POST Data to make sure it's good
if(!isset($_POST['is_post']) || $_POST['is_post'] !== "1")
	die(json_encode(array("error"=>1,"status"=>400,"errors"=>array("POST methods only"))));
foreach($post_requirements as $key=>$value):
if(!isset($_POST[$key]))
		die(json_encode(array("error"=>1,"status"=>412,"errors"=>array("Missing POST data"))));
	if(!is_null($value)):
		if(!$_POST[$key] == $value) //The only time we need to check if $key=>$value is if it exists, and it has to work properly
			die(json_encode(array("error"=>1,"status"=>412,"errors"=>array("POST data failed"))));
	endif;
endforeach;

//Next, make sure that we don't have a browser calling this
$not_allowed = array("HTTP_REFERER","HTTP_CACHE_CONTROL");
foreach($not_allowed as $key) {if(isset($_SERVER[$key])) die(json_encode(array("error"=>1,"status"=>403,"errors"=>array("Security exception"))));}

// finally check and make sure that the headers we want exist. (ie we renamed the user agent header to the app name.)
//$required = array("HTTP_USER_AGENT" => "SkippableTV 1.1"); //ignore for now
$required = array(); // This is temporary until we have the user agent set up from network; right now the requests are sent via a REST app

foreach($required as $key => $value):
	if(!isset($_SERVER[$key]))
		die(json_encode(array("error"=>1,"status"=>403,"errors"=>array("Security Exception $key "))));
	if(!is_null($value)):
		if(!($_SERVER[$key] === $value))
			die(json_encode(array("error"=>1,"status"=>403,"errors"=>array("Security Exception "))));
	endif;
endforeach;

//Ok. Now, we're pretty sure a hacker is not trying to change the data. There's probably a better way for authentication, but I don't know it 
// or have the time to learn it, so we'll stick with what we have so far. Now onto the actual api call


//first, we want to initialize our own variables
$override = $_POST['override'];
$create_episodes = $_POST['autocreate_episodes'];
$season_parent = $_POST['season_parent'];
$season_number = $_POST['season_number'];
$season_episodes = $_POST['season_episodes'];
$season_length = $_POST['season_length'];

/** Now we're going to set the type of the variables. 
* In PHP, especially with HTTP requests, it's not possible to actually determine
* they type of the variable without creating a lot of confusion. So, PHP has a
* built in function called settype which allows us to set the type of a variable
* from anything to anything. Usually POST requests view them as strings.
*/

if(!settype($override,"bool")) $override = false; // Either $override becomes a boolean or we be safe and don't override stuff that already exists
if(!settype($create_episodes,"bool")) $create_episodes = false; // Either $create_episodes becomes a boolean or we be safe and don't waste query time
if(!settype($season_parent,"int")) die(json_encode(array("error"=>1,"status"=>400,"errors"=>array("Given POST data has malformed syntax. Please recheck your syntax and try again later")))); //All of these will fail if it doesn't convert properly.
if(!settype($season_number,"int")) die(json_encode(array("error"=>1,"status"=>400,"errors"=>array("Given POST data has malformed syntax. Please recheck your syntax and try again later")))); //All of these will fail if it doesn't convert properly.
if(!settype($season_episodes,"int")) die(json_encode(array("error"=>1,"status"=>400,"errors"=>array("Given POST data has malformed syntax. Please recheck your syntax and try again later")))); //All of these will fail if it doesn't convert properly.
//We're not going to deal with $season_length because it's supposed to be a time. SQL can deal with it normally.


//Now we're ready to do database queries. 


//The first logical split is to make sure we don't override!
if($override)
{
	//to be done later
}
else // We can't write over
{
	$query = "IF NOT EXISTS (SELECT * FROM `Seasons` WHERE $season_parent=`parent` AND $season_number=`number`) BEGIN INSERT INTO `Seasons` (`parent`,`number`,`episodes`,`total length`)  VALUES ($season_parent,$season_number,$season_episodes,\"$season_length\") END";
}

?>