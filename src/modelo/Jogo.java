package modelo;
import java.util.ArrayList;

public class Jogo {
	private int id;
	private String data;
	private String local;
	private int estoque;
	private double preco;
	private Time time1;
	private Time time2;
	private ArrayList<Ingresso> ingressos = new ArrayList<>();

	public Jogo(int id, String data, String local, int estoque, double preco, Time time1, Time time2) {
		super();
		this.id = id;
		this.data= data;
		this.local = local;
		this.estoque = estoque;
		this.preco = preco;
		this.time1 = time1;
		this.time2 = time2;
	}
	
	@Override
	public String toString() {
		return "Jogo [id=" + id + ", local=" + local + ", data=" + data + ", preco=" + preco + ", time 1= "+ time1+
				", time 2= "+time2+", estoque= "+ estoque+ "]";
	}
	
	public double obterValorArrecadado() {
		double valor = 0;
		for (Ingresso i: ingressos) {
			if(i instanceof Ingresso) {				
				valor += i.calcularValor();
			}
		}
		return valor;
	}
	public void adicionar(Ingresso ing) {
		ingressos.add(ing);
	}
	
	public void remover(Ingresso ing) {
		ingressos.remove(ing);
	}

	public Time getTime1() {
		return time1;
	}

	public void setTime1(Time time1) {
		this.time1 = time1;
	}

	public Time getTime2() {
		return time2;
	}

	public void setTime2(Time time2) {
		this.time2 = time2;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPreco() {
		return preco;
	}
	
	public ArrayList<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(ArrayList<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}
	
}
