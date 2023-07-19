package org.example;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;

import static org.example.Usuario.otherFilterUser;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner ler = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        MeuAtivacao texto = new MeuAtivacao();

        String opcao;
        double saldo = 0;
        int limite = 500;
        String extrato = "";
        int numeros_saque = 0;
        List<Conta> contas = new ArrayList<>();
        List<Usuario> usuarios = new ArrayList<>();
        int LIMITE_SAQUES = 5;
        String AGENCIA = "0001";


        while (true){
            texto.exibirMensagem();
            opcao = ler.next();

            if("d".equals(opcao)){
                String cpf;
                System.out.println("Informe o seu cpf: ");
                cpf = ler.next();
                Usuario usuarioEncontrado = otherFilterUser(cpf, usuarios);

                if(usuarioEncontrado == null){
                    System.out.println("Você não possui conta cadastrada, por favor, crie uma conta para prosseguir com a operação!");
                } else {
                    double valor;
                    System.out.println("Informe o valor do depósito: ");
                    valor = ler.nextDouble();

                    if (valor > 0) {
                        saldo += valor;
                        extrato += String.format("Depósito: R$ %.2f%n", valor);
                        System.out.print("Depósito realizado com sucesso! \n");
                    } else {
                        System.out.print("Erro na operação! O valor informado é inválido.");
                    }
                }
            } else if("s".equals(opcao)){
                String cpf;
                System.out.println("Informe o seu cpf: ");
                cpf = ler.next();
                Usuario usuarioEncontrado = otherFilterUser(cpf, usuarios);

                if(usuarioEncontrado == null){
                    System.out.println("Você não possui conta cadastrada, por favor, crie uma conta para prosseguir com a operação!");
                } else {
                    double valor;
                    System.out.print("Informe o valor do saque: ");
                    valor = ler.nextDouble();

                    boolean excedeu_saldo = valor > saldo;
                    boolean excedeu_limite = valor > limite;
                    boolean excedeu_saques = numeros_saque > LIMITE_SAQUES;

                    if (excedeu_saldo) {
                        System.out.println("Erro na operação! Você não tem saldo suficiente.");
                    } else if (excedeu_limite) {
                        System.out.println("Erro na operação! O valor do saque excedeu o limite");
                    } else if (excedeu_saques) {
                        System.out.println("Erro na operação! Número máximo de saques excedido.");
                    } else if (valor > 0) {
                        saldo -= valor;
                        extrato += String.format("Saque: R$ %.2f%n", valor);
                        System.out.println("Valor sacado com sucesso!");
                    } else {
                        System.out.println("Valor sacado com sucesso!");
                    }
                }

            } else if ("p".equals(opcao)) {
                String valor;
                System.out.print("Digite o número do código de barras: ");
                valor = ler.next();

                while (valor.length() != 13 ){
                    System.out.println("Falha na operação, verifique se digitou os números corretamente \n");
                    System.out.print("Digite o número do código de barras: ");
                    valor = ler.next();
                }
                double valor_total;
                System.out.print("Informe o valor total do boleto: \n");
                valor_total = ler.nextDouble();

                if(saldo < valor_total){
                    System.out.println("Saldo insuficiente para efetuar o pagamento!");
                    System.out.printf("Saldo da conta %.2f%n", saldo);
                    System.out.println("Operação Cancelada!!");
                } else{
                    String option;
                    System.out.printf("Saldo da Conta: R$ %.2f%n" +
                                      "Total do boleto: R$ %.2f%n" +
                                      "Deseja pagar essa conta?%n%n" +
                                      " [s] Sim %n " +
                                      "[n] Não %n%n " +
                                      "=>", saldo, valor_total);
                    option = ler.next();

                    if("s".equals(option)){
                        extrato += String.format("PG de Boleto: R$ %.2f%n", valor_total);
                        saldo -= valor_total;
                        System.out.print("Pagamento realizado com sucesso!\n");
                    } else if ("n".equals(option)) {
                        System.out.print("Operação cancelada!\n");
                    } else{
                        System.out.println("Digite uma opção válida!\n");
                    }
                }

            } else if ("lc".equals(opcao)) {

                int tamanhoContas = contas.size();
                if(tamanhoContas == 0){
                    System.out.println("Você não possui contas cadastradas, por favor, volte ao menu e crie sua conta!");
                } else{
                    for(Conta conta : contas){
                        System.out.println("\n==================================================");
                        System.out.println("Agência:\t" + conta.getAgencia());
                        System.out.println("C/C:\t\t" + conta.getNumeroConta());
                        System.out.println("Titular:\t" + conta.getUsuario().getNome());
                        System.out.println("==================================================\n\n");
                    }
                }

            } else if ("nc".equals(opcao)) {
                String CPF;
                System.out.println("Informe os números do CPF: ");
                CPF = ler.next();

                Usuario usuarioEncontrado = otherFilterUser(CPF, usuarios);

                if(usuarioEncontrado != null){
                    System.out.println("$$$ Conta criada com sucesso! $$$");
                    Conta conta = new Conta(AGENCIA, contas.size() + 1, usuarioEncontrado);
                    contas.add(conta);
                } else {
                    System.out.println("\n$$$ Usuário não cadastrado em nosso banco de dados, fim de operação! $$$\n");
                }

            } else if ("nu".equals(opcao)) {
                System.out.println("Informe os números do CPF: ");
                String cpf = ler.next();
                Usuario usuario = otherFilterUser(cpf, usuarios);

                if(usuario != null){
                    System.out.println("\n ~~ CPF informado já possui usuário, refaça a operação! ~~");
                } else{
                    System.out.print("Informe o nome completo: ");
                    String nome = reader.readLine();
                    System.out.print("Informe a data de nascimento (dd-mm-aaaa): ");
                    String data_nascimento = reader.readLine();
                    System.out.print("Informe o endereço (logradouro, número - bairro - cidade/sigla do estado): ");
                    String endereco = reader.readLine();

                    usuarios.add(new Usuario(nome, data_nascimento, cpf, endereco));

                    System.out.println("\n $$$ Usuário cadastrado com sucesso! $$$");
                }

            } else if ("e".equals(opcao)) {
                System.out.println("\n********** Extrato **********");

                if(extrato.isEmpty()){
                    System.out.println("Não foram realizadas movimentações.");
                } else{
                    System.out.println(extrato);
                }
                System.out.println("Saldo: R$" + String.format("%.2f", saldo));
                System.out.println("*******************************\n\n");

            } else if ("q".equals(opcao)) {
                break;
            } else{
                System.out.println("Operação inválida, por favor selecione novamente a opção desejada! ");
            }
        }
    }
}