function myFunction() {
  document.getElementById("demo").innerHTML = "Paragraph changed.";
}

async function getAllProduct() {
  const url = 'http://127.0.0.1:5000/dictionary/product';
  let response = await fetch(url);

  let data = await response.json();
  let str = "<table><tr><th>id</th><th>name</th></tr>";
  for ( let product of data){
    str += "<tr><td>"+product.product_id+"</td><td>"+product.name+"</td></tr>";
  }
  str += "</table>";

 
  document.getElementById("test").innerHTML = str;
}

function getAllProduct2() {
  fetch('http://127.0.0.1:5000/dictionary/product')
    .then(response => response.json())
    .then(data => document.getElementById('test').innerHTML = JSON.stringify(data));
}