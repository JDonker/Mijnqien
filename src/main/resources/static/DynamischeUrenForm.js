function loadtable(){
    // maak een tabel element aan
    var table = document.createElement("table");

    // json data in de tabel gooien
    for (var i = 0; i < 31; i++) { // loop over de entities
        // voeg een rij toe (-1 voor achteraan in de tabel)
        tr = table.insertRow(-1);
        // cellen toevegen aan een rij en de cellen vullen
        for (var j = 0; j < 8; j++) { // loop over de keys
        var tabCell = tr.insertCell(-1); // alternatieve methode om cell toe tevoegen document.createElement("td") kan ook
        tabCell.innerHTML = "hoi"; // lekker over de data loopen
        }
    }
                            var ding =		document.getElementById("hoi");
        ding.appendChild(table)
}