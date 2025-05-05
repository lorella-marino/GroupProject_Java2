package it.epicode.build_5;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import it.epicode.build_5.comuni.Comune;
import it.epicode.build_5.comuni.ComuneRepository;
import it.epicode.build_5.comuni.Provincia;
import it.epicode.build_5.comuni.ProvinciaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.util.Arrays;

@Component
public class CsvImporter {

    private final JdbcTemplate jdbcTemplate;

    public CsvImporter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private ComuneRepository comuneRepository;

    @PostConstruct
    public void importCsvFiles() {

        importProvinceCsv();
        importComuniCsv();
    }

    private void importComuniCsv() {
        try (CSVReader reader = new CSVReaderBuilder(
                new InputStreamReader(new ClassPathResource("assets/comuni-italiani.csv").getInputStream()))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            String[] nextLine;
            boolean isFirstRow = true;

            while ((nextLine = reader.readNext()) != null) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }

                if (nextLine.length < 4) {
                    System.err.println("Riga non valida: " + Arrays.toString(nextLine));
                    continue;
                }

                try {
                    Provincia provincia = provinciaRepository.findByNome(nextLine[3]);

                    Comune comune = new Comune();
                    comune.setCodiceProvincia(nextLine[0]);
                    comune.setCodiceComune(nextLine[1]);
                    comune.setNome(nextLine[2]);
                    comune.setProvincia(provincia);

                    comuneRepository.save(comune);

                } catch (Exception e) {
                    System.err.println("Errore sulla riga: " + Arrays.toString(nextLine));
                    e.printStackTrace();
                }
            }

            System.out.println("Importazione comuni completata.");

        } catch (Exception e) {
            System.err.println("Errore generale nell'importazione dei comuni:");
            e.printStackTrace();
        }
    }

    private void importProvinceCsv() {
        try (CSVReader reader = new CSVReaderBuilder(
                new InputStreamReader(new ClassPathResource("assets/province-italiane.csv").getInputStream()))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {
            String[] nextLine;
            boolean isFirstRow = true;

            while ((nextLine = reader.readNext()) != null) {
                if (isFirstRow) { // salta intestazione
                    isFirstRow = false;
                    continue;
                }

                jdbcTemplate.update(
                        "INSERT INTO province (sigla, nome, regione) VALUES (?, ?, ?)",
                        nextLine[0], nextLine[1], nextLine[2]
                );
            }

            System.out.println("Importazione province completata.");
        } catch (Exception e) {
            System.err.println("Errore nell'importazione delle province:");
            e.printStackTrace();
        }
    }
}