


function Klik() {
    if (document.getElementById("demo").innerHTML == "Bewerk") {
        document.getElementById("demo").innerHTML = "Geklikt";
    } else {
        document.getElementById("demo").innerHTML = "Bewerk";
    }
}

function opslaan(form){
  xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function(){

    if(this.readyState == 4){
        alert("Uren toegevoegd aan database")
        }
  }
  xhr.open("POST", "http://localhost:8082/api/urenperdag", true);
  var uren_per_dagArray = new Array(2);
  var datum = "";
  var opdracht = "";
  var overwerk = "";
  var verlof = "";
  var ziek = "";
  var training = "";
  var overig = "";
  var VerklaringOverig = "";
  var uren_per_dag = {datum, opdracht, overwerk, verlof, ziek, training, overig, VerklaringOverig};
  for (var i = 0; i<2; i++){
      uren_per_dagArray.push(uren_per_dag);
      console.log(uren_per_dagArray);
  }
  for(var i = 0; i<2; i++){
   //uren_per_dagArray[i].datum=form.datum+i.innerHTML;
   uren_per_dagArray[i].opdracht=form.opdracht[i].value;
   uren_per_dagArray[i].overwerk=form.overwerk[i].value;
   uren_per_dagArray[i].verlof=form.verlof[i].value;
   uren_per_dagArray[i].ziek=form.ziek[i].value;
   uren_per_dagArray[i].training=form.training[i].value;
   uren_per_dagArray[i].overig=form.overig[i].value;
   //uren_per_dagArray[i].VerklaringOverig=form.verklaring[i].value;
  
  }
  xhr.setRequestHeader("Content-type", "application/json");
  xhr.send(JSON.stringify(uren_per_dag));
}

function versturen(form){

}

$(document).ready(function() {
    $('.Declaratieformulier').hide();
 });

  $(document).ready(function(){
    $("#Urenformulier").click(function(){
      $(".Urenformulier, .Declaratieformulier").toggle(1000);
      $(this).text(function(i, v){
        return v === 'Uren' ? 'Declaraties' : 'Uren'
     })
     $(this).text(function(i, v){
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
        backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
        data: [1000,80,344,804,633]
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