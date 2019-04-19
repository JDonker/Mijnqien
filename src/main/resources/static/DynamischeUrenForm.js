var urenformid = 1;

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
var dagenMaand;

function daysInMonth(dagenMaand) {
  //  console.log(dagenMaand);
    return new Date(dagenMaand.getFullYear(), 
                    dagenMaand.getMonth()+1, 
                    0).getDate();}

var urenperdagen="";

var cellnamen = ["datum", "opdracht", "overwerk", "verlof", "ziek", "training", "overig", "verklaring"];

var idurenformulier="";

var datumNu = new Date();

//Urenformulier datum ophalen
function urenDatum() {  
  
    var tn = new XMLHttpRequest();
    tn.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200) {
        var urenform = JSON.parse(this.responseText);
        console.log(urenform);
        var month = urenform.maand;
        ufDatum = new Date(month);
        dagenMaand = new Date(month);
        console.log(ufDatum);
        var datumUrenForm = new Date(JSON.stringify(month));
          document.getElementById("titel").innerHTML = "Urenformulier " + maand[datumUrenForm.getMonth()] + " " + datumUrenForm.getFullYear();
      }
    };
    tn.open("GET", "http://localhost:8082/api/urenform/dummy/" + urenformid);
    tn.send();
  
  }



function onload(){
    if(urenperdagen.length==0){
        urenDatum();
        getUren();
    }else{
        urenWegschrijven();
    }
}

function getUren(){
    var api =  "api/urenperdag";
    // maak een nieuw request volgens het http protecol
    var xhttp = new XMLHttpRequest();
    console.log(api);
    // als staat van het XMLHTTPRequest object verandert doe dan het volgende
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200){

                var jsondata = JSON.parse(this.responseText);
                console.log(jsondata);

                if (jsondata.length < daysInMonth(dagenMaand)) {
                    jsondata = [];
                    for (var i = 0; i < daysInMonth(dagenMaand); i++) {
                        
                        x = {};
                        ufDatum.setDate(i+1);
                        // console.log(ufDatum);
                        x.datum = ufDatum;
                        x.urenformid = urenformid;
                        x.opdracht = 0;
                        x.overig = 0;
                        x.overwerk= 0;
                        x.training= 0;
                        x.verklaring= "";
                        x.verlof= 0;
                        x.ziek= 0;

                        jsondata[i] = x;
                        postUren(JSON.stringify(x));

                    }      
                    urenperdagen = JSON.stringify(jsondata);
                    // console.log(urenperdagen);
 
                
                } else if (urenperdagen!=this.responseText) {
                    urenperdagen = this.responseText;
                }
                onload();
        }
            
        } 

    xhttp.open("GET", "http://localhost:8082/"+api);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function urenWegschrijven(){
    jsondata = JSON.parse(urenperdagen);
   // console.log(urenperdagen);
    // console.log(jsondata);

        var table = document.getElementById("UrenTableBody");
        for (var i = 0; i < jsondata.length; i++) { //het getal 31 moet de lengte zijn van de database van de maand.
            var myDate = ufDatum;
            myDate.setDate(i + 1);
           // console.log(myDate);
            tr = table.insertRow(-1);

            if((myDate.getMonth() == 0 && myDate.getDate() == 1) || 
                 (myDate.getMonth() == 3 && (myDate.getDate() == 21 || myDate.getDate() == 22 || myDate.getDate() == 27)) ||
                 (myDate.getMonth() == 4 && myDate.getDate() == 30) ||
                 (myDate.getMonth() == 5 && (myDate.getDate() == 9 || myDate.getDate() == 10)) ||
                 (myDate.getMonth() == 11 && (myDate.getDate() == 25 || myDate.getDate() == 26))){
                     tr.setAttribute("id", "vakantie");
                     tr.setAttribute("onfocusout", "feestdagalert()");
                }
                else if(myDate.getDay() == 0 || myDate.getDay() == 6){
                tr.setAttribute("id", "weekend");
                tr.setAttribute("onfocusout", "weekendalert()");
            } 
            else{
                tr.setAttribute("id", "row");
                 tr.setAttribute("onfocusout","puturen(" + jsondata[i]["id"] + ")");
            }

            var tabCell = tr.insertCell(-1);
            tabCell.setAttribute("id", "datum" + jsondata[i]["id"]);
            var textfield = document.createElement("div");
            textfield.setAttribute("id", jsondata[i]["id"] + "datumtext");
            textfield.innerHTML = dagenPerWeek[myDate.getDay()] + " " + (i+1) + " " + maand[myDate.getMonth()];
            var datumAlsDate = [];
            for (var j = 0; j < jsondata.length; j++){
                datumAlsDate[j] = myDate;
            }
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

function weekendalert(){
    alert("Let op: je vult nu uren in op een dag in het weekend!");
}

function feestdagalert(){
    alert("Let op: je vult nu uren in op een feestdag!");
}

function puturen(id){
    jsondata = JSON.parse(urenperdagen);
    var urenperdag = {};
     for(var j = 0; j < jsondata.length; j++){
         if (jsondata[j]["id"]==id){
             urenperdag.datum = jsondata[j]["datum"]
         }
    }
    urenperdag.opdracht = document.getElementById("opdracht" + id).value;
    urenperdag.overwerk = document.getElementById("overwerk" + id).value;
    urenperdag.verlof = document.getElementById("verlof" + id).value;
    urenperdag.ziek = document.getElementById("ziek" + id).value;
    urenperdag.training = document.getElementById("training" + id).value;
    urenperdag.overig = document.getElementById("overig" + id).value;
    urenperdag.verklaringOverig = document.getElementById("verklaring" + id).value;
    urenperdag.id = id;
    putUren(id, JSON.stringify(urenperdag));
        
    }

function putUren(id, data){
    var api =  "api/urenperdag/dummy/" + id;
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
    // send request om data te gaan putten
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/send
    xhttp.send(data);
}

function posturen(){
        for (var i = 1; i <= daysInMonth(ufDatum); i++){
            var urenperdag = {};

            console.log(urenperdag.datum = document.getElementById(i+"datumtext").value);
            urenperdag.opdracht = document.getElementById("opdracht" + i).value;
            urenperdag.overwerk = document.getElementById("overwerk" + i).value;
            urenperdag.verlof = document.getElementById("verlof" + i).value;
            urenperdag.ziek = document.getElementById("ziek" + i).value;
            urenperdag.training = document.getElementById("training" + i).value;
            urenperdag.overig = document.getElementById("overig" + i).value;
            urenperdag.verklaringOverig = document.getElementById("verklaring" + i).value;
            urenperdag.formid = i;
    
            postUren(JSON.stringify(urenperdag));
    }
}

function postUren(data){
     var api =  "api/urenperdag/";

    // maak een nieuw request volgens het http protecol
    var xhttp = new XMLHttpRequest();
    // console.log(api);
    // als staat van het XMLHTTPRequest object verandert doe dan het volgende
    xhttp.onreadystatechange = function() {
        // console.log(this.status)
        if (this.readyState == 4 && this.status == 202) {
            console.log("goede status"); 
        }
    }
    // geef aan dt je data wil gaan updaten in de database
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/open
    xhttp.open("POST", "http://localhost:8082/"+api);
    xhttp.setRequestHeader("Content-type", "application/json");
    // send request om data te gaan putten
    // https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/send
    xhttp.send(data);
}





var stat = 0;
var id = 0;
function statusWijzigen(id){
  var urenFormNieuw = {};
  urenFormNieuw.id = id;
  switch(stat){
      case 0:
      urenFormNieuw.stat = 1;
      break;
      case 2:
      urenFormNieuw.stat = 1;
      break;
      default:
      console.log("een urenform met deze status hoort niet bij de trainee te zijn");
      break;
  }
  console.log(urenFormNieuw);
  verzendUrenForm(id, JSON.stringify(urenFormNieuw));
  
}

var invoerString = "";

function verzendUrenForm(id, invoerString){
    var api =  "api/urenform/verwerk/" + id;
    var xhttp = new XMLHttpRequest();
    console.log(api);
      xhttp.onreadystatechange = function() {
          console.log(this.status);
        if (this.readyState == 4 && this.status == 202){

        }
    }
    xhttp.open("PUT", "http://localhost:8082/"+api);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(invoerString);
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