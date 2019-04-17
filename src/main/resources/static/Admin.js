var input = 0;
var trainee = 0;
declaratieForms = [];

var maand = new Array();
maand[0] = "januari";
maand[1] = "februari";
maand[2] = "maart";
maand[3] = "april";
maand[4] = "mei";
maand[5] = "juni";
maand[6] = "juli";
maand[7] = "augustus";
maand[8] = "september";
maand[9] = "oktober";
maand[10] = "november";
maand[11] = "december";


function Klik() {
  if (document.getElementById("demo").innerHTML == "Bewerk") {
    document.getElementById("demo").innerHTML = "Geklikt";
  } else {
    document.getElementById("demo").innerHTML = "Bewerk";
  }
}

$(document).ready(function () {
  $('.Declaratieformulier').hide();
});

$(document).ready(function () {
  $("#Urenformulier").click(function () {
    $(".Urenformulier, .Declaratieformulier").toggle(1000);
    $(this).text(function (i, v) {
      return v === 'Uren' ? 'Declaraties' : 'Uren'
    })
    $(this).text(function (i, v) {
      return c === 'Uren' ? 'Declaraties' : 'Uren'
    })
  });
});

// Bar chart
new Chart(document.getElementById("bar-chart"), {
  type: 'bar',
  data: {
    labels: ["Totaal gewerkte uren", "Totaal ziekteverzuim", "optie 3", "optie 4", "optie 5"],
    datasets: [
      {
        label: "Urenoverzicht",
        backgroundColor: ["#3cba9f", "#3cba9f", "#3cba9f", "#3cba9f", "#3cba9f"],
        data: [1000, 80, 344, 804, 633]
      }
    ]
  },
  options: {
    legend: { display: false },
    title: {
      display: true,
      text: 'Overzicht in uren'
    }
  }
  
});

function wachtwoordTonen() {
  var ww = document.getElementById("wachtwoord");
  if (ww.type === "password") {
    ww.type = "text";
  } else {
    ww.type = "password";
  }
}


function capsTest(event) {
  var x = event.getModifierState("CapsLock");

  if (x == true) {
    document.getElementById("caps").innerHTML = "Capslock staat aan";
  } else {
    document.getElementById("caps").innerHTML = "";
  }
}

function emailCheck(str) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  if (!re.test(str)) {
    document.getElementById("email").innerHTML = "Voer een geldig e-mailadres in";
  } else {
    document.getElementById("email").innerHTML = "";
  }
}




function onload() {
  // console.log(declaratieForms.length);
  if(declaratieForms.length==0) {
      // declaratieforms nog niet opgehaald haal ze nu op
      loadDeclaratieForms();
  } else {
      // maak declaratieform tabel
      DeclaratieFormsWegschrijven();
  }

}

function loadDeclaratieForms(){
  // Dit is het adres waar de declaratieforms vandaan kunnen wordt opgevraagd
  var api =  "api/DeclaratieForm/";
  // maak een nieuw request volgens het http protecol
  var xhttp = new XMLHttpRequest();
  console.log(api);
  // als staat van het XMLHTTPRequest object verandert doe dan het volgende
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          // als de string  is aangepast ga er dan mee aan de slag
          if (declaratieForms!=this.responseText) {
              // schrijf de jsonstring die je van de server krijt naar de globale variable declaratieforms
              declaratieForms = this.responseText;
              console.log("nu hier");
              // herlaad de pagina
              onload();
          }
      }
    };
  // geef aan dt je data wil gaan pakken uit de database
  // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/open
  xhttp.open("GET", "http://localhost:8082/"+api);
  xhttp.setRequestHeader("Content-type", "application/json");
  // xhttp.withCredentials = true;
  // xhttp.setRequestHeader('Authorization', 'Basic dXNlcm5hbWU6cGFzc3dvcmQ=');
  // send request om data te gaan getten body wordt genegeerd
  // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/send
  xhttp.send();
}

// code om de tabel declaratieforms op te maken
function DeclaratieFormsWegschrijven(){

  // vraag de body van de declaratieform tabel op en gooi hem leeg
  var table = document.getElementById("DeclaratieFormTableBody");
  table.innerHTML="";

  // declaratieform string met alle objecten is een globale variabel hier maak je er een lijst met jsonobjecten van
  jsondata =JSON.parse(declaratieForms);
  //console.log(declaratieForms)
  // loop over de collomen van de jsontabel
  for (var i = 0; i < jsondata.length; i++) {
      // voeg nieuwe rij toe
      var row = table.insertRow(-1);
      for(var k=1;k<5;k++){
          // voeg een cell aan de rij toe
          var cellDeclaratieForm = row.insertCell(-1);
          // switch op het rij nummer 
          switch(k){
              case 1: 
                cellDeclaratieForm.innerHTML=jsondata[i]["naam"];
                break;
              case 2:
                var DeclaratieFormMaand = new Date(jsondata[i]["maand"]);
                cellDeclaratieForm.innerHTML=maand[DeclaratieFormMaand.getMonth()];
                break; 
              case 3:
                  cellDeclaratieForm.innerHTML="Declaratieformulier";
                  break;
              case 4: 
              if(jsondata[i]["status"] == "INAFWACHTING"){
                cellDeclaratieForm.innerHTML = "In Afwachting";
              }else if(jsondata[i]["status"] == "INGEDIEND"){
                cellDeclaratieForm.innerHTML = "Ingediend";
              }else if(jsondata[i]["status"] == "WIJZIGEN"){
                cellDeclaratieForm.innerHTML = "Wijzigen";
              }else if(jsondata[i]["status"] == "GOEDGEKEURD"){
                cellDeclaratieForm.innerHTML = "Goedgekeurd";
              }else{
                cellDeclaratieForm.innerHTML = "foutje";
              }
              
              cellDeclaratieForm.id="status" + i;
                break;
              default:
                  break;
          }

      }
      var cellDeclaratieForm = row.insertCell(-1);
      var dropdown = document.createElement("div");
      dropdown.className = "dropdown";
      var btn = document.createElement("button");

      btn.className = "dropbtn";
      btn.innerHTML="Bewerken";

      myFunctionString = "myFunction(" + (i+1) + ")";
      btn.setAttribute("onclick", myFunctionString);

      var dropdown_content = document.createElement("div");
      dropdown_content.className = "dropdown-content";
      var dropdown_contentIdString = "dropdown-test" + (i+1);
      dropdown_content.id = dropdown_contentIdString;

      var keuzeMakenString1 = "keuzeMaken(" + (i+1) + ", 1)";
      var keuzeMakenString2 = "keuzeMaken(" + (i+1) + ", 2)";
      var keuzeMakenString3 = "keuzeMaken(" + (i+1) + ", 3)";
      var keuzeMakenString4 = "keuzeMaken(" + (i+1) + ", 4)";

      var optie1 = document.createElement("a");
      optie1.innerHTML = "In afwachting";
      optie1.setAttribute("onclick", keuzeMakenString1);

      var optie2 = document.createElement("a");
      optie2.innerHTML = "Ingediend";
      optie2.setAttribute("onclick", keuzeMakenString2);

      var optie3 = document.createElement("a");
      optie3.innerHTML = "Wijzigen";
      optie3.setAttribute("onclick", keuzeMakenString3);

      var optie4 = document.createElement("a");
      optie4.innerHTML = "Goedgekeurd";
      optie4.setAttribute("onclick", keuzeMakenString4);

      dropdown_content.appendChild(optie1);
      dropdown_content.appendChild(optie2);
      dropdown_content.appendChild(optie3);
      dropdown_content.appendChild(optie4);

      btn.appendChild(dropdown_content);
      dropdown.appendChild(btn);
     cellDeclaratieForm.appendChild(dropdown);
 
  }
}

function myFunction(input2) {
  var deId2 = "dropdown-test" + input2.toString(10);
  var x = document.getElementById(deId2);
    if (x.style.display === "none") {
      x.style.display = "block";
    } else {
      x.style.display = "none";
    }
  }

var inputId = 0;
var inputKeuze = 0;
function keuzeMaken(inputId, inputKeuze){
  var statusIdString = "status" + inputId;
  console.log(statusIdString);
  switch(inputKeuze){
    case 1:
    statusWijzigen(inputId, 0);
    break;
    case 2:
    statusWijzigen(inputId, 1);
    break;
    case 3:
    statusWijzigen(inputId, 2);
    break;
    case 4:
    statusWijzigen(inputId, 3);
    break;

  }
}


var statusWijziging = 0;
var id = 0;
function statusWijzigen(id, statusWijziging){
  var declaratieFormNieuw = {};
  declaratieFormNieuw.id = id;
  declaratieFormNieuw.status = statusWijziging;
  console.log(declaratieFormNieuw);
  putDeclaratieForm(id, JSON.stringify(declaratieFormNieuw));
  
}
invoerString = "";
function putDeclaratieForm(id, invoerString){
  var api =  "api/DeclaratieForm/verwerk/" + id;
  console.log(api);
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
           console.log(this.status)
           if (this.readyState == 4 && this.status == 202) {
    //           // laad alle declaratieforms weer opnieuw (ze worden naar de declaraties variable geschreven)
           loadDeclaratieForms();
           }
  }
    xhttp.open("PUT", "http://localhost:8082/"+api);
    xhttp.setRequestHeader("Content-type", "application/json");
    // xhttp.withCredentials = true;
    // xhttp.setRequestHeader('Authorization','Basic dXNlcm5hbWU6cGFzc3dvcmQ=');
    xhttp.send(invoerString);
}