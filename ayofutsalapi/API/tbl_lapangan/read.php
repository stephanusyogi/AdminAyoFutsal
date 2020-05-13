<?php
header("Access-Control-Allow-origin: *");
header("Content-Type: application/json; charset=UTF-8");

include_once '../config/database.php';
include_once '../objects/tbl_lapangan.php';

$database = new Database();
$db = $database->getConnection();

$lapangan = new Lapangan($db);

$stmt = $lapangan->read();
$num = $stmt->rowCount();

if ($num>0) {
	$lapangans_arr = array();
	$lapangans_arr["records"] = array();

	while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
		extract($row);
		$lapangan_item = array(
			"id_lap"=>$id_lap,
			"name"=> $name,
			"status"=> $status
			// "description"=> html_entity_decode($description),
		);
		array_push($lapangans_arr["records"], $lapangan_item);
	}

	http_response_code(200);
	echo json_encode($lapangans_arr);
}else{
	http_response_code(404);
	echo json_encode(
		array("message" => "lapangan Tidak Di Temukan")
	);

}
?>