package ecrow.backend.api.controllers;

import ecrow.backend.business.abstracts.CityService;
import ecrow.backend.core.utilities.Utils;
import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.SuccessDataResult;
import ecrow.backend.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/city")
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return Utils.getResponseEntity(cityService.getAll());
    }

    @GetMapping("/getByName")
    public ResponseEntity<?> getByName(String name) {
        return Utils.getResponseEntity(cityService.getByName(name));
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(Integer id){
        return Utils.getResponseEntity(cityService.getById(id));
    }
}
