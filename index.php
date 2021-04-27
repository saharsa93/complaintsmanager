<?php 
// step 1: connect to database
// mysqli_connect function has 4 params (host,user name, password,database_name)
$db_con = mysqli_connect("localhost","admin_sabeti","HNJ1AONxtavlbEKu","admin_sabeti");
 
$response = array();
header('Content-Type: application/json');
 
if(mysqli_connect_errno())
{
    $response["error"] = TRUE;
    $response["message"] ="Faild to connect to database";
    echo json_encode($response);
    exit;
}
 
 
if(isset($_POST["type"]) && ($_POST["type"]=="signup") && isset($_POST["phone"]) && isset($_POST["signaltype"]) && isset($_POST["signalstrength"]) && isset($_POST["latitude"]) && isset($_POST["longitude"]) && isset($_POST["description"])){
    // signup user
    $phone = $_POST["phone"];
    $signaltype = $_POST["signaltype"];
	$signalstrength = $_POST["signalstrength"];
	$latitude = $_POST["latitude"];
	$longitude = $_POST["longitude"];
	$description = $_POST["description"];
 
    //check user email whether its already regsitered
    $checkEmailQuery = "select * from users where phone != '$phone'";
    $result = mysqli_query($db_con);
    // print_r($result); exit;
    if(!empty($result) && $result->num_rows > 0){
        $response["error"] = TRUE;
        $response["message"] ="Sorry email already found.";
        echo json_encode($response);
        exit;
    }else{
        $signupQuery = "INSERT INTO users(phone,signaltype,signalstrength,latitude,longitude,description) values('$phone','$signaltype','$signalstrength','$latitude','$longitude','$description')";
        $signupResult = mysqli_query($db_con,$signupQuery);
        if($signupResult){
            // Get Last Inserted ID
            $id = mysqli_insert_id($db_con);
            // Get User By ID
            $userQuery = "SELECT phone,signaltype,signalstrength,latitude,longitude,description FROM users WHERE id= '$id'";
            $userResult = mysqli_query($db_con,$userQuery);
             
            $user = mysqli_fetch_assoc($userResult);
             
            $response["error"] = FALSE;
            $response["message"] = "Successfully signed up.";
            $response["phone"] = $phone;
            echo json_encode($response);
            exit;
        }else{
            $response["error"] = TRUE;
            $response["message"] ="Unable to signup try again later.";
            echo json_encode($response);
            exit;
        }
         
    }
 
}else if(isset($_POST["type"]) & ($_POST["type"]=="login") & isset($_POST["phone"]) & isset($_POST["signaltype"])){
    //login user
 
    $phone = $_POST["phone"];
    $signaltype = md5($_POST["signaltype"]);
 
    $userQuery = "select id,phone,signaltype from users where phone = '$phone' &amp;&amp; signaltype = '$signaltype'";
    $result = mysqli_query($db_con,$userQuery);
    // print_r($result); exit;
    if($result->num_rows==0){
        $response["error"] = TRUE;
        $response["message"] ="user not found or Invalid login details.";
        echo json_encode($response);
        exit;
    }else{
        $user = mysqli_fetch_assoc($result);
        $response["error"] = FALSE;
        $response["message"] = "Successfully logged in.";
        $response["user"] = $user;
        echo json_encode($response);
        exit;
    }
 
}else {
    // Invalid parameters
    $response["error"] = TRUE;
    $response["message"] ="Invalid parameters";
    echo json_encode($response);
    exit;
}
