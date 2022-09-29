package org.troyka.clientejaxrs;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.internal.BasicAuthentication;
import org.troyka.clientejaxrs.models.Curso;
import org.troyka.clientejaxrs.models.Instructor;

import java.util.List;

public class Main {
    //private static String id = "/5";
    private static Response response;
    private static Curso curso;
    //private static Curso nuevoCurso;

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        WebTarget rootUri = client.target("http://localhost:8080/webapp-jaxrs-jaas/api").path("cursos");
        rootUri.register(new BasicAuthentication("omar", "1914"));
        System.out.println(byId(rootUri,"/5"));
        //listar(rootUri);
    }

    private static Curso nuevoCurso() {
        Curso nCurso = new Curso();
        nCurso.setName("Bootstrap v5");
        nCurso.setDescripcion("Framework para estilos CSS");
        Instructor instructor= new Instructor();
        instructor.setId(1L);
        nCurso.setInstructor(instructor);
        nCurso.setDuracion(60.0);
        return nCurso;
    }

    private static void guardar(WebTarget rootUri) {
        System.out.println("================== Guardando \n");
        Entity<Curso> entityHeader = Entity.entity(nuevoCurso(), MediaType.APPLICATION_JSON);
        curso = rootUri.request(MediaType.APPLICATION_JSON).post(entityHeader, Curso.class);
        System.out.println(curso);
    }

    private static void listar(WebTarget rootUri) {
        System.out.println("================== Listando \n");
        response = rootUri.request(MediaType.APPLICATION_JSON).get();
        List<Curso> cursos = response.readEntity(new GenericType<List<Curso>>(){});
        cursos.forEach(System.out::println);
    }

    private static Curso byId(WebTarget rootUri,String id) {
        response = rootUri.path(id)
                .request(MediaType.APPLICATION_JSON)
                .get();
        curso = response.readEntity(Curso.class);
        return curso;
    }
    private static void editar(WebTarget rootUri,String id){
        System.out.println("==============Editando \n");
        Curso cursoEdit = byId(rootUri,id);
        cursoEdit.setDescripcion("Curso spring-cloud actualizado");
        Entity<Curso> entityHeader = Entity.entity(cursoEdit, MediaType.APPLICATION_JSON);
        Curso respcurso = rootUri.path("/" + cursoEdit.getId()).request(MediaType.APPLICATION_JSON)
                .put(entityHeader, Curso.class);
        System.out.println(respcurso);
    }

    private static void eliminar(WebTarget rootUri,String id) {
        Curso elCurso = byId(rootUri,id);
        response= rootUri.path("/" + elCurso.getId()).request(MediaType.APPLICATION_JSON)
                .delete();
        System.out.println("ProductoEliminado " + response.getStatus());
    }
}
