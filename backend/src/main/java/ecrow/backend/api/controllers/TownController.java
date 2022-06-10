package ecrow.backend.api.controllers;

import ecrow.backend.business.abstracts.TownService;
import ecrow.backend.core.utilities.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/town")
public class TownController {
    private final TownService townService;

    @Autowired
    public TownController(TownService townService) {
        this.townService = townService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return Utils.getResponseEntity(townService.getAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        return Utils.getResponseEntity(townService.getById(id));
    }

    @GetMapping("/getByFkCityId")
    public ResponseEntity<?> getByFkCityId(@RequestParam Integer cityId) {
        return Utils.getResponseEntity(townService.getByFkCityId(cityId));
    }
}
