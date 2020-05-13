<?php
header("Access-Control-Allow-origin: *");
header("Content-Type: application/json; charset=UTF-8");

include_once '../config/database.php';
include_once '../objects/tbl_admin.php';

$database = new Database();
$db = $database->getConnection();

$admin = new Admin($db);

$stmt = $admin->read();
$num = $stmt->rowCount();

if ($num>0) {
	$admins_arr = array();
	$admins_arr["records"] = array();

	while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
		extract($row);
		$admin_item = array(
			"id_admin"=>$id_admin,
			"name"=> $name,
			"username"=> $username,
			"password"=> $password
			// "description"=> html_entity_decode($description),
		);
		array_push($admins_arr["records"], $admin_item);
	}

	http_response_code(200);
	echo json_encode($admins_arr);
}else{
	http_response_code(404);
	echo json_encode(
		array("message" => "admin Tidak Di Temukan")
	);

}
?>