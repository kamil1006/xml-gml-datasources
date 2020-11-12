package com.example.xmlgmldatasources.controller;


import com.example.xmlgmldatasources.db1.entity.*;
import com.example.xmlgmldatasources.excel.ExcelReportView;
import com.example.xmlgmldatasources.excel.UliceToExcel;
import com.example.xmlgmldatasources.db1.repository.StreetRepository;
import com.example.xmlgmldatasources.db1.repository.UzytkownikRepository;
import com.example.xmlgmldatasources.service.XMLService;
import com.example.xmlgmldatasources.service.XMLServiceGML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    //-------------------------------------------------------------------------------------------
    @Autowired
    private XMLService xmlService;
    @Autowired
    private UzytkownikRepository uzytkownikRepository;
    @Autowired
    private StreetRepository streetRepository;

    private  Integer iloscUlic;

    @Autowired
    private XMLServiceGML xmlServiceGML;


    //-------------------------------------------------------------------------------------------
    @RequestMapping("/")
    public String stronaStartowa(Model model){
        model.addAttribute("uzytkownik",new Uzytkownik());



    return "th-home";
    }
    //-------------------------------------------------------------------------------------------
    @RequestMapping  ("/gml")
    public ModelAndView pobierajUlice(Model model){


        List<Ulica> ulice = (List<Ulica>) xmlServiceGML.parseStreet();
        model.addAttribute("uliceGML",ulice);

        List<Adres> adresy = (List<Adres>) xmlServiceGML.pobierzHrefyZTerytami();
        model.addAttribute("adresyGML",adresy);

        return  new ModelAndView(new UliceToExcel(), "uliceGML", ulice);
    }
    //-------------------------------------------------------------------------------------------
    @PostMapping("/ulice2")
    public String enterApplication(@ModelAttribute("uzytkownik") Uzytkownik uzytkownik, Model model) {

        xmlService.setPassword(uzytkownik.getHaslo());
        xmlService.setUser(uzytkownik.getNazwa());
        uzytkownikRepository.save(uzytkownik);

        String url = "https://geoportal.wroclaw.pl/rest/rest/streets/paged/size.xml";
        xmlService.setURL(url);
         iloscUlic = xmlService.countStreet();
       // System.out.println("tyle jest ulic " +xmlService.countStreet());
        model.addAttribute("iloscUlic",iloscUlic);
        model.addAttribute("zakres",new ZakresId());

        return "th-menu";
    }
    //-------------------------------------------------------------------------------------------
    @PostMapping("/wyswietlUlice")
    public String enterApplication(@ModelAttribute("zakres") ZakresId zakresId, Model model) {


        //"https://geoportal.wroclaw.pl/rest/rest/streets/paged/0/070.xml"
        String url = "https://geoportal.wroclaw.pl/rest/rest/streets/paged/"+zakresId.getO_d()+"/"+zakresId.getD_o()+".xml";
        xmlService.setURL(url);

        for(Street street:xmlService.parseStreet()){
            System.out.println(street);
        }
        model.addAttribute("iloscUlic",iloscUlic);

        return "th-menu";
    }
   // @RequestMapping(method=RequestMethod.GET)
    //-------------------------------------------------------------------------------------------
    @RequestMapping  ("/zapiszExcel")
    public ModelAndView zapiszExcel(Model model){

        List<Street> ulice = (List<Street>) streetRepository.findAll();
        model.addAttribute("ulice",ulice);

        return  new ModelAndView(new ExcelReportView(), "ulice", ulice);
    }
    //-------------------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------------------
}
