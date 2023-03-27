let tareasGuardadas = ["Hacer los ejercicios", "Limpiar"];

function agregarLi(texto) {
  let ul = document.querySelector("ul");
  let li = document.createElement("li");
  li.textContent = texto;
  ul.appendChild(li);
}

function conseguirTextoEscrito() {
  let input = document.querySelector("input");
  let texto = input.value;
  return texto;
}

function agregarTarea() {
  let texto = conseguirTextoEscrito();
  agregarLi(texto);
  tareasGuardadas.push(texto); 

localStorage.setItem("tareasGuardadas",JSON.stringify(tareasGuardadas)); 
}

function cargarTareasGuardadas() {
    let tareasGuardadas=JSON.parse(localStorage.getItem("tareasGuardadas"));
  for (let tarea of tareasGuardadas) {
    agregarLi(tarea);
  }
}
cargarTareasGuardadas();

/*ejercicio 2 
2) Hace una página que muestre el “nombre del usuario” y un botón para cambiarlo.
Al clickear el botón se le pedirá el nombre por prompt, este es el nombre que se mostrará.
El nombre debe persistir incluso si se recarga la página. */

function cambiarNombre(){
  
  let nuevoNombre=prompt("ingrese nuevo nombre");
  let nombre=document.getElementById("nombre");
  localStorage.setItem("nuevoNombre",JSON.stringify(nuevoNombre)); 
  let nombreNuevo=JSON.parse(localStorage.getItem("nuevoNombre"));
  nombre.value=nombreNuevo;
  return true;
}

