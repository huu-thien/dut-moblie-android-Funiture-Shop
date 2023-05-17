<?php
include "connect.php";
$status = $_POST['status'];

//check exits account
$query = "SELECT * FROM `user` WHERE `status` ='$status'";

$data = mysqli_query($conn, $query);
$result = array();

while ($row = mysqli_fetch_assoc($data)) {
    $result[] = $row;
}

if(!empty($result)) {
    $arr =[
        'success' => true,
        'message' => 'Success',
        'result' => $result
    ];
    
} else {
    $arr = [
        'success' => false,
        'message' => 'Unsuccess',
        'result' => null
    ];
}

header('Content-type: application/json');
print_r(json_encode($arr));
?>