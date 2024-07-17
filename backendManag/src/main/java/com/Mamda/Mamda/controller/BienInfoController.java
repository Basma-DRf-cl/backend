package com.Mamda.Mamda.controller;

import com.Mamda.Mamda.model.BienInfo;
import com.Mamda.Mamda.service.BienInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/biens") // Base URL mapping for all endpoints in this controller
public class BienInfoController {

    private final BienInfoService bienInfoService;

    @Autowired
    public BienInfoController(BienInfoService bienInfoService) {
        this.bienInfoService = bienInfoService;
    }

    // Endpoint to add a new BienInfo
    @PostMapping("/ajouter")
    public ResponseEntity<BienInfo> ajouterBienInfo(@RequestBody BienInfo bienInfo) {
        BienInfo nouveauBien = bienInfoService.ajouterBienInfo(bienInfo);
        return new ResponseEntity<>(nouveauBien, HttpStatus.CREATED);
    }

    // Endpoint to update an existing BienInfo
    @PutMapping("/modifier")
    public ResponseEntity<BienInfo> modifierBienInfo(@RequestBody BienInfo bienInfo) {
        BienInfo modifieBien = bienInfoService.modifierBienInfo(bienInfo);
        return new ResponseEntity<>(modifieBien, HttpStatus.OK);
    }

    // Endpoint to delete a BienInfo by ID
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> supprimerBienInfo(@PathVariable("id") int id) {
        bienInfoService.supprimerBienInfo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint to get a BienInfo by ID
    @GetMapping("/voir/{id}")
    public ResponseEntity<BienInfo> voirBienInfo(@PathVariable("id") int id) {
        Optional<BienInfo> bienInfo = bienInfoService.voirBienInfo(id);
        return bienInfo.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to get all BienInfo objects
    @GetMapping("/voir-tous")
    public ResponseEntity<List<BienInfo>> voirListeBien() {
        List<BienInfo> listeBiens = bienInfoService.voirListeBien();
        return new ResponseEntity<>(listeBiens, HttpStatus.OK);
    }
}
