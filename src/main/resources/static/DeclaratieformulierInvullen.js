function DeclaratieToevoegen1() {
    var table = document.getElementById("Declaratie");
    console.log(table);
    var row = table.insertRow(document.getElementById("Declaratie").rows.length);
    
    var cell1 = row.insertCell(0);
    cell1.innerHTML = row.rowIndex;
    var cell2 = row.insertCell(1);
    cell2.setAttribute("name", "datum" + row.rowIndex);
    var input2 = document.createElement("input");
    input2.setAttribute("type", "date");
    cell2.appendChild(input2);
    var cell3 = row.insertCell(2);
    cell3.setAttribute("name", "omschrijving" + row.rowIndex);
    var input3 = document.createElement("input");
    input3.setAttribute("type", "text");
    input3.setAttribute("size", "100");
    cell3.appendChild(input3);
    var cell4 = row.insertCell(3);
    cell4.setAttribute("name", "bedragbtw" + row.rowIndex);
    var input4 = document.createElement("input");
    input4.setAttribute("type", "number");
    input4.setAttribute("min", "0");
    input4.setAttribute("value", "0");
    input4.setAttribute("step", ".01");
    cell4.appendChild(input4);
    var cell5 = row.insertCell(4);
    cell5.setAttribute("name", "btw" + row.rowIndex);
    var input5 = document.createElement("select");
    var array = ["Kies BTW tarief", "0%", "9%", "21%"];
    input5.id = "mySelect";
    cell5.appendChild(input5);
    for (var j = 0; j < array.length; j++) {
        var option = document.createElement("option");
        option.value = array[j];
        option.text = array[j];
        input5.appendChild(option);
    }
    var cell6 = row.insertCell(5);
    cell6.setAttribute("name", "bedrag" + row.rowIndex);
    var input6 = document.createElement("input");
    input6.setAttribute("type", "number");
    input6.setAttribute("min", "0");
    input6.setAttribute("value", "0");
    input6.setAttribute("step", ".01");
    cell6.appendChild(input6);    
}

function DeclaratieToevoegen2(){
    var table = document.getElementById("Declaratiekm");
    console.log(table);
    var row = table.insertRow(document.getElementById("Declaratiekm").rows.length);

    var cell7 = row.insertCell(0);
    cell7.setAttribute("name", "datumkm" + row.rowIndex);
    var input7 = document.createElement("input");
    input7.setAttribute("type", "date");
    cell7.appendChild(input7);
    var cell8 = row.insertCell(1);
    cell8.setAttribute("name", "vertrek" + row.rowIndex);
    var input8 = document.createElement("input");
    input8.setAttribute("type", "text");
    input8.setAttribute("size", "50");
    cell8.appendChild(input8);
    var cell9 = row.insertCell(2);
    cell9.setAttribute("name", "aankomst" + row.rowIndex);
    var input9 = document.createElement("input");
    input9.setAttribute("type", "text");
    input9.setAttribute("size", "50");
    cell9.appendChild(input9);
    var cell10 = row.insertCell(3);
    cell10.setAttribute("name", "km" + row.rowIndex);
    var input10 = document.createElement("input");
    input10.setAttribute("type", "number");
    input10.setAttribute("min", "0");
    input10.setAttribute("value", "0");
    cell10.appendChild(input10);
}


function Klik() {
    if (document.getElementById("demo").innerHTML == "Bewerk") {
        document.getElementById("demo").innerHTML = "Geklikt";
    } else {
        document.getElementById("demo").innerHTML = "Bewerk";
    }
}