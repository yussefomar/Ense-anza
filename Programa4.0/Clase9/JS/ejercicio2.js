/*1) Declarar un objeto `Rectangulo` que tenga como atributos ancho y largo. Además que
tenga dos métodos: perimetro() y area() que calculen y devuelvan las magnitudes
correspondientes */
let rectangulo = {
  ancho: 50,
  alto: 60,
  perimetro() { return this.ancho * 2 + this.alto * 2 },
  area() { return this.ancho * this.alto },
}

/*Declarar un objeto `CuentaDeBanco` que tenga como atributos número de cuenta y
balance. Implementar los siguientes métodos:
● depositar(cantidad): Agrega `cantidad` al balance de la cuenta
● extraer(cantidad): Saca `cantidad` del balance de la cuenta
● transferir(cantidad, cuenta): Transfiere `cantidad` a la cuenta recibida. */

 

function crearCuentaDeBanco(numCuenta,balance) {
  return {
    numCuenta: numCuenta,
    balance: balance,
    misDatos(){document.write(" mi numero de cuenta "+this.numCuenta+ " y mi balance actual "+ this.balance + "<br>");},
    depositar(cantidad) { 
      this.balance = this.balance + cantidad;
     document.write("mi cuenta es "+this.numCuenta+" deposite/aron "+cantidad+ "y me quedo "+ this.balance+ "<br>"); },
    extraer(cantidad) { 
      this.balance = this.balance - cantidad 
      document.write("mi cuenta es "+this.numCuenta+" extraje "+cantidad + "y me quedo"+ this.balance+ "<br>"); },
    transferir(cantidad, cuenta) {
      cuenta.depositar(cantidad);
      this.extraer(cantidad);
       
    }
  }
}


