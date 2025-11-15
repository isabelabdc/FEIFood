/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Bebida extends Alimento implements Imposto{

    public Bebida() {
    }

    public Bebida(int idAlimento, String nome, String descricao, String categoria, String tipo, double preco) {
        super(idAlimento, nome, descricao, categoria, tipo, preco);
    }
    
    @Override
    public double calcularImposto() {
        if(categoria.equals("Álcool")){
            return preco*0.10;
        }
        return 0.00;
    }
}
