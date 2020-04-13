<?php
include 'init.php';
if ($_SERVER['REQUEST_METHOD']=='POST') {
    if (isset($_POST['owner_name'])) {  
        $owner_name = $_POST['owner_name'];
        $mobile = $_POST['mobile'];
        $query = "SELECT * FROM shop_owner WHERE owner_name='$owner_name' AND mobile='$mobile'";
        $data = mysqli_query($con, $query);
        $total = mysqli_num_rows($data);
       
        if($total){
            while($row = mysqli_fetch_array($data)){
        
                    $Id = $row['id'];

                }   
            $product = array(

                'Response' => "1",
                'id' => $Id
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