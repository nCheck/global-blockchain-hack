<html>
<body>

<?php
  include 'connect.php';
  $type =  $_GET["type"];
      $name =  $_GET["name"];
      $amount = $_GET["amount"];
      $date = $_GET["date"];
      $email = $_GET["e-mail"];
      $contact = $_GET["contact"];
      $city = $_GET["city"];
      $address = $_GET["address"]; 
      $radio = $_GET["radio"];
      $orgid = 0;
      $status = "New";
  	  $org = "Smile Foundation";
    	$sql = "INSERT INTO `orders` (`type`, `name`, `description`, `date`, `org`, `status`, `address`, `orgid`) VALUES ($type,$name,$amount,$date,$org,$status,$address,$orgid)";
      $result = mysqli_query($connect, $sql);
    if(!$result)
    {
    	echo "error: ".mysqli_error($connect);
    }
    else{
      echo "Success";
    }

      ?><br>
</body>
</html> 