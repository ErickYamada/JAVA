import java.util.Scanner;

class somardoisnumeros {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String args[]) {
		int num1,num2,soma;
		System.out.println("Digite um número");
		num1= sc.nextInt();
		System.out.println("Digite outro Número");
		num2=sc.nextInt();
		soma=num1+num2;
		System.out.println("Soma:"+soma);
		
	}
	
}
