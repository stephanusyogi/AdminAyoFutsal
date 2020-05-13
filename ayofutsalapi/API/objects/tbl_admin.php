<?php
/**
 * 
 */
class Admin
{
	private $conn;
	private $table_name = "tbl_admin";

	public $id_admin;
	public $name;
	public $username;
	public $password;

	public function __construct($db){
		$this->conn = $db;
	}

	function read(){
		$query = " SELECT * FROM " . $this->table_name ;

		$stmt = $this->conn->prepare($query);

		$stmt->execute();
		return $stmt;
	}

	function create()
	{
		$query = "INSERT INTO " . $this -> table_name. " SET name=:name, password=:password, username=:username";

		$stmt = $this->conn->prepare($query);

		$this->name=htmlspecialchars(strip_tags($this->name));
		$this->password=htmlspecialchars(strip_tags($this->password));
		$this->username=htmlspecialchars(strip_tags($this->username));

		$stmt->bindParam(":name",$this->name);
		$stmt->bindParam(":password",$this->password);
		$stmt->bindParam(":username",$this->username);

		if ($stmt->execute()) {
			return true;
		}
		return false;

	}

	function update()
	{
		$query = "UPDATE " . $this -> table_name . " SET name=:name, password=:password, username=:username WHERE id_admin=:id_admin";
		$stmt = $this->conn->prepare($query);

		$this->id_admin=htmlspecialchars(strip_tags($this->id_admin));
		$this->name=htmlspecialchars(strip_tags($this->name));
		$this->password=htmlspecialchars(strip_tags($this->password));
		$this->username=htmlspecialchars(strip_tags($this->username));

		$stmt->bindParam(":id_admin",$this->id_admin);
		$stmt->bindParam(":name",$this->name);
		$stmt->bindParam(":password",$this->password);
		$stmt->bindParam(":username",$this->username);

		if ($stmt->execute()) {
			return true;
		}
		return false;



	}
	function delete()
	{
		$query = "DELETE FROM " . $this -> table_name . " WHERE id_admin =?";
		$stmt = $this->conn->prepare($query);

		$this->created=htmlspecialchars(strip_tags($this->id_admin));

		$stmt->bindParam(1,$this->id_admin);

		if ($stmt->execute()) {
			return true;
		}
		return false;
	}

}
?>