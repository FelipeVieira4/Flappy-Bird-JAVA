package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Game {
	
	//DEFINIÇÃO DE VARIAVEIS
	public int WIDHT,HEIGHT;
	
	public int bird_y=300;
	public int score;
	public boolean paused=true;
	public boolean alive=true;
	
	//CRIAR UMA CLASSE RANDOM
	private Random random=new Random();
	
	//CRIAR CLASS DE IMAGENS
	private Images img_game=new Images();
	
	//ARRAY DE CANOS
	private Pipes pipes_[]=new Pipes[5];
	private int reset_pipes =5;
	
	public Game() {
		//CARREGAR AS IMAGENS
		img_game.load_images();
		
		//SETA CANOS
		for(int i=0;i<pipes_.length;i++) {
			pipes_[i]=new Pipes();
		}
		

	}

	//FUNÇÃO DESENHAR DA CLASSE GAME
	public void render_draw(Graphics g1) {
		
		//SETA O TAMANHO DA GONT
		g1.setFont(g1.getFont().deriveFont(25f));
		
		//DESENHAR AS MONTANHAS ATRÁS
		Images.DrawIMAGE(g1, img_game.background_tex, 0, 0, 8);
	
		//DESENHAR GRAMA
		g1.setColor(new Color(0,160,0));
		g1.fillRect(0, 424, WIDHT, HEIGHT);

		//DESENHAR CANOS
		for(Pipes n:pipes_) {
			Images.DrawIMAGE(g1, img_game.pipes, n.x, n.y, 3);
		}
		
		//DESENHAR O PASSÁRO
		Images.DrawIMAGE(g1, img_game.bird_tex, 20, bird_y, 3);
		
		//SETA COR DA FONT
		g1.setColor(Color.BLACK);
		
		//DESENHAR SCORE
		g1.drawString("SCORE:"+score, 10, 20);
		
		//SE FOR GAME OVER
		if(!alive) {
			g1.setColor(Color.GREEN);
			g1.fillRect(40, 600-25, 105, 30);
			
			g1.setColor(Color.RED);
			g1.drawString("PRESS P",40,600);
		}
		
	}

	//FUNÇÃO ATUALIZAR O JOGO
	public void update() {
		
		//SE JOGO NÃO ESTIVER PAUSADO
		if(!paused) {
			
			//RESETAR CANOS
			if(reset_pipes==pipes_.length) {
				update_pipes();
			}
			
			//ATUAZLIAR OS CANOS
			for(int i=0;i<pipes_.length;i++){
				pipes_[i].Update();
				
					//CHECAR COLISÃO COM DO PASSÁRO COM OS CANOS
				if (((20 < pipes_[i].x + img_game.pipes.getWidth()*3 &&
					20+img_game.bird_tex.getWidth()*3 > pipes_[i].x &&
					bird_y < pipes_[i].y + 169*3 &&
					bird_y + img_game.bird_tex.getHeight()*3 > pipes_[i].y)
						||
					(20 < pipes_[i].x + img_game.pipes.getWidth()*3 &&
					20+img_game.bird_tex.getWidth()*3 > pipes_[i].x &&
					bird_y < pipes_[i].y + img_game.pipes.getHeight()*3 &&
					bird_y + img_game.bird_tex.getHeight()*3 > pipes_[i].y+230*3))
					&& alive) {
					//SE COLIDIR DA GAME OVER
					paused=true;
					alive=false;
				}
				
			}
			//CHECAR SE O PASSÁRO CAIU NO CHÃO
			if(bird_y+img_game.bird_tex.getHeight()>800) {
				paused=true;
				alive=false;
			}
			
			//ADICIONAR GRAVIDADE AO PASSÁRO
			bird_y+=4;

		}
		
	}
	
	//FUNÇÃO PARA RESETAR OS CANOS
	public void update_pipes() {
		for(int i=0;i<pipes_.length;i++) {
				pipes_[i].x=(WIDHT+10)+(300*i);
				pipes_[i].y=random.nextInt(-440, 0);
				pipes_[i].active=true;
		}
		reset_pipes=0;
	}
	
	
	//CLASSE DE CANOS
	private class Pipes{
		
		public int x=470,y;
		private boolean active=true;
		
		//ATUALIZAR CANOS E CHECAR SE ESTÃO NA TELA
		public void Update() {
				//SE CANOS SAIU DA TELA
				if(this.x+img_game.pipes.getWidth()*3<=0 && this.active==true) {
					reset_pipes++;
					active=false;
					score++;
				}
				
				this.x-=2;
		}
	}
}
