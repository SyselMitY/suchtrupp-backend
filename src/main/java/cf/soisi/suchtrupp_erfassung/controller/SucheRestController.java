package cf.soisi.suchtrupp_erfassung.controller;

import cf.soisi.suchtrupp_erfassung.entity.Meldung;
import cf.soisi.suchtrupp_erfassung.entity.Suche;
import cf.soisi.suchtrupp_erfassung.entity.Suchtrupp;
import cf.soisi.suchtrupp_erfassung.repo.MeldungRepository;
import cf.soisi.suchtrupp_erfassung.repo.SucheRepository;
import cf.soisi.suchtrupp_erfassung.repo.SuchtruppRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController

public class SucheRestController {
    private final SucheRepository sucheRepository;
    private final SuchtruppRepository suchtruppRepository;
    private final MeldungRepository meldungRepository;

    public SucheRestController(SucheRepository sucheRepository, SuchtruppRepository suchtruppRepository, MeldungRepository meldungRepository) {
        this.sucheRepository = sucheRepository;
        this.suchtruppRepository = suchtruppRepository;
        this.meldungRepository = meldungRepository;
    }

    @CrossOrigin
    @GetMapping("/suchen")
    public List<Suche> getAllSuchen() {
        return sucheRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/suche")
    public Suche getSucheById(@RequestParam int id) {
        return sucheRepository.findById(id).orElseThrow();
    }

    @CrossOrigin
    @PostMapping("/suchen")
    public ResponseEntity<Suche> saveSuche(@RequestBody @Valid Suche sucheToSave) {
        Suche safeSuche = new Suche(sucheToSave.getName(), sucheToSave.getBereich());
        Suche savedSuche = sucheRepository.save(safeSuche);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath("/suche?id={id}")
                .build(savedSuche.getId());

        return ResponseEntity.created(uri).body(savedSuche);
    }

    @CrossOrigin
    @PostMapping("/suchtrupps/{sucheId}")
    public ResponseEntity<Suchtrupp> saveSuchtrupp(@RequestBody @Valid Suchtrupp suchtruppToSave,@PathVariable Integer sucheId) {
        Suche suche = sucheRepository.findById(sucheId).orElseThrow();

        Suchtrupp safeSuchtrupp = new Suchtrupp(suchtruppToSave.getLeiter(), suchtruppToSave.getAnzahlPersonen(), suche);
        Suchtrupp savedSuchtrupp = suchtruppRepository.save(safeSuchtrupp);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath("/suchtrupps?id={id}")
                .build(savedSuchtrupp.getId());

        return ResponseEntity.created(uri).body(savedSuchtrupp);
    }

    @CrossOrigin
    @PostMapping("/meldungen/{suchtruppId}")
    public ResponseEntity<Meldung> saveMitteilung(@RequestBody Meldung mitteilungToSave,@PathVariable Integer suchtruppId) {
        Suchtrupp suchtrupp = suchtruppRepository.findById(suchtruppId).orElseThrow();
        Meldung safeMitteilung = new Meldung(mitteilungToSave.getBeschreibung(), mitteilungToSave.getTags(),suchtrupp);
        Meldung savedMitteilung = meldungRepository.save(safeMitteilung);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath("/meldungen?id={id}")
                .build(savedMitteilung.getId());

        return ResponseEntity.created(uri).body(savedMitteilung);
    }
}
