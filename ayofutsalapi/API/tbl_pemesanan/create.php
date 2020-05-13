<?php
header("Access-Control-Allow-origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Method: POST");
header("Access-Control-Max-Age:3600");
header("Access-Control-Allow-Headers: Content-Type,Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../config/database.php';
include_once '../objects/tbl_pemesanan.php';

$database = new Database();
$db = $database->getConnection();

$pemesanan = new Pemesanan($db);

$data =
json_decode (file_get_contents("php://input"));

if(
	!empty($data->nama) &&
	!empty($data->lapangan) &&
	!empty($data->mulai_jam) &&
	!empty($data->selesai_jam) &&
	!empty($data->tanggal) &&
	!empty($data->catatan) &&
	!empty($data->status_bayar)
){
	$pemesanan->nama = $data->nama;
	$pemesanan->lapangan = $data->lapangan;
	$pemesanan->mulai_jam = $data->mulai_jam;
	$pemesanan->selesai_jam = $data->selesai_jam;
	$pemesanan->tanggal = $data->tanggal;
	$pemesanan->catatan = $data->catatan;
	$pemesanan->status_bayar = $data->status_bayar;

	if ($pemesanan -> create()) {
		http_response_code(201);
		echo json_encode(array("Message" => "Pesanan Telah Dibuat" ));
	}
	else{
		http_response_code(503);
		echo json_encode(array("Message" => "Unable to create pesanan" ));
	}
}
else{
	http_response_code(400);
	echo json_encode(array("Message" => "Isikan data dengan benar"));
}

?>