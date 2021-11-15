package com.projetointegrado.gerenciamentobolvino;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projetointegrado.gerenciamentobolvino.domain.Animal;
import com.projetointegrado.gerenciamentobolvino.domain.HistoricoMedicacao;
import com.projetointegrado.gerenciamentobolvino.domain.Lote;
import com.projetointegrado.gerenciamentobolvino.domain.Medicacao;
import com.projetointegrado.gerenciamentobolvino.domain.Pasto;
import com.projetointegrado.gerenciamentobolvino.domain.Peso;
import com.projetointegrado.gerenciamentobolvino.domain.Racao;
import com.projetointegrado.gerenciamentobolvino.domain.StatusBovinoAndLote;
import com.projetointegrado.gerenciamentobolvino.domain.StatusPastoAndLote;
import com.projetointegrado.gerenciamentobolvino.domain.StatusRacaoAndLote;
import com.projetointegrado.gerenciamentobolvino.domain.Usuario;
import com.projetointegrado.gerenciamentobolvino.domain.Venda;
import com.projetointegrado.gerenciamentobolvino.repositories.AnimalRepository;
import com.projetointegrado.gerenciamentobolvino.repositories.HistoricoMedicacaoRepository;
import com.projetointegrado.gerenciamentobolvino.repositories.LoteRepository;
import com.projetointegrado.gerenciamentobolvino.repositories.MedicacaoRepository;
import com.projetointegrado.gerenciamentobolvino.repositories.PastoRepository;
import com.projetointegrado.gerenciamentobolvino.repositories.PesoRepository;
import com.projetointegrado.gerenciamentobolvino.repositories.RacaoRepository;
import com.projetointegrado.gerenciamentobolvino.repositories.StatusBovinoAndLoteRepository;
import com.projetointegrado.gerenciamentobolvino.repositories.StatusPastoAndLoteRepository;
import com.projetointegrado.gerenciamentobolvino.repositories.StatusRacaoAndLoteRepository;
import com.projetointegrado.gerenciamentobolvino.repositories.UsuarioRepository;
import com.projetointegrado.gerenciamentobolvino.repositories.VendaRepository;


@SpringBootApplication
public class GerenciamentoBolvinoBackendApplication implements CommandLineRunner{

	@Autowired
	private AnimalRepository animalrepository;
	@Autowired
	private PesoRepository pesorepository;
	@Autowired
	private HistoricoMedicacaoRepository historicomedicacaorepository;
	@Autowired
	private MedicacaoRepository medicacaorepository;
	@Autowired
	private LoteRepository loterepository;
	@Autowired
	private PastoRepository pastorepository;
	@Autowired
	private RacaoRepository racaorepository;
	@Autowired
	private StatusBovinoAndLoteRepository statusbovinoandloterepository;
	@Autowired
	private StatusPastoAndLoteRepository statuspastoandloterepository;
	@Autowired
	private StatusRacaoAndLoteRepository statusracaoandloterepository;
	@Autowired
	private VendaRepository vendarepository;
	
	@Autowired
	private UsuarioRepository usuariorepository;
	
	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoBolvinoBackendApplication.class, args); 
	}

	@Override
	public void run(String... args) throws Exception {

		Usuario user1 = new Usuario(null, "Paulo Guilherme", "paulo", "paulo123", "15/11/2021");
        Usuario user2 = new Usuario(null, "Ariane Silva", "ariane", "123456", "15/12/2021");
        
        this.usuariorepository.saveAll(Arrays.asList(user1, user2));
        
        Animal ani1 = new Animal(null,"02PG","Nelore","12/11/2021","Vivo");
        Animal ani2 = new Animal(null,"GHP3","Angus","15/11/2021","Vivo");
        Animal ani3 = new Animal(null,"HL32","Nelore","15/11/2021","Vivo");
        Animal ani4 = new Animal(null,"LA12","Holandês","12/11/2021","Vendido");
        Animal ani5 = new Animal(null,"VC32","Nelore","16/11/2021","Morto");
        Animal ani6 = new Animal(null,"VC32","Angus","21/11/2021","Vivo");

        Medicacao medic1 = new Medicacao(null, "3 meses", "Vitamina", "L01");
        Medicacao medic2 = new Medicacao(null, "6 meses", "Carrapaticida", "H03");
        Medicacao medic3 = new Medicacao(null, "9 meses", "Vermifugo", "V92");
        
        Lote lot1 = new Lote(null, "15/11/2021", "Lote Brasileiro");
        Lote lot2 = new Lote(null, "23/11/2021", "Lote Espanhol");
        Lote lot3 = new Lote(null, "23/11/2021", "Lote Americano");
        
        Pasto past1 = new Pasto(null, "11/10/2021", "Pasto Brasileiro");
        Pasto past2 = new Pasto(null, "11/10/2021", "Pasto Espanhol");
        Pasto past3 = new Pasto(null, "11/10/2021", "Pasto Americano");
        
        Racao rac1 = new Racao(null, "Trigo", "15/10/2021");
        Racao rac2 = new Racao(null, "Soja", "15/10/2021");
        Racao rac3 = new Racao(null, "Sal", "15/10/2021");

        
        Peso pes1 = new Peso(null, "15/11/2021", 110, ani1);
        ani1.getPesos().addAll(Arrays.asList(pes1));
		Peso pes2 = new Peso(null, "31/11/2021", 220, ani1);
		ani1.getPesos().addAll(Arrays.asList(pes2));
		Peso pes3 = new Peso(null, "17/12/2021", 330, ani1);
		ani1.getPesos().addAll(Arrays.asList(pes3));
		
        Peso pes4 = new Peso(null, "15/11/2021", 110, ani2);
        ani2.getPesos().addAll(Arrays.asList(pes4));
		Peso pes5 = new Peso(null, "16/12/2021", 220, ani2);
		ani2.getPesos().addAll(Arrays.asList(pes5));

		
        Peso pes6 = new Peso(null, "15/11/2021", 110, ani3);
        ani3.getPesos().addAll(Arrays.asList(pes6));
		Peso pes7 = new Peso(null, "16/12/2021", 220, ani3);
		ani3.getPesos().addAll(Arrays.asList(pes7));

		
        Peso pes8 = new Peso(null, "15/11/2021", 110, ani4);
        ani4.getPesos().addAll(Arrays.asList(pes8));
		Peso pes9 = new Peso(null, "16/12/2021", 220, ani4);
		ani4.getPesos().addAll(Arrays.asList(pes9));

		
        Peso pes10 = new Peso(null, "15/11/2021", 110, ani5);
        ani5.getPesos().addAll(Arrays.asList(pes10));
		Peso pes11 = new Peso(null, "16/12/2021", 220, ani5);
		ani5.getPesos().addAll(Arrays.asList(pes11));

		
        Peso pes12 = new Peso(null, "15/11/2021", 110, ani6);
        ani6.getPesos().addAll(Arrays.asList(pes12));
		Peso pes13 = new Peso(null, "16/12/2021", 220, ani6);
		ani6.getPesos().addAll(Arrays.asList(pes13));


        HistoricoMedicacao histmedic1 = new HistoricoMedicacao(null, "22/11/2021", 350, ani1, medic1);
        ani1.getHistoricoMedicacaos().addAll(Arrays.asList(histmedic1));
        medic1.getHistoricoMedicacaos().addAll(Arrays.asList(histmedic1));
        
        HistoricoMedicacao histmedic2 = new HistoricoMedicacao(null, "22/11/2021", 350, ani2, medic2);
        ani2.getHistoricoMedicacaos().addAll(Arrays.asList(histmedic1));
        medic2.getHistoricoMedicacaos().addAll(Arrays.asList(histmedic1));
        
        HistoricoMedicacao histmedic3 = new HistoricoMedicacao(null, "22/11/2021", 350, ani3, medic3);
        ani3.getHistoricoMedicacaos().addAll(Arrays.asList(histmedic1));
        medic3.getHistoricoMedicacaos().addAll(Arrays.asList(histmedic1));
        
        HistoricoMedicacao histmedic4 = new HistoricoMedicacao(null, "22/11/2021", 350, ani4, medic1);
        ani4.getHistoricoMedicacaos().addAll(Arrays.asList(histmedic1));
        medic1.getHistoricoMedicacaos().addAll(Arrays.asList(histmedic1));
        
        HistoricoMedicacao histmedic5 = new HistoricoMedicacao(null, "22/11/2021", 350, ani5, medic2);
        ani5.getHistoricoMedicacaos().addAll(Arrays.asList(histmedic1));
        medic2.getHistoricoMedicacaos().addAll(Arrays.asList(histmedic1));
        
        HistoricoMedicacao histmedic6 = new HistoricoMedicacao(null, "22/11/2021", 350, ani6, medic3);
        ani6.getHistoricoMedicacaos().addAll(Arrays.asList(histmedic1));
        medic3.getHistoricoMedicacaos().addAll(Arrays.asList(histmedic1));
        
        
        StatusBovinoAndLote statusBovinoAndLote1 = new StatusBovinoAndLote(null, "15/10/2021", "", ani1, lot1);
        ani1.getStatusBovinoAndLotes().addAll(Arrays.asList(statusBovinoAndLote1));
        lot1.getStatusBovinoAndLotes().addAll(Arrays.asList(statusBovinoAndLote1));

        StatusBovinoAndLote statusBovinoAndLote2 = new StatusBovinoAndLote(null, "15/10/2021", "", ani2, lot2);
        ani2.getStatusBovinoAndLotes().addAll(Arrays.asList(statusBovinoAndLote2));
        lot2.getStatusBovinoAndLotes().addAll(Arrays.asList(statusBovinoAndLote2));
        
        StatusBovinoAndLote statusBovinoAndLote3 = new StatusBovinoAndLote(null, "15/10/2021", "", ani3, lot3);
        ani3.getStatusBovinoAndLotes().addAll(Arrays.asList(statusBovinoAndLote3));
        lot3.getStatusBovinoAndLotes().addAll(Arrays.asList(statusBovinoAndLote3));
        
        StatusBovinoAndLote statusBovinoAndLote4 = new StatusBovinoAndLote(null, "15/10/2021", "19/11/2021", ani4, lot1);
        ani4.getStatusBovinoAndLotes().addAll(Arrays.asList(statusBovinoAndLote4));
        lot1.getStatusBovinoAndLotes().addAll(Arrays.asList(statusBovinoAndLote4));
        
        StatusBovinoAndLote statusBovinoAndLote5 = new StatusBovinoAndLote(null, "15/10/2021", "19/11/2021", ani5, lot2);
        ani5.getStatusBovinoAndLotes().addAll(Arrays.asList(statusBovinoAndLote5));
        lot2.getStatusBovinoAndLotes().addAll(Arrays.asList(statusBovinoAndLote5));
        
        StatusBovinoAndLote statusBovinoAndLote6 = new StatusBovinoAndLote(null, "15/10/2021", "", ani6, lot3);
        ani6.getStatusBovinoAndLotes().addAll(Arrays.asList(statusBovinoAndLote6));
        lot3.getStatusBovinoAndLotes().addAll(Arrays.asList(statusBovinoAndLote6));
        

        StatusPastoAndLote statusPastoAndLote1 = new StatusPastoAndLote(null, "15/10/2021", "", past1, lot1);
        past1.getStatusPastoAndLotes().addAll(Arrays.asList(statusPastoAndLote1));
        lot1.getStatusPastoAndLotes().addAll(Arrays.asList(statusPastoAndLote1));

        StatusPastoAndLote statusPastoAndLote2 = new StatusPastoAndLote(null, "15/10/2021", "", past2, lot2);
        past2.getStatusPastoAndLotes().addAll(Arrays.asList(statusPastoAndLote2));
        lot2.getStatusPastoAndLotes().addAll(Arrays.asList(statusPastoAndLote2));
        
        StatusPastoAndLote statusPastoAndLote3 = new StatusPastoAndLote(null, "15/10/2021", "", past3, lot3);
        past1.getStatusPastoAndLotes().addAll(Arrays.asList(statusPastoAndLote3));
        lot1.getStatusPastoAndLotes().addAll(Arrays.asList(statusPastoAndLote3));
        

        
        StatusRacaoAndLote statusRacaoAndLote1 = new StatusRacaoAndLote(null, "15/10/2021", "", rac1, lot1);
        rac1.getStatusRacaoAndLotes().addAll(Arrays.asList(statusRacaoAndLote1));
        lot1.getStatusRacaoAndLotes().addAll(Arrays.asList(statusRacaoAndLote1));

        StatusRacaoAndLote statusRacaoAndLote2 = new StatusRacaoAndLote(null, "15/10/2021", "", rac2, lot2);
        rac1.getStatusRacaoAndLotes().addAll(Arrays.asList(statusRacaoAndLote2));
        lot1.getStatusRacaoAndLotes().addAll(Arrays.asList(statusRacaoAndLote2));

        StatusRacaoAndLote statusRacaoAndLote3 = new StatusRacaoAndLote(null, "15/10/2021", "", rac3, lot3);
        rac1.getStatusRacaoAndLotes().addAll(Arrays.asList(statusRacaoAndLote3));
        lot1.getStatusRacaoAndLotes().addAll(Arrays.asList(statusRacaoAndLote3));
        
        
        Venda vend1 = new Venda(null, 140, "23/12/2021", "Ariane Silva", 14000, user1);
        user1.getVendas().addAll(Arrays.asList(vend1));
        
        Venda vend2 = new Venda(null, 140, "01/12/2021", "Paulo Guilherme", 18000, user1);
        user1.getVendas().addAll(Arrays.asList(vend1));

        
        this.animalrepository.saveAll(Arrays.asList(ani1, ani2, ani3, ani4, ani5, ani6));
        
        this.pesorepository.saveAll(Arrays.asList(pes1, pes2, pes3, pes4, pes5, pes6, pes7, pes8, pes9, pes10, pes11, pes12, pes13));
        
        this.medicacaorepository.saveAll(Arrays.asList(medic1, medic2, medic3));
        
        this.historicomedicacaorepository.saveAll(Arrays.asList(histmedic1, histmedic2, histmedic3, histmedic4, histmedic5, histmedic6));
        
        this.loterepository.saveAll(Arrays.asList(lot1, lot2, lot3));
        
        this.pastorepository.saveAll(Arrays.asList(past1, past2, past3));
        
        this.racaorepository.saveAll(Arrays.asList(rac1, rac2, rac3));
        
        this.statusbovinoandloterepository.saveAll(Arrays.asList(statusBovinoAndLote1, statusBovinoAndLote2, statusBovinoAndLote3, statusBovinoAndLote4, statusBovinoAndLote5, statusBovinoAndLote6));
        
        this.statuspastoandloterepository.saveAll(Arrays.asList(statusPastoAndLote1, statusPastoAndLote2, statusPastoAndLote3));
        
        this.statusracaoandloterepository.saveAll(Arrays.asList(statusRacaoAndLote1, statusRacaoAndLote2, statusRacaoAndLote3));
        
        this.vendarepository.saveAll(Arrays.asList(vend1, vend2));
       
	}

}
