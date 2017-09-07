<?php 

// Error Reporting - Uncomment it if needed
/*ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);*/

// Declare constants
const OFFICE_LATITUDE = 53.339428;
const OFFICE_LONGITUDE = -6.257664;
const R = 6371; //Radius for spherical earth in KM
const DISTANCE = 100; // Minimum distance in KM

// Get list of users
$usersList = getJSONData();

// Create array to hold list of users to be invited
$usersToInvite = array();

// Loop through array looking for users within the minimum distance
foreach($usersList as $user) {
	if(checkForValidCoordinates($user['latitude']) && checkForValidCoordinates($user['longitude'])) {
		$userDistance = calculateDistance($user['latitude'], $user['longitude']);
		if($userDistance <= DISTANCE) {
			$usersToInvite[$user['user_id']] = $user['name'];
		}
	}
}

// Sort usersToInvite array by user_id
ksort($usersToInvite);

// Print list of users to be invited
printListOfUsers($usersToInvite);

/*
* Reads data from 'customers.json' file and builds a valid JSON object.
* @return a valid JSON object
* @throws expection in case there is a problem reading the file or if there are no customers in the file
*/
function getJSONData() {
	if ($file = fopen("customers.json", "r")) { // Assume file is in the same directory
		// Build a valid JSON string based on the lines read from the file
		$jsonString = "[";

	    while(!feof($file)) {
	        $line = fgets($file);
	        $jsonString .= $line . ",";
	    }
	    fclose($file);

	    $jsonString = substr($jsonString, 0, -1);
	    $jsonString .= "]";

	    $usersData = json_decode($jsonString, true);

	    if(count($usersData) <= 0) {
	    	throw new Exception("There are no customers in the file.");	
	    } else {
	    	return $usersData;
	    }
	} else {
		throw new Exception("There was a problem reading the file.");
	}
}

/*
* Validate the user coordinates.
* @params value containing the user coordinates
* @return boolean true in case it is a valid coordinate
* @throws expection in case value is invalid
*/
function checkForValidCoordinates($userCoordinates) {
	if(isset($userCoordinates) && $userCoordinates != "" && is_numeric($userCoordinates)) {
		return true;
	} else {
		throw new Exception("Invalid coordinates found in the file.");
	}
}

/*
* Calculate the distance between 2 points using the Spherical Law of Cosines.
* @params latitude and longitude values for 2nd point - customer coordinates
* @return float distance between 2 points
*/
function calculateDistance($userLatitude, $userLongitude) {
	$userLatitudeRad = deg2rad($userLatitude);
	$officeLatitudeRad = deg2rad(OFFICE_LATITUDE);
	$longitudeDifference = deg2rad($userLongitude - OFFICE_LONGITUDE);
	$distance = acos( sin($officeLatitudeRad) * sin($userLatitudeRad) + cos($officeLatitudeRad) * cos($userLatitudeRad) * cos($longitudeDifference) ); 
	return $distance * R;
}

/*
* Print list of users.
* @params array with list of users to be printed
*/
function printListOfUsers($usersToInvite) {
	if(count($usersToInvite) > 0) {
		echo " ------ LIST OF CUSTOMERS TO BE INVITED ---------- <br><br>";
		echo " CUSTOMER ID | CUSTOMER NAME <br><br>";
		foreach($usersToInvite as $userID=>$userName) {
			echo " " . $userID . " | " . $userName . "<br>"; 
			echo " ---------------------- <br>"; 
		}
	}
}

?>