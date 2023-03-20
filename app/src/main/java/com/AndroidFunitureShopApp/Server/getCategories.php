<?php
    // include "connect.php";
    // VA thêm vào
    $host = "localhost";
        $user = "root";
        $pass = "";
        $database = "categories";

        $conn = mysqli_connect($host, $user, $pass, $database);
        mysqli_set_charset($conn,'utf8');

        if($conn) {
            // echo "Connection established";
        }
    $query = "SELECT * FROM `categories`";

    $data = mysqli_query($conn, $query);
    $result = array();

    while ($row = mysqli_fetch_assoc($data)) {
        $result[] = ($row);
    }

    if(!empty($result)) {
        $arr =
            $result
        ;
    } else {
        $arr = [
            'success' => false,
            'message' => 'Unsuccess',
            'result' => $result
        ];
    }
    print_r(json_encode($arr));
?>