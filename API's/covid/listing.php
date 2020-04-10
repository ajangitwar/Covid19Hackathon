<?php 
 
include 'init.php';
 //creating a query
$location = $_POST['location'];
$stmt = $con->prepare("SELECT id, name,owner_name, address,email,mobile,location,mask_count,sanitize_count FROM shop_owner WHERE location = '$location';");
 
 //executing the query 
$stmt->execute();
 
 //binding results to the query 
$stmt->bind_result($id, $name,$owner_name, $address,$email,$mobile,$location,$mask_count,$sanitize_count);
 
$products = array(); 

	while($stmt->fetch()){
		$temp = array();
		$temp['id'] = $id; 
		$temp['name'] = $name; 
		$temp['owner_name'] = $owner_name; 
		$temp['address'] = $address; 
		$temp['email'] = $email; 
		$temp['mobile'] = $mobile; 
		$temp['location'] = $location; 
		$temp['mask_count'] = $mask_count; 
		$temp['sanitize_count'] = $sanitize_count; 
		array_push($products, $temp);
	}
		
	if($stmt->num_rows > 0){
		echo json_encode(array('response' => '1','result' =>$products)); 	
	}else{
		$temp = array(

            'id' => "0"
        );
        array_push($products, $temp);
        echo json_encode(array('response' => '0','result' =>$products));
	}

 ?>