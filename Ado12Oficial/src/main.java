import java.util.Arrays;
import java.util.Scanner;

public class main {
	
	private static final int [][] Objetivo = {{1,2,3}, {4,5,6}, {7,8,0}};
	private static final int Tamanho= 3;
	
	
	private int [] [] matriz;
	private int movimentos; 
	
	public main() {
		matriz = new int[Tamanho][Tamanho];
		movimentos = 0;
		
		// Inicializa a matriz com valores desordenados 
		
		int count = 1;
		
		for(int i = 0; i < Tamanho; i++)
		{
			for(int j = 0; j < Tamanho; j++)
			{
				matriz[i][j] = count++;
			}
		}
		
		matriz[Tamanho - 1] [ Tamanho - 1]=0; // Espaço livre 
		embaralhar();
		
		
	}
	
	private void embaralhar() {
		// Embaralha os valores da matriz 
		
		for(int i = 0; i < Tamanho; i++) {
			for(int j =0; j < Tamanho; j++)
			{
				int randomRow = (int) (Math.random() * Tamanho);
				int randomCol= (int) (Math.random() * Tamanho);
				int temp = matriz[i][j];
				matriz[i][j] = matriz[randomRow][randomCol];
				matriz[randomRow][randomCol] = temp;
			}
		}
		
	}
	
	private void imprimirQuebraCabeca() {
		// imprime a matriz do quebra cabeça
		
		for(int i = 0; i < Tamanho; i++)
		{
			for(int j = 0; j < Tamanho; j++) {
				if(matriz[i][j] == 0) {
					System.out.print("  ");
				}else {
					System.out.print(matriz[i][j] + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	private boolean verificarObjetivo() {
		// Verifica se a matriz esta no estado objetivo
		return Arrays.deepEquals(matriz, Objetivo);
	}
	
	
	private boolean moverPeca(int valor) {
		// Move a peça com o valor fornecido para o espaço livre 
		
		int[] posicaoValor = encontrarPosicao(valor);
		int[] posicaoZero = encontrarPosicao(0);
		
		if(posicaoValor == null || posicaoZero == null) {
			return false;
		}
		
		if ((posicaoValor[0] == posicaoZero[0] && Math.abs(posicaoValor[1] - posicaoZero[1]) == 1) ||
                (posicaoValor[1] == posicaoZero[1] && Math.abs(posicaoValor[0] - posicaoZero[0]) == 1)) {
            matriz[posicaoZero[0]][posicaoZero[1]] = valor;
            matriz[posicaoValor[0]][posicaoValor[1]] = 0;
            movimentos++;
            return true;
        }
        return false;
  	}
	
	
	private int[] encontrarPosicao(int valor) {
		// Encontra a posicao(linha, coluna) do valor na matriz
		int[] posicao = new int[2];
		for(int i =0; i< Tamanho; i++){
			for(int j =0; j < Tamanho; j++) {
				if(matriz[i][j] == valor) {
					posicao[0] =i;
					posicao[1] =j;
					return posicao;
				}
			}
		}
		return null;
	}
	
	
	

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		main quebraCabeca = new main();

		System.out.println("Quebra Cabeça iniciado!!");
		quebraCabeca.imprimirQuebraCabeca();
		
		while(quebraCabeca.verificarObjetivo() == false) {
			System.out.println("Digite o numero da peça que deseja mover (1-8: ");
			int valor = sc.nextInt();
			
			boolean movimentoValido = quebraCabeca.moverPeca(valor);
			if(movimentoValido) {
				quebraCabeca.imprimirQuebraCabeca();			
			}else {
			System.out.println("Movimento Invalido! Tente novamente");
		    }
		
		
		}
		
		System.out.println("Parabens! você resolveu o quebra cabeça.");
		System.out.println("Total de movimentos" + quebraCabeca.movimentos);
		
		sc.close();
	}

}
