package appconsole;
import regras_negocio.Fachada;
import repositorio.Repositorio;
import modelo.Time;
import modelo.Jogo;

import java.util.ArrayList;
import java.util.Random;

import modelo.Jogo;
public class testin {

	public static void main(String[] args) {
			try {
				Fachada.criarTime("brasil", "br");
				Fachada.criarTime("argentina", "ar");
				Fachada.criarTime("chile", "ch");	
				Fachada.criarTime("bolivia", "bo");	
			} catch(Exception ex) {
				System.out.println("problema ao criar time-->"+ex.getMessage());
			}
			
			try {
				Fachada.criarJogo("02/12/2022", "maracana", 10000, 20.0, "brasil", "argentina");
				Fachada.criarJogo("02/12/2022", "maracana", 10000, 20.0, "chile", "bolivia");
				Fachada.criarJogo("04/12/2022", "maracana", 10000, 20.0, "brasil", "chile");
				Fachada.criarJogo("04/12/2022", "maracana", 10000, 20.0, "argentina", "bolivia");
			}
			catch(Exception ex) {
				System.out.println("problema ao criar jogo-->"+ex.getMessage());
			}
//		Random sorteio = new Random();
//		Time t1 = new Time("Brasil","br");
//		Time t2 = new Time("Brasil","br");
//		System.out.println(Fachada.listarTimes());
//		String teste3 = "";
//		String teste2 = " ";
//		Jogo j = new Jogo(8383, "","",0,2.0,t1,t2);
//		Jogo jogo = null;
//		
//		int[] testearray = {1,2,3};
//		ArrayList<Integer> teste = new ArrayList<>();
//		teste.add(1);
//		teste.add(2);
//		teste.add(3);
//		teste.add(4);
//		teste.add(5);
//		System.out.println(teste.contains(testearray));
		
		
	}


}
