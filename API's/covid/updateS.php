<?php
	require 'init.php';

	if ($_SERVER['REQUEST_METHOD']=='POST') {
    if (isset($_POST['id'])) {
			$id = $_POST["id"];
			$scount = $_POST["sanitize_count"];
			

			$sql = "UPDATE shop_owner
					SET sanitize_count = '$scount'
					WHERE id = '$id';";

			if(mysqli_query($con,$sql)){
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