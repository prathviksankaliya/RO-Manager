<?php
require("./config/dbconnection.php");

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $cust_id = mysqli_real_escape_string($con, $_POST['cust_id']);
    $plant_id = mysqli_real_escape_string($con, $_POST['plant_id']);

    $sql = "select * from customer_transactions where plant_id = '$plant_id' && cust_id = '$cust_id'";
    if ($q = mysqli_query($con, $sql)) {
        $transactions = mysqli_fetch_all($q, MYSQLI_ASSOC);
        $output = array('status' => 'success', 'data' => $transactions);
    } else {
        $output = array('status' => 'fail', 'message' => 'Api Request Error!!');
    }
} else {
    $output = ['status' => 'error', 'message' => 'Invalid Request!!'];
}
echo json_encode($output);