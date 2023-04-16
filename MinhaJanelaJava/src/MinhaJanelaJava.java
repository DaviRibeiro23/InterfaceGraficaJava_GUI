import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MinhaJanelaJava {

	public static void main(String[] args) {
		
		//-------CONTEUDO (LABEL)---------------------------------------------//
		
		//Escrever texto e centraliza-lo
		JLabel label = new JLabel("Olá, eu sou uma janela Java.",JLabel.CENTER);
		
		//Definir fonte, estilo (negrito e tamanho)
		label.setFont(new Font("Calibri",Font.BOLD, 40));
		
		//Cor da fonte
		label.setForeground(Color.white);
		
		//---------------JANELA (FRAME)--------------------------------//
		
		//Criar Janela
		JFrame janela = new JFrame();
		
		//Torna-la visível
		janela.setVisible(true);
		
		//Tamanho da Janela 
		           
		          // Largura X Altura
		janela.setSize (700,700);
		
		//Definir cor janela - Vermelho
		janela.getContentPane().setBackground(new Color(200,30,50));
		
		//Adicionar texto escrito
		janela.add(label);
		
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
