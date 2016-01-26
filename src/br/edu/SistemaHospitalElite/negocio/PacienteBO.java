/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.SistemaHospitalElite.negocio;

import br.edu.SistemaHospitalElite.apresentacao.Paciente;
import br.edu.SistemaHospitalElite.persistencia.PacienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rayssa
 */
public class PacienteBO {
    
    public List<Paciente> buscarTodos() throws SQLException {
        PacienteDAO pacienteDAO = new PacienteDAO();
        return pacienteDAO.buscarTodos();
    }

    public void Salvar(Paciente pacienteEmEdicao) throws SQLException {
        this.ValidarCampos(pacienteEmEdicao);
        //this.idadeNaoPermitida(pacienteEmEdicao);
        //this.rendaNaoPermitida(pacienteEmEdicao);
        //this.cpfJaExiste(pacienteEmEdicao);
        PacienteDAO pacienteDAO = new PacienteDAO();
        pacienteDAO.criar(pacienteEmEdicao);
       
    }

    private void ValidarCampos(Paciente pacienteEmEdicao) {
        if(pacienteEmEdicao.getNome().trim().equals("")){
            throw new CampoObrigatorioException("Campo Nome do paciente está vazio!\n");
        }
        if(pacienteEmEdicao.getCpf().trim().equals("")){
            throw new CampoObrigatorioException("Campo CPF do paciente está vazio!\n");
       }
        
        
    }

    /*private void idadeNaoPermitida(Paciente pacienteEmEdicao) {
            
         if((pacienteEmEdicao.getIdade() >= 12 )||(pacienteEmEdicao.getIdade()<=65)) {
            throw new IdadeInvalidaException("Idade permitida!\n");
        }
         else{
             System.out.println("Idade inválida!");
         }
        
    }

    private void rendaNaoPermitida(Paciente pacienteEmEdicao) {
        
    }
*/
  /*  private void cpfJaExiste(Paciente pacienteEmEdicao) throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        PacienteDAO pacienteDAO = new PacienteDAO();
        pacienteDAO.buscarCpfPaciente(pacienteEmEdicao);
        for(Paciente p : pacientes){
           if(p.getCpf().equals(pacienteEmEdicao.getCpf()))
           {
               throw new CpfInvalidoException("Já existe um jogador como este cpf!");
           }
       }
        
    }*/

  
   
}
