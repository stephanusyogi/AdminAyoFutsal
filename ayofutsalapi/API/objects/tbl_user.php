<?php
/**
 * 
 */
class User
{
	private $conn;
	private $table_name = "tbl_user";

	public $id_user;
	public $name;
	public $nohp;
	public $point;
	public $username;
	public $password;

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
		$query = "INSERT INTO " . $this -> table_name. " SET name=:name, point=:point, nohp=:nohp,username=:username, password=:password";

		$stmt = $this->conn->prepare($query);

		$this->name=htmlspecialchars(strip_tags($this->name));
		$this->point=htmlspecialchars(strip_tags($this->point));
		$this->nohp=htmlspecialchars(strip_tags($this->nohp));
		$this->username=htmlspecialchars(strip_tags($this->username));
		$this->password=htmlspecialchars(strip_tags($this->password));

		$stmt->bindParam(":name",$this->name);
		$stmt->bindParam(":point",$this->point);
		$stmt->bindParam(":nohp",$this->nohp);
		$stmt->bindParam(":username",$this->username);
		$stmt->bindParam(":password",$this->password);

		if ($stmt->execute()) {
			return true;
		}
		return false;

	}

	function update()
	{
		$query = "UPDATE " . $this -> table_name . " SET name=:name, point=:point, nohp=:nohp,username=:username, password=:password WHERE id_user=:id_user";
		$stmt = $this->conn->prepare($query);

		$this->id_user=htmlspecialchars(strip_tags($this->id_user));
		$this->name=htmlspecialchars(strip_tags($this->name));
		$this->point=htmlspecialchars(strip_tags($this->point));
		$this->nohp=htmlspecialchars(strip_tags($this->nohp));
		$this->username=htmlspecialchars(strip_tags($this->username));
		$this->password=htmlspecialchars(strip_tags($this->password));

		$stmt->bindParam(":id_user",$this->id_user);
		$stmt->bindParam(":name",$this->name);
		$stmt->bindParam(":point",$this->point);
		$stmt->bindParam(":nohp",$this->nohp);
		$stmt->bindParam(":username",$this->username);
		$stmt->bindParam(":password",$this->password);

		if ($stmt->execute()) {
			return true;
		}
		return false;



	}
	function delete()
	{
		$query = "DELETE FROM " . $this -> table_name . " WHERE id_user =?";
		$stmt = $this->conn->prepare($query);

		$this->password=htmlspecialchars(strip_tags($this->id_user));

		$stmt->bindParam(1,$this->id_user);

		if ($stmt->execute()) {
			return true;
		}
		return false;
	}

	function login()
    {
        $query = "CALL login_user(:username,:password)";

        $stmt  = $this->conn->prepare($query);

        // bind id of product to be updated
        // sanitize
        $this->username = htmlspecialchars(strip_tags($this->username));
        $this->password = htmlspecialchars(strip_tags($this->password));

        // bind the values
        $stmt->bindParam(':username', $this->username);
        $stmt->bindParam(':password', $this->password);

        $stmt->execute();
        // get retrieved row
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        // set values to object properties
        $this->id_user = $row['id_user'] ?? 0;
        // $this->password_pengelola = $row['password_pengelola'];
    }

}
?>