<?php 
 include 'init.php';
if ($_SERVER['REQUEST_METHOD']=='POST') {
    if (isset($_POST['name'])) {  
		 $name = $_POST['name'];
		 $owner_name = $_POST['owner_name'];
		 $email = $_POST['email'];
		 $mobile = $_POST['mobile'];
		 $address = $_POST['address'];
		 $location = $_POST['location'];
		 $username = $_POST['username'];
		 $password = $_POST['password'];

		$que = "SELECT email from shop_owner where email = '$email'";
		$res = $con->query($que);
		if (mysqli_num_rows($res) > 0) {
			$product = array(

		        'Response' => "0"
		    );
		            
		    echo json_encode($product);

			mysqli_close($con);

		}else{



			$que = "INSERT INTO `shop_owner` (`name`, `owner_name`, `mobile`, `email`, `address`, `location`, `uname`, `password`) VALUES ('$name','$owner_name','$email','$mobile','$address','$location','$username','$password');";

			if(mysqli_query($con,$que)){
				$product = array(

		                'Response' => "1"
		            );
		            
		            echo json_encode($product);
			}else{
				$product = array(

		                'Response' => "2"
		            );
		            
		            echo json_encode($product);
			}

			mysqli_close($con);
		}
	}
}
?>