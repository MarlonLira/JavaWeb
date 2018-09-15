
package model;

public class Cliente {
     int id;
     String nomeCliente;
     String enderecoCliente;
     String emailCliente;
     String numeroCliente;
     String cpfCliente;

    public Cliente() {
        
    }

     public Cliente(String nomeCliente, String enderecoCliente, String emailCliente, String numeroCliente, String cpfCliente) {
        this.nomeCliente = nomeCliente;
        this.enderecoCliente = enderecoCliente;
        this.emailCliente = emailCliente;
        this.numeroCliente = numeroCliente;
        this.cpfCliente = cpfCliente;
    }
    
    public int getid() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
    }
    
    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
  
    
}
