<?php
	$connect=mysqli_connect('shareddb-f.hosting.stackcp.net','helfyDB-3639aa6c','gau27rav','helfyDB-3639aa6c');
	if(!$connect)
	{
		echo "Error while connecting to Database:".mysqli_connect_errno($connect) ;
	}
	$db=mysqli_select_db($connect, 'orders');
?>