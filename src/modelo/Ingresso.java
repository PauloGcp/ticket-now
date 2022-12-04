package modelo;

public abstract class Ingresso {
	private int codigo;
	
	public abstract double calcularValor();

	public Ingresso(int codigo) {
		super();
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}
