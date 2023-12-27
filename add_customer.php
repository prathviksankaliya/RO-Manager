<?php

require_once("./config/dbconnection.php");

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $cust_name = mysqli_real_escape_string($con, $_POST['cust_name']);
    $cust_phone = mysqli_real_escape_string($con, $_POST['cust_phone']);
    $cust_address = mysqli_real_escape_string($con, $_POST['cust_address']);
    $cust_msg = mysqli_real_escape_string($con, $_POST['cust_msg']);

    if (empty($cust_name)) {
        $output = array('status' => 'error', 'message' => 'Please Enter Customer name!!');
    } else if (empty($cust_address)) {
        $output = array('status' => 'error', 'message' => 'Please Enter Valid address!!');
    } else {
        if (empty($cust_msg)) {
            $cust_msg = null;
        }
        if (empty($cust_phone)) {
            $cust_phone = null;
        }
        $current_time = time();
        $targetDir = "images/customers/";
        $targetFile = $targetDir . 'IMG_' . $current_time . ".png";

        date_default_timezone_set("Asia/Kolkata");
        $date_time = date("Y-m-d H:i:s");
        if ($_FILES["cust_image"]["tmp_name"] != null) {
            if (move_uploaded_file($_FILES["cust_image"]["tmp_name"], $targetFile)) {
                $imagePath = $targetFile;
            } else {
                $output = array('status' => 'error', 'message' => 'Image Uploading Failed!!');
            }
        } else {
            $imagePath = null;
        }
        $sql = "insert into customer_details (cust_name, cust_phone, cust_image, cust_address, cust_date, cust_msg)
             values ('$cust_name', '$cust_phone', '$imagePath', '$cust_address', '$date_time', '$cust_msg')";
        if (mysqli_query($con, $sql)) {
            $output = array('status' => 'success', 'message' => 'Add Customer Details Successfully...');
        } else {
            $output = array('status' => 'fail', 'message' => 'Api Request is Failed!!');
        }
    }
} else {
    $output = ['status' => 'error', 'message' => 'Invalid Request!!'];
}

echo json_encode($output);
