var start2019 = new Date(2019, 0, 1);

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

var startMaand = maand[start2019.getMonth()];
var startDag = start2019.getDate();

var jaarIndex = 2019;
var maandIndex = 0;
var dagIndex = 1;
var gewensteDatum = new Date(jaarIndex, maandIndex, dagIndex);
var gewensteMaand = maand[gewensteDatum.getMonth()];
var gewensteDag = gewensteDatum.getDate();
var gewenstJaar = gewensteDatum.getFullYear();

function starten(){
    alert(startDag + " " + startMaand);
}

function toonGewensteDatum(){
     gewensteDag = gewensteDatum.getDate();
     gewensteMaand = maand[gewensteDatum.getMonth()];
     gewenstJaar = gewensteDatum.getFullYear();
        alert(gewensteDag + " " + gewensteMaand + " " + gewenstJaar);
}

function volgendeMaand(){
    maandIndex++;
    gewensteDatum.setMonth(maandIndex);
    toonGewensteDatum();
}

function volgendeDag(){
    dagIndex++;
    gewensteDatum.setDate(dagIndex);
    toonGewensteDatum();
}
function volgendJaar(){
    jaarIndex++;
    gewensteDatum.setFullYear(jaarIndex);
    toonGewensteDatum();
}