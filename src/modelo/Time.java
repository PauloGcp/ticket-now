package modelo;
import java.util.ArrayList;

public class Time {
	private String nome;
	private String origem;
	private ArrayList<Jogo> jogos = new ArrayList<Jogo>();
	
	public Time(String nome, String origem) {
		super();
		this.nome = nome;
		this.origem= origem;
	}
	
	@Override
	public String toString() {
		return "Time: "+ nome+", origem: "+origem;
	}
	
	public double obterValorArrecadado() {
		double valorTime = 0;
		for (Jogo j: jogos) {
			valorTime += j.obterValorArrecadado();
		}
		return valorTime;
	}
	
	public ArrayList<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(ArrayList<Jogo> jogos) {
		this.jogos = jogos;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void adicionar(Jogo jog) {
		jogos.add(jog);
	}
}
