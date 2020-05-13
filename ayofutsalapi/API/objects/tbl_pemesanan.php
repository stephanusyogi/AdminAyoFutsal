<?php
/**
 * 
 */
class Pemesanan
{
	private $conn;
	private $table_name = "tbl_pemesanan";

	public $id_pesanan;
	public $nama;
	public $lapangan;
	public $mulai_jam;
	public $selesai_jam;
	public $tanggal;
	public $catatan;
	public $status_bayar;

	public function __construct($db){
		$this->conn = $db;
	}

	function read(){
		$query = " SELECT * FROM " . $this->table_name . " ORDER BY tanggal DESC ";

		$stmt = $this->conn->prepare($query);

		$stmt->execute();
		return $stmt;
	}

	function create()
	{
		$query = "INSERT INTO " . $this -> table_name. " SET nama=:nama, mulai_jam=:mulai_jam, lapangan=:lapangan,selesai_jam=:selesai_jam, tanggal=:tanggal, catatan=:catatan, status_bayar=:status_bayar";

		$stmt = $this->conn->prepare($query);

		$this->nama=htmlspecialchars(strip_tags($this->nama));
		$this->mulai_jam=htmlspecialchars(strip_tags($this->mulai_jam));
		$this->lapangan=htmlspecialchars(strip_tags($this->lapangan));
		$this->selesai_jam=htmlspecialchars(strip_tags($this->selesai_jam));
		$this->tanggal=htmlspecialchars(strip_tags($this->tanggal));
		$this->catatan=htmlspecialchars(strip_tags($this->catatan));
		$this->status_bayar=htmlspecialchars(strip_tags($this->status_bayar));

		$stmt->bindParam(":nama",$this->nama);
		$stmt->bindParam(":mulai_jam",$this->mulai_jam);
		$stmt->bindParam(":lapangan",$this->lapangan);
		$stmt->bindParam(":selesai_jam",$this->selesai_jam);
		$stmt->bindParam(":tanggal",$this->tanggal);
		$stmt->bindParam(":catatan",$this->catatan);
		$stmt->bindParam(":status_bayar",$this->status_bayar);

		if ($stmt->execute()) {
			return true;
		}
		return false;

	}

	function update()
	{
		$query = "UPDATE " . $this -> table_name . " SET nama=:nama, mulai_jam=:mulai_jam, lapangan=:lapangan,selesai_jam=:selesai_jam, tanggal=:tanggal, catatan=:catatan, status_bayar=:status_bayar WHERE id_pesanan=:id_pesanan";
		$stmt = $this->conn->prepare($query);

		$this->id_pesanan=htmlspecialchars(strip_tags($this->id_pesanan));
		$this->nama=htmlspecialchars(strip_tags($this->nama));
		$this->mulai_jam=htmlspecialchars(strip_tags($this->mulai_jam));
		$this->lapangan=htmlspecialchars(strip_tags($this->lapangan));
		$this->selesai_jam=htmlspecialchars(strip_tags($this->selesai_jam));
		$this->tanggal=htmlspecialchars(strip_tags($this->tanggal));
		$this->catatan=htmlspecialchars(strip_tags($this->catatan));
		$this->status_bayar=htmlspecialchars(strip_tags($this->status_bayar));

		$stmt->bindParam(":id_pesanan",$this->id_pesanan);
		$stmt->bindParam(":nama",$this->nama);
		$stmt->bindParam(":mulai_jam",$this->mulai_jam);
		$stmt->bindParam(":lapangan",$this->lapangan);
		$stmt->bindParam(":selesai_jam",$this->selesai_jam);
		$stmt->bindParam(":tanggal",$this->tanggal);
		$stmt->bindParam(":catatan",$this->catatan);
		$stmt->bindParam(":status_bayar",$this->status_bayar);

		if ($stmt->execute()) {
			return true;
		}
		return false;



	}
	function delete()
	{
		$query = "DELETE FROM " . $this -> table_name . " WHERE id_pesanan =?";
		$stmt = $this->conn->prepare($query);

		$this->tanggal=htmlspecialchars(strip_tags($this->id_pesanan));

		$stmt->bindParam(1,$this->id_pesanan);

		if ($stmt->execute()) {
			return true;
		}
		return false;
	}

}
?>