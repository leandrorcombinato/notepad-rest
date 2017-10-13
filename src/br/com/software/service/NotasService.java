package br.com.software.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.software.dao.NotaDAO;
import br.com.software.entidade.Nota;

@Path("/notas")
public class NotasService {
	
	private NotaDAO notaDAO;
	
	@PostConstruct
	private void init() {
		notaDAO = new NotaDAO();
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarNotas() {
		List<Nota> responses = new ArrayList<>();
		try {
			responses = notaDAO.listarNotas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		GenericEntity<List<Nota>> lista = new GenericEntity<List<Nota>>(responses) {};
        return Response.ok(lista).build();
	}
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addNota(Nota nota) {
		String msg = "";

		System.out.println(nota.getTitulo());
		
		try {
			int idGerado = notaDAO.addNota(nota);
			msg = "Nota "+String.valueOf(idGerado)+" add com sucesso! ";
			nota.setId(idGerado);
		    String json = Nota.converterFromJson(nota);
		    Nota entity = Nota.converterFromEntity(json, Nota.class);
		    System.out.println("Entity Nota Descrição "+entity.getDescricao());
		    return Response.ok(json, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			msg = "Erro ao add a nota! "+e.getMessage();
			return Response.status(Response.Status.NOT_FOUND).entity(msg).build();
		}

	}

	@GET
	@Path("/get/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response buscarPorId(@PathParam("id") int idNota) {
		Nota nota = new Nota();
		String msg = "";
		try {
			nota = notaDAO.buscarNotaPorId(idNota);
		} catch (Exception e) {
			msg = "Numero da Nota "+idNota+" não foi encontrado! "+e.getMessage();
			return Response.status(Response.Status.NOT_FOUND).entity(msg).build();
		}

		msg = ", { message: Numero da Nota "+idNota+" foi encontrado com sucesso. }";
		return Response.status(Response.Status.OK).entity(Nota.converterFromJson(nota).concat(msg)).build();
	}

	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response editarNota(Nota nota, @PathParam("id") int idNota) {
		String msg = "";
		
		System.out.println(nota.getTitulo());
		
		try {
			notaDAO.editarNota(nota, idNota);
			
			msg = "Nota editada com sucesso!";
			return Response.ok(Nota.converterFromJson(nota), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			msg = "Erro ao editar a nota! "+e.getMessage();
			return Response.status(Response.Status.NOT_FOUND).entity(msg).build();
		}
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response removerNota(@PathParam("id") int idNota) {
		String msg = "";
		
		try {
			notaDAO.removerNota(idNota);
			
			msg = "Nota removida com sucesso!";
			return Response.ok(msg, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			msg = "Erro ao remover a nota!";
			return Response.status(Response.Status.NOT_FOUND).entity("Message: "+msg).build();
		}
		
	}
	
}
