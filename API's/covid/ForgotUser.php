<?php
include 'init.php';
if ($_SERVER['REQUEST_METHOD']=='POST') {
    if (isset($_POST['name'])) {  
        $name = $_POST['name'];
        $mobile = $_POST['mobile'];
        $query = "SELECT * FROM user WHERE name='$name' AND mobile='$mobile'";
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