//https://www.glassdoor.ie/Interview/Zalando-Frontend-Developer-Interview-Questions-EI_IE613421.0,7_KO8,26.htm
//https://jobs.zalando.com/tech/blog/what-knowledge-should-you-have-to-be-a-good-frontend-developer/?gh_src=4n3gxh1

/*********************************** Technical Interview *****************************************/

// you can write to stdout for debugging purposes, e.g.
// console.log('this is a debug message');

function solution(N, S, T) {
    // write your code in JavaScript (Node.js 6.4.0)

    var shipCells = buildShipsMatrix(S);
    //console.log(shipCells);
    
    var hitsHashTable = createHitsHashTable(T);
    //console.log(hitsHashTable);
    
    var shipsSunk = 0;
    var shipsHit = 0;
    
    for(var i=0; i<shipCells.length; i++) {
        var currShipLength = shipCells[i].length;
        var currHitsCount = 0;
        
        for(var j=0; j<currShipLength; j++) {
            var currCell = shipCells[i][j];
            
            if(hitsHashTable[currCell] != null) {
                currHitsCount++;
            }
        }
        
        if(currHitsCount == currShipLength) {
            shipsSunk++;
        } else if (currHitsCount > 0) {
            shipsHit++;
        }
    }
    
    //console.log(shipsSunk + " - " + shipsHit);
        
    return shipsSunk + "," + shipsHit;
}

/*
  Create an array of the alphabet letters
*/
function buildAlphabetArray() {
    var alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    var alphabetArray = alphabet.split('');
    
    return alphabetArray;
}

/*
 Build Matrix with all cells that compose a ship based on the inital and end positions
 passed by the array S
*/
function buildShipsMatrix(S) {
    var shipsArray = S.split(',');
    var shipCells = [];
    var shipCellsIdx = 0;
    
    //get alphabet array
    alphabetArray = buildAlphabetArray();
    
    for(var i=0; i<shipsArray.length; i++) {
        var singleCells = shipsArray[i].split(' ');
        
        shipCells[shipCellsIdx] = [];
    
        var cellTopRow = singleCells[0].split('')[0];
        var cellTopColumn = singleCells[0].split('')[1];
        var cellBottomRow = singleCells[1].split('')[0];
        var cellBottomColumn = singleCells[1].split('')[1];
        
        //console.log(cellTopRow + " - " + cellTopColumn + " - " + cellBottomRow + " - " + cellBottomColumn);
        
        //Build array with all ship positions 
        if(singleCells[0] == singleCells[1]) {
            shipCells[shipCellsIdx].push(singleCells[0]);    
        } else {
            var currShipRow = cellTopRow;
            
            while(currShipRow <= cellBottomRow) {
                var currShipColumn = cellTopColumn;
                
                while(currShipColumn != cellBottomColumn) {
                    shipCells[shipCellsIdx].push('' + currShipRow + "" + currShipColumn + '');
                    currShipColumn = alphabetArray[alphabetArray.indexOf(currShipColumn) + 1];
                }
                shipCells[shipCellsIdx].push('' + currShipRow + "" + currShipColumn + '');
                
                currShipRow++;       
            }
        }  
        
        shipCellsIdx++;
    }
    
    //console.log(shipCells);
    
    return shipCells;
}

/*
   Create hashTable with all values from array T
*/
function createHitsHashTable(T) {
    var hitsHashTable = {};
    hitsArray = T.split(' ');
    
    for(var i=0; i<hitsArray.length; i++) {
        hitsHashTable[hitsArray[i]] = 1; //1 just to symbolize that there was 1 hit in each cell
    }
    
    return hitsHashTable;
}



/************************************************************************************************************************/

function solution() {
    // write your code in JavaScript (Node.js 6.4.0)
    //
    // you can access DOM Tree using DOM Object Model:
    //    document.getElementById
    // or using jQuery:
    //    $('some_tag')
    //
    // you can write to stdout for debugging purposes, e.g.
    // console.log('this is a debug message');
    
    formValidationResult = true;
    var checkedOption = $('input[name="type"]:checked').val();
    
    if(checkedOption == "person") {
        formValidationResult = validatePerson();
    } else if(checkedOption == "company") {
        formValidationResult = validateCompany();
    }

    return formValidationResult;
}

function validatePerson() {
    //get input field values
    var firstName = $('input[name="first_name"]').val();
    var lastName = $('input[name="last_name"]').val();
    var email = $('input[name="email"]').val();
    
    //validate first and last names
    if(!validateNonEmpty(firstName) || !validateNonEmpty(lastName) || !validateNonEmpty(email)) return false;
    
    //validate email
    var countEmailSign = (email.match(/@/g) || []).length; //return zero - [] - if no match
    if(countEmailSign != 1) return false; //if email == " " this check will catch it
    
    var emailString = email.split('@'); //create string array
    if(emailString[0].length > 64 || emailString[1].length > 64) return false;
    
    var validLetters = /^[A-Za-z0-9.]+$/; //valid set of characters
    if(emailString[0].match(validLetters) == null || emailString[1].match(validLetters) == null) return false;
    
    return true;
}

function validateCompany() {
    //get input field values
    var companyName = $('input[name="company_name"]').val();
    var phone = $('input[name="phone"]').val();
    
    if(!validateNonEmpty(companyName) || !validateNonEmpty(phone)) return false;
    
    var validLetters = /^[0-9-]+$/; //valid set of characters
    if(phone.match(validLetters) == null || phone.length < 6) return false;
    
    return true;
}

function validateNonEmpty(stringToValidate) {
    if(stringToValidate == null || typeof stringToValidate == 'undefined' || stringToValidate.length <=0) {
        return false;
    }
    
    return true;
}


/************************************************************************************************************************/

// you can write to stdout for debugging purposes, e.g.
// console.log('this is a debug message');

function solution() {
    // write your code in Javascript
    //
    // you can access DOM Tree using DOM Object Model:
    //    document.getElementsByTagName
    // or using jQuery:
    //    $('some_tag')
    
    var parentLength = 0;
    var maxDepth = 0;
    
    $('li:not(:has(ol)):not(:has(ul))').each(function() {
        parentLength = $(this).parents('ul,ol').length;
        
        if(parentLength > maxDepth) maxDepth = parentLength;
    });

    return maxDepth;
}



/*********************************** On Site Possible Interview Questions *****************************************/

/* How you would make loading component with 3 dots */
<span id="wait">.</span>
<script>
var dots = window.setInterval( function() {
    var wait = document.getElementById("wait");
    if ( wait.innerHTML.length > 3 ) 
        wait.innerHTML = "";
    else 
        wait.innerHTML += ".";
    }, 100);
</script>


/* What is a singleton 
In software engineering, the singleton pattern is a software design pattern that restricts the instantiation of a class to one object.
This is useful when exactly one object is needed to coordinate actions across the system. 
var user = {  
    firstName: 'John',
    lastName: 'Doe',
    sayName: function() {
        return this.firstName + ' ' + this.lastName;
    }
};
Yep, that's a Singleton. Once you create an object literal in JavaScript you've reserved a little piece of memory and no other object will 
ever be just like that one. Now, depending on the scope you might have only created a local variable but if that user is sitting outside a 
function then it's globally available to anyone who wants to fiddle with it. I guess the most famous Singleton probably looks like this: $. 
To put it another way, ever notice how you can use jQuery anywhere in your app after you've included it on the page...? */



/* Lazy loading
Lazy loading is a design pattern commonly used in computer programming to defer initialization of an object until the point at which it is 
needed. Basically you put a dummy image in your src attribute and add another attribute for the actual image, JS detects the scroll position of
the page, and loads the image data once you get close enough to the image. it does that by replacing the src with the source of the actual image.
<img src="/images/lazy.jpg" data-real-src="/images/company-1.jpg">
$('img[src="/images/lazy.jpg"]').each(function(index, el) {
    $(el).attr('src', $(el).data('real-src'));
});



/* XHTML is HTML written in XML - Makes sure HTML is valid */



/* SOAP - It is an XML-based messaging protocol for exchanging information among computers */



/* CSS Position -> https://www.w3schools.com/css/css_positioning.asp */



/* Immediately Invoked Function Expressions (IIFE) 
IIFE’s are used to help prevent your functions and variables from affecting the global scope. All the properties within are scoped to the 
anonymous function. This is a common design pattern that’s used to prevent your code from having unwanted or undesired side-effects elsewhere.
(function () {
    // code in here
}());
*/



/* Function Expression vs Function Declaration
https://javascriptweblog.wordpress.com/2010/07/06/function-declarations-vs-function-expressions/
->Functoin Declaration 
function foo() { ... }
Because of function hoisting, the function declared this way can be called both after and before the definition.

->Function Expression
--Named Function Expression
var foo = function bar() { ... }

--Anonymous Function Expression
var foo = function() { ... }
foo() can be called only after creation.
*/



/* Dependency Injection 
Dependency injection in javascript typically follows one of two patterns. In constructor injection, a dependency is assigned to an instance 
variable whenever the dependent function is initialized. A car, for instance, might require some type of engine to run:
function Car (engine) {
  this.engine = engine;
};

var myCar = new Car(new V8Engine);
It’s a trivial example with interesting implications. Since the V8Engine has instead been extracted into a shiny new class of its own, 
it may now be used by anything else that needs an engine. It can be tested independently and passed to the car with some degree of confidence 
that–if the car needs an engine–it will be able to drive.
Setter injection takes things a step further. The engine assigned at the factory will be good enough for most cars, but what if we want to 
upgrade? If the dependency is moved from the constructor to a method in the function’s prototype, it can now be replaced as the program runs:
function Car () {};

Car.prototype.setEngine (engine) {
  this.engine = engine;
};

var car = new Car();
car.setEngine(new V8Engine);
car.setEngine(new V12Engine);
Setter injection circumvents the single-use limitation of constructor injection, but–since frequent changes to a class (whether via an 
injection or anything else) make program flow difficult to follow–this is a technique to be used with care.
*/



/* call() vs apply() -> https://hangar.runway7.net/javascript/difference-call-apply


/* Cache Buster 
A cache-buster is a unique piece of code that prevents a browser from reusing an ad it has already seen and cached, or saved, to a 
temporary memory file. The cache-buster doesn’t stop a browser from caching the file, it just prevents it from reusing it. In most cases, 
this is accomplished with nothing more than a random number inserted into the ad tag on each page load. The random number makes every ad call 
look unique to the browser and therefore prevents it from associating the tag with a cached file, forcing a new call to the ad server. */


/* HTTP Caching 
https://devcenter.heroku.com/articles/increasing-application-performance-with-http-cache-headers
https://jakearchibald.com/2016/caching-best-practices/
https://www.w3.org/2005/MWI/BPWG/techs/CachingWithETag.html
*/

/* Authentication
https://jwt.io/introduction/
https://blog.risingstack.com/web-authentication-methods-explained/
*/

/* TDD 
Test-Driven Development
Test-driven development, or TDD, is a rapid cycle of testing, coding, and refactoring. When adding a feature, a pair may perform dozens of 
these cycles, implementing and refining the software in baby steps until there is nothing left to add and nothing left to take away.
*/