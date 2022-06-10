package ecrow.backend.api.controllers;

import ecrow.backend.business.abstracts.CityService;
import ecrow.backend.core.utilities.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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
    public ResponseEntity<?> getByName(@RequestParam String name) {
        return Utils.getResponseEntity(cityService.getByName(name));
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Integer id){
        return Utils.getResponseEntity(cityService.getById(id));
    }
}
