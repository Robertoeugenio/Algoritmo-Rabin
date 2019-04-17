import java.util.Scanner;
import java.util.Random;
import java.math.BigInteger;

/*  Roberto Eugenio dos Santos */

public class AlgoritmoRabin {

	public boolean Primo(long n, int iteration) {

		if (n == 0 || n == 1)
			return false;

		if (n == 2)
			return true;
		/** se entrar não é primo */
		if (n % 2 == 0)
			return false;

		long s = n - 1;
		while (s % 2 == 0)
			s /= 2;

		Random rand = new Random();
		for (int i = 0; i < iteration; i++) {
			long r = Math.abs(rand.nextLong());
			long a = r % (n - 1) + 1, temp = s;
			long mod = modPow(a, temp, n);
			while (temp != n - 1 && mod != 1 && mod != n - 1) {
				mod = mulMod(mod, mod, n);
				temp *= 2;
			}
			if (mod != n - 1 && temp % 2 == 0)
				return false;
		}
		return true;
	}

	/** calculo da função (a ^ b) % c **/
	public long modPow(long a, long b, long c) {
		long res = 1;
		for (int i = 0; i < b; i++) {
			res *= a;
			res %= c;
		}
		return res % c;
	}

	/** calculo da função (a * b) % c **/
	public long mulMod(long a, long b, long mod) {
		return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).mod(BigInteger.valueOf(mod)).longValue();
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Entre com numero para efetuar o teste\n");
		AlgoritmoRabin mr = new AlgoritmoRabin();

		System.out.println("Digite o Número \n");
		long num = scan.nextLong();

		System.out.println("\n Entre coma  quantidade de interações");
		int k = scan.nextInt();
		/** check if prime **/
		boolean prime = mr.Primo(num, k);
		if (prime)
			System.out.println("\n" + num + "  é um numero primo");
		else
			System.out.println("\n" + num + " is composite");

	}
}