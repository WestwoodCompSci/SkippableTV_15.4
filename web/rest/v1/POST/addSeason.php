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
	"season_name" => null,
	"override" => null
);
//First, check the POST Data to make sure it's good
if(!isset($_POST['is_post']) || $_POST['is_post'] != "1")
	die(json_encode(array("error"=>1,"status"=>403,"errors"=>array("POST methods only"))));
foreach($post_requirements as $key=>$value):
if(!isset($_POST[$key]))
		die(json_encode(array("error"=>1,"status"=>403,"errors"=>array("Missing POST data"))));
	if(!is_null($value)):
		if(!$_POST[$key] == $value)
			die(json_encode(array("error"=>1,"status"=>403,"errors"=>array("POST data failed"))));
	endif;
endforeach;

//Next, make sure that we don't have a browser calling this
$not_allowed = array("HTTP_REFERER","HTTP_CACHE_CONTROL");
foreach($not_allowed as $key) {if(isset($_SERVER[$key])) die(json_encode(array("error"=>1,"status"=>403,"errors"=>array("Security exception"))));}

// finally check and make sure that the headers we want exist.
//$required = array("HTTP_USER_AGENT" => "SkippableTV 1.1"); //ignore for now
$required = array();

foreach($required as $key => $value):
	if(!isset($_SERVER[$key]))
		die(json_encode(array("error"=>1,"status"=>403,"errors"=>array("Security Exception $key "))));
	if(!is_null($value)):
		if(!($_SERVER[$key] === $value))
			die(json_encode(array("error"=>1,"status"=>403,"errors"=>array("Security Exception "))));
	endif;
endforeach;
?>