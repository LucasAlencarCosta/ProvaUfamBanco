package ap2;
import java.util.Scanner;

class Agencia{

	private String nome;
	private int numero;

	Conta[] contas = new Conta[10];

	String getnome(){

		return nome;

	}
	void setnome(String x){

		this.nome = x;

	}
	int getnumero(){
		return numero;
	}
	void setnumero(int y){
		this.numero = y;
	}

}

class Conta{

	private String nome;
	private int numero;
	private double saldo = 0;

	boolean saldoCheck(double valor){
		return valor<=saldo;
	}
	String getnome(){

		return nome;

	}

	void setnome(String x){

		this.nome = x;

	}
	int getnumero(){

		return numero;

	}
	void setnumero(int y){

		this.numero = y;

	}
	double getsaldo(){

		return saldo;

	}

	void setsaldo(double x){

		this.saldo = x;

	}
	void depositar(Scanner ler){
        System.out.println("Insira o valor que deseja depositar");
        double x = ler.nextDouble();
		saldo = saldo + x;
        System.out.println("Operacao concluida\n");

	}
	void sacar(Scanner ler){
        System.out.println("Insira o valor que deseja sacar");
        double x = ler.nextDouble();
		if(saldoCheck(x)){
			saldo = saldo -x;
            System.out.println("Operacao concluida\n");
		}else{
			System.out.println("Valor invalido, operacao cancelada\n");
		}
	}
	void transferir(Scanner ler, Agencia agencia){
        int conta = -1;
        System.out.println("Insira o numero da conta que deseja transferir");
        int nConta = ler.nextInt();
        for(int i =0; i<agencia.contas.length; i++){
            if(agencia.contas[i] != null){
            if(agencia.contas[i].numero == nConta){
                conta = i;
            }
        }
        }
        if(conta != -1){
        System.out.println("Insira o valor que deseja transferir");
        double x = ler.nextDouble();
        
		if(saldoCheck(x) && conta != -1){
			saldo = saldo - x;
			agencia.contas[conta].saldo = agencia.contas[conta].saldo + x;
            System.out.println("Operacao concluida\n");

		}else if(!saldoCheck(x)){
			System.out.println("Valor invalido, operacao cancelada\n");
		}
    }else{
        System.out.println("Numero nao encontrado, operacao cancelada\n");
    }
	}

    void verSaldo(){
        System.out.printf("Nome - %s | Numero da conta - %d | Saldo - %.2f\n\n",nome,numero,saldo);
    }
}

class Banco{
	private String Nome;
	Agencia[] Agencias = new Agencia[10];

    public static void texto(Conta conta){
		System.out.printf("Menu da conta - %s\n",conta.getnome());
		System.out.println("1. Depositar");
		System.out.println("2. Sacar");
		System.out.println("3. Transferir");
		System.out.println("4. Ver Saldo");
		System.out.println("5. Voltar");

	}

	public static void textoConta(Agencia agencia){
		System.out.printf("----Menu da agencia %s----\n",agencia.getnome());
		System.out.println("1. Criar conta");
		System.out.println("2. Remover Conta");
		System.out.println("3. Listar Contas");
		System.out.println("4. Selecionar Contas");
		System.out.println("5. Voltar");

	}

	public static void textoAgencia(){
		System.out.printf("----MENU----\n");
		System.out.println("1. Criar agencia");
		System.out.println("2. Remover agencia");
		System.out.println("3. Listar agencias");
		System.out.println("4. Selecionar agencia");
		System.out.println("5. Sair");

	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
    public void switchCase(Conta conta, Agencia agencia){
		int choice = 0;boolean valido = true;
		Scanner ler = new Scanner(System.in);
		while(choice != 5){
			Banco.texto(conta);
			try{
				choice = ler.nextInt();
				valido = true;
			}catch(Exception e){
				ler.nextLine();
				System.out.printf("Insira um valor valido\n");
				valido = false;
			}
			if(valido == true){
				switch(choice){
					case 1:
                    conta.depositar(ler);
						break;
					case 2:
                    conta.sacar(ler);
						break;
					case 3:
                    conta.transferir(ler, agencia);
						break;
					case 4:
                    conta.verSaldo();
						break;
					case 5:
                        choice = 5;
						switchCaseConta(agencia);
						break;
					default:
                        System.out.println("Opcao nao encontrada");
						break;
				}
			}
		}
		ler.close();

	}

	private int currentArrayContas = 0;

	public void switchCaseConta(Agencia agencia){
		int choice = 0;boolean valido = true;
		Scanner ler = new Scanner(System.in);
		while(choice != 5){
			Banco.textoConta(agencia);
			try{
				choice = ler.nextInt();
				valido = true;
			}catch(Exception e){
				ler.nextLine();
				System.out.printf("Insira um valor valido\n");
				valido = false;
			}
			if(valido == true){
				switch(choice){
					case 1:
						if(currentArrayContas <10){
							ler.nextLine();
							System.out.printf("Digite o nome do titular\n");
							String b = ler.nextLine();
							System.out.printf("Digite o numero da conta\n");
							int a = ler.nextInt();
							criarConta(a,b,agencia);

						}else{
							System.out.println("Capacidade maxima alcancada");
						}
						break;
					case 2:
						removerConta(ler, agencia);
						break;
					case 3:
						listarContas(agencia);
						break;
					case 4:
                        if(currentArrayContas != 0){
                            choice = 5;
						selecionarConta(ler, agencia);
                        }else{
                            System.out.println("Valor invalido");
                        }
						break;
					case 5:
						switchCaseAgencia();
						break;
					default:
						System.out.println("Opcao nao encontrada");
						break;
				}
			}
		}
		ler.close();

	}
	public void criarConta(int i, String n, Agencia agencia){
		agencia.contas[currentArrayContas] = new Conta();//parei aqui, fazendo a criação de contas dentro de Agencias, terei que passar por parametro qual agencia o mesmo está para criar na agencia certa.
		agencia.contas[currentArrayContas].setnome(n);
		agencia.contas[currentArrayContas].setnumero(i);
		currentArrayContas = currentArrayContas + 1;
		System.out.println("Operacao concluida\n");
	}

	public void removerConta(Scanner ler, Agencia agencia){
		boolean apagado = false;
		if(currentArrayContas == 0){
			System.out.println("Nao eh possivel remover, lista vazia");
		}else{
			System.out.println("Digite o numero da conta");
			int c = ler.nextInt();
			for(int i1 = 0; i1<10 ; i1++){
				if(agencia.contas[i1]!= null){
					if(agencia.contas[i1].getnumero()==c){
						int i2 = i1;
						while(i2<10){
							if(i2!=9){
								agencia.contas[i2] = agencia.contas[i2+1];
							}else{
								agencia.contas[i2] = null;
							}
							i2++;
						}
						apagado = true;
						System.out.println("Operacao concluida\n");
					}
				}
			}
			if(!apagado){
				System.out.println("Numero nao encontrado\n");
			}
			currentArrayContas = currentArrayContas - 1;   
		}
	}

	public void listarContas(Agencia agencia){

		System.out.printf("----CONTAS----\n");
		for(int i = 0; i<10; i++){
			if(agencia.contas[i] != null){
				System.out.printf("%d.Nome: %s - Numero: %d\n",i+1, agencia.contas[i].getnome(), agencia.contas[i].getnumero());
			}
		}
		System.out.printf("\n");
	}

	public void selecionarConta(Scanner ler, Agencia agencia){
		System.out.println("Digite o numero da conta");
		Conta atual = new Conta();
		int conta = ler.nextInt();
		for(int i = 0; i<currentArrayContas; i++){
			if(agencia.contas[i].getnumero() == conta){
				atual = agencia.contas[i];
			}
		}
		if(atual.getnome() != null){
			switchCase(atual, agencia);
		}else{
			System.out.println("Numero nao encontrado, operacao cancelada\n");
			switchCaseConta(agencia);
		}
	}

	private int currentArrayAgencias = 0;

	public void switchCaseAgencia(){
		int choice = 0;boolean valido = true;
		Scanner ler = new Scanner(System.in);
		while(choice != 5){
			Banco.textoAgencia();
			try{
				choice = ler.nextInt();
				valido = true;
			}catch(Exception e){
				ler.nextLine();
				System.out.printf("Insira um valor valido\n");
				valido = false;
			}
			if(valido == true){
				switch(choice){
					case 1:
						if(currentArrayAgencias <10){
							ler.nextLine();
							System.out.printf("Digite o nome da agencia\n");
							String b = ler.nextLine();
							System.out.printf("Digite o numero da agencia\n");
							int a = ler.nextInt();
							criarAgencia(a,b);

						}else{
							System.out.println("Capacidade maxima alcancada\n");
						}
						break;
					case 2:
						removerAgencia(ler);
						break;
					case 3:
						listarAgencias();
						break;
					case 4:
						choice = 5;
						selecionarAgencia(ler);
						break;
					case 5:
						System.out.println("Volte sempre :)");
						break;
					default:
						System.out.println("Opcao nao encontrada\n");
						break;
				}
			}
		}
		ler.close();

	}

	public void criarAgencia(int i, String n){
		Agencias[currentArrayAgencias] = new Agencia();
		Agencias[currentArrayAgencias].setnome(n);
		Agencias[currentArrayAgencias].setnumero(i);
		currentArrayAgencias = currentArrayAgencias + 1;
		System.out.println("Operacao concluida\n");
	}
	public void removerAgencia(Scanner ler){
		boolean apagado = false;
		if(Agencias[0] == null){
			System.out.println("Nao eh possivel remover, lista vazia\n");
		}else{
			System.out.println("Digite o numero da agencia");
			int c = ler.nextInt();
			for(int i1 = 0; i1<10 ; i1++){
				if(Agencias[i1]!= null){
					if(Agencias[i1].getnumero()==c){
						int i2 = i1;
						while(i2<10){
							if(i2!=9){
								Agencias[i2] = Agencias[i2+1];
							}else{
								Agencias[i2] = null;
							}
							i2++;
						}
						apagado = true;
						System.out.println("Operacao concluida\n");
					}
				}
			}
			if(!apagado){
				System.out.println("Agencia nao encontrada\n");
			}
			currentArrayAgencias = currentArrayAgencias - 1;   
		}
	}
	public void listarAgencias(){
		if(Agencias[0] != null){
			System.out.printf("\n----AGENCIAS----\n");
		}
		for(int i = 0; i<10; i++){
			if(Agencias[i] != null){
				System.out.printf("%d. Nome: %s - Numero: %d\n",i+1, Agencias[i].getnome(), Agencias[i].getnumero());
			}
		}
	}
	public void selecionarAgencia(Scanner ler){
		System.out.println("Digite o numero da agencia");
		Agencia atual = new Agencia();
		int agencia = ler.nextInt();
		for(int i = 0; i<currentArrayAgencias; i++){
			if(Agencias[i].getnumero() == agencia){
				atual = Agencias[i];
			}
		}
		if(atual.getnome() != null){
			switchCaseConta(atual);
		}else{
			System.out.println("Numero nao encontrado, operacao cancelada\n");
			switchCaseAgencia();
		}
	}
}


class main{
	public static void main(String [] args){
		Banco banco = 	new Banco();
		System.out.println("Bem vindo ao sistema de gerenciamento do Banco Icomp\n");
		banco.switchCaseAgencia();
	}
}