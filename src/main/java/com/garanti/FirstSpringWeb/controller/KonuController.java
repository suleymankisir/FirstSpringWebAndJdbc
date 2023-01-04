package com.garanti.FirstSpringWeb.controller;

import com.garanti.FirstSpringWeb.model.Konu;
import com.garanti.FirstSpringWeb.repo.KonuRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("konular")
@AllArgsConstructor
public class KonuController {

    private KonuRepo konuRepo;

    @GetMapping(value = "getall",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(){
        List<Konu> res = konuRepo.getAll();
        if(res == null || res.size() == 0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else{
            return ResponseEntity.ok(res);
        }
    }
    @GetMapping(value = "getByIdHeader",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByIdHeader(@RequestHeader("id") int id){

        Konu res = konuRepo.getById(id);
        if (res != null){
            return ResponseEntity.ok(res);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @GetMapping(value = "getById",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByIdQueryParam(@RequestParam("id") int id){
        Konu res = konuRepo.getById(id);
        if (res != null){
            return ResponseEntity.ok(res);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @GetMapping(value = "getById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByIdPathParam(@PathVariable("id") int id){
        Konu res = konuRepo.getById(id);
        if (res != null){
            return ResponseEntity.ok(res);
        }
        else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @PostMapping(value = "save",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody Konu konu){
        if(konuRepo.save(konu)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Başarı ile kaydedildi");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Kaydedilemedi");
        }
    }
    @DeleteMapping(value = "deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id){
        if (konuRepo.deleteById(id)){
            return ResponseEntity.ok("Başarı ile silindi");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Silinemedi");
        }
    }
    @DeleteMapping(value = "deleteByIdHeader")
    public ResponseEntity<?> deleteByIdHeader(@RequestHeader("id") int id){
        if (konuRepo.deleteById(id)){
            return ResponseEntity.ok("Başarı ile silindi");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Silinemedi");
        }
    }
}
