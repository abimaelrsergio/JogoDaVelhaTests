/**
 * 
 */
package br.com.fiap.velha.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.fiap.velha.VelhaBean;
import br.com.fiap.velha.VelhaEngine;
import br.com.fiap.velha.VelhaParser;

/** Caso de Teste para a classe VelhaParser.
 *  Requer o JUnit. */
public class VelhaParserTest {

	private VelhaParser velhaParser = null;
	
	/** Constante que representa um tabuleiro vazio. */
	private static final char[] TAB_VAZIO = {
		' ',' ',' ',
		' ',' ',' ',
		' ',' ',' '
	};
	
	/** Constante que representa um tabuleiro com jogo empatado. */
	private static final char[] TAB_EMPATE = {
		'X','O','X',
		'X','O','O',
		'O','X','X'
	};
	
	/** Constante que representa um tabuleiro com um jogo onde o X ganhou. */
	private static final char[] TAB_X_GANHOU = {
		'X','O','X',
		'O','O','X',
		'O','X','X'
	};
	
	/** Constante que representa um tabuleiro com um jogo onde o O ganhou. */
	private static final char[] TAB_O_GANHOU = {
		'O',' ',' ',
		'O',' ',' ',
		'O','X','X'
	};
	
	/**
	 * Inicializa os objetos de teste.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		velhaParser = new VelhaParser();
	}

	/**
	 * Test method for {@link br.com.fiap.velha.VelhaParser#getVelhaBean(java.lang.String)}.
	 */
	@Test
	public void testGetVelhaBean() {
		VelhaBean velhaBean = null;
		String xml = null;
		
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><JogoDaVelha><id> </id><status>aguarde</status><jogada/><tabuleiro><p0/><p1/><p2/><p3/><p4/><p5/><p6/><p7/><p8/></tabuleiro>";
		velhaBean = velhaParser.getVelhaBean(xml);
		assertNull(velhaBean);

		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><JogoDaVelha><status>wrong</status><jogada/><tabuleiro><p0/><p1/><p2/><p3/><p4/><p5/><p6/><p7/><p8/></tabuleiro></JogoDaVelha>";
		velhaBean = velhaParser.getVelhaBean(xml);
		assertNull(velhaBean);

		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><JogoDaVelha><id> </id><status>aguarde</status><jogada/><tabuleiro><p0/><p1/><p2/><p3/><p4/><p5/><p6/><p7/><p8/></tabuleiro></JogoDaVelha>";
		velhaBean = velhaParser.getVelhaBean(xml);
		assertNull(velhaBean);
		
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><JogoDaVelha><id>X</id><status>jogue</status><jogada>0</jogada><tabuleiro><p0/><p1/><p2/><p3/><p4/><p5/><p6/><p7/><p8/></tabuleiro></JogoDaVelha>";
		velhaBean = velhaParser.getVelhaBean(xml);
		assertNotNull(velhaBean);
		assertEquals(VelhaEngine.JOGADOR_X,	velhaBean.getId());
		assertEquals(0, velhaBean.getJogada());
		assertEquals(VelhaEngine.STATUS_JOGUE, velhaBean.getStatus());
		assertArrayEquals(TAB_VAZIO, velhaBean.getTabuleiro());
		
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><JogoDaVelha><id>O</id><status>empate</status><jogada/><tabuleiro><p0>X</p0><p1>O</p1><p2>X</p2><p3>X</p3><p4>O</p4><p5>O</p5><p6>O</p6><p7>X</p7><p8>X</p8></tabuleiro></JogoDaVelha>";
		velhaBean = velhaParser.getVelhaBean(xml);
		assertNotNull(velhaBean);
		assertEquals(VelhaEngine.JOGADOR_O,	velhaBean.getId());
		assertEquals(-1, velhaBean.getJogada());
		assertEquals(VelhaEngine.STATUS_EMPATE, velhaBean.getStatus());
		assertArrayEquals(TAB_EMPATE, velhaBean.getTabuleiro());
		
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><JogoDaVelha><id>X</id><status>ganhou</status><jogada/><tabuleiro><p0>X</p0><p1>O</p1><p2>X</p2><p3>O</p3><p4>O</p4><p5>X</p5><p6>O</p6><p7>X</p7><p8>X</p8></tabuleiro></JogoDaVelha>";
		velhaBean = velhaParser.getVelhaBean(xml);
		assertNotNull(velhaBean);
		assertEquals(VelhaEngine.JOGADOR_X,	velhaBean.getId());
		assertEquals(-1, velhaBean.getJogada());
		assertEquals(VelhaEngine.STATUS_GANHOU, velhaBean.getStatus());
		assertArrayEquals(TAB_X_GANHOU, velhaBean.getTabuleiro());
		
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><JogoDaVelha><id>X</id><status>perdeu</status><jogada/><tabuleiro><p0>O</p0><p1/><p2/><p3>O</p3><p4/><p5/><p6>O</p6><p7>X</p7><p8>X</p8></tabuleiro></JogoDaVelha>";
		velhaBean = velhaParser.getVelhaBean(xml);
		assertNotNull(velhaBean);
		assertEquals(VelhaEngine.JOGADOR_X,	velhaBean.getId());
		assertEquals(-1, velhaBean.getJogada());
		assertEquals(VelhaEngine.STATUS_PERDEU, velhaBean.getStatus());
		assertArrayEquals(TAB_O_GANHOU, velhaBean.getTabuleiro());
	}

	/**
	 * Test method for {@link br.com.fiap.velha.VelhaParser#getVelhaXML(br.com.fiap.velha.VelhaBean)}.
	 */
	@Test
	public void testGetVelhaXML() {
		VelhaBean velhaBean = new VelhaBean();
		String xml = null;
		String expectedXml = null;
		
		xml = velhaParser.getVelhaXML(velhaBean);
		expectedXml = null;
		assertEquals(expectedXml, xml);
		
		velhaBean.setId(VelhaEngine.JOGADOR_X);
		velhaBean.setJogada(0);
		velhaBean.setStatus(VelhaEngine.STATUS_JOGUE);
		xml = velhaParser.getVelhaXML(velhaBean);
		expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><JogoDaVelha><id>X</id><status>jogue</status><jogada>0</jogada><tabuleiro><p0/><p1/><p2/><p3/><p4/><p5/><p6/><p7/><p8/></tabuleiro></JogoDaVelha>";
		assertEquals(expectedXml, xml);
		
		velhaBean.setId(VelhaEngine.JOGADOR_O);
		velhaBean.setJogada(-1);
		velhaBean.setStatus(VelhaEngine.STATUS_EMPATE);
		velhaBean.copiarTabuleiro(TAB_EMPATE);
		xml = velhaParser.getVelhaXML(velhaBean);
		expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><JogoDaVelha><id>O</id><status>empate</status><jogada/><tabuleiro><p0>X</p0><p1>O</p1><p2>X</p2><p3>X</p3><p4>O</p4><p5>O</p5><p6>O</p6><p7>X</p7><p8>X</p8></tabuleiro></JogoDaVelha>";
		assertEquals(expectedXml, xml);
		
		velhaBean.setId(VelhaEngine.JOGADOR_X);
		velhaBean.setStatus(VelhaEngine.STATUS_GANHOU);
		velhaBean.copiarTabuleiro(TAB_X_GANHOU);
		xml = velhaParser.getVelhaXML(velhaBean);
		expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><JogoDaVelha><id>X</id><status>ganhou</status><jogada/><tabuleiro><p0>X</p0><p1>O</p1><p2>X</p2><p3>O</p3><p4>O</p4><p5>X</p5><p6>O</p6><p7>X</p7><p8>X</p8></tabuleiro></JogoDaVelha>";
		assertEquals(expectedXml, xml);
		
		velhaBean.setId(VelhaEngine.JOGADOR_X);
		velhaBean.setStatus(VelhaEngine.STATUS_PERDEU);
		velhaBean.copiarTabuleiro(TAB_O_GANHOU);
		xml = velhaParser.getVelhaXML(velhaBean);
		expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><JogoDaVelha><id>X</id><status>perdeu</status><jogada/><tabuleiro><p0>O</p0><p1/><p2/><p3>O</p3><p4/><p5/><p6>O</p6><p7>X</p7><p8>X</p8></tabuleiro></JogoDaVelha>";
		assertEquals(expectedXml, xml);
	}

}
