package com.example.xmlgmldatasources.service;


import com.example.xmlgmldatasources.db1.entity.Adres;
import com.example.xmlgmldatasources.db1.entity.Punkt;
import com.example.xmlgmldatasources.db1.entity.Ulica;
import com.example.xmlgmldatasources.db1.repository.AdresRepository;
import com.example.xmlgmldatasources.db1.repository.PunktRepository;
import com.example.xmlgmldatasources.db1.repository.UlicaRepository;
import lombok.Getter;
import lombok.Setter;
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
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
public class XMLServiceGML {



    @Autowired
    UlicaRepository repository;
    @Autowired
    AdresRepository adresRepository;

    @Autowired
    PunktRepository punktRepository;


    private final Logger logger = LoggerFactory.getLogger(XMLServiceGML.class);
    //--------------------------------------------------------------------------------------------

    private String URL="gml.xml";

    //--------------------------------------------------------------------------------------------
    public List<Ulica> parseStreet(){
      List<Ulica> ulice = new ArrayList<>()    ;

       try{


            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //Document dom = documentBuilder.parse(URL);
            Document dom = documentBuilder.parse(URL);

            // normalize XML response
            dom.getDocumentElement().normalize();

           // street = new Street(Integer.parseInt(dom.getElementsByTagName("id").item(0).getTextContent());
            NodeList nodeList = dom.getElementsByTagName("mua:AD_NazwaUlicy");
            String a;
            String b;
            String c;
           //##################################3
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);


                    if(node.getNodeType() == Node.ELEMENT_NODE) {

                        Element elem = (Element) node;


                        if (elem.getElementsByTagName("mua:idTERYT").item(0).getTextContent() != null) {

                          a=elem.getElementsByTagName("mua:idTERYT").item(0).getTextContent();
                            c=elem.getElementsByTagName("mua:nazwaGlownaCzesc").item(0).getTextContent();
                            Ulica ulica;
                           try {
                               b = elem.getElementsByTagName("mua:przedrostek1Czesc").item(0).getTextContent();
                                ulica = new Ulica(
                                       (elem.getElementsByTagName("mua:idTERYT").item(0).getTextContent()),
                                       elem.getElementsByTagName("mua:przedrostek1Czesc").item(0).getTextContent(),
                                       elem.getElementsByTagName("mua:nazwaGlownaCzesc").item(0).getTextContent()

                               );
                           }
                           catch (Exception e){
                               ulica = new Ulica(
                                       (elem.getElementsByTagName("mua:idTERYT").item(0).getTextContent()),

                                       elem.getElementsByTagName("mua:nazwaGlownaCzesc").item(0).getTextContent()

                               );

                           }


                            ulice.add(ulica);
                            repository.save(ulica);
                            System.out.println(ulica.toString());
                        }
                    }
                    else{
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@nie node");
                    }
                System.out.println("i na dole" + i);
            }


           System.out.println("tyle jest nodow" +nodeList.getLength());
        }catch (Exception e){
            System.out.println("wyjatek" + e.getMessage());
           System.out.println(e.getStackTrace());
       }
      return ulice;
    }
    //--------------------------------------------------------------------------------------------
    public List<Adres> pobierzHrefy(){
        List<Adres> adresList = new ArrayList<>()    ;

        try{


            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //Document dom = documentBuilder.parse(URL);
            Document dom = documentBuilder.parse(URL);

            // normalize XML response
            dom.getDocumentElement().normalize();

            // street = new Street(Integer.parseInt(dom.getElementsByTagName("id").item(0).getTextContent());
            NodeList nodeList = dom.getElementsByTagName("mua:AD_Ulica");

            //##################################3
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
               if(node.getNodeType() == Node.ELEMENT_NODE) {

                    Element elem = (Element) node;
                    NodeList hrefy= elem.getElementsByTagName("mua:adres2");
                        for(int j=0;j< hrefy.getLength();j++){

                            Node node2 = hrefy.item(j);
                            Element elem2 = (Element) node2;
                            Adres adres =null;
                            try {

                                adres = new Adres(elem2.getAttribute("xlink:href"));
                                adresList.add(adres);
                                adresRepository.save(adres);
                                System.out.println(adres.toString());

                            }
                            catch (Exception e){    }

                        }





                    }

                else{
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@nie node");
                }

            }



        }catch (Exception e){
            System.out.println("wyjatek" + e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return adresList;
    }
    //--------------------------------------------------------------------------------------------
    public List<Adres> pobierzHrefyZTerytami(){
        List<Adres> adresList = new ArrayList<>()    ;

        try{


            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //Document dom = documentBuilder.parse(URL);
            Document dom = documentBuilder.parse(URL);

            // normalize XML response
            dom.getDocumentElement().normalize();

            // street = new Street(Integer.parseInt(dom.getElementsByTagName("id").item(0).getTextContent());
            NodeList nodeList = dom.getElementsByTagName("mua:AD_Ulica");

            //##################################3
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                String teryt;


                if(node.getNodeType() == Node.ELEMENT_NODE) {

                    Element elem = (Element) node;
                        NodeList hrefy= elem.getElementsByTagName("mua:adres2");



                                         teryt =elem.getElementsByTagName("mua:idTERYT").item(0).getTextContent();

                    for(int j=0;j< hrefy.getLength();j++){

                        Node node2 = hrefy.item(j);
                        Element elem2 = (Element) node2;
                        Adres adres =null;
                        try {

                            adres = new Adres(elem2.getAttribute("xlink:href"),teryt);
                            adresList.add(adres);
                            adresRepository.save(adres);
                            System.out.println(adres.toString());

                        }
                        catch (Exception e){    }

                    }





                }

                else{
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@nie node");
                }

            }



        }catch (Exception e){
            System.out.println("wyjatek" + e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return adresList;
    }

    //--------------------------------------------------------------------------------------------
    public List<Punkt> pobierzPunktyAdresowe(){
        List<Punkt> punkty = new ArrayList<>()    ;

        try{


            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //Document dom = documentBuilder.parse(URL);
            Document dom = documentBuilder.parse(URL);

            // normalize XML response
            dom.getDocumentElement().normalize();

            // street = new Street(Integer.parseInt(dom.getElementsByTagName("id").item(0).getTextContent());
            NodeList nodeList = dom.getElementsByTagName("mua:AD_PunktAdresowy");
            String a;
            String b;
            String c;
            //##################################3
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);
              if(node.getNodeType() == Node.ELEMENT_NODE) {

                    Element elem = (Element) node;

                    a=elem.getElementsByTagName("mua:numerPorzadkowy").item(0).getTextContent();
                        c=elem.getElementsByTagName("mua:kodPocztowy").item(0).getTextContent();
                       Punkt punkt = null;
                        try {
                            b = elem.getAttribute("gml:id");
                            punkt = new Punkt(  b,   a, c   );
                        }
                        catch (Exception e){


                        }


                        punkty.add(punkt);
                        punktRepository.save(punkt);
                        System.out.println(punkt.toString());
                    }


            }



        }catch (Exception e){
            System.out.println("wyjatek" + e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return punkty;
    }
    //--------------------------------------------------------------------------------------------
}
