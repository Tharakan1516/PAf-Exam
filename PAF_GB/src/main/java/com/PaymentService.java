package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Payment;


@Path("/Payment")

public class PaymentService {
	
	

	Payment Obj = new Payment();

	@GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
	public String readPayment(){
 
		return Obj.readPayment();
 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("Name") String Name,
				@FormParam("ProductName") String ProductName,
				@FormParam("ProductId") String ProductId,
				@FormParam("Amount") String Amount,
				@FormParam("PaymentType") String PaymentType,
				@FormParam("CardNo") String CardNo) 
	{
		String output = Obj.insertPayment(Name, ProductName, ProductId, Amount, PaymentType, CardNo );
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String PaymentData)
	{
	//Convert the input string to a JSON object
	 JsonObject Object = new JsonParser().parse(PaymentData).getAsJsonObject();
	//Read the values from the JSON object
	//ID CardHolderFirstname CardHolderLastname Amount mobile PaymentMethod
	 String Id = Object.get("Id").getAsString();
	 String Name = Object.get("Name").getAsString();
	 String ProductName = Object.get("ProductName").getAsString();
	 String ProductId = Object.get("ProductId").getAsString();
	 String Amount = Object.get("Amount").getAsString();
	 String PaymentType = Object.get("PaymentType").getAsString();
	 String CardNo = Object.get("CardNo").getAsString();
	 String output = Obj.updatePayment(Id, Name, ProductName, ProductId, Amount, PaymentType, CardNo);
	
	 return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String PaymentData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(PaymentData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String Id = doc.select("Id").text();
	 String output = Obj.deletePayment(Id);
	return output;
	}


}



