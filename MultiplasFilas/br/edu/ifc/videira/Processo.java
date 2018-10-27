package br.edu.ifc.videira;


public class Processo {
	private int n;
	private int tempo;
	private int executou=0;
	
	public int getExecutou() {
		return executou;
	}
	public void setExecutou(int executou) {
		this.executou = executou;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}


}
