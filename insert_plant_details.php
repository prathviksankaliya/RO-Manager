<?php

require_once("./config/dbconnection.php");

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $auth_id = mysqli_real_escape_string($con, $_POST['auth_id']);
    $plant_name = mysqli_real_escape_string($con, $_POST['plant_name']);
    $plant_phone = mysqli_real_escape_string($con, $_POST['plant_phone']);
    $email = mysqli_real_escape_string($con, $_POST['plant_email']);
    $city_name = mysqli_real_escape_string($con, $_POST['plant_city']);
    $address = mysqli_real_escape_string($con, $_POST['plant_address']);
    $plant_security = mysqli_real_escape_string($con, $_POST['plant_security']);

    if (empty($auth_id)) {
        $output = array('status' => 'error', 'message' => 'Please Enter Auth ID!!');
    } else if (empty($plant_name)) {
        $output = array('status' => 'error', 'message' => 'Please Enter Plant Name!!');
    } else if (strlen($plant_phone) != 10) {
        $output = array('status' => 'error', 'message' => 'Please Enter Valid Phone Number!!');
    } else if (empty($city_name)) {
        $output = array('status' => 'error', 'message' => 'Please Enter Valid City Name!!');
    } else if (empty($address)) {
        $output = array('status' => 'error', 'message' => 'Please Enter valid address!!');
    } else {
        if (empty($email)) {
            $email = null;
        } else if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            $output = array('status' => 'error', 'message' => 'Please Enter valid email!!');
            echo json_encode($output);
            exit;
        }
        if (empty($plant_security)) {
            $plant_security = null;
        }
        $current_time = time();
        $targetDir = "images/";
        $targetFile = $targetDir . 'IMG_' . $current_time . ".png";

        if (move_uploaded_file($_FILES["plant_image"]["tmp_name"], $targetFile)) {
            $imagePath = $targetFile;
            $sql = "insert into plant_details (auth_id, plant_name, plant_phone, plant_email, plant_image, plant_city, plant_address, plant_security)
         values ('$auth_id', '$plant_name', '$plant_phone', '$email', '$imagePath', '$city_name', '$address', '$plant_security')";
            if (mysqli_query($con, $sql)) {
                $output = array('status' => 'success', 'message' => 'Plant Details insert Successfully...');
            } else {
                $output = array('status' => 'fail', 'message' => 'Api Request is Failed!!');
            }
        }else{
            $output = array('status' => 'error', 'message' => 'Image Uploading Failed!!');
        }
    }
} else {
    $output = ['status' => 'error', 'message' => 'Invalid Request!!'];
}

echo json_encode($output);
