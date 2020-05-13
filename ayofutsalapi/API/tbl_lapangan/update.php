<?php
header("Access-Control-Allow-origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Method: POST");
header("Access-Control-Max-Age:3600");
header("Access-Control-Allow-Headers: Content-Type,Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../config/database.php';
include_once '../objects/tbl_lapangan.php';

$database = new Database();
$db = $database->getConnection();

$lapangan = new Lapangan($db);

$data =
json_decode (file_get_contents("php://input"));
{
	$lapangan->id_lap = $data->id_lap;
  
	$lapangan->name = $data->name;
	$lapangan->status = $data->status;

	if ($lapangan -> update()) {
		http_response_code(201);
		echo json_encode(array("Message" => "lapangan was Updated" ));
	}
	else{
		http_response_code(503);
		echo json_encode(array("Message" => "Unable to update lapangan" ));
	}
}

?>