/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class PedidoAlimento {
    //atributos:
    private Pedido pedido;
    private Alimento alimento;
    private int quantidade;
    private double subtotal;
    
    //construtores:
    public PedidoAlimento() {
    }

    public PedidoAlimento(Pedido pedido, Alimento alimento, int quantidade) {
        this.pedido = pedido;
        this.alimento = alimento;
        this.quantidade = quantidade;
        atualizarSubtotal();
    }  
    
    //getters e setters:
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {    
        this.pedido = pedido;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
        atualizarSubtotal();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        atualizarSubtotal();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    //metodo:
    public void atualizarSubtotal(){
       double precoUnitario = alimento.getPreco();
       double imposto = 0;
       if (alimento instanceof Imposto alcool){
           imposto = alcool.calcularImposto();
       }
       this.subtotal = (precoUnitario + imposto) * quantidade;
    }
    
}
