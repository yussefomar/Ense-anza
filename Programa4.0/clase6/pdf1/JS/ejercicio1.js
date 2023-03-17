 
 /*Hacer un programa que:
● Pida un número (utilizando prompt)
● Si es mayor a cero mostrar “Número positivo”
● Si es menor a cero mostrar “Número negativo”
● Si es igual a cero mostrar “Es cero”
● Si no cumple ninguna mostrar “No es un número! */
 let num1=parseInt( prompt("ingrese un numero y te dire como es "));
 if (num1>0){
  document.write("<p> Es un numero positivo</p>");
 }else if(num1<0){
  document.write("<p> Es un numero negativo</p>");
 }else if(num1==0){
  document.write("<p> Es un numero igual a cero</p>");
}else {
    document.write("<p> no es un numero</p>");
}