<?php
	$connect=mysqli_connect('shareddb-g.hosting.stackcp.net','helfyhelfy-3235d477','helfy123','helfyhelfy-3235d477');
	if(!$connect)
	{
		echo "Error while connecting to Database:".mysqli_connect_errno($connect) ;
	}
	$db=mysqli_select_db($connect, 'eventsap_eventsbuddy');
?>