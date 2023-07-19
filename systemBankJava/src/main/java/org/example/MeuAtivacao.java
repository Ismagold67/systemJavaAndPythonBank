package org.example;

public class MeuAtivacao {
    private String menu;
    public void MenuAtivacao(String menu){
        this.menu = menu;
    }
    public void exibirMensagem(){
        System.out.println("========== MENU ==========\n" +
                "[d]  Depositar \n" +
                "[s]  Sacar \n" +
                "[p]  Pagar\n" +
                "[nc]  Nova Conta\n" +
                "[lc] Listar contas\n" +
                "[nu] Novo Usuário\n" +
                "[e]  Extrato\n" +
                "[q]  Sair");
    }
}
