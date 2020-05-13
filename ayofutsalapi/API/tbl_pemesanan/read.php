<?php
header("Access-Control-Allow-origin: *");
header("Content-Type: application/json; charset=UTF-8");

include_once '../config/database.php';
include_once '../objects/tbl_pemesanan.php';

$database = new Database();
$db = $database->getConnection();

$pemesanan = new Pemesanan($db);

$stmt = $pemesanan->read();
$num = $stmt->rowCount();

if ($num>0) {
	$pemesanans_arr = array();
	$pemesanans_arr["records"] = array();

	while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
		extract($row);
		$pemesanan_item = array(
			"id_pesanan"=>$id_pesanan,
			"nama"=> $nama,
			"lapangan"=> $lapangan,
			"mulai_jam"=> $mulai_jam,
			"selesai_jam"=> $selesai_jam,
			"tanggal"=> $tanggal,
			"catatan"=> $catatan,
			"status_bayar"=> $status_bayar
			// "description"=> html_entity_decode($description),
		);
		array_push($pemesanans_arr["records"], $pemesanan_item);
	}

	http_response_code(200);
	echo json_encode($pemesanans_arr);
}else{
	http_response_code(404);
	echo json_encode(
		array("message" => "pemesanan Tidak Di Temukan")
	);

}
?>