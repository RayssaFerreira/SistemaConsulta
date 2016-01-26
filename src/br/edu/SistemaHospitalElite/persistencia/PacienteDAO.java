/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.SistemaHospitalElite.persistencia;

import br.edu.SistemaHospitalElite.apresentacao.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rayssa
 */
public class PacienteDAO {

    private static final String SQL_INSERT = "INSERT INTO PACIENTE(NOME, CPF, IDADE, RENDA) VALUES (?, ?, ?, ?)";
    //private static final String SQL_BUSCAR_BY_CPF = "SELECT * FROM PACIENTE WHERE CPF = ?";
     private static final String SQL_BUSCA_TODOS = "SELECT * FROM PACIENTE ORDER BY NOME";
    public void criar(Paciente pacienteEmEdicao) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_INSERT);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)
            comando.setString(1, pacienteEmEdicao.getNome());
            comando.setString(2, pacienteEmEdicao.getCpf());
            comando.setInt(3, pacienteEmEdicao.getIdade());
            comando.setDouble(4, pacienteEmEdicao.getRenda());
         
            //Executa o comando
            comando.execute();
            //Persiste o comando no banco de dados
            conexao.commit();
            //System.out.println("Manobra cadastrada com sucesso!");
        } catch (Exception e) {
            //Caso aconteça alguma exeção é feito um rollback para o banco de
            //dados retornar ao seu estado anterior.
            if (conexao != null) {
                conexao.rollback();
            }
            throw e;
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando);
        }
    }

    /*public List<Paciente> buscarCpfPaciente(Paciente pacienteEmEdicao) throws SQLException {
        Connection conexao =  null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Paciente> listaPacientes = new ArrayList<>();
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_BUSCAR_BY_CPF);
            //Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                Paciente paciente = this.extrairLinhaResultadoBuscarTodosPacientes(resultado);
                //Adiciona um item à lista que será retornada
                listaPacientes.add(paciente);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return listaPacientes;
    }

    private Paciente extrairLinhaResultadoBuscarTodosPacientes(ResultSet resultado) throws SQLException {
        Paciente paciente = new Paciente();
        paciente.setNome(resultado.getString(1));
        paciente.setCpf(resultado.getString(2));
        paciente.setIdade(resultado.getInt(3));
        paciente.setRenda(resultado.getDouble(4));
        
        return paciente;
    }
*/    

   public List<Paciente> buscarTodos() throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        Paciente paciente = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        PacienteDAO pacienteDAO = new PacienteDAO();
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCA_TODOS);
            resultado = comando.executeQuery();
            while (resultado.next()) {
                paciente = new Paciente();
                paciente.setNome(resultado.getString(1));
                paciente.setCpf(resultado.getString(2));
                paciente.setIdade(resultado.getInt(3));
                paciente.setRenda(resultado.getDouble(4));
                
                pacientes.add(paciente);
            }

        } catch (Exception e) {
            if (conexao != null) {
//                conexao.rollback();
            }
//            throw new RuntimeException();
        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
        return pacientes;
    }
}
