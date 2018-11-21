package Trab1Gb;

public class Cenario {
	
	protected String algoritmo;
	protected int tamArray;
	protected double[] tempos;
	protected double[] temposDentroDesvio;
	protected double mediaInicial;
	protected double desvio;
	protected double variancia;
	protected double mediaFinal;
	
	//Construtor
	public Cenario(String algoritmo, int tamArray) {
		this.algoritmo = algoritmo;
		this.tamArray = tamArray;
		tempos = new double[10];
		temposDentroDesvio = new double[10];
		mediaInicial = 0.0;
		desvio = 0.0;
		variancia = 0.0;
		mediaFinal = 0.0;
	}
	
	//Executa todos os métodos necessários para os cálculos
	public void calculaTudo() {
		calculaMedia();
		calculaVariancia();
		calculaDesvio();
		verificaDesvios();
		calculaMediaFinal();
	}
	
	//Calcula a média dos 10 tempos 
	public void calculaMedia() {
		double aux = 0.0;
		for(int i = 0; i < tempos.length; i++) {
			aux += tempos[i];
		}
		mediaInicial = aux / tempos.length;
	}
	
	//Calcula a média final dos valores dentro de media +- desvio
	public void calculaMediaFinal() {
		int contElements = 0;
		double sum = 0.0;
		for(int i = 0; temposDentroDesvio[i] != 0; i++) {
			sum += temposDentroDesvio[i];
			contElements++;
		}
		mediaFinal = sum / contElements;
	}
	
	//Calcula a variancia.
	public void calculaVariancia() {
		double aux = 0.0;
		double media = mediaInicial;
		for(int i = 0; i < tempos.length; i++) {
			aux += ((tempos[i] - media) * (tempos[i] - media));
		}
		variancia =  aux / (tempos.length -1);
	}
	
	//Calcula o desvio
	public void calculaDesvio() {
		desvio = Math.sqrt(variancia); 
	}
	
	//Verifica os valores que estao dentro de media +- desvio
	public void verificaDesvios() {
		double min = mediaInicial - desvio;
		double max = mediaInicial + desvio;
		int pos = 0;
		for(int i = 0; i < tempos.length; i++) {
			if(tempos[i] >= min && tempos[i] <= max) {
				temposDentroDesvio[pos++] = tempos[i];
			}
		}
		
	}
	
	public String toString() {
		String aux = "Algoritmo: " + this.algoritmo + " Tamanho do array: " + this.tamArray + " Tempos:";
		for(int i = 0; i < tempos.length; i++) {
			aux += "\n" + tempos[i];
		}
		aux += "\nMédia inicial: " + mediaInicial + "\nVariancia: " + variancia + "\nDesvio: " + desvio + "\nTempos dentro do desvio: ";
		for(int j = 0; temposDentroDesvio[j] != 0; j++) {
			aux += "\n" + temposDentroDesvio[j];
		}
		aux += "\nMédia final: " + mediaFinal;
		return aux;
	}

	//Getters and Setters
	public String getAlgoritmo() {
		return algoritmo;
	}

	public void setAlgoritmo(String algoritmo) {
		this.algoritmo = algoritmo;
	}

	public int getTamArray() {
		return tamArray;
	}

	public void setTamArray(int tamArray) {
		this.tamArray = tamArray;
	}

	public double[] getTempos() {
		return tempos;
	}

	public void setTempos(double[] tempos) {
		this.tempos = tempos;
	}
	
	
	

}
