package com.example.xmlgmldatasources.service;


import com.example.xmlgmldatasources.db1.entity.Street;
import com.example.xmlgmldatasources.db1.repository.StreetRepository;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
public class XMLService {


      public String user;//="dostep@publiczny";
      public String password;//="publiczny";

   // user="dostep@publiczny";
   // password="publiczny";
    @Autowired
   StreetRepository repository;

    private final Logger logger = LoggerFactory.getLogger(XMLService.class);
    //--------------------------------------------------------------------------------------------

    private String URL="https://geoportal.wroclaw.pl/rest/rest/streets/paged/0/070.xml";
   // private String URL="7000.xml";
    //--------------------------------------------------------------------------------------------
    private String createBasicAuthHeaderValue() {
        String auth = user + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
        String authHeaderValue = "Basic " + new String(encodedAuth);
        return authHeaderValue;
    }
    //--------------------------------------------------------------------------------------------
    private final class BasicAuthenticator extends Authenticator {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password.toCharArray());
        }
    }
    //--------------------------------------------------------------------------------------------
    private void setAuthenticator() {
        Authenticator.setDefault(new BasicAuthenticator());
    }
    //--------------------------------------------------------------------------------------------
    private HttpURLConnection createConnection(String urlString) throws MalformedURLException, IOException, ProtocolException {
        URL url = new URL(String.format(urlString));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }
    //--------------------------------------------------------------------------------------------
    public List<Street> parseStreet(){
      List<Street> streets = new ArrayList<>()    ;
      HttpURLConnection connection = null;
       try{
         connection = createConnection(URL);
            connection.setRequestProperty("Authorization", createBasicAuthHeaderValue());
            System.out.println(" otrzymalismy kod odpowiedzi" + connection.getResponseCode());

            InputStream inputStream =  connection.getInputStream();

            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //Document dom = documentBuilder.parse(URL);
            Document dom = documentBuilder.parse(inputStream);

            // normalize XML response
            dom.getDocumentElement().normalize();

           // street = new Street(Integer.parseInt(dom.getElementsByTagName("id").item(0).getTextContent());
            NodeList nodeList = dom.getElementsByTagName("street");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                    if(node.getNodeType() == Node.ELEMENT_NODE) {
                        Element elem = (Element) node;
                         Street street = new Street(
                                Integer.parseInt(elem.getElementsByTagName("id").item(0).getTextContent()),
                                elem.getElementsByTagName("namePrefix").item(0).getTextContent(),
                                elem.getElementsByTagName("namePrefix2").item(0).getTextContent(),
                                elem.getElementsByTagName("nameOfficial").item(0).getTextContent(),
                                 elem.getElementsByTagName("typeSymbol").item(0).getTextContent(),
                                 elem.getElementsByTagName("typeName").item(0).getTextContent(),
                                 elem.getElementsByTagName("status").item(0).getTextContent()
                        );
                        streets.add(street);
                        repository.save(street);
                    }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
      return streets;
    }
    //--------------------------------------------------------------------------------------------
    public Integer countStreet(){

        Integer liczbaUlic = null;

        HttpURLConnection connection = null;
        try{
            connection = createConnection(URL);
            connection.setRequestProperty("Authorization", createBasicAuthHeaderValue());
            System.out.println(" otrzymalismy kod odpowiedzi" + connection.getResponseCode());

            InputStream inputStream =  connection.getInputStream();

            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //Document dom = documentBuilder.parse(URL);
            Document dom = documentBuilder.parse(inputStream);

            // normalize XML response
            dom.getDocumentElement().normalize();

            // street = new Street(Integer.parseInt(dom.getElementsByTagName("id").item(0).getTextContent());
            NodeList nodeList = dom.getElementsByTagName("streets-size");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                   liczbaUlic=Integer.parseInt(elem.getElementsByTagName("size").item(0).getTextContent());

                }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return liczbaUlic;
    }
    //--------------------------------------------------------------------------------------------


    //--------------------------------------------------------------------------------------------
}
