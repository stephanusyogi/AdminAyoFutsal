<?php
header("Access-Control-Allow-origin: *");
header("Content-Type: application/json; charset=UTF-8");

include_once '../config/database.php';
include_once '../objects/tbl_user.php';

$database = new Database();
$db = $database->getConnection();

$user = new User($db);

$stmt = $user->read();
$num = $stmt->rowCount();

if ($num>0) {
	$users_arr = array();
	$users_arr["records"] = array();

	while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
		extract($row);
		$user_item = array(
			"id_user"=>$id_user,
			"name"=> $name,
			"nohp"=> $nohp,
			"point"=> $point,
			"username"=> $username,
			"password"=> $password
			// "description"=> html_entity_decode($description),
		);
		array_push($users_arr["records"], $user_item);
	}

	http_response_code(200);
	echo json_encode($users_arr);
}else{
	http_response_code(404);
	echo json_encode(
		array("message" => "user Tidak Di Temukan")
	);

}
?>