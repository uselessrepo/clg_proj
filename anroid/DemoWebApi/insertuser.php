<?php
 
/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_GET['name']) && isset($_GET['email']) && isset($_GET['phone'])) {
 
    $name = $_GET['name'];
    $email = $_GET['email'];
    $phone = $_GET['phone'];
 
    
    
 
    // connecting to db
    $db = mysqli_connect('localhost', 'root', '','MyDb') or die(mysql_error());
 
    // mysql inserting a new row
    $result = mysqli_query($db,"INSERT INTO user(name, email, phone) VALUES('$name', '$email', '$phone')");
 
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "User successfully Inserted.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
 
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