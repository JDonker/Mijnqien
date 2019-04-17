var urenformid = 2;

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

var aantalDagenPerMaand = new Array();
aantalDagenPerMaand[0] = 31;
aantalDagenPerMaand[1] = 28;
aantalDagenPerMaand[2] = 31;
aantalDagenPerMaand[3] = 30;
aantalDagenPerMaand[4] = 31;
aantalDagenPerMaand[5] = 30;
aantalDagenPerMaand[6] = 31;
aantalDagenPerMaand[7] = 31;
aantalDagenPerMaand[8] = 30;
aantalDagenPerMaand[9] = 31;
aantalDagenPerMaand[10] = 30;
aantalDagenPerMaand[11] = 31;

var dagenPerWeek = new Array();
dagenPerWeek[0]= "zondag";
dagenPerWeek[1]= "maandag";
dagenPerWeek[2]= "dinsdag";
dagenPerWeek[3]= "woensdag";
dagenPerWeek[4]= "donderdag";
dagenPerWeek[5]= "vrijdag";
dagenPerWeek[6]= "zaterdag";

//var ufDatum = new Date(2020, 1, 2);
var ufDatum = new Date();
var dagenMaand = ufDatum;

function daysInMonth(dagenMaand) {
    console.log(dagenMaand);
    return new Date(dagenMaand.getFullYear(), 
                    dagenMaand.getMonth()+1, 
                    0).getDate();}

var urenperdagen="";

var cellnamen = ["datum", "opdracht", "overwerk", "verlof", "ziek", "training", "overig", "verklaring"];



var datumNu = new Date();

function loadTitle(){
    var title = document.getElementById("titel");
    title.innerHTML = "Urenformulier " + maand[ufDatum.getMonth()] + " " + ufDatum.getFullYear();
}

//Urenformulier datum ophalen
function urenDatum() {  
  
    var tn = new XMLHttpRequest();
    tn.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200) {
        var urenperdag = JSON.parse(this.responseText);
        console.log(urenperdag);
        var month = urenperdag.maand;
        ufDatum = month;
        console.log(JSON.stringify(month));
        var datumUrenForm = new Date(JSON.stringify(month));
         console.log(datumUrenForm.getMonth());
         console.log(datumUrenForm.getFullYear());
//        if (urenperdag.length != 0) {
         console.log(datumUrenForm.getDay());
        if (urenperdag.length != 0) {
          document.getElementById("titel").innerHTML = "Urenformulier " + maand[datumUrenForm.getMonth()] + " " + datumUrenForm.getFullYear();
          //console.log(document.getElementById("titel").innerHTML = "Urenformulier " + maand[datumMaand.getMonth()] + " " + datumJaar.getFullYear());
          console.log(urenperdag[0].datum);
          //console.log(month) 
          //console.log(trainee[0]);
          //console.log(uren)
 //       }
 //       else { 
   //       document.getElementById("titel").innerHTML = "Geen bestaande trainee";
     //   }  
      }
    };
    tn.open("GET", "http://localhost:8082/api/urenform/61", true);
    tn.send();
  
  }


function onload(){
    urenDatum()
    if(urenperdagen.length==0){
        getUren();
    }else{
        urenDatum()
        urenWegschrijven();
    }
}

function urenWegschrijven(){
    loadTitle();
    jsondata = JSON.parse(urenperdagen);
    // console.log(jsondata);

        var table = document.getElementById("UrenTableBody");
        for (var i = 0; i < jsondata.length; i++) { //het getal 31 moet de lengte zijn van de database van de maand.
            var myDate = ufDatum;
            myDate.setDate(i + 1);
            tr = table.insertRow(-1);
            if(myDate.getDay() == 0 || myDate.getDay() == 6){
                tr.setAttribute("id", "weekend");
            }
            // else if((myDate.getMonth() == 0 && myDate.getDate() == 1) || 
            //     (myDate.getMonth() == 3 && (myDate.getDate() == 21 || myDate.getDate() == 22 || myDate.getDate() == 27)) ||
            //     (myDate.getMonth() == 4 && myDate.getDate() == 30) ||
            //     (myDate.getMonth() == 5 && (myDate.getDate() == 9 || myDate.getDate() == 10)) ||
            //     (myDate.getMonth() == 11 && (myDate.getDate() == 25 || myDate.getDate() == 26))){
            //         tr.setAttribute("id", "weekend");
            //     } 
            else{
                tr.setAttribute("id", "row");
            }
            tr.setAttribute("onfocusout","puturen(" + jsondata[i]["id"] + ")");
  //          tr.setAttribute("onfocusout", "puturen(" + jsondata[i]["id"] + ")");
            var tabCell = tr.insertCell(-1);
            tabCell.setAttribute("id", "datum" + jsondata[i]["id"]);
            var textfield = document.createElement("div");
            textfield.setAttribute("id", jsondata[i]["id"] + "datumtext");
            textfield.innerHTML = dagenPerWeek[myDate.getDay()] + " " + (i+1) + " " + maand[myDate.getMonth()];
            tabCell.appendChild(textfield);
            for (var j = 0; j < 7; j++) {
                var tabCell = tr.insertCell(-1);
                var input1 = document.createElement("input");
                input1.setAttribute("type", "text");
                input1.setAttribute("id", cellnamen[j + 1] + jsondata[i]["id"]);
                input1.value=jsondata[i][cellnamen[j + 1]];
                             jsondata[i][cellnamen[j + 1]]
                if(j < 6){
                    input1.setAttribute("size", "1");
                }
                tabCell.appendChild(input1);
            } 
 //       }
 //   } else {
 //       getUren();
 //   }
}
}

function puturen(id){
    var urenperdag = {};
    urenperdag.datum = document.getElementById(id+"datumtext").innerText;
    urenperdag.opdracht = document.getElementById("opdracht" + id).value;
    urenperdag.overwerk = document.getElementById("overwerk" + id).value;
    urenperdag.verlof = document.getElementById("verlof" + id).value;
    urenperdag.ziek = document.getElementById("ziek" + id).value;
    urenperdag.training = document.getElementById("training" + id).value;
    urenperdag.overig = document.getElementById("overig" + id).value;
    urenperdag.verklaringOverig = document.getElementById("verklaring" + id).value;
    urenperdag.id = id;
    putUren(JSON.stringify(urenperdag));
        
    }

function putUren(data){
    var api =  "api/urenperdag/" + urenformid;
    var xhttp = new XMLHttpRequest();
    console.log(api);
    xhttp.onreadystatechange = function() {
        console.log(this.status)
        if (this.readyState == 4){
            if (this.status == 202){
            }
        }
    };
    // geef aan dt je data wil gaan pakken uit de database
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/open
    xhttp.open("PUT", "http://localhost:8082/"+api);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.setRequestHeader("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ=");
    xhttp.withCredentials = true;
    // send request om data te gaan putten
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/send
    xhttp.send(data);
}

function posturen(){
        for (var i = 1; i <= daysInMonth(ufDatum); i++){
            var urenperdag = {};

            urenperdag.datum = document.getElementById(i+"datumtext").innerText;
            urenperdag.opdracht = document.getElementById("opdracht" + i).value;
            urenperdag.overwerk = document.getElementById("overwerk" + i).value;
            urenperdag.verlof = document.getElementById("verlof" + i).value;
            urenperdag.ziek = document.getElementById("ziek" + i).value;
            urenperdag.training = document.getElementById("training" + i).value;
            urenperdag.overig = document.getElementById("overig" + i).value;
            urenperdag.verklaringOverig = document.getElementById("verklaring" + i).value;
            urenperdag.id = i;
            postUren(JSON.stringify(urenperdag));
    }
}

function postUren(data){
     var api =  "api/urenperdag/";

    // maak een nieuw request volgens het http protecol
    var xhttp = new XMLHttpRequest();
    console.log(api);
    // als staat van het XMLHTTPRequest object verandert doe dan het volgende
    xhttp.onreadystatechange = function() {
        console.log(this.status)
        if (this.readyState == 4) {
            if (this.status == 200) {
                var jsondata = JSON.parse(this.responseText);
                if (jsondata.length>0){

                }
                // haal de lijst met reizen opnieuw op uit de database
            //    loadReizen()
            } 
            // else {
            //     alert("Er gaat iets mis " + this.status )
            // }
        }
    };
    // geef aan dt je data wil gaan updaten in de database
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/open
    xhttp.open("POST", "http://localhost:8082/"+api);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.setRequestHeader("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ=");
    xhttp.withCredentials = true;
    // send request om data te gaan putten
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/send
    xhttp.send(data);
}



function getUren(){
    var api =  "api/urenperdag";
    // maak een nieuw request volgens het http protecol
    var xhttp = new XMLHttpRequest();
    console.log(api);
    // als staat van het XMLHTTPRequest object verandert doe dan het volgende
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4){
            if (this.status == 200){

                console.log(JSON.parse(this.responseText));

                var jsondata = JSON.parse(this.responseText);
                console.log(jsondata);


                console.log(jsondata.length)

                if (jsondata.length < daysInMonth(dagenMaand)) {
                    jsondata = [];
                    for (var i = 0; i < daysInMonth(dagenMaand); i++) {
                        
                        x = {};
                        var newDate = new Date();
                        newDate.setDate(i+1);
                        x.datum = newDate;
                        x.id = i;
                        x.opdracht = 0
                        x.overig = 0
                        x.overwerk= 0
                        x.training= 0
                        x.verklaring= ""
                        x.verlof= 
                        x.ziek= 0

                        jsondata[i] = x;
                        postUren(JSON.stringify(x));

                    }      
                    urenperdagen = JSON.stringify(jsondata);
 
                
                } else if (urenperdagen!=this.responseText) {
                    urenperdagen = this.responseText;
                }
                onload();
            }
        }
            
        } 
    // geef aan dt je data wil gaan pakken uit de database
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/open
    xhttp.open("GET", "http://localhost:8082/"+api);
    // xhttp.setRequestHeader("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ=");
    // xhttp.withCredentials = true;
    // send request om data te gaan getten body wordt genegeerd
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/send
    xhttp.send();
}


function verzendUrenform(){
    var api =  "api/urenperdag/" + urenformid;
    var xhttp = new XMLHttpRequest();
    console.log(api);
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4){}}
}


//Html export naar .xls - Uren
function downloadUren() {
  var tab_text = '<html xmlns:x="urn:schemas-microsoft-com:office:excel">';
  tab_text = tab_text + '<head><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet>';

  tab_text = tab_text + '<x:Name>Test Sheet</x:Name>';

  tab_text = tab_text + '<x:WorksheetOptions><x:Panes></x:Panes></x:WorksheetOptions></x:ExcelWorksheet>';
  tab_text = tab_text + '</x:ExcelWorksheets></x:ExcelWorkbook></xml></head><body>';

  tab_text = tab_text + "<table border='1px'>";
  tab_text = tab_text + $('#Uren').attr('value', function() {
    return $(this).val();
  });
  tab_text = tab_text + '</table></body></html>';

  var data_type = 'data:application/vnd.ms-excel';

  var ua = window.navigator.userAgent;
  var msie = ua.indexOf("MSIE");

  if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) {
    if (window.navigator.msSaveBlob) {
      var blob = new Blob([tab_text], {
        type: "application/csv;charset=utf-8;"
      });
      navigator.msSaveBlob(blob, 'Test file.xls');
    }
  } else {
    $('#downloadUren').attr('href', data_type + ', ' + encodeURIComponent(tab_text));
    $('#downloadUren').attr('download', 'Test file.xls');
  }

}