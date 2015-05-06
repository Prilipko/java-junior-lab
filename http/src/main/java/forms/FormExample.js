/**
 * Created by Alexander on 06.05.2015.
 */
console.log(encodeURIComponent(" "));
console.log(encodeURIComponent("/"));
console.log(encodeURIComponent("В"));
console.log(encodeURIComponent("Виктор"));

var xhr = new XMLHttpRequest();

var name = "Ваня";
var surname = "Иванов";

var params = 'name=' + encodeURIComponent(name) +
    '&surname=' + encodeURIComponent(surname);

xhr.open("GET", '/submit?' + params, true);

//xhr.onreadystatechange =
//...
//;

xhr.send();