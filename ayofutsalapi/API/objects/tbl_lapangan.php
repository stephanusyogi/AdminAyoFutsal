<?php
/**
 * 
 */
class Lapangan
{
	private $conn;
	private $table_name = "tbl_lapangan";

	public $id_lap;
	public $name;
	public $status;

	public function __construct($db){
		$this->conn = $db;
	}

	function read(){
		$query = " SELECT * FROM " . $this->table_name;

		$stmt = $this->conn->prepare($query);

		$stmt->execute();
		return $stmt;
	}

	function create()
	{
		$query = "INSERT INTO " . $this -> table_name. " SET name=:name, status=:status";

		$stmt = $this->conn->prepare($query);

		$this->name=htmlspecialchars(strip_tags($this->name));
		$this->status=htmlspecialchars(strip_tags($this->status));

		$stmt->bindParam(":name",$this->name);
		$stmt->bindParam(":status",$this->status);

		if ($stmt->execute()) {
			return true;
		}
		return false;

	}

	function update()
	{
		$query = "UPDATE " . $this -> table_name . " SET name=:name, status=:status WHERE id_lap=:id_lap";
		$stmt = $this->conn->prepare($query);

		$this->id_lap=htmlspecialchars(strip_tags($this->id_lap));
		$this->name=htmlspecialchars(strip_tags($this->name));
		$this->status=htmlspecialchars(strip_tags($this->status));

		$stmt->bindParam(":id_lap",$this->id_lap);
		$stmt->bindParam(":name",$this->name);
		$stmt->bindParam(":status",$this->status);

		if ($stmt->execute()) {
			return true;
		}
		return false;



	}
	function delete()
	{
		$query = "DELETE FROM " . $this -> table_name . " WHERE id_lap =?";
		$stmt = $this->conn->prepare($query);

		$this->created=htmlspecialchars(strip_tags($this->id_lap));

		$stmt->bindParam(1,$this->id_lap);

		if ($stmt->execute()) {
			return true;
		}
		return false;
	}

}
?>