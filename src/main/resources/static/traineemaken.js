function go(form){
      xhr = new XMLHttpRequest();
      xhr.onreadystatechange = function(){

        if(this.readyState == 4){
            alert("User toegevoegd aan database")

            xhr2 = new XMLHttpRequest();
      xhr2.onreadystatechange = function(){

        if(this.readyState == 4){
            alert("Trainee toegevoegd aan database")
            }
      }
      xhr2.open("POST", "http://localhost:8082/api/trainee", true);
      var trainee = {};
      trainee.achternaam=form.an.value;
      trainee.voornaam=form.vn.value;
      trainee.tussenvoegsel=form.tv.value;
      trainee.personeelsnummer=form.pn.value;
      xhr2.setRequestHeader("Content-type", "application/json");
      xhr2.send(JSON.stringify(trainee));
            }
      }
      xhr.open("POST", "http://localhost:8082/api/user", true);
      var user = {};
      user.username=form.un.value;
      user.password=form.pw.value;
      user.role="trainee";
      xhr.setRequestHeader("Content-type", "application/json");
      xhr.send(JSON.stringify(user));

      
  }