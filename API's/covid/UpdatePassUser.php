<?php
	require 'init.php';

	if ($_SERVER['REQUEST_METHOD']=='POST') {
    if (isset($_POST['password'])) {
			$password = $_POST["password"];
			$id = $_POST["id"];
			

			$sql = "UPDATE user
					SET password = '$password'
					WHERE id = '$id';";

			$demo = mysqli_query($con,$sql);


			if(mysqli_affected_rows($con) >0 ){
				$product = array(

		                'Response' => "1"
		            );
		            
		            echo json_encode($product);
			}
			else{
				$product = array(

		                'Response' => "0"
		            );
		            
		            echo json_encode($product);
			}
		}
	}
	?>