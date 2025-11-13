/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

public class Pedido {
    //atributos: 
    private int idPedido;
    private Usuario usuario;
    private ArrayList<PedidoAlimento> itens = new ArrayList<>();
    private double precoTotal;
    private String status;
    private int avaliacao;
    
    //construtores:
    public Pedido() {
    }

    public Pedido(int idPedido, Usuario usuario, ArrayList<PedidoAlimento> itens, double precoTotal, String status, int avaliacao) {
        this.idPedido = idPedido;
        this.usuario = usuario;
        this.itens = itens;
        this.precoTotal = precoTotal;
        this.status = status;
        this.avaliacao = avaliacao;
    }

    //getters e setters:
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<PedidoAlimento> getItens() {
        return itens;
    }

    public void setItens(ArrayList<PedidoAlimento> itens) {
        this.itens = itens;
        atualizarPrecoTotal();
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
    
    //metodo:
    public void atualizarPrecoTotal(){
        precoTotal = 0;
        for(PedidoAlimento i : itens){
            precoTotal += i.getSubtotal();
        }
    }
    
}
