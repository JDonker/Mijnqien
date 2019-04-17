function logout() {
    localStorage.clear();
    location.href = "logout";
}

function herDeclaForm1(){
    var api =  "api/Email/herinnering1";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        console.log(this.status)
        if (this.readyState == 4 && this.status == 202) {
            console.log("gelukt");
        }
    };
    // geef aan dat je data wil gaan posten in de database
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/open
    xhttp.open("POST", "http://localhost:8082/"+api);
    xhttp.setRequestHeader("Content-type", "application/json");
    // send request om data te gaan posten
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/send
    xhttp.send();
}