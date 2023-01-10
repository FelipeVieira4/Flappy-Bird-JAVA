package game;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Render extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;

	private Thread game_thread;	
	Game game = new Game();
	
	
	public Render() {
		
		//SETA COR DO BACKGROUND COMO CIANO
		setBackground(Color.CYAN);
		
		//ADICIONAR CLASSE "key_system" AO RENDER
		addKeyListener(new key_system());
		
		//Set and Start the Thread
		game_thread=new Thread(this);
		game_thread.start();
		
	}

	//DESENHAR DESENHAR DO RENDER
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render_draw(g);
	}
	
	//LOOP DO GAME
	public void run() {
		while(true) {
			
			Toolkit.getDefaultToolkit().sync();
			this.repaint();
			
			//ATUALIZAR O JOGO
			game.update();
			
			//ADICIONAR UM DELAY NO JOGO
			try {Thread.sleep(30);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
	
	private class key_system extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			
			//TECLA PARA FAZER PASSÁRO SUBIR
			if(e.getKeyCode()==KeyEvent.VK_SPACE && game.alive){game.bird_y-=10;}
			
			//TECLA PARA PAUSAR E RENICIAR O JOGO
			else if(e.getKeyCode()==KeyEvent.VK_P && game.alive)game.paused=!game.paused;
			else if(e.getKeyCode()==KeyEvent.VK_P && !game.alive) {
				game.alive=true;
				game.paused=!game.paused;
				game.update_pipes();
				game.score=0;
				game.bird_y=30;
			}
			
		}
	}
	//FUNÇÃO PARA PASSAR TAMANHO DO JFRAME Para o OBJETO GAME
	public void SetSize(JFrame j) {
		game.WIDHT=j.getWidth();
		game.HEIGHT=j.getHeight();
	}
}
