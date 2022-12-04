package modelo;
import java.util.ArrayList;

public class IngressoGrupo extends Ingresso{
	private ArrayList<Jogo> jogos = new ArrayList<Jogo>();

	public ArrayList<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(ArrayList<Jogo> jogos) {
		this.jogos = jogos;
	}
	
	public String toString() {
		return "Código:"+ this.getCodigo();
	}
	public IngressoGrupo(int cod) {
		super(cod);
	}
	
	public double calcularValor() {
		double valorJogos = 0;
		for (Jogo j: jogos) {
			valorJogos += j.getPreco();
		}
		return valorJogos*0.9;
	}
	
	public void adicionar(Jogo jog) {
		jogos.add(jog);
	}
}

