<?php
header("Access-Control-Allow-origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Method: POST");
header("Access-Control-Max-Age:3600");
header("Access-Control-Allow-Headers: Content-Type,Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../config/database.php';
include_once '../objects/tbl_admin.php';

$database = new Database();
$db = $database->getConnection();

$admin = new Admin($db);

$data =
json_decode (file_get_contents("php://input"));

if(
	!empty($data->name) &&
	!empty($data->username) &&
	!empty($data->password)
){
	$admin->name = $data->name;
	$admin->username = $data->username;
	$admin->password = $data->password;

	if ($admin -> create()) {
		http_response_code(201);
		echo json_encode(array("Message" => "admin was created" ));
	}
	else{
		http_response_code(503);
		echo json_encode(array("Message" => "Unable to create admin" ));
	}
}
else{
	http_response_code(400);
	echo json_encode(array("Message" => "Unable to create admin. Data is incomplete"));
}

?>