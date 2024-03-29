package ecrow.backend.api.controllers;

import ecrow.backend.business.abstracts.EmployeeService;
import ecrow.backend.core.utilities.Utils;
import ecrow.backend.entities.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return Utils.getResponseEntity(employeeService.getAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        return Utils.getResponseEntity(employeeService.getById(id));
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<?> getByEmail(@RequestParam String email) {
        return Utils.getResponseEntity(employeeService.getByEmail(email));
    }

    @GetMapping("/getByPhoneNumber")
    public ResponseEntity<?> getByPhoneNumber(@RequestParam String phoneNumber) {
        return Utils.getResponseEntity(employeeService.getByPhoneNumber(phoneNumber));
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> getByEmailAndPassword(@RequestBody SignInDto signInDto) {
        return Utils.getResponseEntity(employeeService.signIn(signInDto));
    }

    @GetMapping("/getByFkTownId")
    public ResponseEntity<?> getByFkTownId(@RequestParam Integer townId) {
        return Utils.getResponseEntity(employeeService.getByFkTownId(townId));
    }

    @GetMapping("/getByFkCityId")
    public ResponseEntity<?> getByFkCityId(@RequestParam Integer cityId) {
        return Utils.getResponseEntity(employeeService.getByFkCityId(cityId));
    }

    @GetMapping("/getAllByAvailableIsTrue")
    public ResponseEntity<?> getAllByAvailableIsTrue() {
        return Utils.getResponseEntity(employeeService.getAllByAvailableIsTrue());
    }

    @GetMapping("/getByFkTownIdAndAvailableIsTrue")
    public ResponseEntity<?> getByFkTownIdAndAvailableIsTrue(@RequestParam Integer townId) {
        return Utils.getResponseEntity(employeeService.getByFkTownIdAndAvailableIsTrue(townId));
    }

    @GetMapping("/getByFkCityIdAndAvailableIsTrue")
    public ResponseEntity<?> getByFkCityIdAndAvailableIsTrue(@RequestParam Integer cityId) {
        return Utils.getResponseEntity(employeeService.getByFkCityIdAndAvailableIsTrue(cityId));
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam Integer id) {
        return Utils.getResponseEntity(employeeService.deleteById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody EmployeeAddDto employeeAddDto) {
        return Utils.getResponseEntity(employeeService.add(employeeAddDto));
    }

    @PatchMapping("/updateBase")
    public ResponseEntity<?> updateBase(@RequestBody EmployeeBaseUpdateDto employeeBaseUpdateDto) {
        return Utils.getResponseEntity(employeeService.updateBase(employeeBaseUpdateDto));
    }

    @PatchMapping("/updateDetails")
    public ResponseEntity<?> updateDetails(@RequestBody EmployeeDetailsUpdateDto employeeDetailsUpdateDto) {
        return Utils.getResponseEntity(employeeService.updateDetails(employeeDetailsUpdateDto));
    }

}
