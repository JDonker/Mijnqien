var trainee = 2;
var jsoninputs = {};


var declaraties = "";
var reizen = "";
var formulier = {};
formulier.datum = new Date();

function DeclaratieToevoegen1() {
    var table = document.getElementById("Declaratie");
    console.log(table);
    var row = table.insertRow(document.getElementById("Declaratie").rows.length);
    
    var cell1 = row.insertCell(0);
    cell1.innerHTML = row.rowIndex;
    var cell2 = row.insertCell(1);
    cell2.setAttribute("name", "datum" + row.rowIndex);
    var input2 = document.createElement("input");
    input2.setAttribute("type", "date");
    cell2.appendChild(input2);
    var cell3 = row.insertCell(2);
    cell3.setAttribute("name", "omschrijving" + row.rowIndex);
    var input3 = document.createElement("input");
    input3.setAttribute("type", "text");
    input3.setAttribute("size", "75");
    cell3.appendChild(input3);
    var cell4 = row.insertCell(3);
    cell4.setAttribute("name", "bedragbtw" + row.rowIndex);
    var input4 = document.createElement("input");
    input4.setAttribute("type", "number");
    input4.setAttribute("min", "0");
    input4.setAttribute("value", "0");
    input4.setAttribute("step", ".01");
    cell4.appendChild(input4);
    var cell5 = row.insertCell(4);
    cell5.setAttribute("name", "btw" + row.rowIndex);
    var input5 = document.createElement("select");
    var array = ["Kies BTW tarief", "0%", "9%", "21%"];
    input5.id = "mySelect";
    cell5.appendChild(input5);
    for (var j = 0; j < array.length; j++) {
        var option = document.createElement("option");
        option.value = array[j];
        option.text = array[j];
        input5.appendChild(option);
    }
    var cell6 = row.insertCell(5);
    cell6.setAttribute("name", "bedrag" + row.rowIndex);
    var input6 = document.createElement("input");
    input6.setAttribute("type", "number");
    input6.setAttribute("min", "0");
    input6.setAttribute("value", "0");
    input6.setAttribute("step", ".01");
    cell6.appendChild(input6); 
    var cell7 = row.insertCell(6);
    cell7.setAttribute("name", "bestand" + row.rowIndex);
    var input7 = document.createElement("input");
    input7.setAttribute("type", "file");   
    cell7.appendChild(input7);
}


// code om de tabel declaraties op te maken
function DeclaratieWegschrijven(){

    // vraag de body van de declaratie tabel op en gooi hem leeg
    var table = document.getElementById("DeclaratieTableBody");
    table.innerHTML="";

    // declaratie string met alle objecten is een globale varaible hier maak je er een lijst met jsonobjecten van
    jsondata =JSON.parse(declaraties);

    // loop over de collomen van de jsontabel
    for (var i = 0; i < jsondata.length; i++) {
        // voeg nieuwe rij toe
        var row = table.insertRow(-1);
        // voeg functie onfocusout toe : als je het input veld verlaat wordt de functie putdeclaratie aan geroepen die het record update
        row.setAttribute("onfocusout","putdeclaratie(" + jsondata[i]["id"] + ")");
        for(var k=1;k<4;k++){
            // voeg een cell aan de rij toe
            var cellDeclaratie = row.insertCell(-1);
            // maak een input element
            var cellDeclaratieinput = document.createElement("input");
           //cellDeclaratieinput.setAttribute("onfocusout","putdeclaratie(" + jsondata[i]["id"] + ")");
            // switch op het rij nummer 
            switch(k){
                case 1: 
                    
                    // geef een id mee bestaande uit het de veld naam en het object id in de database (hier de datum)
                    cellDeclaratieinput.setAttribute("id","datum" + jsondata[i]["id"] );
                    // cell is van type datum
                    cellDeclaratieinput.setAttribute("type", "date");
                    // haal begin en end datum uit de datum van het formulier
     

                    // info uit het formulier halen hier gaat het om de datum 

                    // eerste en laatste dag van de datum bepalen
                    var eerste = new Date(jsondata[i]["datum"]);
                    eerste.setDate=1;
                    var laatste = new Date(eerste.getFullYear(),eerste.getMonth()+1,0);
              //      var today = new Date();

              //      if (today<laatste){
              //          laatste=today;
              //      }
                    // van eerste en laatste dag string maken
                    var eersteVanDeMaand = eerste.getUTCFullYear() + "-" +
                    ("0" + (eerste.getUTCMonth()+1)).slice(-2) + "-" +
                    ("01").slice(-2); 
                    var laatsteVanDeMaand = laatste.getUTCFullYear() + "-" +
                    ("0" + (laatste.getUTCMonth()+1)).slice(-2) + "-" +
                    ("0" + (laatste.getDate())).slice(-2); 
                    // zorgt dat alleen datums binnen een bepaalde maand kunnen worden geselecteerd
                    cellDeclaratieinput.setAttribute("min", eersteVanDeMaand);
                    cellDeclaratieinput.setAttribute("max", laatsteVanDeMaand);
                    // haal uit de de database: record i en daarvan het field datum
                    cellDeclaratieinput.value=jsondata[i]["datum"];
                    break;
                case 2:
                    // geef een id mee bestaande uit het de veld naam en het object id in de database (hier het id)
                    cellDeclaratieinput.setAttribute("id","omschrijving" + jsondata[i]["id"] );
                    //input veld  is van type tekst
                    cellDeclaratieinput.setAttribute("type", "text");
                    // haal uit de de database: record i en daarvan het field omschrijving
                    cellDeclaratieinput.value=jsondata[i]["omschrijving"];
                    break; 
                case 3:
                    // geef een id mee bestaande uit het de veld naam en het object id in de database (hier het bedrag)
                    cellDeclaratieinput.setAttribute("id","bedrag" + jsondata[i]["id"] );
                         //input veld  is van type tekst
                    cellDeclaratieinput.setAttribute("type", "text");
                             // haal uit de de database: record i en daarvan het field bedrag
                    cellDeclaratieinput.value=jsondata[i]["bedrag"];
                    break;
                    
                default:
                    break;
            }
            // voeg het input veld toe aan de cell
            cellDeclaratie.appendChild(cellDeclaratieinput);

        }
        var cellDeclaratie = row.insertCell(-1);

        var btn = document.createElement("button");
        var inp = document.createElement("input");
 
        inp.type = "file";
        inp.name = "submit" + jsondata[i]["id"];
        inp.id = "filename";

        btn.type="submit";
        btn.name="submit" + jsondata[i]["id"];

        btn.innerHTML="Upload";
        btn.setAttribute("onclick",)
       
        addfile.appendChild(inp);
        addfile.appendChild(btn);
   
        

        cellDeclaratie.appendChild(addfile);
      


        // hier voeg ik een delete button toe dit is een nieuwe feature die nog moet worden geimplementeerd
        
        var cellDeclaratie = row.insertCell(-1);
        var deleteButton = document.createElement("Button");
        deleteButton.setAttribute("class","button")
        deleteButton.innerHTML= "X"
        deleteButton.setAttribute("onclick","DeleteDeclaratie("+ jsondata[i]["id"] + ")");
        cellDeclaratie.appendChild(deleteButton);
    }
}

function putBestand(id){

    //server adres
    var api =  "api/upload/" +  trainee + "/" + id;


    var file = document.getElementById('input' + id).files[0];
    var xhttp = new XMLHttpRequest;

    var formData = new FormData;
    formData.append('multipart/form-data', file);

    xhttp.upload.addEventListener("progress", myProgressHandler, false);
    xhttp.setRequestHeader("Content-type", "multipart/form-data");
    xhttp.addEventListener('load', myOnLoadHandler, false);
    xhttp.open('POST', 'api', true);
    xhttp.send(formData);
}





// code om de tabel reizen op te maken
function ReizenWegschrijven(){

    // zoek het element reizentabelbody op en maak het leeg
    var table = document.getElementById("ReizenTableBody");
    table.innerHTML="";
    // pak de globale variable reizen waarin de jsonstring staat die van de backend is gekomen
    jsondata =JSON.parse(reizen);
    // loop over de records in reizen
    for (var i = 0; i < jsondata.length; i++) {
        // voeg rij toe en een focusout functie die de reis update zodra je een veld verlaat
        var row = table.insertRow(-1);

        // de putreis functie krijgt het id van het aangepaste object in de database mee
        row.setAttribute("onfocusout","putreis(" + jsondata[i]["id"] + ")");

        // loop over de cellen in de rij
        for(var k=0;k<4;k++){
            // voeg een cell toe aan de rij
            var cellReis = row.insertCell(-1);
            // maak een input element
            var cellReisInput = document.createElement("input");
           // cellReisInput.setAttribute("onfocusout","putreis(" + jsondata[i]["id"] + ")");
            if(k>0 && k<3){
                // maak een input veld van type tekst
                cellReisInput.setAttribute("type", "text");
                // zet de grootte van het veld op 50 tekens
                cellReisInput.setAttribute("size", "50");
                if(k==1) {
                    // haal de plek waar de reis is gestart uit de database
                    cellReisInput.value=jsondata[i]["van"];
                    cellReisInput.setAttribute("id","van" + jsondata[i]["id"]);
                } else {
                    // haal de plek waar de reis is geindigd uit de database
                    cellReisInput.value=jsondata[i]["naar"];
                    // maak het id zo dat het veld makkelijk is te herleiden naar het object id in de database door het id te koppelen
                    cellReisInput.setAttribute("id","naar" + jsondata[i]["id"]);
                }
            }
            if(k==0){
                // hier het datum veld (de datum check zoals in declaratie moet hier nog bij)
                cellReisInput.setAttribute("type", "date");
                cellReisInput.value=jsondata[i]["datum"];
                // haal de datum uit de database en zet die in het veld
                cellReisInput.setAttribute("id","datum" + jsondata[i]["id"]);
            }
            if(k==3){
                // zet de eigenschappen van het kilometer input veld
                cellReisInput.setAttribute("type", "number");
                cellReisInput.setAttribute("min", "0");
                cellReisInput.setAttribute("value", "0");
                cellReisInput.value=jsondata[i]["kilometers"];
                // haal de kilometers uit de database en plaats ze in het veld
                cellReisInput.setAttribute("id","kilometers" + jsondata[i]["id"]);
            }
            cellReis.appendChild(cellReisInput);
        }
    }
}

// de tabellen (her)laden
function onload() {
    console.log(declaraties.length);
    if(declaraties.length==0) {
        // declaraties nog niet opgehaald haal ze nu op
        loadDeclaraties();
    } else {
        // maak declaratie tabel
        DeclaratieWegschrijven();
    }

    if(reizen.length==0){
        // reizen nog niet opgehaald haal ze nu op
        loadReizen();
    } else {
        // maak reizen tabel
        ReizenWegschrijven();
    }

}

function loadDeclaraties(){
        // Dit is het adres waar de declaraties vandaan kunnen wordt opgevraagd
        var api =  "api/DeclaratieForm/" + trainee
        // maak een nieuw request volgens het http protecol
        var xhttp = new XMLHttpRequest();
        console.log(api);
        // als staat van het XMLHTTPRequest object verandert doe dan het volgende
          xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // als de string  is aangepast ga er dan mee aan de slag
                if (declaraties!=this.responseText) {
                    // schrijf de jsonstring die je van de server krijt naar de globale variable declaraties
                    declaraties = this.responseText;
                    console.log("nu hier");
                    // herlaad de pagina
                    onload();
                }
            }
          };
        // geef aan dt je data wil gaan pakken uit de database
        // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/open
        xhttp.open("GET", "http://localhost:8082/"+api);
        // send request om data te gaan getten body wordt genegeerd
        // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/send
        xhttp.send();
}

function loadReizen(){
    // server adres om reizen op te halen
    var api =  "api/DeclaratieForm/Reis/" + trainee

    // maak een nieuw request volgens het http protecol
    var xhttp = new XMLHttpRequest();
    console.log(api);
    // als staat van het XMLHTTPRequest object verandert doe dan het volgende
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if (reizen!=this.responseText) {
                // schrijf de jsonstring die je van de server krijgt naar de globale variable reizen
                reizen = this.responseText;
                console.log(reizen);
                onload();
            }
        }
    };
    // geef aan dt je data wil gaan pakken uit de database
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/open
    xhttp.open("GET", "http://localhost:8082/"+api);
    // send request om data te gaan getten body wordt genegeerd
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/send
    xhttp.send();
}

function putdeclaratie(id){
    // maak een object van de te updaten reis
    var declaratie= {};
    // haal de velden op uit het formulier ze zijn makkelijk te vinden aan de hand van het meegegeven id
    declaratie.omschrijving = document.getElementById("van" + id).value;
    declaratie.bedrag = document.getElementById("naar" + id).value;
    declaratie.btw = document.getElementById("kilometers" + id).value;
    declaratie.datum = document.getElementById("datum" + id).value;
    declaratie.id = id;
    console.log(declaratie);
        // maak een string van het te updaten declaratie object een stuur dat naar de put functie
    putReis(JSON.stringify(declaratie));
}

function putreis(id){
    // maak een object van de te updaten reis
    var reis= {};
    // haal de velden op uit het formulier ze zijn makkelijk te vinden aan de hand van het meegegeven id
    reis.van = document.getElementById("van" + id).value;
    reis.naar = document.getElementById("naar" + id).value;
    reis.kilometers = document.getElementById("kilometers" + id).value;
    reis.datum = document.getElementById("datum" + id).value;
    reis.id = id;
    console.log(reis);
    // maak een string van het te updaten reis object een stuur dat naar de put functie
    putReis(JSON.stringify(reis));
}

function putDeclaratie(data){
    var api =  "api/DeclaratieForm/" + trainee

    // maak een nieuw request volgens het http protecol
    var xhttp = new XMLHttpRequest();
    console.log(api);
    // als staat van het XMLHTTPRequest object verandert doe dan het volgende
    xhttp.onreadystatechange = function() {
        console.log(this.status)
        if (this.readyState == 4 && this.status == 202) {
            // laad alle declaraties weer opnieuw (ze worden naar de declaraties variable geschreven)
            loadDeclaraties()
        }
    };
    // geef aan dt je data wil gaan updaten in de database
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/open
    xhttp.open("PUT", "http://localhost:8082/"+api);
    xhttp.setRequestHeader("Content-type", "application/json");
    // send request om data te gaan putten
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/send
    xhttp.send(data);
}



function putReis(data){
    // stuur de nieuwe reis op naar de server op het onderstaande adres
    var api =  "api/DeclaratieForm/Reis/" + trainee

    // maak een nieuw request volgens het http protecol
    var xhttp = new XMLHttpRequest();
    console.log(api);
    // als staat van het XMLHTTPRequest object verandert doe dan het volgende
    xhttp.onreadystatechange = function() {
        console.log(this.status)
        if (this.readyState == 4 && this.status == 202) {
            // haal de lijst met reizen opnieuw op uit de database
            loadReizen()
        }
    };
    // geef aan dt je data wil gaan updaten in de database
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/open
    xhttp.open("PUT", "http://localhost:8082/"+api);
    xhttp.setRequestHeader("Content-type", "application/json");
    // send request om data te gaan putten
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/send
    xhttp.send(data);
}



// Maak een nieuwe declaratie aan
function postDeclaratie(){
    // adres van server
    var api =  "api/DeclaratieForm/" + trainee
    // maak nieuw declaratie object
    var declaratie= {};
    declaratie.bedrag = "0";
    declaratie.omschrijving = "omschrijving";

    // maak strin van declaratie object
    data = JSON.stringify(declaratie);
    // maak een nieuw request volgens het http protecol
    var xhttp = new XMLHttpRequest();
    console.log(data);
    // als staat van het XMLHTTPRequest object verandert doe dan het volgende
    xhttp.onreadystatechange = function() {
        console.log(this.status)
        if (this.readyState == 4 && this.status == 202) {
            // haal de lijst van declaraties opnieuw op 
            loadDeclaraties();
        }
    };
    // geef aan dat je data wil gaan posten in de database
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/open
    xhttp.open("POST", "http://localhost:8082/"+api);
    xhttp.setRequestHeader("Content-type", "application/json");
    // send request om data te gaan posten
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/send
    xhttp.send(data);
}


// maakt een lege reis aan een stuur deze naar de server
function postReis(){

    //server adres
    var api =  "api/DeclaratieForm/Reis/" + trainee

    // nieuwe reis maken
    var reis= {};
    reis.van = "van";
    reis.naar = "naar";
    reis.kilometers = 0;
    console.log( new Date());

    // string van het reis object maken
    data = JSON.stringify(reis);
    // maak een nieuw request volgens het http protecol
    var xhttp = new XMLHttpRequest();
    console.log(api);
    // als staat van het XMLHTTPRequest object verandert doe dan het volgende
    xhttp.onreadystatechange = function() {
        console.log(this.status)
        if (this.readyState == 4 && this.status == 202) {
            // link naar functie die reizen lijst opnieuw opvraagd
            loadReizen();
        }
    };
    // geef aan dt je data wil posten in de database
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/open
    xhttp.open("POST", "http://localhost:8082/"+api);
    xhttp.setRequestHeader("Content-type", "application/json");
    // send request om data te gaan putten
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/send
    xhttp.send(data);
}

function putBestand(id){

    //server adres
    var api =  "api/upload/" +  trainee + "/" + id;


    var file = document.getElementById('input' + id).files[0];
    var xhttp = new XMLHttpRequest;

    var formData = new FormData;
    formData.append('multipart/form-data', file);

    xhttp.upload.addEventListener("progress", myProgressHandler, false);
    xhttp.setRequestHeader("Content-type", "multipart/form-data");
    xhttp.addEventListener('load', myOnLoadHandler, false);
    xhttp.open('POST', 'api', true);
    xhttp.send(formData);
}
