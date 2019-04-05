


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