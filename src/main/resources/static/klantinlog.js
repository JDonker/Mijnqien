urenForms = [];

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

function logout() {
    localStorage.clear();
    location.href = "logout";
}

function onload(){
    if (urenForms.length == 0) {
    // declaratieforms nog niet opgehaald haal ze nu op
    loadUrenForms();
  } else {
    // maak declaratieform tabel
    UrenFormsWegschrijven();
  }
}

function loadUrenForms() {
    var api = "api/urenform/admin/ingediend";
    var xhttp = new XMLHttpRequest();
    console.log(api);
  
    xhttp.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200) {
        if (urenForms != this.responseText) {
          urenForms = this.responseText;
          console.log("nu hier");
          onload();
        }
      }
    }
    xhttp.open("GET", "http://localhost:8082/" + api);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
  }

  function UrenFormsWegschrijven() {

    var table = document.getElementById("UrenFormTableBody");
    table.innerHTML = "";
  
    jsondata = JSON.parse(urenForms);
  
    for (var i = 0; i < jsondata.length; i++) {
  
      var row = table.insertRow(-1);
      for (var k = 1; k < 5; k++) {
  
        var cellUrenForm = row.insertCell(-1);
  
        switch (k) {
          case 1:
            cellUrenForm.innerHTML = jsondata[i]["naam"];
            break;
          case 2:
            var UrenFormMaand = new Date(jsondata[i]["maand"]);
            cellUrenForm.innerHTML = maand[UrenFormMaand.getMonth()];
            break;
          case 3:
            cellUrenForm.innerHTML = "Urenformulier";
            break;
          case 4:
            if (jsondata[i]["stat"] == "INAFWACHTING") {
              cellUrenForm.innerHTML = "In Afwachting";
            } else if (jsondata[i]["stat"] == "INGEDIEND") {
              cellUrenForm.innerHTML = "Ingediend";
            } else if (jsondata[i]["stat"] == "WIJZIGEN") {
              cellUrenForm.innerHTML = "Wijzigen";
            } else if (jsondata[i]["stat"] == "GOEDGEKEURD") {
              cellUrenForm.innerHTML = "Goedgekeurd";
            } else {
              cellUrenForm.innerHTML = "foutje";
            }
  
            cellUrenForm.id = "status" + i;
            break;
          default:
            break;
        }
  
      }
      var cellUrenForm = row.insertCell(-1);
      var dropdown2 = document.createElement("div");
      dropdown2.className = "dropdown";
      var btn2 = document.createElement("button");
  
      btn2.className = "dropbtn";
      btn2.innerHTML = "Goedkeuren";
  
      myFunctionString2 = "myFunction(" + (i + 1) + ")";
      btn2.setAttribute("onclick", myFunctionString2);
  
      var dropdown_content2 = document.createElement("div");
      dropdown_content2.className = "dropdown-content";
      var dropdown_contentIdString2 = "dropdown-test" + (i + 1);
      dropdown_content2.id = dropdown_contentIdString2;
  
      var keuzeMakenString5 = "keuzeMaken(" + (i + 1) + ", 1)";
      var keuzeMakenString6 = "keuzeMaken(" + (i + 1) + ", 2)";
      var keuzeMakenString7 = "keuzeMaken(" + (i + 1) + ", 3)";
      var keuzeMakenString8 = "keuzeMaken(" + (i + 1) + ", 4)";
  
      var optie5 = document.createElement("a");
      optie5.innerHTML = "In afwachting";
      optie5.setAttribute("onclick", keuzeMakenString5);
  
      var optie6 = document.createElement("a");
      optie6.innerHTML = "Ingediend";
      optie6.setAttribute("onclick", keuzeMakenString6);
  
      var optie7 = document.createElement("a");
      optie7.innerHTML = "Wijzigen";
      optie7.setAttribute("onclick", keuzeMakenString7);
  
      var optie8 = document.createElement("a");
      optie8.innerHTML = "Goedgekeurd";
      optie8.setAttribute("onclick", keuzeMakenString8);
  
      dropdown_content2.appendChild(optie5);
      dropdown_content2.appendChild(optie6);
      dropdown_content2.appendChild(optie7);
      dropdown_content2.appendChild(optie8);
  
      btn2.appendChild(dropdown_content2);
      dropdown2.appendChild(btn2);
      cellUrenForm.appendChild(dropdown2);
  
    }
  }

//Tabel sorteren door op de header van een kolom te klikken
function sortTable(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("Uren");
    switching = true;
    //Set the sorting direction to ascending:
    dir = "asc";
    /*Make a loop that will continue until
    no switching has been done:*/
    while (switching) {
      //start by saying: no switching is done:
      switching = false;
      rows = table.rows;
      /*Loop through all table rows (except the
      first, which contains table headers):*/
      for (i = 1; i < (rows.length - 1); i++) {
        //start by saying there should be no switching:
        shouldSwitch = false;
        /*Get the two elements you want to compare,
        one from current row and one from the next:*/
        x = rows[i].getElementsByTagName("TD")[n];
        y = rows[i + 1].getElementsByTagName("TD")[n];
        /*check if the two rows should switch place,
        based on the direction, asc or desc:*/
        if (dir == "asc") {
          if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
            //if so, mark as a switch and break the loop:
            shouldSwitch = true;
            break;
          }
        } else if (dir == "desc") {
          if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
            //if so, mark as a switch and break the loop:
            shouldSwitch = true;
            break;
          }
        }
      }
      if (shouldSwitch) {
        /*If a switch has been marked, make the switch
        and mark that a switch has been done:*/
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
        //Each time a switch is done, increase this count by 1:
        switchcount++;
      } else {
        /*If no switching has been done AND the direction is "asc",
        set the direction to "desc" and run the while loop again.*/
        if (switchcount == 0 && dir == "asc") {
          dir = "desc";
          switching = true;
        }
      }
    }
  }

  function doorzoekTabelUren() {

    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("inputUrenTabel");
    filter = input.value.toUpperCase();
    table = document.getElementById("Uren");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
      td = tr[i].getElementsByTagName("td")[0];
      if (td) {
        txtValue = td.textContent || td.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
          tr[i].style.display = "";
        } else {
          tr[i].style.display = "none";
        }
      }
    }
  }