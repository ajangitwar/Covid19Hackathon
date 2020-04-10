<?php
include 'init.php';
if ($_SERVER['REQUEST_METHOD']=='POST') {
    if (isset($_POST['username'])) {  
        $username = $_POST['username'];
        $password = $_POST['password'];
        $query = "SELECT * FROM user WHERE Uname='$username' AND Password='$password'";
        $data = mysqli_query($con, $query);
        $total = mysqli_num_rows($data);
    
        if($total){
            // echo "True";
                while($row = mysqli_fetch_array($data)){
        
                     $Name = $row['name'];
                     $Email = $row['email'];
                     $Mobile = $row['mobile'];
                     $Address = $row['address'];
                }   
                $temp = array(
                   
                        'Response'=> "1",
                        'Name'=> $Name,
                        'Email'=> $Email,
                        'Mobile'=> $Mobile,
                       'Address'=> $Address

                        );
                
        echo json_encode($temp);

       
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