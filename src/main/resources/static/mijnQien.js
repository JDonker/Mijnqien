// //trainee voornaam ophalen
// function trainee1() {
//   var number;
//   var tn = new XMLHttpRequest();
//   tn.onreadystatechange = function () {
//       if (this.readyState == 4 && this.status == 200) {
//           var trainee = JSON.parse(this.responseText);
//           if(trainee.length != 0){
//           document.getElementById("trainee1").innerHTML = "Voornaam: " + trainee[0].voornaam;
//           number = traineesTest[i].personeelsnummer;
//           //console.log(apk);
//           //console.log(trainee[0]);
//           }
//           else{
//               document.getElementById("trainee1").innerHTML = "Geen bestaande trainee";
//           }
          

        
//       }
//   };
//   tn.open("GET", "http://localhost:8082/api/trainee", true);
//   tn.send();

// }





function showTrainees() {
  rebuildTraineeLijst();
  GrafiekData()
}



function rebuildTraineeLijst() {
  var xhttp = new XMLHttpRequest();

  xhttp.onreadystatechange = function () {

      if (this.readyState == 4) {
          if (this.status == 200) {
              document.getElementById("trainee2").innerHTML = "";

              

              var auto = JSON.parse(this.responseText);


              var table = document.createElement("table");
              table.setAttribute("id", "testTabel");
              // createNewAutoTableHeader();
              addHtmlElement(
                  table,
                  autoTableHeader());

              var tbody = addHtmlElement(table, document.createElement("tbody"));
              for(var i = 0; i< auto.length; i++) {
                  addHtmlElement(tbody, autoTableRow(auto[i]));
              }

              document.getElementById("trainee2").appendChild(table);
              
             
          } else {
              alert(this.statusText)
          }
      }
  };

  xhttp.open("GET", "http://localhost:8082/api/trainee", true);
  xhttp.send();
}

function autoTableHeader() {

{/* <input type="text" id="myInput" onkeyup="doorzoekTabel()" placeholder="Zoek op naam"></input> */}
//input (zoek)veld aanmaken in de table header
  var zoekVeld = document.createElement("input");
  zoekVeld.type = "text";
  zoekVeld.placeholder = "Zoek op naam";
  zoekVeld.id = "myInput";
  zoekVeld.addEventListener("keyup", doorzoekTabel);

  var tableHeader = document.createElement("thead");
  var tr = addHtmlElement(tableHeader, document.createElement("tr"));
  addHtmlElementContent(tr, document.createElement("th"), "Id");
  addHtmlElementContent(tr, document.createElement("th"), "Achternaam").appendChild(zoekVeld);
  addHtmlElementContent(tr, document.createElement("th"), "Personeelsnummer");
  addHtmlElementContent(tr, document.createElement("th"), "Tussenvoegsel");
  addHtmlElementContent(tr, document.createElement("th"), "Voornaam");
  return tableHeader;
}



function autoTableRow(auto) {
  var tr = document.createElement("tr");
  addHtmlElementContent(tr, document.createElement("td"), auto.id);
  addHtmlElementContent(tr, document.createElement("td"), auto.achternaam);
  addHtmlElementContent(tr, document.createElement("td"), auto.personeelsnummer);
  addHtmlElementContent(tr, document.createElement("td"), auto.tussenvoegsel);
  addHtmlElementContent(tr, document.createElement("td"), auto.voornaam);

  return tr;
}

function addHtmlElement(parent, child) {
  parent.appendChild(child);
  return child;
}

function addHtmlElementContent(parent, child, tekst) {
  parent.appendChild(child);
  child.innerHTML = tekst;
  return child;
}

function showIt(event) {
  console.log(event);
}

//Html export to .xls
function fnExcelReport() {
  var tab_text = '<html xmlns:x="urn:schemas-microsoft-com:office:excel">';
  tab_text = tab_text + '<head><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet>';

  tab_text = tab_text + '<x:Name>Test Sheet</x:Name>';

  tab_text = tab_text + '<x:WorksheetOptions><x:Panes></x:Panes></x:WorksheetOptions></x:ExcelWorksheet>';
  tab_text = tab_text + '</x:ExcelWorksheets></x:ExcelWorkbook></xml></head><body>';

  tab_text = tab_text + "<table border='1px'>";
  tab_text = tab_text + $('#testTabel').html();
  tab_text = tab_text + '</table></body></html>';

  var data_type = 'data:application/vnd.ms-excel';

  var ua = window.navigator.userAgent;
  var msie = ua.indexOf("MSIE ");

  if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) {
      if (window.navigator.msSaveBlob) {
          var blob = new Blob([tab_text], {
              type: "application/csv;charset=utf-8;"
          });
          navigator.msSaveBlob(blob, 'Test file.xls');
      }
  } else {
      $('#testButton').attr('href', data_type + ', ' + encodeURIComponent(tab_text));
      $('#testButton').attr('download', 'Test file.xls');
  }

}

//Login wachtwoord tonen
function wachtwoordTonen() {
  var ww = document.getElementById("wachtwoord");
  if (ww.type === "password") {
    ww.type = "text";
  } else {
    ww.type = "password";
  }
}

//Login capslock melding
function capsTest(event) {
  var x = event.getModifierState("CapsLock");

  if (x == true) {
    document.getElementById("caps").innerHTML = "Capslock staat aan";
  } else {
    document.getElementById("caps").innerHTML = "";
  }
}

//Login e-mail syntax check
function emailCheck(str) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  if (!re.test(str)) {
    document.getElementById("email").innerHTML = "Voer een geldig e-mailadres in";
  } else {
    document.getElementById("email").innerHTML = "";
  }
}

//Tabel td doorzoeken op de th van locatie voornaam[1]
function doorzoekTabel() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("testTabel");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
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
