package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
	//IMAGENS USADAS NO JOGO
	BufferedImage background_tex;
	BufferedImage bird_tex;
	BufferedImage pipes;
	
	//FUNÇÃO PARA CARREGAR AS IMAGENS
	public void load_images() { 
		try {
			background_tex = ImageIO.read(new File("rsc/background.png"));
			bird_tex = ImageIO.read(new File("rsc/bird_tex.png"));
			pipes=ImageIO.read(new File("rsc/pipes.png"));
		} catch (IOException e) {}
	}
	
	//FUNÇÃO CRIADA PARA FACILITAR DESENHAR TEXTURAS
	public static void DrawIMAGE(Graphics g,BufferedImage Texture,int x,int y,int SIZE) {
		g.drawImage(Texture, x, y, x+(Texture.getWidth()*SIZE), y+(Texture.getHeight()*SIZE), 0, 0, Texture.getWidth(), Texture.getHeight(), null);
		return;
	}
	
}