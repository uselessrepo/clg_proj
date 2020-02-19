<?php
 
/*
 * Following code will list all the products
 */
 
// array for JSON response
$response = array();
 require_once __DIR__ . '/db_config.php';
 
    // connecting to db
    $db = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD,DB_DATABASE) or die(mysqli_error());
	$result = mysqli_query($db,"SELECT *FROM user") or die(mysqli_error());
 
// check for empty result
if (mysqli_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["Users"] = array();
 
    while ($row = mysqli_fetch_array($result)) {
        // temp user array
        $user = array();
        $user["idd"] = $row["id"];
        $user["namee"] = $row["name"];
        $user["emaill"] = $row["email"];
        $user["mobile"] = $row["phone"];
        $phone=array()
		$phone["mobile"]=$row["mobile"];
		$phone["landline"]=$row["landline"];
		
 array_push($user["phone"], $phone);
        // push single product into final response array
        array_push($response["Users"], $user);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No users found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>