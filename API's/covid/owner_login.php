<?php
include 'init.php';
if ($_SERVER['REQUEST_METHOD']=='POST') {
    if (isset($_POST['username'])) {  
        $username = $_POST['username'];
        $password = $_POST['password'];
        $query = "SELECT * FROM shop_owner WHERE Uname='$username' AND Password='$password'";
        $data = mysqli_query($con, $query);
        $total = mysqli_num_rows($data);
       
        if($total){
            // echo "True";
                while($row = mysqli_fetch_array($data)){
        
                    $Id = $row['id'];
                     $Name = $row['name'];
                     $Owner_Name = $row['owner_name'];
                     $Email = $row['email'];
                     $Mobile = $row['mobile'];
                     $Address = $row['address'];
                     $Location = $row['location'];
                     $Mcount = $row['mask_count'];
                     $Scount = $row['sanitize_count'];

                }   
                $temp = array(
                   
                        'Response'=> "1",
                        'Id'=> $Id,
                        'Name'=> $Name,
                        'Owner_Name'=> $Owner_Name,
                        'Email'=> $Email,
                        'Mobile'=> $Mobile,
                        'Address'=> $Address,
                         'Location' => $Location,
                         'Mcount' =>$Mcount,
                         'Scount' =>$Scount
                       
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