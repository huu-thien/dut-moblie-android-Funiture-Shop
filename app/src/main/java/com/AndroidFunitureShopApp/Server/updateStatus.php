<?php
include "connect.php";
$id = $_POST['id'];
$status = $_POST['status'];

//
$query = "UPDATE `user` SET `status`='$status' WHERE id='$id'";

$data = mysqli_query($conn, $query);
$result = array();
if($data) {
    $arr =[
        'success' => true,
        'message' => 'Success',
        'result' => $result
    ]
    ;
} else {
    $arr = [
        'success' => false,
        'message' => 'Unsuccess',
        'result' => $result
    ];
}
print_r(json_encode($arr));