/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Alejandro
 */
public final class Vehiculo {
    private String placa;
    private int horaEntrada, horaSalida, ValorHora;
    
    
    Vehiculo (String placa, int horaEntrada, int horaSalida, int ValorHora) {
        setPlaca(placa);
        setHoraEntrada(horaEntrada);
        setHoraSalida(horaSalida);
        setValorHora(ValorHora);
    }
    
    // Metodo set
    public void setPlaca(String n) {placa = n;}
    public void setHoraEntrada(int px) {horaEntrada = px;}
    public void setHoraSalida(int px) {horaSalida = px;}
    public void setValorHora(int cr) {ValorHora = cr;}
    
    // Metodo get
    public String getPlaca() {return placa;}
    public int getHoraEntrada() {return horaEntrada;}
    public int getHoraSalida() {return horaSalida;}
    public int getValorHora() {return ValorHora;}
    
    public void mostrarVehiculo(){
        double valorapagar = getHoraSalida() - getHoraEntrada() * getValorHora();
        System.out.print(" placa: "+ getPlaca() +" HoraEntrada: "+ getHoraEntrada() + " HoraSalida: " + getHoraSalida()+ " Valor a Pagar: "+ valorapagar);
}
    
    
    
    
}

