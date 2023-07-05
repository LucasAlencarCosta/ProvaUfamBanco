import java.util.InputMismatchException;
import java.util.Scanner;
//Somente um projeto da faculdade no qual não poderia ser feito utilizando algum padrão de arquitetura.
class Agencia{
	private String nome;
	private int numero;
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
class Banco{
	private String Nome;
	Agencia[] Agencias = new Agencia[10];
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
	private int currentArray = 0;

    public void switchcase(){
        int choice = 0;
        Scanner ler = new Scanner(System.in);
        try{
            while(choice != 5){
            main.texto();
            choice = ler.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Digite o nome da agencia");
                    String b = ler.next();
                    System.out.println("Digite o numero da agencia");
                    int a = ler.nextInt();
                    criarAgencia(a,b);
                    break;
                case 2:
                    System.out.println("Digite o numero da agencia");
                    int c = ler.nextInt();
                    removerAgencia(c);
                    break;
                case 3:
                    listarAgencias();
                    break;
                default:
                System.out.println("\nInsira um valor válido");
                break;
            }
        }
    }catch(InputMismatchException e){
        System.out.println("\nInsira um valor válido");
        switchcase();
    }
            ler.close();
        }

	public void criarAgencia(int i, String n){
        try{
		Agencias[currentArray] = new Agencia();
		Agencias[currentArray].setnome(n);
		Agencias[currentArray].setnumero(i);
		currentArray = currentArray + 1;
        System.out.println("Operacao concluida");
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Limite de agências atingido");
        }
	}
    public void removerAgencia(int i){
        boolean apagado = false;
		for(int i1 = 0; i1<10 ; i1++){
            if(Agencias[i1]!= null){
            if(Agencias[i1].getnumero()==i){
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
                System.out.println("Operação Concluida");
            }
        }
            i1++;
        }
        if(!apagado){
            System.out.println("Agência não encontrada");
        }
		currentArray = currentArray - 1;   
	}
    public void listarAgencias(){
        for(int i = 0; i<10; i++){
            if(Agencias[i] != null){
                System.out.printf("Nome: %s - Agencia: %d\n", Agencias[i].getnome(), Agencias[i].getnumero());
            }
        }
    }
}
class main{
	public static void texto(){
		System.out.printf("\n----MENU----\n");
		System.out.println("1. Criar agencia");
		System.out.println("2. Remover agencia");
		System.out.println("3. Listar agencias");
		System.out.println("4. Selecionar agencia");
		System.out.println("5. Sair");

	}
	public static void main(String [] args){
		Banco banco = new Banco();
        System.out.println("Bem vindo ao sistema de gerenciamento do Banco Icomp");
        banco.switchcase();
    }
}