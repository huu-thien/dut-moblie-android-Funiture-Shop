<?php
$host = "localhost";
$user = "root";
$pass = "";
$database = "furnitureshop_v2";

$conn = mysqli_connect($host, $user, $pass, $database, 3306) or die("Connection Failed" + mysqli_connect_error());

// if ($conn) {
//     echo "Connection established";
// }
