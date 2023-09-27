/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.controllers.ResourceNotFoundException;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {
   
    @Autowired
    BlueprintsPersistence bpp;

    @Autowired
    BlueprintFilter bpf;

    
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        bpp.saveBlueprint(bp);
    }
    
    public Set<Blueprint> getAllBlueprints() throws ResourceNotFoundException {
        if (bpp.getAllBlueprints().isEmpty()) throw new ResourceNotFoundException();
        Set<Blueprint> filteredBlueprints = new HashSet<Blueprint>();
        for (Blueprint blueprint:bpp.getAllBlueprints()) {
            filteredBlueprints.add(getBlueprintFiltered(blueprint));
        }
        return  filteredBlueprints;
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
        Blueprint bpSearched = null;
        bpSearched = bpp.getBlueprint(author, name);
        //throw new UnsupportedOperationException("Not supported yet.");
        return bpSearched;

    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        Set<Blueprint> blueprintSet = new HashSet<>();
        blueprintSet = bpp.getAuthorBlueprints(author);
        return blueprintSet;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param bp Blueprint to be filtered
     * @return the Blueprint filtered
     */
    public Blueprint getBlueprintFiltered(Blueprint bp) {
        return bpf.getFilteredBlueprint(bp);
    }
    
}
