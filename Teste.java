package Trab1Gb;

public class Teste {

	public static void main(String[] args) {

	
		
		
		CalculaT t = new CalculaT();

		Cenario[] cenarios = new Cenario[70];
		cenarios[0] = new Cenario("Quick", 128);
		double[] aux = new double[10];
		t.ascendente(35000);;
		for (int i = 0; i < 10; i++) {
			aux[i] = t.bubbleSort((Comparable[]) t.getArrayAscendente());
		}
		cenarios[0].setTempos(aux);
		cenarios[0].calculaTudo();
		System.out.println(cenarios[0].toString());

	}
}

