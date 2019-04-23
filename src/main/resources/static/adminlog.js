function logout() {
    localStorage.clear();
    location.href = "logout";
}

var input = 0;
function herDeclaForm(input) {
    var api = ""
    switch (input) {
        case 1:
            api = "api/Email/herinnering1";
            break;
        case 2:
            api = "api/Email/herinnering2";
            break;
        case 3:
            api = "api/Email/herinnering3";
            break;
        case 4:
            api = "api/Email/herinnering4";
            break;
    }
    herDeclaForm1(api);
}

inputString = "";
function herDeclaForm1(inputString) {
    var api = inputString;

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        console.log(this.status)
        if (this.readyState == 4 && this.status == 202) {
            console.log("gelukt");
        }
    };

    xhttp.open("POST", "http://localhost:8082/" + api);
    xhttp.setRequestHeader("Content-type", "application/json");

    xhttp.send();
}


function formmaken() {


    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var trainees = JSON.parse(this.responseText);
           
            for (var i = 0; i < trainees.length; i++) {


                var uform = {};
                uform.naam = trainees[i].voornaam + " " +trainees[i].tussenvoegsel + " " + trainees[i].achternaam;
                uform.maand = new Date();
                uform.stat = 0;

                var dform = {};

                dform.naam = trainees[i].voornaam + " " +trainees[i].tussenvoegsel + " " + trainees[i].achternaam;
                dform.maand = new Date();
                dform.stat = 0;

              


                createuform(JSON.stringify(uform));
                createdecform(JSON.stringify(dform))
            }



        }
    }
    xhttp.open("GET", "http://localhost:8082/api/trainee", true);
    xhttp.send();

}

function createuform(a) {
    var xhttp2 = new XMLHttpRequest();
    xhttp2.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

        }
    }

    xhttp2.open("POST", "http://localhost:8082/api/urenform/newform", true);
    xhttp2.setRequestHeader("Content-type", "application/json");
    xhttp2.send(a);

}

function createdecform(b) {
    var xhttp3 = new XMLHttpRequest();
    xhttp3.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

        }
    }

    xhttp3.open("POST", "http://localhost:8082/api/DeclaratieForm/newform", true);
    xhttp3.setRequestHeader("Content-type", "application/json");
    xhttp3.send(b);
}