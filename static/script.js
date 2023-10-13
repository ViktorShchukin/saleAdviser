function myFunction() {
  document.getElementById("demo").innerHTML = "Paragraph changed.";
}

let timeout = 0;

async function getProduct() {
  let product_name = document.getElementById("product_name").value;
  console.log("productName=" + product_name);

  const url = 'http://127.0.0.1:5000/dictionary/product' + "?product_name=" + product_name;
  let response = await fetch(url);

  let data = await response.json();
  let str = "<table><tr><th>id</th><th>name</th></tr>";
  for ( let product of data){
    str += "<tr><td>"+product.product_id+"</td><td>"+product.name+"</td></tr>";
  }
  str += "</table>";

 
  document.getElementById("tableOfProduct").innerHTML = str;
}



function getProductWithTimeOut(){
  if (timeout > 0){
    clearTimeout(timeout);
    timeout = 0;
  }
  timeout = setTimeout(getProduct, 2000);
  
}

