package org.acc.rest.jersy;

import java.io.File;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;
 
@Path("/hello")
public class HelloWorldService {
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
	

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtmlUniversityInfo(){
        return "<html><body>test</body></html>";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTextUniversityInfo(){
        return "test";
    }
    
    @GET
	@Path("{id : \\d+}") //support digit only
	public Response getUserById(@PathParam("id") String id) {
 
	   return Response.status(200).entity("getUserById is called, id : " + id).build();
 
	}
 
	@GET
	@Path("/username/{username : [a-zA-Z][a-zA-Z_0-9]}")
	public Response getUserByUserName(@PathParam("username") String username) {
 
	   return Response.status(200)
		.entity("getUserByUserName is called, username : " + username).build();
 
	}
 
	@GET
	@Path("/books/{isbn : \\d+}")
	public Response getUserBookByISBN(@PathParam("isbn") String isbn) {
 
	   return Response.status(200)
		.entity("getUserBookByISBN is called, isbn : " + isbn).build();
 
	}
	
	@GET
	@Path("{year}/{month}/{day}")
	public Response getUserHistory(
			@PathParam("year") int year,
			@PathParam("month") int month, 
			@PathParam("day") int day) {
 
	   String date = year + "/" + month + "/" + day;
 
	   return Response.status(200)
		.entity("getUserHistory is called, year/month/day : " + date)
		.build();
 
	}
	
	@GET
	@Path("/query1")
	public Response getUsers(
		@QueryParam("from") int from,
		@QueryParam("to") int to,
		@QueryParam("orderBy") List<String> orderBy) {
 
		return Response
		   .status(200)
		   .entity("getUsers is called, from : " + from + ", to : " + to
			+ ", orderBy" + orderBy.toString()).build();
 
	}
	
	@GET
	@Path("/query2")
	public Response getUsers(@Context UriInfo info) {
 
		String from = info.getQueryParameters().getFirst("from");
		String to = info.getQueryParameters().getFirst("to");
		List<String> orderBy = info.getQueryParameters().get("orderBy");
 
		return Response
		   .status(200)
		   .entity("getUsers is called, from : " + from + ", to : " + to
			+ ", orderBy" + orderBy.toString()).build();
 
	}
	
	@GET
	@Path("/query3")
	public Response getUsers1(
		@DefaultValue("1000") @QueryParam("from") int from,
		@DefaultValue("999")@QueryParam("to") int to,
		@DefaultValue("name") @QueryParam("orderBy") List<String> orderBy) {
 
		return Response
		   .status(200)
		   .entity("getUsers is called, from : " + from + ", to : " + to
			+ ", orderBy" + orderBy.toString()).build();
 
	}
	
	//4. URI Pattern : “/books/MatrixParam/2011;country=malaysia;author=mkyong”
	@GET
	@Path("/MatrixParam/{year}")
	public Response getBooks(@PathParam("year") String year,
			@MatrixParam("author") String author,
			@MatrixParam("country") String country) {
 
		return Response
			.status(200)
			.entity("getBooks is called, year : " + year
				+ ", author : " + author + ", country : " + country)
			.build();
 
	}
	
	@POST
	@Path("/add")
	public Response addUser(
		@FormParam("name") String name,
		@FormParam("age") int age) {
 
		return Response.status(200)
			.entity("addUser is called, name : " + name + ", age : " + age)
			.build();
 
	}
	
	@GET
	@Path("/get")
	public Response addUser(@HeaderParam("user-agent") String userAgent) {
 
		return Response.status(200)
			.entity("addUser is called, userAgent : " + userAgent)
			.build();
 
	}
	
	@GET
	@Path("/get1")
	public Response addUser(@Context HttpHeaders headers) {
 
		String userAgent = headers.getRequestHeader("user-agent").get(0);
 
		return Response.status(200)
			.entity("addUser is called, userAgent : " + userAgent)
			.build();
 
	}
	
	private static final String FILE_PATH = "c:\\Users\\ashish.g.agarwal\\Desktop\\lucene.txt";
	
	@GET
	@Path("/getFile")
	@Produces("text/plain")
	public Response getFile() {
 
		File file = new File(FILE_PATH);
 
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=\"file_from_server.log\"");
		return response.build();
 
	}
	
	private static final String IMAGE_FILE_PATH = "c:\\Users\\ashish.g.agarwal\\Desktop\\lucene.txt";
	 
	@GET
	@Path("/getImage")
	@Produces("image/png")
	public Response getImageFile() {
 
		File file = new File(IMAGE_FILE_PATH);
 
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=image_from_server.png");
		return response.build();
 
	}
	
	private static final String PDF_FILE_PATH = "c:\\Users\\ashish.g.agarwal\\Desktop\\my_docs\\acc_zms_Rent_Receipt_Format.pdf";
	 
	@GET
	@Path("/getPDF")
	@Produces("application/pdf")
	public Response getPDFFile() {
 
		File file = new File(PDF_FILE_PATH);
 
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
				"attachment; filename=new-android-book.pdf");
		return response.build();
 
	}
	
	private static final String XL_FILE_PATH = "c:\\Users\\ashish.g.agarwal\\Desktop\\Wizard_doc\\Defect_Activity_IDC_new.xlsx";
	 
	@GET
	@Path("/getXL")
	@Produces("application/vnd.ms-excel")
	public Response getXLFile() {
 
		File file = new File(XL_FILE_PATH);
 
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=new-excel-file.xls");
		return response.build();
 
	}
 
}