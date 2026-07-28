package logica;

import datos.AuditoriaDAO;
import entidades.Auditoria;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AuditoriaLOG {

    public static void registrarAuditoria(int idUsuario, String modulo, String operacion){

        Auditoria a = new Auditoria(idUsuario, LocalDate.now(), LocalTime.now(), modulo, operacion);

        AuditoriaDAO.registrarAuditoria(a);
    }

    public static ArrayList<Auditoria> listarAuditorias(){
        return AuditoriaDAO.listarAuditorias();
    }
}
