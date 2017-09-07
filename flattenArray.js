var theArray = [];
var newArray = [];

// Create a sample array to test output
theArray[0] = [];
theArray[0][0] = 1;
theArray[0][1] = 2;
theArray[0][2] = [3];
theArray[1] = 4;
console.log("Unflatenned Array: ");
console.log(theArray);

// Call function to flatten array
newArray = flattenArray(theArray);
console.log("Flattened Array: ");
console.log(newArray);

/*
* Flatten an array of arbitrarily nested arrays of integers into a flat array of integers. e.g. [[1,2,[3]],4] -> [1,2,3,4].
* @params arrayToFlatten an array of Integers or nested arrays of Integers
* @return flattened array of Integers or null if input is null
* @throws expection in case a not valid integer is found
*/
function flattenArray(arrayToFlatten) {
	if(!Array.isArray(arrayToFlatten)) {
  	if(!checkIfInteger(arrayToFlatten)) {
    	throw "Invalid integer";
    }
    return [arrayToFlatten];
  }

  var flattenedArray = [];
  for(var i = 0; i < arrayToFlatten.length; i++) {
    flattenedArray = flattenedArray.concat(flattenArray(arrayToFlatten[i]));
  }
  
  return flattenedArray;
}

/*
* Check if a value is a valid input - valid integer.
* @params arrayValue is the value to be checked. Note that 1 and "1" are both considered valid integer values
* @return boolean true if value is a valid input or false otherwise
*/
function checkIfInteger(arrayValue) {
	return !isNaN(arrayValue) && 
         parseInt(Number(arrayValue)) == arrayValue && 
         !isNaN(parseInt(arrayValue, 10));
}