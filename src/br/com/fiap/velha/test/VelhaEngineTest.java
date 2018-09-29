/**
 * 
 */
package br.com.fiap.velha.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.fiap.velha.VelhaEngine;

/** Caso de Teste para a classe VelhaEngine.
 *  Requer o JUnit. */
public class VelhaEngineTest {

	/** Instancia local de um objeto VelhaEngine */
	private VelhaEngine velhaEngine = null;
	
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
		velhaEngine = new VelhaEngine();
	}

	/**
	 * Test method for {@link br.com.fiap.velha.VelhaEngine#getOponente(char)}.
	 */
	@Test
	public void testGetOponente() {
		assertEquals(
				VelhaEngine.JOGADOR_X,
				VelhaEngine.getOponente(VelhaEngine.JOGADOR_O));
		assertEquals(
				VelhaEngine.JOGADOR_O,
				VelhaEngine.getOponente(VelhaEngine.JOGADOR_X));
		assertEquals(
				VelhaEngine.JOGADOR_VAZIO,
				VelhaEngine.getOponente(VelhaEngine.JOGADOR_VAZIO));
		assertEquals(
				VelhaEngine.JOGADOR_VAZIO,
				VelhaEngine.getOponente('K'));
		assertEquals(
				VelhaEngine.JOGADOR_VAZIO,
				VelhaEngine.getOponente('\00'));
	}

	/**
	 * Test method for {@link br.com.fiap.velha.VelhaEngine#setPosicao(int, char)}.
	 */
	@Test
	public void testSetPosicao() {

		velhaEngine.limparTabuleiro();
		
		for (int i = 0; i < 9; i++) {

			velhaEngine.setPosicao(i, 'K');
			assertEquals(
					VelhaEngine.JOGADOR_VAZIO,
					velhaEngine.getPosicao(i));
			
			velhaEngine.setPosicao(i, VelhaEngine.JOGADOR_VAZIO);
			assertEquals(
					VelhaEngine.JOGADOR_VAZIO,
					velhaEngine.getPosicao(i));

			velhaEngine.setPosicao(i, VelhaEngine.JOGADOR_X);
			assertEquals(
					VelhaEngine.JOGADOR_X,
					velhaEngine.getPosicao(i));
			
			velhaEngine.setPosicao(i, VelhaEngine.JOGADOR_O);
			assertEquals(
					VelhaEngine.JOGADOR_O,
					velhaEngine.getPosicao(i));
		}

		velhaEngine.limparTabuleiro();
	}

	/**
	 * Test method for {@link br.com.fiap.velha.VelhaEngine#limparTabuleiro()}.
	 */
	@Test
	public void testLimparTabuleiro() {
		testIsVazio();
	}

	/**
	 * Test method for {@link br.com.fiap.velha.VelhaEngine#copiarTabuleiro(char[])}.
	 */
	@Test
	public void testCopiarTabuleiro() {
		velhaEngine.copiarTabuleiro(TAB_VAZIO);
		assertArrayEquals(
				TAB_VAZIO,
				velhaEngine.getTabuleiro());

		velhaEngine.copiarTabuleiro(TAB_EMPATE);
		assertArrayEquals(
				TAB_EMPATE,
				velhaEngine.getTabuleiro());
		
		velhaEngine.copiarTabuleiro(TAB_X_GANHOU);
		assertArrayEquals(
				TAB_X_GANHOU,
				velhaEngine.getTabuleiro());
		
		velhaEngine.copiarTabuleiro(TAB_O_GANHOU);
		assertArrayEquals(
				TAB_O_GANHOU,
				velhaEngine.getTabuleiro());

		velhaEngine.copiarTabuleiro(TAB_VAZIO);
		assertTrue(velhaEngine.isVazio());
	}

	/**
	 * Test method for {@link br.com.fiap.velha.VelhaEngine#isGameOver()}.
	 */
	@Test
	public void testIsGameOver() {
		velhaEngine.copiarTabuleiro(TAB_VAZIO);
		assertFalse(velhaEngine.isGameOver());

		velhaEngine.copiarTabuleiro(TAB_EMPATE);
		assertTrue(velhaEngine.isGameOver());
		
		velhaEngine.copiarTabuleiro(TAB_X_GANHOU);
		assertTrue(velhaEngine.isGameOver());
		
		velhaEngine.copiarTabuleiro(TAB_O_GANHOU);
		assertTrue(velhaEngine.isGameOver());

		velhaEngine.limparTabuleiro();
		assertFalse(velhaEngine.isGameOver());
	}

	/**
	 * Test method for {@link br.com.fiap.velha.VelhaEngine#getGanhador()}.
	 */
	@Test
	public void testGetGanhador() {
		velhaEngine.copiarTabuleiro(TAB_VAZIO);
		assertEquals(
				VelhaEngine.JOGADOR_VAZIO,
				velhaEngine.getGanhador());

		velhaEngine.copiarTabuleiro(TAB_EMPATE);
		assertEquals(
				VelhaEngine.JOGADOR_VAZIO,
				velhaEngine.getGanhador());
		
		velhaEngine.copiarTabuleiro(TAB_X_GANHOU);
		assertEquals(
				VelhaEngine.JOGADOR_X,
				velhaEngine.getGanhador());
		
		velhaEngine.copiarTabuleiro(TAB_O_GANHOU);
		assertEquals(
				VelhaEngine.JOGADOR_O,
				velhaEngine.getGanhador());

		velhaEngine.limparTabuleiro();
		assertEquals(
				VelhaEngine.JOGADOR_VAZIO,
				velhaEngine.getGanhador());
	}

	/**
	 * Test method for {@link br.com.fiap.velha.VelhaEngine#getTrioGanhador()}.
	 */
	@Test
	public void testGetTrioGanhador() {
		velhaEngine.copiarTabuleiro(TAB_VAZIO);
		assertArrayEquals(
				null,
				velhaEngine.getTrioGanhador());

		velhaEngine.copiarTabuleiro(TAB_EMPATE);
		assertArrayEquals(
				null,
				velhaEngine.getTrioGanhador());
		
		velhaEngine.copiarTabuleiro(TAB_X_GANHOU);
		assertArrayEquals(
				new int[]{2,5,8},
				velhaEngine.getTrioGanhador());
		
		velhaEngine.copiarTabuleiro(TAB_O_GANHOU);
		assertArrayEquals(
				new int[]{0,3,6},
				velhaEngine.getTrioGanhador());

		velhaEngine.limparTabuleiro();
		assertArrayEquals(
				null,
				velhaEngine.getTrioGanhador());
	}

	/**
	 * Test method for {@link br.com.fiap.velha.VelhaEngine#isGanhador(char)}.
	 */
	@Test
	public void testIsGanhador() {
		velhaEngine.copiarTabuleiro(TAB_VAZIO);
		assertFalse(velhaEngine.isGanhador(VelhaEngine.JOGADOR_X));
		assertFalse(velhaEngine.isGanhador(VelhaEngine.JOGADOR_O));
		assertTrue(velhaEngine.isGanhador(VelhaEngine.JOGADOR_VAZIO));

		velhaEngine.copiarTabuleiro(TAB_EMPATE);
		assertFalse(velhaEngine.isGanhador(VelhaEngine.JOGADOR_X));
		assertFalse(velhaEngine.isGanhador(VelhaEngine.JOGADOR_O));
		assertTrue(velhaEngine.isGanhador(VelhaEngine.JOGADOR_VAZIO));
		
		velhaEngine.copiarTabuleiro(TAB_X_GANHOU);
		assertTrue(velhaEngine.isGanhador(VelhaEngine.JOGADOR_X));
		assertFalse(velhaEngine.isGanhador(VelhaEngine.JOGADOR_O));
		assertFalse(velhaEngine.isGanhador(VelhaEngine.JOGADOR_VAZIO));
		
		velhaEngine.copiarTabuleiro(TAB_O_GANHOU);
		assertFalse(velhaEngine.isGanhador(VelhaEngine.JOGADOR_X));
		assertTrue(velhaEngine.isGanhador(VelhaEngine.JOGADOR_O));
		assertFalse(velhaEngine.isGanhador(VelhaEngine.JOGADOR_VAZIO));

		velhaEngine.limparTabuleiro();
		assertFalse(velhaEngine.isGanhador(VelhaEngine.JOGADOR_X));
		assertFalse(velhaEngine.isGanhador(VelhaEngine.JOGADOR_O));
		assertTrue(velhaEngine.isGanhador(VelhaEngine.JOGADOR_VAZIO));
	}

	/**
	 * Test method for {@link br.com.fiap.velha.VelhaEngine#isEmpate()}.
	 */
	@Test
	public void testIsEmpate() {
		velhaEngine.copiarTabuleiro(TAB_VAZIO);
		assertFalse(velhaEngine.isEmpate());

		velhaEngine.copiarTabuleiro(TAB_EMPATE);
		assertTrue(velhaEngine.isEmpate());
		
		velhaEngine.copiarTabuleiro(TAB_X_GANHOU);
		assertFalse(velhaEngine.isEmpate());
		
		velhaEngine.copiarTabuleiro(TAB_O_GANHOU);
		assertFalse(velhaEngine.isEmpate());

		velhaEngine.limparTabuleiro();
		assertFalse(velhaEngine.isEmpate());
	}

	/**
	 * Test method for {@link br.com.fiap.velha.VelhaEngine#isVazio()}.
	 */
	@Test
	public void testIsVazio() {

		velhaEngine.limparTabuleiro();
		assertTrue(velhaEngine.isVazio());
		
		for (int i = 0; i < 9; i++) {
			velhaEngine.setPosicao(i, 'K');
			assertTrue(velhaEngine.isVazio());
			
			velhaEngine.setPosicao(i, VelhaEngine.JOGADOR_VAZIO);
			assertTrue(velhaEngine.isVazio());

			velhaEngine.setPosicao(i, VelhaEngine.JOGADOR_X);
			assertFalse(velhaEngine.isVazio());
			velhaEngine.limparTabuleiro();
			
			velhaEngine.setPosicao(i, VelhaEngine.JOGADOR_O);
			assertFalse(velhaEngine.isVazio());
			velhaEngine.limparTabuleiro();
		}
		
		assertTrue(velhaEngine.isVazio());
	}

	/**
	 * Test method for {@link br.com.fiap.velha.VelhaEngine#getJogada(char)}.
	 */
	@Test
	public void testGetJogada() {
		
		VelhaEngine velhaOponente = new VelhaEngine();
		velhaOponente.setNivel(3);
		
		int posicao = -1;
		final char jogador[] = {VelhaEngine.JOGADOR_X, VelhaEngine.JOGADOR_O};
		
		System.out.println("Partida #1");
		velhaEngine.limparTabuleiro();
		
		while (!velhaEngine.isGameOver()) {
			
			posicao = velhaEngine.getJogada(jogador[0]); 
			velhaEngine.setPosicao(posicao, jogador[0]);
			printTab();
			
			if (velhaEngine.isGameOver()) { break; }
			
			velhaOponente.copiarTabuleiro(velhaEngine.getTabuleiro());
			posicao = velhaOponente.getJogada(jogador[1]); 
			velhaEngine.setPosicao(posicao, jogador[1]);
			printTab();
		}
		
		if (velhaEngine.isEmpate()) {
			System.out.println("Houve empate.");
		} else {
			System.out.println("Jogador "
					+ velhaEngine.getGanhador() + " ganhou.");
		}
		
		System.out.println("Partida #2");
		velhaEngine.limparTabuleiro();
		
		while (!velhaEngine.isGameOver()) {
			
			velhaOponente.copiarTabuleiro(velhaEngine.getTabuleiro());
			posicao = velhaOponente.getJogada(jogador[1]); 
			velhaEngine.setPosicao(posicao, jogador[1]);
			printTab();
			
			if (velhaEngine.isGameOver()) { break; }
			
			posicao = velhaEngine.getJogada(jogador[0]); 
			velhaEngine.setPosicao(posicao, jogador[0]);
			printTab();
		}
		
		if (velhaEngine.isEmpate()) {
			System.out.println("Houve empate.");
		} else {
			System.out.println("Jogador "
					+ velhaEngine.getGanhador() + " ganhou.");
		}
	}

	/** Imprime o tabuleiro na tela. */
	private void printTab() {
		System.out.println("---------");
		for (int i = 0; i < 9; i++) {
			System.out.print("[" + velhaEngine.getPosicao(i) + "]");
			if ((i + 1) % 3 == 0) System.out.println();
		}
	}

}
