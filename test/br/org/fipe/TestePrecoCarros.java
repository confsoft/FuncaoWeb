package br.org.fipe;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestePrecoCarros {

	ConsultaPrecoMedioVeiculos consulta = new ConsultaPrecoMedioVeiculos();
	
	@Before
	public void setUp() throws Exception {
		consulta.navegar();
	}

	@After
	public void tearDown() throws Exception {
		consulta.fechar();
	}

	@Test
	public void test() {
        consulta.funcao.usarUtilidade().msg("Espera","Verificar onde clicar");
        consulta.consultaCarrosClickLink();
        /* mudou a pagina, mas vai ficar aqui para exemplo de page object
		consulta.selecionaMarca("Ford");
		consulta.selecionarModelo("Fiesta TITANIUM 1.6 16V Flex Aut.");
		consulta.selecionarAno("2014 Gasolina");
		System.out.println(consulta.buscaValorResultado());
		assertTrue("Carro maior que 50k", consulta.buscaValorResultado() > 50000);
		*/
    }

}
