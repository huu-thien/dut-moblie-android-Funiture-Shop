<?php
    include "connect.php";
    $query = "SELECT * FROM `Categories`";

    $data = mysqli_query($conn, $query);
    $result = array();

    while ($row = mysqli_fetch_assoc($data)) {
        $result[] = ($row);
        //code
    }

    if(!empty($result)) {
        $arr = [
            'success' => true,
            'message' => 'success',
            'result' => $result
        ];
    } else {
        $arr = [
            'success' => false,
            'message' => 'Unsuccess',
            'result' => $result
        ];
    }
    print_r(json_encode($arr));
?>