
/*1) Hace una función que reciba un arreglo de números y devuelva el número más grande
observar que lo resolvimos con 3 versiones*/
function numMasGrande(arregloNumo) {
  let maximo = 0;
  for (let i = 0; i < arregloNumo.length; i++) {
    if (arregloNumo[i] > maximo) {
      maximo = arregloNumo[i];
    }
  }
  return maximo;
}

function numMasGrande2(arregloNumo) {
  let maximo = 0;
  for (let numero of arregloNumo) {
    if (numero > maximo) {
      maximo = numero;
    }
  }
  return maximo;
}

function numMasGrande3(arregloNumo) {
  let maximo = 0;
  arregloNumo.forEach((numero) => {
    if (numero > maximo) {
      maximo = numero;
    }
  });
  return maximo;
}

/*2)Crea un arreglo con números del 1 al 6, luego, mostrando el arreglo en cada paso :
○ Remove el ultimo numero
○ Agrega el número 0 al final
○ Ordena el arreglo */

function crearArreglo() {
  let arregloNum = [1, 2, 3, 4, 5, 6];
  document.writeln("arreglo original" + arregloNum);
  arregloNum.pop();
  document.writeln("arreglo removido el ultimo numero" + arregloNum);
  arregloNum.push(0);
  document.writeln("arreglo agrego en el ultimo posicion cero" + arregloNum);
  arregloNum.sort();
  document.writeln("arreglo ordenado" + arregloNum);
  return true;

}

/*3) Definir un arreglo de colores.
Hacer una función que al invocarse inserte una sección con el color respectivo ( puede
terminar al insertar todos o repetirlos ).
Agregar un botón que llame a la función*/



function insertarSeccionColor(){
  let arregloColor=["red","#faa","#aaf"];
   for (let unColor of arregloColor){
    let div=document.createElement("div");
    div.style.backgroundColor=unColor;
    div.style.height="122px"
    document.body.appendChild(div);
   }
    
return true;
}

