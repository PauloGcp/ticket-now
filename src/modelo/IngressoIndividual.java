package modelo;

public class IngressoIndividual extends Ingresso {
	private Jogo jogo;

	public IngressoIndividual(int cod) {
		super(cod);
	}
	
	public double calcularValor() {
		return jogo.getPreco()*1.2;
	}
	public String toString() {
		return "Código:"+ this.getCodigo();
	}
	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	

}
