package game;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		//Criar Janela
		JFrame window=new JFrame();
		window.setSize(460, 800);
		
		//Criar Render
		Render render_system=new Render();
		render_system.SetSize(window);
		
		//Configurar Janela
		window.setTitle("Flappy Bird");
		window.setResizable(false);
		render_system.setFocusable(true);
		
		//Adicionar Render a Janela
		window.add(render_system);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

}
