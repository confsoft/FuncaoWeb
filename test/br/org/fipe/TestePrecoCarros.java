package br.org.fipe;

import static org.junit.Assert.*;

import br.com.configuracoes.EnumElemento;
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
		consulta.selecionaMarca("Ford");
		consulta.selecionarModelo("Fiesta TIT./TIT.Plus 1.6 16V Flex Aut.");
		consulta.selecionarAno("2016 Gasolina");
		consulta.funcao.clicar(EnumElemento.ID, "buttonPesquisarcarro");
		System.out.println(consulta.buscaValorResultado());
		assertTrue("Esperava carro com valor maior que 50k", consulta.buscaValorResultado() > 50000);
		consulta.funcao.usarUtilidade().msg("Espera","Foi validado o valor do carro se é maior que 50k");
    }

}
