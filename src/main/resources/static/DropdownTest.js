/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function toggleOpties() {
    document.getElementById("myDropdown").classList.toggle("show");
  }
  
  // Close the dropdown menu if the user clicks outside of it
  window.onclick = function(event) {
    if (!event.target.matches('.dropbtn')) {
      var dropdowns = document.getElementsByClassName("dropdown-content");
      var i;
      for (i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.classList.contains('show')) {
          openDropdown.classList.remove('show');
        }
      }
    }
  }

  var input = 0;
  function keuzeMaken(input){
    switch(input){
      case 1:
      document.getElementById("keuze").innerHTML = "In afwachting";
      break;
      case 2:
      document.getElementById("keuze").innerHTML = "Ingediend";
      break;
      case 3:
      document.getElementById("keuze").innerHTML = "Wijzigen";
      break;
      case 4:
      document.getElementById("keuze").innerHTML = "Goedgekeurd";
      break;

    }
  }
