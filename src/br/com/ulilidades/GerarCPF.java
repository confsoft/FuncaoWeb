package br.com.ulilidades;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Random;



public class GerarCPF {

	    /**
	     * Gera um CPF válido e retorna o mesmo.
	     * @return
	     * Retorna o CPF gerado.
	     * @author Rogério Figueiredo.
	     */
	    public String gerarCPF(){

	        Random r = new Random();

	        StringBuilder sbCpfNumber = new StringBuilder();

	        for(int i = 0; i < 9; i++){

	            sbCpfNumber.append(r.nextInt(9));

	        }

	        return gerarDigitosCPF(sbCpfNumber.toString());

	    }

	   
	    /**
	     * Válida um CPF.
	     * @param cpf
	     * @return
	     * Retorna um booleano.
	     * @author Rogério Figueiredo.
	     */
	    public boolean ValidaCPF(String cpf){

	        if(cpf.length() == 11){

	            if(cpf.equals(gerarDigitosCPF(cpf.substring(0, 9)))){

	                return true;

	            }

	        }

	        return false;

	    }

	    /* Gera digitos validadores  */
	    private String gerarDigitosCPF(String digitsBase) {

	        StringBuilder sbCpfNumber = new StringBuilder(digitsBase);

	        int total = 0;

	        int multiple = digitsBase.length() + 1;

	        for (char digit : digitsBase.toCharArray()) {

	            long parcial = Integer.parseInt(String.valueOf(digit)) * (multiple--);

	            total += parcial;

	        }

	        int resto = Integer.parseInt(String.valueOf(Math.abs(total % 11)));

	        if (resto < 2) {

	            resto = 0;

	        } else {

	            resto = 11 - resto;

	        }

	        sbCpfNumber.append(resto);

	        if (sbCpfNumber.length() < 11) {

	            return gerarDigitosCPF(sbCpfNumber.toString());

	        }

	        return sbCpfNumber.toString();

	    }
	    
	   /**
	    * Gera um CPF e coloca o mesmo na memória, use Ctrl+v para colar o mesmo.
	    * @author Rogério Figueiredo.
	    */
	   
	    public void colocarCpfNaMemoria()
		{
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();  
	        String text = gerarCPF();
	        StringSelection selection = new StringSelection(text);  
	        clipboard.setContents(selection, null); 
		}

}	
