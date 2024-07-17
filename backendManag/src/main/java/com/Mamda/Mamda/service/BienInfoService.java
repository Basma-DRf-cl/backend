package com.Mamda.Mamda.service;

import com.Mamda.Mamda.model.BienInfo;
import com.Mamda.Mamda.repository.BienInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BienInfoService {

    private final BienInfoRepository bienInfoRepository;

    @Autowired
    public BienInfoService(BienInfoRepository bienInfoRepository) {
        this.bienInfoRepository = bienInfoRepository;
    }

    // Méthode pour ajouter un bien informatique
    public BienInfo ajouterBienInfo(BienInfo bienInfo) {
        return bienInfoRepository.save(bienInfo);
    }

    // Méthode pour modifier un bien informatique
    public BienInfo modifierBienInfo(BienInfo bienInfo) {
        // Vérifier si le bien existe dans la base de données
        Optional<BienInfo> existingBienInfo = bienInfoRepository.findById(bienInfo.getId());
        if (existingBienInfo.isPresent()) {
            return bienInfoRepository.save(bienInfo);
        } else {
            // Gérer le cas où le bien n'existe pas
            throw new IllegalArgumentException("Le bien informatique avec l'ID " + bienInfo.getId() + " n'existe pas.");
        }
    }

    // Méthode pour supprimer un bien informatique par ID
    public void supprimerBienInfo(int id) {
        bienInfoRepository.deleteById(id);
    }

    // Méthode pour récupérer un bien informatique par ID
    public Optional<BienInfo> voirBienInfo(int id) {
        return bienInfoRepository.findById(id);
    }

    // Méthode pour récupérer tous les biens informatiques
    public List<BienInfo> voirListeBien() {
        return bienInfoRepository.findAll();
    }
}
