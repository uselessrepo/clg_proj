<?php
 
/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['uname']) && isset($_POST['password'])) {
 
    $uname = $_POST['uname'];
    $password = $_POST['password'];
 
    // include db connect class
    require_once __DIR__ . '/db_config.php';
 
    // connecting to db
    $db = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD,DB_DATABASE) or die(mysql_error());
 
    // mysql inserting a new row
    $result = mysqli_query($db,"select * from user where username='$uname' and password='$password'");
 
    // check if row inserted or not
    if (mysqli_num_rows($result)>0) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Login Successful";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "User Not Found";
 
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>