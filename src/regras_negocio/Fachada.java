package regras_negocio;
/**********************************
 * Projeto2 de POO (2022.2)
 * 
 * Grupo de alunos: 
 * fulano, beltrano e cicrano
 * 
 **********************************/

import java.util.ArrayList;
import java.util.Random;

import modelo.Ingresso;
import modelo.IngressoGrupo;
import modelo.Jogo;
import modelo.Time;
import repositorio.Repositorio;
import modelo.IngressoIndividual;

public class Fachada {
	private static Repositorio repositorio = new Repositorio();	
	private Fachada() {}	

	
	public static ArrayList<Time> listarTimes() {
		//retorna todos os times do reposit�rio
		return repositorio.getTimes();
	}
	public static ArrayList<Jogo> listarJogos() {
		//retorna todos os jogos do reposit�rio
		return repositorio.getJogos();
	}
	
	public static ArrayList<Ingresso> listarIngressos() {
		//retorna todos os ingressos do reposit�rio
		return repositorio.getIngressos();
	}
	public static ArrayList<Jogo> listarJogos(String data) {
		//retorna os jogos do reposit�rio na data fornecida
		ArrayList<Jogo> jogosPorData = new ArrayList<>();
		for (Jogo j: listarJogos()) {
			if (j.getData().equals(data)) {
				jogosPorData.add(j);
			}
		}
		return jogosPorData;
	}
	public static Ingresso localizarIngresso(int codigo) {
		//retorna o ingresso do reposit�rio com o c�digo fornecido
		//...
		return repositorio.localizarIngresso(codigo);
	}
	
	public static Jogo	localizarJogo(int id) throws Exception {
		//retorna o jogo do reposit�rio com o id fornecido
		for (Jogo j: listarJogos()) {
			if (j.getId() == id) {
				return j;
			} 			
		}
		throw new Exception("Jogo inexistente");
	}

	public static Time 	criarTime(String nome, String origem) throws Exception {
		//Exce��o: nome existente no repositorio
		//criar o time
		//adicionar este time no reposit�rio
		//salvar o repositorio em arquivo
		for (Time t: listarTimes()) {
			if (t.getNome() == nome) {
				throw new Exception("Time já cadastrado");
			}
		}
		Time time = new Time(nome, origem);
		repositorio.adicionar(time);
		repositorio.salvar();
		return time;
	}

	public static Jogo criarJogo(String data, String local, int estoque, double preco, String nometime1, String nometime2)  throws Exception {
		
		//Exce��o: nometime1 ou nometime2 inexistente no repositorio, 
		//  local ou data vazios, estoque ou pre�o menor ou igual a zero
		//gerar id sequencial do jogo 
		//criar o jogo, 
		//relacionar o jogo com os dois times 
		//adicionar este jogo no reposit�rio
		//salvar o repositorio em arquivo
		if (data.trim().length() == 0 || local.trim().length() == 0) {
			throw new Exception("Data e/ou local devem ser preenchidos.");
		}
		if (estoque <= 0 || preco <= 0.0) {
			throw new Exception("Estoque e/ou preço devem ser maior que zero.");
		}
		Time time1 = repositorio.localizarTime(nometime1);
		Time time2 = repositorio.localizarTime(nometime2);
		Jogo newJogo;
		
		if (time1 != null || time2 != null) {
			if (listarJogos().size() == 0) {
				newJogo = new Jogo(1, data, local, estoque, preco, time1, time2);
			} else {
				int ultimoIndex = listarJogos().size();
				newJogo = new Jogo(ultimoIndex + 1, data, local, estoque, preco, time1, time2);
			}
			newJogo.setTime1(time1);
			newJogo.setTime2(time2);
			time1.adicionar(newJogo);
			time2.adicionar(newJogo);
			repositorio.adicionar(newJogo);
			repositorio.salvar();
			return newJogo;
		}
		throw new Exception("Time 1 e/ou time 2 inexistentes");
		
	}
	
	public static void 	apagarJogo(int id) throws Exception{
		//Exce��o: id inexistente no repositorio
		//remover o jogo do reposit�rio
		//salvar o repositorio em arquivo
		Jogo jogo = repositorio.localizarJogo(id);
		
		if (jogo == null) {			
			throw new Exception("Id inexistente");
		}
		for (Jogo j: listarJogos()) {
			if (j.getIngressos().size() > 0) {
				throw new Exception("O jogo não pode ser cancelado pois já existem ingressos comprados");
			}
		}
		repositorio.remover(jogo);
		repositorio.salvar();	
}
	

	
	public static IngressoIndividual criarIngressoIndividual(int id) throws Exception{
		//Exce��o: id inexistente no repositorio
		//gerar codigo aleat�rio e verificar unicididade do codigo no jogo indicado
		//criar o ingresso individual 
		//relacionar este ingresso com o jogo indicado
		//adicionar este ingresso no reposit�rio
		//salvar o repositorio em arquivo
		Jogo jogo = repositorio.localizarJogo(id);
		
		if (jogo != null) {
			Random random = new Random();
			
			ArrayList<Integer> ingressosId = new ArrayList<>();
			for(Ingresso i: jogo.getIngressos()) {
				ingressosId.add(i.getCodigo());
			}
			
			int codigo = random.nextInt(1000000);
			while(ingressosId.contains(codigo)) {
				codigo = random.nextInt(1000000);
			}
			
			IngressoIndividual ingresso = new IngressoIndividual(codigo);
			ingresso.setJogo(jogo);
			jogo.adicionar(ingresso);
			jogo.setEstoque(jogo.getEstoque()-1);
			repositorio.adicionar(ingresso);
			repositorio.salvar();
			return ingresso;
		}
		throw new Exception("Jogo não encontrado");
	}
	
	public static IngressoGrupo	criarIngressoGrupo(int[] id) throws Exception{
		//Exce��o: id inexistente no repositorio 
		//gerar codigo aleat�rio e verificar unicididade do codigo nos jogos indicados
		//criar o ingresso de grupo, 
		//relacionar este ingresso com os jogos indicados 
		//adicionar este ingresso no reposit�rio
		//salvar o repositorio em arquivo
		
		Random random = new Random();
		ArrayList<Jogo> jogosIndicados = new ArrayList<>();
		ArrayList<Integer> idIngressosJogos = new ArrayList<>();
		ArrayList<Integer> codJogos = new ArrayList<>();
		
		// criação dos codigos de jogos disponíveis no repositório
		for (Jogo j: listarJogos()) {
			codJogos.add(j.getId());
		}
		
		// Verificação se o código disponibilizado pelo usuário é condizente com os códigos 
		// disponíveis e no seguinte fluxo do código, caso não retorne uma exceção,
		// adição do jogo à lista de jogos indicados pelo usuário
		for (Integer i: id) {
			if(!codJogos.contains(i)) {
				throw new Exception("O jogo de id '"+i+"' não existe.");
			}
			jogosIndicados.add(repositorio.localizarJogo(i));
		}
		
		// Adição dos códigos de ingressos à lista que mostrará todos os ingressos dos possíveis jogos indicados pelo usuário
		for (Jogo j: jogosIndicados) {
			for (Ingresso ing: j.getIngressos()) {
				idIngressosJogos.add(ing.getCodigo());
			}
		}
		
		int codigo = random.nextInt(1000000);
		while(idIngressosJogos.contains(codigo)) {
			codigo = random.nextInt(1000000);
		}
		
		IngressoGrupo ingresso = new IngressoGrupo(codigo);
		
		// Associação de cada ingresso à seu respectivo jogo
		for (Jogo j: jogosIndicados) {
			j.adicionar(ingresso);
			j.setEstoque(j.getEstoque()-1);
		}
		
		ingresso.setJogos(jogosIndicados);
		repositorio.adicionar(ingresso);
		repositorio.salvar();
		return ingresso;
	}
	
	public static void	cancelarIngresso(int codigo) throws Exception {
		//Exce��o: codigo inexistente no repositorio
		//remover o relacionamento entre ele e o(s) jogo(s) ligados a ele
		//remover ingresso do reposit�rio 
		//salvar o repositorio em arquivo
		Ingresso ingresso = repositorio.localizarIngresso(codigo);
		
		if (ingresso == null) {			
			throw new Exception("Ingresso '"+ codigo +"' não encontrado");
		}
			
		if (ingresso instanceof IngressoGrupo) {
			ArrayList<Jogo> jogosGrupo = ((IngressoGrupo) ingresso).getJogos();
			((IngressoGrupo) ingresso).setJogos(null);
			for (Jogo j: jogosGrupo) {
				j.remover(ingresso);
				j.setEstoque(j.getEstoque()+1);
			}
			repositorio.remover(ingresso);
			repositorio.salvar();
		}else {				
			Jogo jogoIndividual = ((IngressoIndividual) ingresso).getJogo();
			((IngressoIndividual) ingresso).setJogo(null);
			jogoIndividual.remover(ingresso);
			jogoIndividual.setEstoque(jogoIndividual.getEstoque()+1);
			repositorio.remover(ingresso);
			repositorio.salvar();
		}
	}
	
}

