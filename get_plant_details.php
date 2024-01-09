<?php
require("./config/dbconnection.php");

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $auth_id = mysqli_real_escape_string($con, $_POST['auth_id']);


    $sql = "select * from plant_details where auth_id = '$auth_id'";
    if ($q = mysqli_query($con, $sql)) {
        $plants = mysqli_fetch_assoc($q);
        $output = array('status' => 'success', 'data' => $plants);
    } else {
        $output = array('status' => 'fail', 'message' => 'Api Request Error!!');
    }
} else {
    $output = ['status' => 'error', 'message' => 'Invalid Request!!'];
}
echo json_encode($output);