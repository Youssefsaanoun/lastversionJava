package myproject.java;

import java.util.List;
import java.util.Optional;

import Models.Etudiant;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repositoryImplementation.RepositoryServise;
import servisesImplementation.EtudiantServisesImplementation;

@Path("/myresource")
public class MyResource {
    private final EtudiantServisesImplementation etudiantServisesImplementation;

    public MyResource() {
        this.etudiantServisesImplementation = new EtudiantServisesImplementation(new RepositoryServise());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Etudiant> getEtudiants() {
        return etudiantServisesImplementation.GetAllEtudiant();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    
     
    public void deleteEtudiant(@PathParam("id") Long id ) {
    	etudiantServisesImplementation.DeleteEtudiant(id);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response ajouteEtudiant(Etudiant etu) {
        etudiantServisesImplementation.AddEtudiant(etu);
        return Response.status(Response.Status.CREATED).entity(etu).build();
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Boolean edidtEtudiant(@PathParam("id") Long id ,Etudiant newetud) {
    	 return etudiantServisesImplementation.modifEtudiant(id, newetud);
    	
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Etudiant getEtudiantById(@PathParam("id")Long id  ) {
        return etudiantServisesImplementation.getEudiantById(id);
    }

    
    
}