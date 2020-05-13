<?php
header("Access-Control-Allow-origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Method: POST");
header("Access-Control-Max-Age:3600");
header("Access-Control-Allow-Headers: Content-Type,Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../config/database.php';
include_once '../objects/tbl_user.php';

$database = new Database();
$db = $database->getConnection();

$user = new User($db);

$data =
json_decode (file_get_contents("php://input"));

if(
	!empty($data->name) &&
	!empty($data->nohp) &&
	!empty($data->point) &&
	!empty($data->username) &&
	!empty($data->password)
){
	$user->name = $data->name;
	$user->nohp = $data->nohp;
	$user->point = $data->point;
	$user->username = $data->username;
	$user->password = $data->password;

	if ($user -> create()) {
		http_response_code(201);
		echo json_encode(array("Message" => "user was created" ));
	}
	else{
		http_response_code(503);
		echo json_encode(array("Message" => "Unable to create user" ));
	}
}
else{
	http_response_code(400);
	echo json_encode(array("Message" => "Unable to create user. Data is incomplete"));
}

?>