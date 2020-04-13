<?php 
 include 'init.php';
if ($_SERVER['REQUEST_METHOD']=='POST') {
    if (isset($_POST['name'])) {  
		 $name = $_POST['name'];
		 $email = $_POST['email'];
		 $mobile = $_POST['mobile'];
		 $address = $_POST['address'];
		 $username = $_POST['username'];
		 $password = $_POST['password'];

		$que = "SELECT email from user where name = '$name' AND uname = '$username'";
		$res = $con->query($que);
		if (mysqli_num_rows($res) > 0) {
			$product = array(

		        'Response' => "0"
		    );
		            
		    echo json_encode($product);

			mysqli_close($con);

		}else{



			$que = "INSERT INTO `user` (`name`, `mobile`, `email`, `address`, `uname`, `password`) 
								VALUES ('$name','$mobile','$email','$address','$username','$password');";

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