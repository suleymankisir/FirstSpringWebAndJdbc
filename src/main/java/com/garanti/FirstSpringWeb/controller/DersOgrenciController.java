package com.garanti.FirstSpringWeb.controller;

import com.garanti.FirstSpringWeb.model.Ders_Ogrenci;
import com.garanti.FirstSpringWeb.repo.Ders_OgrenciRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("dersogrenci")
@AllArgsConstructor
public class DersOgrenciController {
    private Ders_OgrenciRepo dersOgrenciRepo;

    @GetMapping(value = "getall",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(){
        List<Ders_Ogrenci> res = dersOgrenciRepo.getAll();
        if(res == null || res.size() == 0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Null değer");
        }
        else{
            return ResponseEntity.ok(res);
        }
    }
    @GetMapping(value = "getByIdHeader",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByIdHeader(@RequestHeader("id") int id){

        Ders_Ogrenci res = dersOgrenciRepo.getById(id);
        if (res != null){
            return ResponseEntity.ok(res);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @GetMapping(value = "getById",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByIdQueryParam(@RequestParam("id") int id){
        Ders_Ogrenci res = dersOgrenciRepo.getById(id);
        if (res != null){
            return ResponseEntity.ok(res);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @GetMapping(value = "getById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByIdPathParam(@PathVariable("id") int id){
        Ders_Ogrenci res = dersOgrenciRepo.getById(id);
        if (res != null){
            return ResponseEntity.ok(res);
        }
        else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @PostMapping(value = "save",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody Ders_Ogrenci dersOgrenci){
        if(dersOgrenciRepo.save(dersOgrenci)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Başarı ile kaydedildi");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Kaydedilemedi");
        }
    }
    @DeleteMapping(value = "deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id){
        if (dersOgrenciRepo.deleteById(id)){
            return ResponseEntity.ok("Başarı ile silindi");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Silinemedi");
        }
    }
    @DeleteMapping(value = "deleteByIdHeader")
    public ResponseEntity<?> deleteByIdHeader(@RequestHeader("id") int id){
        if (dersOgrenciRepo.deleteById(id)){
            return ResponseEntity.ok("Başarı ile silindi");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Silinemedi");
        }
    }
}

