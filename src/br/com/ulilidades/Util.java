package br.com.ulilidades;
import static org.junit.Assert.*;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class Util {
	
	/**
	 * Limpa o console.
	 */
	public void limparConsole()
	{
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	/**
	 * Exibe uma mensagem na tela em formato JOptionPane
	 * @param titulo
	 * Passe o t�tulo da mensagem
	 * @param mensagem
	 * Passe a mensagem que deseja exibir
	 * @param icone
	 * Passe um n�mero:<br>
	 * 0 - �cone de erro.<br>
	 * 1 - �cone de informa��o.<br>
	 * 2 - �cone de aten��o, exclama��o.<br>
	 * 3 - �cone de interroga��o.
	 * @author Rog�rio Figueiredo.
	 */
	public void mensagem(String titulo,String mensagem,int icone)
	{
				 
		JOptionPane optionPane = new JOptionPane(mensagem);
		JDialog dialog = optionPane.createDialog(titulo);
		dialog.setLocation(10, 25);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
		
		
		
	}
	
	
	public void msg(String titulo,String mensagem)
	{
				 
		
		msg(titulo,mensagem,"");
		        
        

	}
	
	public void msg(String titulo,String mensagem,String caminhoImg)
	{
		if(caminhoImg.equals(""))
		{
			
			JOptionPane optionPane = new JOptionPane(mensagem);
			JDialog dialog = optionPane.createDialog(titulo);
			dialog.setLocation(10, 25);
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
			dialog.setResizable(true);	
		}
		else
		{
			ImageIcon icon = new ImageIcon(caminhoImg);
			JOptionPane optionPane = new JOptionPane(mensagem,1,-1,icon);
			JDialog dialog = optionPane.createDialog("Teste de Aceita��o");
			dialog.setLocation(10, 25);
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
			dialog.setResizable(true);
		}
				
		        
        

	}
	
	
	/**
	 * Executa um arquivo exe.
	 * @param caminho
	 * Passe o caminho do arquivo.
	 */
	public void executarArquivoExe(String caminho) {
		try {
			String[] commands = new String[] {};
			commands = new String[] { caminho };
			Runtime.getRuntime().exec(commands);
		} catch (IOException e) {
		}

	}
	
	/**
	 * Quebra o texto e retorna um array.
	 * @param texto
	 * Texto que deseja quebrar.
	 * @param delimitador
	 * Delimitador, ex: //?, //.
	 * @return
	 */
	
	public String[] quebrarTexto(String texto, String delimitador)
	{
		String[] textoQuebrado = texto.split(delimitador);
		return textoQuebrado;
					
	}
	
	/**
	 * Compara se um texto � igual ao outro e imprime no console o resultado.
	 * @param primeiroTexto
	 * Passe o primeiro texto que ser� comparado com o segundo.
	 * @param segundoTexto
	 * Passe o segundo texto que ser� comparado com o primeiro.
	 * @param msgSucesso
	 * Passe uma mensagem de sucesso caso o resultado seja positivo.
	 * @param msgFalhou
	 * Passe uma mensagem de falha caso o resultado seja negativo.
	 */
	public void assertTexto(String primeiroTexto,String segundoTexto, String msgSucesso,String msgFalhou)
	{
		if(primeiroTexto.equalsIgnoreCase(segundoTexto))
		{
			System.out.println(msgSucesso);
		}
		else
		{
			System.err.println(msgFalhou);
		fail();	
		}
		
	}
	
	/**
	 * Verifica se o resultado esperado � verdadeiro e imprime no console o resultado
	 * @param resultadoEsperado
	 * Passe o retorno esperado.
	 * @param msgSucesso
	 * Passe uma mensagem de sucesso caso o resultado seja positivo.
	 * @param msgFalhou
	 * Passe uma mensagem de falha caso o resultado seja negativo.
	 */
	public void assertBoolean(boolean resultadoEsperado, String msgSucesso,String msgFalhou)
	{
		if(resultadoEsperado)
		{
			System.out.println(msgSucesso);
		}
		else
		{
			System.err.println(msgFalhou);
		}
		
	}
	
	
	public String criarTextoRandon(String texto)
	{
		
		int i = (int) (1+Math.random()*1000);
		texto = texto + " " + i;
		return texto;
	}
	
	
	public String criarNumeroRandon(String texto)
	{
		
		int i = (int) (1+Math.random()*1000);
		texto = texto + "" + i;
		return texto;
	}

	
	
	/**
	 * Mata todos os processos do item passado por par�metro
	 * @param processo - Passe o processo que deseja matar.
	 * @return
	 */
	public  boolean kill(String processo) {  
        try {  
            String line;  
            Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");  
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));  
            while ((line = input.readLine()) != null) {  
                if (!line.trim().equals("")) {  
                    if (line.substring(1, line.indexOf("\"", 1)).equalsIgnoreCase(processo)) {  
                        Runtime.getRuntime().exec("taskkill /F /IM " + line.substring(1, line.indexOf("\"", 1)));  
                        return true;  
                    }  
                }  
            }  
            input.close();  
        } catch (Exception err) {  
            err.printStackTrace();  
        }  
        return false;  
    }  
	
	
	   
    public void colocarNaMemoria(String texto)
	{
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();  
        StringSelection selection = new StringSelection(texto);  
        clipboard.setContents(selection, null); 
	}
    
    /**
	 * String nomeArquivo = "Numeros.txt";
	 * String mensagem = "Mensagem Teste";
	 *
	 * funcao.usarUtilidade().logTXT(nomeArquivo, mensagem);
	 * @param nomeArquivo
	 * @param msg
	 */
    public void logTXT(String nomeArquivo, String msg, String local){
    	File arquivo;  
  	   	FileWriter escreve;  
 	   	arquivo = new File(local+nomeArquivo);  
  	   	if(arquivo.exists()== false){  
	         try {  
	            arquivo.createNewFile();  
	         } catch (IOException e) {  
	              
	            e.printStackTrace();  
	         }  
	      }  
	      try {  
	         escreve = new FileWriter(local+nomeArquivo, true);  
	         //Escrever o conte�do
	         escreve.write(msg+"\r");   
	         escreve.close();  
	      } catch (IOException e) {  
	         e.printStackTrace();  
	      }  
	}
    
    public String pegarData(int subtrair)
    {
    	// data final igual a hoje  
	    Date dataFinal = new Date();  
	      
	    // usa calendar para subtrair data  
	    Calendar calendarData = Calendar.getInstance();  
	    calendarData.setTime(dataFinal);  
	      
	    int numeroDiasParaSubtrair = subtrair;  
	      
	    // achar data de in�cio  
	    calendarData.add(Calendar.DATE,numeroDiasParaSubtrair);  
	    Date dataInicial = calendarData.getTime();
	    String data = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dataInicial);
	    return data;
    }
 
}
