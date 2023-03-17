 
 /*JS Clase 2: Estructuras Cíclicas
Ejercicio 1 - Usando FOR
1. Hacer una cuenta regresiva desde 10, mostrando cada número,
y luego mostrar “Despegue!”.
2. Mostrar los primeros 20 números pares, separados por coma.
Ejercicio 2 - Usando WHILE o DO WHILE
 
2. Preguntarle al usuario si quiere “Ver la página” (usando confirm) hasta
que acepte
3. Solicitar al internauta el ingreso de valores (usando prompt), de a uno
por vez, hasta que ingrese 0. Mostrar en la página, la cantidad de
números ingresados, y el total acumulado*/

function cuentaRegresiva(){
    
    for (let contador=10;contador>=0;contador--){
      document.write("<p>"+contador+"</p>");
    }
    document.write("<p> despegue </p>");
  }

  function mostrarNumerosPares(){
    
    
    let  result=" ";//se inicializa afuera del ciclo porque podria pasar que no entre al for(no es este caso) pero js te pide por las dudas inicializar afuera asi cuando entra a document.write se imprime  espacio minimo
    for (let contador=25;contador>0;contador--){
     let modulo=contador%2;
     
      if(modulo==0){//si es igual a cero significa que es par
         result=result + contador + ";";
      }
      
    }

    document.write("<p> lso numeros  pares son: "+result +"</p>");
     
  }

  function deseaVerLaPagina(){
    let confirmar=confirm(" desea ver la pagina")
    while(!confirmar){
       confirmar=confirm(" desea ver la pagina")
    }
  }

  function estadisticasNumerosIngresados(){
  let num1=parseInt(prompt("ingrese un numero distinto de cero para no finalizar y mostrar la cantidad y acumulador"));
  let contador=0;
  let acumulador=0;
    while(num1!=0){
      contador=contador +1;
      acumulador=acumulador + num1;
      document.write("el numero ingresado es "+ num1 + " se contabilizo " +contador+ " acumulando " + acumulador);
      num1=parseInt(prompt("ingrese un numero distinto de cero para no finalizar y mostrar la cantidad y acumulador"));//importante no olvidarse esto porque sino se hara un while infinito
    } 
  }