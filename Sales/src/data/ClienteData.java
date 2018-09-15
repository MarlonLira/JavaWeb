package data;


import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Cliente;



public class ClienteData {
    
    
    public void Incluir(Cliente objCliente) throws SQLException{
      
      Connection con = Conexao.getConnection();
      PreparedStatement stmt =  null; 
      
      try{
      stmt = con.prepareStatement("insert into clientes (nome_Cliente, endecreco_Cliente, email_Cliente, numContato_CLiente, cpf_Cliente) values (?,?,?,?,?)");
      stmt.setString(1, objCliente.getNomeCliente());
      stmt.setString(2, objCliente.getEnderecoCliente());
      stmt.setString(3, objCliente.getEmailCliente());
      stmt.setString(4, objCliente.getNumeroCliente());
      stmt.setString(5, objCliente.getCpfCliente());
      
      
      stmt.executeUpdate();
      
      
      stmt.close();
      }catch(SQLException e){
          JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e);
      }finally{
          Conexao.closeConnection(con, stmt);
      }    
}
    
 
    public List<Cliente> consultar(){
      
      Connection con = Conexao.getConnection();
      PreparedStatement stmt =  null; 
      ResultSet rs =  null;
      
      List<Cliente> clienteList = new ArrayList<>();
      
        try {
            stmt = con.prepareStatement("SELECT * FROM clientes");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Cliente cliente = new Cliente();
                
                cliente.setId(rs.getInt("id_Cliente"));
                cliente.setNomeCliente(rs.getString("nome_cliente"));
                cliente.setEnderecoCliente(rs.getString("endecreco_cliente"));
                cliente.setEmailCliente(rs.getString("email_Cliente"));
                cliente.setNumeroCliente(rs.getString("numContato_CLiente"));
                cliente.setCpfCliente(rs.getString("cpf_Cliente"));
                clienteList.add(cliente);
                
            }
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }finally{
           Conexao.closeConnection(con, stmt, rs);
        }
        
        return clienteList;
    }
  
    public void Atualizar(Cliente objCliente) throws SQLException{
      
      Connection con = Conexao.getConnection();
      PreparedStatement stmt =  null; 
      
      try{
      stmt = con.prepareStatement("UPDATE clientes SET nome_Cliente = ?, endecreco_Cliente = ?, email_Cliente = ?, numContato_CLiente = ?, cpf_Cliente = ? WHERE id = ? ");
      stmt.setString(1, objCliente.getNomeCliente());
      stmt.setString(2, objCliente.getEnderecoCliente());
      stmt.setString(3, objCliente.getEmailCliente());
      stmt.setString(4, objCliente.getNumeroCliente());
      stmt.setString(5, objCliente.getCpfCliente());
      stmt.setInt(6,  objCliente.getid());
      
      stmt.executeUpdate();
      
      
      stmt.close();
      }catch(SQLException e){
          JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e);
      }finally{
          Conexao.closeConnection(con, stmt);
      }    
}   
    
}
   
    
