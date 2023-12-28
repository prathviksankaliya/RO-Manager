<?php
require("./config/dbconnection.php");

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    $sql = "select * from customer_details";
    if ($q = mysqli_query($con, $sql)) {
        $customers = mysqli_fetch_all($q);
        $output = array('status' => 'success', 'data' => $customers);
    } else {
        $output = array('status' => 'fail', 'message' => 'Api Request Error!!');
    }
} else {
    $output = ['status' => 'error', 'message' => 'Invalid Request!!'];
}
echo json_encode($output);