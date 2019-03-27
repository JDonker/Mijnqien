


function Klik() {
    if (document.getElementById("demo").innerHTML == "Bewerk") {
        document.getElementById("demo").innerHTML = "Geklikt";
    } else {
        document.getElementById("demo").innerHTML = "Bewerk";
    }
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