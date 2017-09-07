<?php
	date_default_timezone_set("Europe/Dublin");

	$userDateForSaturday = null;
	$userDateForWednesday = null;
	$nextDrawDate = "";

	if (isset($_GET['userDate']) && $_GET['userDate'] != "" && (strtotime($_GET['userDate']) >= strtotime("now"))) {
		$userDateForSaturday = DateTime::createFromFormat('Y-m-d', $_GET['userDate']);
        $userDateForWednesday = DateTime::createFromFormat('Y-m-d', $_GET['userDate']);
    } else {
    	$userDateForSaturday = new DateTime();
    	$userDateForWednesday = new DateTime();
    }

    $copyDateForSaturday = $userDateForSaturday;
    $copyDateForWednesday = $userDateForWednesday;

    // Get next Saturday and Wednesday from selectd date
	$nextSaturday = $copyDateForSaturday->modify('next saturday');
	$nextWednesday = $copyDateForWednesday->modify('next wednesday');

	if($nextSaturday < $nextWednesday) {
		$nextDrawDate = $nextSaturday->format('D, M dS Y');
	} else {
		$nextDrawDate = $nextWednesday->format('D, M dS Y');
	}
?>
<!doctype html>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" >
		<title>Bet Bright - Date Calculation</title> 

		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<style type="text/stylesheet">
			@-webkit-viewport   { width: device-width; }
			@-moz-viewport      { width: device-width; }
			@-ms-viewport       { width: device-width; }
			@-o-viewport        { width: device-width; }
			@viewport           { width: device-width; }
		</style>

		<!-- Load Google Fonts -->
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700" rel="stylesheet">

		<!-- CSS -->
		<style>
			*{
			    font-family: 'Open Sans', sans-serif;
			}
			html {
			    height: 100%;
			}
			body {
			    height: 100%;
			}
			.clear {
				clear: both;
			}
			.main-container {
				width: 800px;
				height: 100%;
				margin-left: auto;
				margin-right: auto;
				text-align: center;
				margin-top: 3em;
			}
			form {
				margin-bottom: 1em;
			}
			form label {
				display: block;
			}
			form input[type="submit"] {
			    border: none;
			    background: #0F7895;
			    color: #fff;
			    outline: none;
			}
			.nextDrawDate {
				font-weight: bold;
			}
			.nextDrawDate span {
				text-decoration: underline;
			}
			
		</style>
	</head>
	<body>
		<div class="main-container">
			<h2>Get Next Lottery Draw Date</h2>
			<form action="">
				<label>Enter a date or simply press the "Get Next Draw Date" to get the next draw based on the current date.</label>
				<input type="date" name="userDate" value="">
				<input type="submit" value="Get Next Draw Date">
			</form>
			<?php if($nextDrawDate != ""): ?>
				<label class="nextDrawDate">The next draw date is: <span><?php echo $nextDrawDate; ?></span></label>
			<?php endif; ?>
		</div>
	</body>
</html>