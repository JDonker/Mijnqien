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
maand[9] = "october";
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

var datumNu = new Date();

function loadTitle(){
    var title = document.getElementById("titel");
    title.innerHTML = "Urenformulier " + maand[datumNu.getMonth()];
}


function loadtable(){

 //   var headerUrenformulier = document.createElement("header");
 //   headerUrenformulier.innerHTML = "Urenformulier "; //variabele maand moet gelinkt zijn aan de knop waar de user hiervoor op drukt.

    var table = document.createElement("table");
    table.setAttribute("id", "Uren");
    var cellnamen = ["datum", "opdracht", "overwerk", "verlof", "ziek", "training", "overig", "verklaring"];
    th = table.insertRow(-1);
    th.setAttribute("id", 'Urenheader');
    for (var h = 0; h < 8; h++){
        var headerCell = th.insertCell(-1);
        headerCell.setAttribute("id", "HeaderCell");
        headerCell.setAttribute("name", "h" + cellnamen[h] + table.rows.length);
        var htextfield = document.createElement("div");
        htextfield.innerHTML = cellnamen[h];
        headerCell.appendChild(htextfield);
    }
    for (var i = 0; i < aantalDagenPerMaand[datumNu.getMonth()]; i++) { //het getal 31 moet de lengte zijn van de database van de maand.
        tr = table.insertRow(-1);
        var tabCell = tr.insertCell(-1);
        tabCell.setAttribute("name", "datum" + (table.rows.length - 1));
        var textfield = document.createElement("div");
        textfield.setAttribute("id", (table.rows.length - 1) + "datumtext");
        textfield.innerHTML = (table.rows.length - 1) + " " + maand[datumNu.getMonth()];
        tabCell.appendChild(textfield);
        for (var j = 0; j < 7; j++) {
            var tabCell = tr.insertCell(-1);
            tabCell.setAttribute("name", cellnamen[j + 1] + table.rows.length);
            var input1 = document.createElement("input");
            input1.setAttribute("type", "text");
            if(j < 6){
                input1.setAttribute("size", "1");
            }
            tabCell.appendChild(input1);
        } 
    }
    var ding = document.getElementById("hoi");
    ding.appendChild(table)
    loadTitle();
}