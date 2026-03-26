import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) throws Exception {

        // CONFIGURACOES
        String caminhoImagem = "Estrela.png";

        // Coordenadas do pixel inicial do Flood Fill
        int inicioX = 150;
        int inicioY = 150;

        // Carrega a imagem original
        File arquivoOriginal = new File(caminhoImagem);
        if (!arquivoOriginal.exists()) {
            System.out.println("ERRO: Arquivo '" + caminhoImagem + "' não encontrado!");
            System.out.println("Coloque a imagem PNG na mesma pasta que os arquivos .java");
            return;
        }

        BufferedImage imagemOriginal = ImageIO.read(arquivoOriginal);

        System.out.println("Imagem carregada: " + imagemOriginal.getWidth() + "x" + imagemOriginal.getHeight() + " pixels");
        System.out.println("Pixel inicial: (" + inicioX + ", " + inicioY + ")");
        System.out.println("Cor do pixel inicial: " + Integer.toHexString(imagemOriginal.getRGB(inicioX, inicioY)));
        System.out.println("=".repeat(50));

        // EXECUCAO COM PILHA
        BufferedImage imagemPilha = copiarImagem(imagemOriginal);
        File pastaPilha = new File("frames_pilha");
        pastaPilha.mkdirs();

        FloodFill.preencherComPilha(imagemPilha, inicioX, inicioY, "frames_pilha");
        ImageIO.write(imagemPilha, "png", new File("saida_pilha.png"));
        System.out.println("Imagem final salva em: saida_pilha.png");
        System.out.println("=".repeat(50));


        // EXECUCAO COM FILA
        BufferedImage imagemFila = copiarImagem(imagemOriginal);
        File pastaFila = new File("frames_fila");
        pastaFila.mkdirs();

        FloodFill.preencherComFila(imagemFila, inicioX, inicioY, "frames_fila");
        ImageIO.write(imagemFila, "png", new File("saida_fila.png"));
        System.out.println("Imagem final salva em: saida_fila.png");
        System.out.println("=".repeat(50));

        System.out.println("Programa encerrado com sucesso!");
    }

    // Copia uma BufferedImage para não modificar a original
    private static BufferedImage copiarImagem(BufferedImage original) {
        BufferedImage copia = new BufferedImage(
                original.getWidth(),
                original.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );
        for (int y = 0; y < original.getHeight(); y++) {
            for (int x = 0; x < original.getWidth(); x++) {
                copia.setRGB(x, y, original.getRGB(x, y));
            }
        }
        return copia;
    }
}