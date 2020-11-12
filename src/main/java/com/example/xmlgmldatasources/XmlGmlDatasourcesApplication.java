package com.example.xmlgmldatasources;


import com.example.xmlgmldatasources.db1.entity.Adres;
import com.example.xmlgmldatasources.db1.entity.Punkt;
import com.example.xmlgmldatasources.db1.entity.Ulica;
import com.example.xmlgmldatasources.db4.entity.Klasa1;
import com.example.xmlgmldatasources.db4.repository.Klasa1Repository;
import com.example.xmlgmldatasources.excel.DbToExcel;
import com.example.xmlgmldatasources.service.XMLService;
import com.example.xmlgmldatasources.service.XMLServiceGML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XmlGmlDatasourcesApplication implements CommandLineRunner {

	@Autowired
	private XMLService xmlService;

	@Autowired
	private XMLServiceGML xmlServiceGML;

	@Autowired
	Klasa1Repository klasa1Repository;


	public static void main(String[] args) {
		SpringApplication.run(XmlGmlDatasourcesApplication.class, args);
		System.out.println("uruchamiamy sie");
	}

	@Override
	public void run(String... args) throws Exception {
		//pobieranie danych z pliku GML i zasilamy baze H2


		System.out.println("uruchamiamy sie2");
		for(Ulica ulica:xmlServiceGML.parseStreet()){
			System.out.println(ulica);
		}


		System.out.println("uruchamiamy sie3");
		for(Adres adres:xmlServiceGML.pobierzHrefyZTerytami()){
			System.out.println(adres);
		}

		System.out.println("uruchamiamy sie4");
		for(Punkt punkt:xmlServiceGML.pobierzPunktyAdresowe()){
			System.out.println(punkt);



		}




		// generujemy pliki xls z bazy h2
		DbToExcel exporter = new DbToExcel();
		exporter.export("adres");
		exporter.export("punkty");
		exporter.export("ulice");

		// pobieramy zapytanie z bazy h2 i zapisujemy do excela

		System.out.println("przed");
		//String zapytanie="SELECT a.id, a.idteryt, a.nazwa_glowna_czesc, a.przedrostek1czesc ,b.href FROM ULICE as a left join ADRES as b on a.idteryt=b.id_teryt;";

		/*
		String zapytanie="SELECT a.id, a.idteryt, a.nazwa_glowna_czesc, a.przedrostek1czesc ,b.href, c.kod , c.nr "
				+"FROM ULICE as a left join ADRES as b on a.idteryt=b.id_teryt "
				+"left join punkty as c on b.href=c.href;";

		*/
		//	"left join ADRES as b on a.idteryt=b.id_teryt " ;//+
		//"left join punkty as c on b.href=c.href";
		//exporter.exportTablefromQuery(zapytanie);
		System.out.println("po");

		/*
		//-------------
		Klasa1 klasa1 = new Klasa1();
		klasa1.setPole2("testujemy");
		klasa1Repository.save(klasa1);
		for(Klasa1 k:klasa1Repository.findAll()){
			System.out.println(k);
		}
		System.out.println("po po po");
		//-------------
		*/


	}



}
