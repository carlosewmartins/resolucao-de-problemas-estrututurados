import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FloodFill {

    // Define cor RGB(123, 45, 167)
    private static final Color controleMagenta = new Color(123, 45, 167);

    // Direções dos 4 vizinhos: cima, baixo, esquerda, direita
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};

    // =====================================================================
    // VERSÃO COM PILHA (LIFO)
    // =====================================================================
    public static void preencherComPilha(BufferedImage imagem, int inicioX, int inicioY, String pastaFrames) throws IOException {
        int largura = imagem.getWidth();
        int altura = imagem.getHeight();
        int corOriginal = imagem.getRGB(inicioX, inicioY);
        int novaCor = controleMagenta.getRGB();

        // Não faz nada se o pixel já tem a nova cor
        if (corOriginal == novaCor) return;

        Pilha<Pixel> filaPrimariaExecucao = new Pilha<>();
        filaPrimariaExecucao.empilhar(new Pixel(inicioX, inicioY));

        int contadorFrames = 0;
        int contadorPixels = 0;
        int intervaloFrame = Math.max(1, (largura * altura) / 150);

        System.out.println("[PILHA] Iniciando preenchimento...");

        while (!filaPrimariaExecucao.estaVazia()) {

            // Desempilha o próximo pixel a ser processado
            Pixel pixelSentinela = filaPrimariaExecucao.desempilhar();

            int x = pixelSentinela.getX();
            int y = pixelSentinela.getY();

            // Verifica se está dentro dos limites da imagem
            if (x < 0 || x >= largura || y < 0 || y >= altura) continue;

            // Verifica se ainda tem a cor original
            if (imagem.getRGB(x, y) != corOriginal) continue;

            // Pinta o pixel com a nova cor
            imagem.setRGB(x, y, novaCor);
            contadorPixels++;

            // Salva frame a cada intervalo de pixels
            if (contadorPixels % intervaloFrame == 0) {
                salvarFrame(imagem, pastaFrames, contadorFrames++);
            }

            // Empilha os 4 vizinhos laterais
            for (int i = 0; i < 4; i++) {
                filaPrimariaExecucao.empilhar(new Pixel(x + dx[i], y + dy[i]));
            }
        }

        // Frame final com imagem completa
        salvarFrame(imagem, pastaFrames, contadorFrames);
        System.out.println("[PILHA] Concluído! " + contadorPixels + " pixels pintados. " + (contadorFrames + 1) + " frames salvos.");
    }

    // =====================================================================
    // VERSÃO COM FILA (FIFO)
    // =====================================================================
    public static void preencherComFila(BufferedImage imagem, int inicioX, int inicioY, String pastaFrames) throws IOException {
        int largura = imagem.getWidth();
        int altura = imagem.getHeight();
        int corOriginal = imagem.getRGB(inicioX, inicioY);
        int novaCor = controleMagenta.getRGB();

        // Não faz nada se o pixel já tem a nova cor
        if (corOriginal == novaCor) return;

        Fila<Pixel> filaPrimariaExecucao = new Fila<>();
        filaPrimariaExecucao.enfileirar(new Pixel(inicioX, inicioY));

        int contadorFrames = 0;
        int contadorPixels = 0;
        int intervaloFrame = Math.max(1, (largura * altura) / 150);

        System.out.println("[FILA] Iniciando preenchimento...");

        while (!filaPrimariaExecucao.estaVazia()) {

            // Desenfileira o próximo pixel a ser processado
            Pixel pixelSentinela = filaPrimariaExecucao.desenfileirar();

            int x = pixelSentinela.getX();
            int y = pixelSentinela.getY();

            // Verifica se está dentro dos limites da imagem
            if (x < 0 || x >= largura || y < 0 || y >= altura) continue;

            // Verifica se ainda tem a cor original
            if (imagem.getRGB(x, y) != corOriginal) continue;

            // Pinta o pixel com a nova cor
            imagem.setRGB(x, y, novaCor);
            contadorPixels++;

            // Salva frame a cada intervalo de pixels
            if (contadorPixels % intervaloFrame == 0) {
                salvarFrame(imagem, pastaFrames, contadorFrames++);
            }

            // Enfileira os 4 vizinhos laterais
            for (int i = 0; i < 4; i++) {
                filaPrimariaExecucao.enfileirar(new Pixel(x + dx[i], y + dy[i]));
            }
        }

        // Frame final com imagem completa
        salvarFrame(imagem, pastaFrames, contadorFrames);
        System.out.println("[FILA] Concluído! " + contadorPixels + " pixels pintados. " + (contadorFrames + 1) + " frames salvos.");
    }

    // =====================================================================
    // MÉTODO AUXILIAR: salva um frame da animação
    // =====================================================================
    private static void salvarFrame(BufferedImage imagem, String pasta, int numero) throws IOException {
        String nomeArquivo = pasta + "/frame_" + String.format("%05d", numero) + ".png";
        ImageIO.write(imagem, "png", new File(nomeArquivo));
    }
}