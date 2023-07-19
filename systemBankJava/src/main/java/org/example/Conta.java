package org.example;

 class Conta {
     private int agencia;
     private int numeroConta;
     private Usuario usuario;

     public Conta(String agencia, int numeroConta, Usuario usuario) {
         this.agencia = Integer.parseInt(agencia);
         this.numeroConta = numeroConta;
         this.usuario = usuario;
     }

     public int getAgencia() {
         return agencia;
     }

     public int getNumeroConta() {
         return numeroConta;
     }

     public Usuario getUsuario() {
         return usuario;
     }
}
