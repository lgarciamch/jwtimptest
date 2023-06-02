package com.testing.jwtimp.controllers;


import com.testing.jwtimp.persist.SmartPhoneEntity;
import com.testing.jwtimp.service.SmartPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/celular/")
public class SmartPhoneController {
    private final SmartPhoneService phoneService;

    @Autowired
    public SmartPhoneController(SmartPhoneService phoneService) {
        this.phoneService = phoneService;
    }

    //Petición para crear un  celular
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public void crearCelular(@RequestBody SmartPhoneEntity smartPhoneEntity) {
        phoneService.crear(smartPhoneEntity);
    }

    //Petición para obtener todos los celulares en la BD
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public List<SmartPhoneEntity> listarCelulares() {
        return phoneService.readAll();
    }

    //Petición para obtener celular mediante "ID"
    @GetMapping(value = "listarId/{id}", headers = "Accept=application/json")
    public Optional<SmartPhoneEntity> obtenerCelularPorId(@PathVariable Long id) {
        return phoneService.readOne(id);
    }

    //Petición para actualizar un celular
    @PutMapping(value = "actualizar", headers = "Accept=application/json")
    public void actualizarCelular(@RequestBody SmartPhoneEntity smartPhoneEntity) {
        phoneService.update(smartPhoneEntity);
    }

    //Petición para eliminar un celular por "Id"
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public void eliminarCelular(@PathVariable Long id) {
        phoneService.delete(id);
    }
}
