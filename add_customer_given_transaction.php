<?php

require_once("./config/dbconnection.php");

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $cust_id = mysqli_real_escape_string($con, $_POST['cust_id']);
    $plant_id = mysqli_real_escape_string($con, $_POST['plant_id']);
    $debit = mysqli_real_escape_string($con, $_POST['debit']);
    $total = mysqli_real_escape_string($con, $_POST['total']);
    $jag = mysqli_real_escape_string($con, $_POST['jag']);
    $bottle = mysqli_real_escape_string($con, $_POST['bottle']);
    $note = mysqli_real_escape_string($con, $_POST['note']);
    $cust_tra_date = mysqli_real_escape_string($con, $_POST['cust_tra_date']);

    if (empty($cust_id)) {
        $output = array('status' => 'error', 'message' => 'Please Enter Customer Id!!');
    } else if ((int)$debit <= 0) {
        $output = array('status' => 'error', 'message' => 'Please Enter Valid debit value!!');
    } else {
        if (empty($note)) {
            $note = null;
        }
        if($bottle == null){
            $bottle = 0;
        }
        $total = (int)$total - (int)$debit;

        $sql = "insert into customer_transactions (cust_id, plant_id,debit, total, jag, bottle, note, cust_tra_date)
             values ($cust_id, $plant_id,$debit, $total, $jag, $bottle,'$note', '$cust_tra_date')";
        if (mysqli_query($con, $sql)) {
            $output = array('status' => 'success', 'message' => 'Add Customer Transaction Details Successfully...');
        } else {
            $output = array('status' => 'fail', 'message' => 'Api Request is Failed!!');
        }
    }
} else {
    $output = ['status' => 'error', 'message' => 'Invalid Request!!'];
}

echo json_encode($output);
